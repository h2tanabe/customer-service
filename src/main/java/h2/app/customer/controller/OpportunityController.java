package h2.app.customer.controller;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import h2.app.customer.dto.OpportunityDto;
import h2.app.customer.entity.Customer;
import h2.app.customer.entity.Opportunity;
import h2.app.customer.enums.OpptunityStatusType;
import h2.app.customer.form.OpportunityForm;
import h2.app.customer.service.CustomerSerivce;
import h2.app.customer.service.OpportunitySerivce;
import h2.app.customer.util.SelectOptionsUtils;

@Controller
@RequestMapping("opportunities")
public class OpportunityController extends AbstractController {

	@Autowired
	private OpportunitySerivce opportunitySerivce;

	@Autowired
	private CustomerSerivce customerService;

	@ModelAttribute
	OpportunityForm setupForm() {
		return new OpportunityForm();
	}

	@GetMapping("/list")
	String listPage(Model model, OpportunityForm form, @PageableDefault(size = 100, page = 0) Pageable pageable) {
		SelectOptions options = SelectOptionsUtils.get(pageable, true);
		List<OpportunityDto> page = opportunitySerivce.findAll();
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pageOption", options);
		return "opportunities/list";
	}

	@GetMapping("/create")
	String createPage(Model model) {
		return "opportunities/create";
	}

	@PostMapping("/create")
	String create(
			RedirectAttributes attributes,
			@AuthenticationPrincipal UserDetails loginUser,
			@Validated OpportunityForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return createPage(model);
		}
		Opportunity opportunity = registerOpportunity(form,result,model);
		if(opportunity.accountId == null) {
			result.rejectValue("accoutId", "error.accoutId", "該当の顧客がありません");
			return createPage(model);
		}
		opportunity.createdBy = loginUser.getName();
		opportunity.updatedBy = loginUser.getName();
		opportunitySerivce.insert(opportunity);
		attributes.addFlashAttribute("successMessage", "商談作成しました");
		return "redirect:/opportunities/list";
	}

	@GetMapping("/edit/{opportunityId}")
	String editPage(@PathVariable("opportunityId") Long opportunityId, Model model, OpportunityForm form,
			boolean result) {
		if (false == result) {
			Opportunity opportunity = opportunitySerivce.findById(opportunityId).get();
			form.setName(opportunity.name);
			form.setAmmount(opportunity.ammount);
			form.setStatus(opportunity.status.getValue());
			Optional<Customer> customer = customerService.findById(opportunity.accountId);
			if(customer.isPresent()) {
				form.setAccoutId(opportunity.accountId + ":" + customer.get().name);
			}
		}
		model.addAttribute("opportunityId", opportunityId);
		return "opportunities/edit";
	}

	@GetMapping("/delete/{opportunityId}")
	String delete(@PathVariable("opportunityId") Long opportunityId, Model model) {
		Opportunity opportunity = new Opportunity();
		opportunity.id = opportunityId;
		opportunitySerivce.delete(opportunity);
		return "redirect:/opportunities/list";
	}

	@PostMapping("/update")
	String edit(@RequestParam("id") Long id, @Validated OpportunityForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return editPage(id, model, form, true);
		}
		Opportunity opportunity = registerOpportunity(form,result,model);
		if(opportunity.accountId == null) {
			result.rejectValue("accoutId", "error.accoutId", "該当の顧客がありません");
			return createPage(model);
		}
		opportunity.id = id;
		opportunitySerivce.update(opportunity);
		return "redirect:/opportunities/list";
	}


	//共通登録・更新
	private Opportunity registerOpportunity(OpportunityForm form, BindingResult result, Model model) {
		Opportunity opportunity = new Opportunity();
		Long accountId = null;
		try {
			accountId = Long.parseLong(form.getAccoutId().replaceAll("[^0-9]", ""));
		}catch(NumberFormatException ex){
			opportunity.accountId = null;
			return opportunity;
		}
		if(customerService.findById(accountId).isPresent()) {
			opportunity.accountId = accountId;
		}else {
			opportunity.accountId = null;
		}
		opportunity.name = form.getName();
		opportunity.ammount = form.getAmmount();
		opportunity.status = OpptunityStatusType.of(form.getStatus());
		return opportunity;
	}

}

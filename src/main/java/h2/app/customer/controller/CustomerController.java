package h2.app.customer.controller;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import h2.app.customer.entity.Customer;
import h2.app.customer.form.CustomerForm;
import h2.app.customer.form.CustomerSearchForm;
import h2.app.customer.service.CustomerSerivce;
import h2.app.customer.util.SelectOptionsUtils;

@Controller
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerSerivce customerService;

    @ModelAttribute
    CustomerForm setupForm() {
    	return new CustomerForm();
    }

    @ModelAttribute
    CustomerSearchForm setupSearchForm() {
    	return new CustomerSearchForm();
    }

	@GetMapping("/list")
	String listPage(Model model,CustomerSearchForm form,@PageableDefault(size = 100,page = 0) Pageable pageable) {
		Customer customer = new Customer();
		customer.name = form.getName();
		customer.address = form.getAddress();
		customer.mail = form.getMail();
		customer.tel = form.getTel();
		SelectOptions options = SelectOptionsUtils.get(pageable, true);
		List<Customer> page = customerService.findList(customer,options);
        model.addAttribute("page", page);
        model.addAttribute("pageOption", options);
        model.addAttribute("pageable", pageable);
		return "customers/list";
	}

	@GetMapping("/create")
	String createPage(Model model) {
		return "customers/create";
	}

	@PostMapping("/create")
	String create(@Validated CustomerForm form,BindingResult result ,Model model) {

		if(result.hasErrors()) {
			return createPage(model);
		}
		Customer customer = new Customer();
		customer.nameKana = form.getName();
		customer.name = form.getName();
		customer.postalCode = form.getPostalCode();
		customer.address = form.getAddress();
		customer.tel = form.getTel();
		customer.mail = form.getMail();
		//customer.birthDate = form.getBirthDate();
		customerService.insert(customer);
		return "redirect:/customers/list";
	}

	@GetMapping("/edit/{accountId}")
	String editPage(@PathVariable("accountId") Long accountId, Model model,CustomerForm form, boolean result) {
		if(false == result){
			Customer customer = customerService.findById(accountId).get();
			form.setName(customer.name);
			form.setNameKana(customer.nameKana);
			form.setPostalCode(customer.postalCode);
			form.setAddress(customer.address);
			form.setTel(customer.tel);
			form.setMail(customer.mail);
		}
		model.addAttribute("accountId", accountId);
        //model.addAttribute("customer", customer); formを利用するため model.addAttributeを使わない
		return "customers/edit";
	}

	@PostMapping("/edit")
	String edit(@RequestParam("id")Long accountId,@Validated CustomerForm form,BindingResult result ,Model model) {
		if(result.hasErrors()){
			return editPage(accountId,model,form,true);
		}
		Customer customer = new Customer();
		customer.id = accountId;
		customer.nameKana = form.getNameKana();
		customer.name = form.getName();
		customer.postalCode = form.getPostalCode();
		customer.address = form.getAddress();
		customer.tel = form.getTel();
		customer.mail = form.getMail();
		customerService.update(customer);
        return "redirect:/customers/list";
	}

	@GetMapping("/delete/{accountId}")
	String delete(@PathVariable("accountId") Long accountId,Model model) {
		Customer customer = new Customer();
		customer.id = accountId;
		customerService.delete(customer);
		return "redirect:/customers/list";
	}

}

package h2.app.customer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import h2.app.customer.entity.Customer;
import h2.app.customer.entity.CustomerCSV;
import h2.app.customer.form.CustomerForm;
import h2.app.customer.form.CustomerSearchForm;
import h2.app.customer.service.CustomerSerivce;
import h2.app.customer.util.DateUtils;
import h2.app.customer.util.SelectOptionsUtils;

@Controller
@RequestMapping("customers")
public class CustomerController extends AbstractController{

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
	String listPage(Model model, CustomerSearchForm form, @PageableDefault(size = 100, page = 0) Pageable pageable) {
		Customer customer = new Customer();
		customer.name = StringUtils.trim(form.getName());
		customer.address = StringUtils.trim(form.getAddress());
		customer.mail = StringUtils.trim(form.getMail());
		customer.tel = StringUtils.trim(form.getTel());
		SelectOptions options = SelectOptionsUtils.get(pageable, true);
		List<Customer> page = customerService.findList(customer, options);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		model.addAttribute("pageOption", options);
		return "customers/list";
	}

	@GetMapping("/create")
	String createPage(Model model) {
		return "customers/create";
	}

	@PostMapping("/create")
	String create(@AuthenticationPrincipal UserDetails loginUser,
				  @Validated CustomerForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return createPage(model);
		}
		Customer customer = new Customer();
		customer.nameKana = form.getName();
		customer.name = form.getName();
		customer.postalCode = form.getPostalCode();
		customer.address = form.getAddress();
		customer.tel = form.getTel();
		customer.mail = form.getMail();
		customer.createdBy = loginUser.getName();
		customer.updatedBy = loginUser.getName();
		customer.birthDate = DateUtils.stringToLoacleDate(form.getBirthDate());
		customerService.insert(customer);
		return "redirect:/customers/list";
	}

	@GetMapping("/edit/{accountId}")
	String editPage(@PathVariable("accountId") Long accountId, Model model, CustomerForm form, boolean result) {
		if (false == result) {
			Customer customer = customerService.findById(accountId).get();
			form.setName(customer.name);
			form.setNameKana(customer.nameKana);
			form.setPostalCode(customer.postalCode);
			form.setAddress(customer.address);
			form.setTel(customer.tel);
			form.setMail(customer.mail);
			form.setBirthDate(customer.birthDate.toString());
		}
		model.addAttribute("accountId", accountId);
		//model.addAttribute("customer", customer); formを利用するため model.addAttributeを使わない
		return "customers/edit";
	}

	@PostMapping("/edit")
	String edit(@RequestParam("id") Long accountId, @Validated CustomerForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return editPage(accountId, model, form, true);
		}
		Customer customer = new Customer();
		customer.id = accountId;
		customer.nameKana = form.getNameKana();
		customer.name = form.getName();
		customer.postalCode = form.getPostalCode();
		customer.address = form.getAddress();
		customer.tel = form.getTel();
		customer.mail = form.getMail();
		customer.birthDate = DateUtils.stringToLoacleDate(form.getBirthDate());
		customerService.update(customer);
		return "redirect:/customers/list";
	}

	@GetMapping("/delete/{accountId}")
	String delete(@PathVariable("accountId") Long accountId, Model model) {
		Customer customer = new Customer();
		customer.id = accountId;
		customerService.delete(customer);
		return "redirect:/customers/list";
	}

	@GetMapping("/download")
	@ResponseBody
    public String download(@RequestParam("fileName")String fileName,HttpServletResponse response) throws IOException {
    	List<Customer> customers = customerService.findAll();
    	List<CustomerCSV> csvlist = new ArrayList<>();
		for(Customer cs : customers) {
			CustomerCSV csv = new CustomerCSV();
    		csv.id = cs.id;
    		csv.nameKana = cs.nameKana;
    		csv.name = cs.name;
    		csv.postalCode = cs.postalCode;
    		csv.address = cs.address;
    		csv.tel = cs.tel;
    		csv.mail = cs.mail;
    		Collections.addAll(csvlist,csv);
		}
	    response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CustomerCSV.class).withHeader();
	    return mapper.writer(schema).writeValueAsString(csvlist);
	}

}

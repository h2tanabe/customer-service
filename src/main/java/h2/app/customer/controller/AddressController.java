package h2.app.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import h2.app.customer.service.AddressSerivce;

@Controller
@RequestMapping("/address")
public class AddressController extends AbstractController {

	@Autowired
	AddressSerivce addressSerivce;

	@GetMapping("/create")
	String createPage(Model model) {
		return "address/create";
	}

}

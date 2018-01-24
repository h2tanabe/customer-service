package h2.app.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/er")
public class ErController extends AbstractController {

	@GetMapping("/")
	public String findEr() {
		return "er/index";
	}

}

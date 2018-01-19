package h2.app.customer.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("news")
public class NewsController extends AbstractController {

	@GetMapping("")
	String blank() {
		return "news/index";
	}

	@RequestMapping("/encode")
	@ResponseBody
	public String encode(@RequestParam String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}

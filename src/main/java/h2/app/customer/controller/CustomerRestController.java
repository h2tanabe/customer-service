package h2.app.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController extends AbstractController{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/list")
	public List<Map<String, Object>> findAll() {
		return jdbcTemplate.queryForList("select * from customers");
	}

}

package h2.app.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController extends AbstractController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "顧客全件取得", notes = "顧客全件の情報を取得します")
	@GetMapping("/list")
	public List<Map<String, Object>> findAll() {
		return jdbcTemplate.queryForList("select * from customers");
	}

}

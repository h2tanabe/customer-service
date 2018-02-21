package h2.app.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import h2.app.customer.entity.Address;
import h2.app.customer.exception.DataNotFoundException;
import h2.app.customer.service.AddressSerivce;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/address")
public class AddressRestController extends AbstractController {

	@Autowired
	AddressSerivce addressSerivce;

	//郵便番号検索API
	@ApiOperation(value = "一件取得", notes = "郵便番号から住所の情報を取得します。", response = Address.class, tags = {"Address",})
	@GetMapping("zip={zip}")
	public List<Address> findZip(@PathVariable("zip") String zip) {
		List<Address> address = addressSerivce.findByZip(zip);
		if (address.size() == 0) {
			throw new DataNotFoundException("NotFound");
		}
		return address;
	}

}

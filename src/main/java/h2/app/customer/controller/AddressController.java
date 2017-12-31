package h2.app.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import h2.app.customer.entity.Address;
import h2.app.customer.service.AddressSerivce;

@RestController
@RequestMapping("api/address")
public class AddressController {

	@Autowired
	AddressSerivce addressSerivce;

	@GetMapping("zip={zip}")
	public List<Address>  findZip(@PathVariable("zip") String zip) {
		return addressSerivce.findByZip(zip);
	}

}

package h2.app.customer.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import h2.app.customer.entity.Address;
import h2.app.customer.entity.AddressCSV;
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

	@PostMapping("/create")
	@ResponseStatus(code=HttpStatus.OK)
	String create(@RequestParam("upload_file") MultipartFile multipartFile,
			@AuthenticationPrincipal UserDetails loginUser,
			//@RequestParam("filetype") String fileType,
			Model model) throws Exception {

		addressSerivce.deleteAll();

		Pattern pat = Pattern.compile("^#.*\n", Pattern.MULTILINE);
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(AddressCSV.class);
		List<Address> adressList = new ArrayList<>();
		int raw = 0;
		InputStream inputStream = multipartFile.getInputStream();
		try(BufferedReader reader = new BufferedReader(
		        new InputStreamReader(inputStream, Charset.forName("UTF-8")))){
		    String line;
		    while((line = reader.readLine()) != null){
		    	if(raw == 0){
			    	raw++;
                    continue;
                }
				String newTxt = pat.matcher(line).replaceAll("");
				MappingIterator<AddressCSV> mi = mapper.readerFor(AddressCSV.class).with(schema).readValues(newTxt);
				AddressCSV csv = mi.nextValue();
				Address adress = new Address();
				adress.oldZip = Integer.parseInt(StringUtils.trim(csv.kenCd));
				adress.kenCd = Integer.parseInt(StringUtils.trim(csv.allAreaCd));
				adress.zip = Integer.parseInt(StringUtils.trim(csv.zip));
				adress.kenFuri = csv.kenFuri;
				adress.kenName = csv.kenName;
				adress.cityFuri = csv.cityFuri;
				adress.cityName = csv.cityName;
				adress.townFuri = csv.townFuri;
				adress.townName = csv.townName;
				adress.createdAt = LocalDateTime.now();
				adress.createdBy = "test";
				adressList.add(adress);
				System.out.println(line);
		    }
			addressSerivce.batchInsert(adressList);
		    return "redirect:/address/create";

		}catch (IOException | IllegalArgumentException | StringIndexOutOfBoundsException e) {
		    return "redirect:/address/create";
		}

		/*
		InputStream inputStream = multipartFile.getInputStream();
		try(BufferedReader reader = new BufferedReader(
		        new InputStreamReader(inputStream, Charset.forName("UTF-8")))){
		    String line;
		    while((line = reader.readLine()) != null){
		    	System.out.println(line);
		    }
		    return "redirect:/address/create";

		}catch (IOException | IllegalArgumentException | StringIndexOutOfBoundsException e) {
		    return "redirect:/address/create";
		}
		*/

		/*
		File csvFile = new File(multipartFile.getOriginalFilename());
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(AddressCSV.class);
		//File csvFile = new File("C:\\j\\19YAMANA.CSV");
		MappingIterator<AddressCSV> it = mapper.readerFor(AddressCSV.class).with(schema).readValues(csvFile);
		it.next();
		List<AddressCSV> csvList = it.readAll();
		List<Address> adressList = new ArrayList<>();
		for (AddressCSV csv : csvList) {
			Address adress = new Address();
			adress.oldZip = Integer.parseInt(StringUtils.trim(csv.kenCd));
			adress.kenCd = Integer.parseInt(StringUtils.trim(csv.allAreaCd));
			adress.zip = Integer.parseInt(StringUtils.trim(csv.zip));
			adress.kenFuri = csv.kenFuri;
			adress.kenName = csv.kenName;
			adress.cityFuri = csv.cityFuri;
			adress.cityName = csv.cityName;
			adress.townFuri = csv.townFuri;
			adress.townName = csv.townName;
			adress.createdAt = LocalDateTime.now();
			adress.createdBy = "test";
			adressList.add(adress);
		}
		addressSerivce.batchInsert(adressList);
		return "redirect:/address/create";
		*/
	}
}

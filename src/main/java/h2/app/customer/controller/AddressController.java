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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import h2.app.customer.entity.Address;
import h2.app.customer.entity.AddressCSV;
import h2.app.customer.form.FileUploadForm;
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

	@GetMapping("/upload")
	String uploadPage(Model model) {
		return "address/upload";
	}

	@ModelAttribute
	FileUploadForm setupForm() {
		return new FileUploadForm();
	}

	@PostMapping("/upload")
	public String upload(RedirectAttributes attributes,FileUploadForm fileUploadForm) throws Exception {
		Pattern pat = Pattern.compile("^#.*\n", Pattern.MULTILINE);
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(AddressCSV.class);
		List<Address> adressList = new ArrayList<>();
		int raw = 0;
		InputStream inputStream = fileUploadForm.getFile().getInputStream();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (raw == 0) {
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
				raw++;
			}
			addressSerivce.batchInsert(adressList);
			attributes.addFlashAttribute("successMessage", "住所マスタを" + raw + "件アップデート完了しました");
			return "redirect:/address/upload";
		} catch (IOException | IllegalArgumentException | StringIndexOutOfBoundsException e) {
			return "redirect:/address/upload";
		}
	}
}

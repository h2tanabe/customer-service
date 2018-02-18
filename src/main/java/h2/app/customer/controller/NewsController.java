package h2.app.customer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("news")
public class NewsController extends AbstractController {

	@GetMapping("")
	String blank(Model model) {
		String path = new File(".").getAbsoluteFile().getParent();
		model.addAttribute("path", path);
		return "news/index";
	}

	@GetMapping("/ps")
	String getProccess(@RequestParam("path") String path, Model model) throws IOException {
		try {
			String lineCd = System.getProperty("line.separator");
			InputStream is = new FileInputStream("C:\\j\\1.txt");
		    InputStreamReader reader = new InputStreamReader(is);
		    StringBuilder builder = new StringBuilder();
		    //char[] buffer = new char[512];
		    int read;
		    while (0 <= (read = reader.read())) {
		        builder.append(read);
		        builder.append(lineCd);
		        builder.append("row:" + read);
		    }
		    reader.close();
			model.addAttribute("fileReader", builder);
			model.addAttribute("filePath", "Success" + path);

		} catch (IOException ex) {
			//例外発生時処理
			ex.printStackTrace();
			model.addAttribute("fileReader", "Error" + path);
			model.addAttribute("filePath", ex.toString());
		}
		return "news/path";
	}

	@RequestMapping("/encode")
	@ResponseBody
	public String encode(@RequestParam String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}

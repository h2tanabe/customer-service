package h2.app.customer.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileSerivceTest {

	@Test
	public void testRead() {
		Path path = Paths.get("C:\\j\\1.txt");
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String str = new String();
			List<String> lines = new ArrayList<>();
			while ((str = reader.readLine()) != null) {
				lines.add(str);
				System.out.println(str);
			}
			System.out.println("resul[0]" + lines.get(0));
			System.out.println("resul[1]" + lines.get(1));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		assertThat("java", either(startsWith("j")).or(startsWith("g")));
	}

}

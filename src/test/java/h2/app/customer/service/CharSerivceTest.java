package h2.app.customer.service;

import org.junit.Test;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class CharSerivceTest {

	@Test
	public void JavaのUnicodeを出力() {
		String input = "入力必須項目です";
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < input.length(); i++) {
		result.append("\\u").append(String.format("%04x", (int) input.charAt(i)));
		}
		System.out.println(result.toString());
	}

	@Test
	public void 暗号化パスワード() {
		String rawPassword = "password";
		System.out.println(new Pbkdf2PasswordEncoder().encode(rawPassword));
	}

}

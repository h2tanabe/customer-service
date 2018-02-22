package h2.app.customer.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import h2.app.customer.entity.User;
import lombok.Data;

@Data
public class LoginUserDeatils extends org.springframework.security.core.userdetails.User {

	private final User user;

	public LoginUserDeatils(User user) {
		super(	user.userName
				// Spring5からエンコード必要 Pbkdf2PasswordEncoder
				// There is no PasswordEncoder mapped for the id "null"
				,PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.encodedPassword)
				,AuthorityUtils.createAuthorityList("USER"));
		this.user = user;
	}

}

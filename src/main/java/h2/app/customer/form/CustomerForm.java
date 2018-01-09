package h2.app.customer.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CustomerForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	@NotNull
	@Size(min = 1, max = 255)
	private String nameKana;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String birthDate;

	private String postalCode;

	@Size(max = 100)
	private String address;

	private String gender;

	@NotNull
	@Email
	@Size(min = 1, max = 100)
	private String mail;

	@Pattern(regexp = "^([0-9]*)?$", message = "数字を入力下さい")
	private String tel;

}

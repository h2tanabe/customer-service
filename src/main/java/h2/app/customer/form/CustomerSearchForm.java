package h2.app.customer.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerSearchForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;

	private String address;

	private String mail;

	private String tel;

}

package h2.app.customer.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class OpportunityForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	@Min(0)
	@Max(10000000)
	private Long ammount;

	private String status;

	@NotNull
	private String accoutId;
}

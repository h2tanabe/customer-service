package h2.app.customer.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateUtils {

	public static LocalDate stringToLoacleDate(String s) {
		Optional<String> op = Optional.ofNullable(s);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(op.isPresent()) {
			return LocalDate.parse(s, formatter);
		}else {
			return null;
		}
	}

}

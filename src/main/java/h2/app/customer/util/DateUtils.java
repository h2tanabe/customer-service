package h2.app.customer.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {

	public static LocalDate stringToLoacleDate(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (StringUtils.isNotBlank(s)) {
			return LocalDate.parse(s, formatter);
		} else {
			return null;
		}
	}

	public static String loacleDateToString(LocalDate localDate) {
		Optional<LocalDate> localDateOpt = Optional.ofNullable(localDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (localDateOpt.isPresent()) {
			return localDate.format(formatter);
		} else {
			return StringUtils.EMPTY;
		}
	}

}

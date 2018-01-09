package h2.app.customer;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import h2.app.customer.util.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataUtilTests {

	@Test
	public void StringをLocalDateに変換() {
		String s = "2017-10-01";
		LocalDate strdate = DateUtils.stringToLoacleDate(s);
		LocalDate date = LocalDate.of(2017, 10, 01);
		assertEquals(strdate,date);
	}

}

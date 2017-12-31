package h2.app.customer.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import h2.app.customer.annotation.DomaRepository;
import h2.app.customer.entity.Address;

@DomaRepository
@Dao
public interface AddressDao {

	@Select
    List<Address> selectByZip(String zip);

	@Insert
	int insert(Address address);

	@Update
	int update(Address address);

	@Delete
	int delete(Address address);

}

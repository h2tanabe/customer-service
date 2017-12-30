package h2.app.customer.dao;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.SelectOptions;

import h2.app.customer.annotation.DomaRepository;
import h2.app.customer.entity.Customer;

@DomaRepository
@Dao
public interface CustomerDao {

	@Select
    Optional<Customer> selectById(Long id);

	@Insert
	int insert(Customer customer);

	@Update
	int update(Customer customer);

	@Delete
	int delete(Customer customer);

	@Select
	List<Customer> search(Customer customer,SelectOptions options,String orderBy);

}

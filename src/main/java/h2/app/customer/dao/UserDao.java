package h2.app.customer.dao;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import h2.app.customer.annotation.DomaRepository;
import h2.app.customer.entity.User;

@DomaRepository
@Dao
public interface UserDao {

	@Select
    Optional<User> selectById(String id);

	@Insert
	int insert(User user);

	@Update
	int update(User user);

	@Delete
	int delete(User user);

}

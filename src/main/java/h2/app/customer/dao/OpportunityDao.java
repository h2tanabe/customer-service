package h2.app.customer.dao;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import h2.app.customer.annotation.DomaRepository;
import h2.app.customer.dto.OpportunityDto;
import h2.app.customer.entity.Opportunity;

@DomaRepository
@Dao
public interface OpportunityDao {

	@Select
    Optional<Opportunity> selectById(Long id);

	@Insert
	int insert(Opportunity opportunity);

	@Update
	int update(Opportunity opportunity);

	@Delete
	int delete(Opportunity opportunity);

	@Select
	List<OpportunityDto> selectAll(String orderBy);

}

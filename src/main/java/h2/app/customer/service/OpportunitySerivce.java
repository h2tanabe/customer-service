package h2.app.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import h2.app.customer.dao.OpportunityDao;
import h2.app.customer.dto.OpportunityDto;
import h2.app.customer.entity.Opportunity;

@Service
@Transactional
public class OpportunitySerivce {

	@Autowired
	private OpportunityDao opportunityDao;

	public Optional<Opportunity> findById(Long id) {
		return opportunityDao.selectById(id);
	}

	public List<OpportunityDto> findAll() {
		return opportunityDao.selectAll("order by 1 desc");
	}

	public void insert(Opportunity opportunity) {
		opportunityDao.insert(opportunity);
	}

	public void update(Opportunity opportunity) {
		opportunityDao.update(opportunity);
	}

	public void delete(Opportunity opportunity) {
		opportunityDao.delete(opportunity);
	}

}

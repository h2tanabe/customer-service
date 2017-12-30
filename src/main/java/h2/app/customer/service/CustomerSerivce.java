package h2.app.customer.service;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import h2.app.customer.dao.CustomerDao;
import h2.app.customer.entity.Customer;

@Service
@Transactional
public class CustomerSerivce {

    @Autowired
    private CustomerDao customerDao;

    public Optional<Customer> findById(Long id) {
        return customerDao.selectById(id);
    }

    public List<Customer> findList(Customer customer,SelectOptions option) {
        return customerDao.search(customer,option,"order by 1 desc");
    }

	public void insert(Customer customer) {
        customerDao.insert(customer);
	}

	public void update(Customer customer) {
        customerDao.update(customer);
	}

	public void delete(Customer customer) {
        customerDao.delete(customer);
	}
}

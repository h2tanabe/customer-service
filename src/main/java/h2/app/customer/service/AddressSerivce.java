package h2.app.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import h2.app.customer.dao.AddressDao;
import h2.app.customer.entity.Address;

@Service
@Transactional
public class AddressSerivce {

    @Autowired
    private AddressDao addressDao;

    public List<Address> findByZip(String zip) {
        return addressDao.selectByZip(zip);
    }

    public  int[] batchInsert(List<Address> address) {
        return addressDao.bacthInsert(address);
    }

    public  int deleteAll() {
        return addressDao.deleteAll();
    }
}

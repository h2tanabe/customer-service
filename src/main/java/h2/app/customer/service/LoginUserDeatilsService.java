package h2.app.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import h2.app.customer.dao.UserDao;
import h2.app.customer.entity.User;

@Service
public class LoginUserDeatilsService implements UserDetailsService{

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)  {
		User user = userDao.selectById(username).get();
		return new LoginUserDeatils(user);
	}

}

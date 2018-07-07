package cn.itcast.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.bos.dao.UserDAO;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.UserService;
import cn.itcast.bos.utils.MD5Utils;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	public void saveUser(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		userDAO.insert(user);
	}

	public User findUserByLogin(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		return userDAO.login(user);
	}

	public void updatePassword(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		userDAO.updatePassword(user);
	}

}

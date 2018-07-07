package cn.itcast.bos.service;

import cn.itcast.bos.domain.User;

public interface UserService {
	public void saveUser(User user);
	public User findUserByLogin(User user); 
	public void updatePassword(User user);
}

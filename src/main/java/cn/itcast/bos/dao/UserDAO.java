package cn.itcast.bos.dao;

import cn.itcast.bos.domain.User;

public interface UserDAO extends BaseDAO<User> {
	// 从父类基础增删改查方法
	
	// 登录
	public User login(User user);

	// 修改密码
	public void updatePassword(User user);
}

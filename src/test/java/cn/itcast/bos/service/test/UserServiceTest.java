package cn.itcast.bos.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserServiceTest {
	// 测试UserService
	@Autowired
	private UserService userService;

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setId("2");
		user.setUsername("abc");
		user.setPassword("123");
		
		userService.saveUser(user);
	}

	@Test
	public void testFindUserByLogin() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123");
		
		User loginUser = userService.findUserByLogin(user);
		System.out.println(loginUser);
	}

}

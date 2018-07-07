package cn.itcast.bos.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.UserService;

/**
 * 和登录相关控制
 * @author seawind
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService; 
	
	@RequestMapping("/login.do") // 用户登录
	public String login(User user,String checkcode, HttpServletRequest request, Model model){
		// 判断验证码 是否正确
		String key = (String) request.getSession().getAttribute("key");//session中验证码
		if(key== null || !key.equals(checkcode)){
			// 验证码无效
			model.addAttribute("msg", "验证码输入错误");
			return "forward:login.jsp";
		}
		
		// 判断用户名和密码
		User loginUser = userService.findUserByLogin(user);
		if(loginUser == null){
			// 登录失败
			model.addAttribute("msg", "用户名或者密码错误");
			return "forward:login.jsp";
		}else{
			request.getSession().setAttribute("user", loginUser);
			// 重定向主页
			return "redirect:index.jsp";
		}
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request){
		// 销毁当前Session对象
		request.getSession().invalidate();
		return "redirect:login.jsp";
	}
}

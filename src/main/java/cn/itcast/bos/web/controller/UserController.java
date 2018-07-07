package cn.itcast.bos.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.UserService;
import flexjson.JSONSerializer;

/**
 * 用户操作控制器
 * @author seawind
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 修改密码  (使用 flexjson)
	 * @return
	 * @throws IOException 
	 */
//	@RequestMapping("/updatePassword.do")
//	public void updatePassword(String password, HttpServletRequest request, HttpServletResponse response) throws IOException{
//		// 封装新密码
//		User user = (User) request.getSession().getAttribute("user");
//		user.setPassword(password);
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		try{
//			// 调用Service层
//			userService.updatePassword(user);
//			// 向客户端返回 结果（以json返回 ）
//			result.put("success", true); 
//			result.put("msg", "修改密码成功！");
//		}catch(Exception e){
//			result.put("success", false); 
//			result.put("msg", "修改密码失败！发送异常："+ e.getMessage());
//		}
//		
//		// 将结果转换json
//		JSONSerializer jsonSerializer = new JSONSerializer();
//		String json = jsonSerializer.deepSerialize(result);
//		
//		response.setContentType("application/json;charset=utf-8");
//		response.getWriter().print(json);
//		
//	}
	
	@RequestMapping("/updatePassword.do")
	@ResponseBody // 注解 将返回值，自动转换为 json格式
	public Object updatePassword(String password, HttpServletRequest request) throws IOException{
		// 封装新密码
		User user = (User) request.getSession().getAttribute("user");
		user.setPassword(password);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			// 调用Service层
			userService.updatePassword(user);
			// 向客户端返回 结果（以json返回 ）
			result.put("success", true); 
			result.put("msg", "修改密码成功！");
		}catch(Exception e){
			result.put("success", false); 
			result.put("msg", "修改密码失败！发送异常："+ e.getMessage());
		}
		return result;
	}
}

package cn.itcast.bos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.page.PaginationInfo;
import cn.itcast.bos.service.StaffService;

import java.util.List;

/**
 * 取派员 管理
 * @author seawind
 *
 */
@Controller
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	/**
	 * 保存取派员
	 * @param staff
	 * @return
	 */
	@RequestMapping("/staff_save.do")
	public String save(Staff staff){
		// 调用Service 保存到数据库
		staffService.saveStaff(staff);
		
		return "base/staff";
	}

	/**
	 * 取派员分页查询
	 * 	 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("staff_pageQuery.do")
	@ResponseBody // 将方法返回值 转换为json
	public Object pageQuery(int page,int rows){
		// 分页Bean 含有 total 和 rows 属性 
		PaginationInfo<Staff> paginationInfo = new PaginationInfo<Staff>();
		paginationInfo.setPagesize(rows);
		paginationInfo.setPageno(page);

		// 调用业务层
		staffService.pageQuery(paginationInfo);

		return paginationInfo;
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	@RequestMapping("/staff_delBatch.do")
	public String delBatch(String[] id){
		// 调用业务层，完成批量作废
		staffService.deleteBatch(id);
		
		return "base/staff";
	}

	/**
	 *查询有效的取派员
	 *
	 */
	@RequestMapping("/staff_ajaxlist.do")
	@ResponseBody
	public Object findAll(){
		List<Staff> staffList = staffService.findAll();
		return staffList;
	}
}

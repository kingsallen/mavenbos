package cn.itcast.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.bos.dao.StaffDAO;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.page.PaginationInfo;
import cn.itcast.bos.service.StaffService;

import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	private StaffDAO staffDAO;

	public void saveStaff(Staff staff) {
		// 判断编号是否存在，如果存在执行update，如果不存在，执行 insert 
		Staff result = staffDAO.findById(staff.getId());
		if(result == null){
			// 不存在
			staffDAO.insert(staff);
		}else{
			// 存在
			staffDAO.update(staff);
		}
	}

	public void pageQuery(PaginationInfo<Staff> paginationInfo) {
		// 分页 查询，至少两条SQL 语句 
		paginationInfo.setTotal(staffDAO.findTotalCount());
		// 左闭右开
		int first = (paginationInfo.getPageno() - 1) * paginationInfo.getPagesize() ;
		int last = paginationInfo.getPageno() * paginationInfo.getPagesize() ;
		
		paginationInfo.setRows(staffDAO.findPaginationData(first, last));
	}

	public void deleteBatch(String[] idArray) {
		if(idArray!=null){
			// 一条条作废
			/*
			for (String id : idArray) {
				Staff staff = new Staff();
				staff.setId(id);
				
				staffDAO.updateDelTag(staff);
			}
			*/
			
			// 传递给数据库，一起执行作废
			staffDAO.updateDelTagBatch(idArray);
		}
	}

	public List<Staff> findAll() {
		return staffDAO.findAll();
	}

}

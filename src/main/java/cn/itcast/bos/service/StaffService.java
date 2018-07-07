package cn.itcast.bos.service;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.page.PaginationInfo;

import java.util.List;

/**
 * 取派员业务接口
 * @author seawind
 *
 */
public interface StaffService {

	/**
	 * 保存取派员信息
	 * @param staff
	 */
	public void saveStaff(Staff staff);

	/**
	 * 分页查询
	 * @param paginationInfo
	 */
	public void pageQuery(PaginationInfo<Staff> paginationInfo);

	/**
	 * 批量作废
	 * @param idArray
	 */
	public void deleteBatch(String[] idArray);

    public List<Staff> findAll();
}

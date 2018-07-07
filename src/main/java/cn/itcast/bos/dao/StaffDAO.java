package cn.itcast.bos.dao;

import cn.itcast.bos.domain.Staff;

/**
 * 取派员操作
 * @author seawind
 *
 */
public interface StaffDAO extends BaseDAO<Staff>{

	/**
	 * 修改 删除标记
	 * @param staff
	 */
	public void updateDelTag(Staff staff); 

	/**
	 * 批量 修改删除标记
	 * @param idArray
	 */
	public void updateDelTagBatch(String[] idArray);

}

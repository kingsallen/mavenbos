package cn.itcast.bos.service;

import java.util.List;
import java.util.Map;

import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.page.PaginationInfo;

/**
 * 分区管理
 * 
 * @author seawind
 * 
 */
public interface SubareaService {
	/**
	 * 保存分区
	 * 
	 * @param subarea
	 */
	public void saveSubarea(Subarea subarea);

	/**
	 * 有条件分页查询
	 * 
	 * @param paginationInfo
	 */
	public void pageQuery(PaginationInfo<Subarea> paginationInfo);

	/**
	 * 条件查询
	 * 
	 * @param condition
	 * @return
	 */
	public List<Subarea> findSubareasByCondition(Map<String, Object> condition);

    public List<Subarea> findNoAssociations();
}

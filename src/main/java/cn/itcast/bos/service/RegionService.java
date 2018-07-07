package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.page.PaginationInfo;

/**
 * 区域管理
 * 
 * @author seawind
 * 
 */
public interface RegionService {

	/**
	 * 保存区域
	 * 
	 * @param region
	 */
	public void save(Region region);

	/**
	 * 分页查询
	 * 
	 * @param paginationInfo
	 */
	public void pageQuery(PaginationInfo<Region> paginationInfo);

	/**
	 * 批量插入
	 * 
	 * @param regions
	 */
	public void saveBatch(List<Region> regions);

	/**
	 * 查询所有区域
	 * 
	 * @return
	 */
	public List<Region> findAllRegions();

	/**
	 * 根据条件查询
	 * 
	 * @param q
	 * @return
	 */
	public List<Region> findRegionsByCondition(String q);

}

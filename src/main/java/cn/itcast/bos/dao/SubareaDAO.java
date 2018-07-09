package cn.itcast.bos.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.bos.domain.Subarea;
import org.apache.ibatis.annotations.Param;

public interface SubareaDAO extends BaseDAO<Subarea> {

	/**
	 * 查询分区数据
	 * 
	 * @param condition
	 * @return
	 */
	public List<Subarea> findSubareasByCondition(Map<String, Object> condition);

    public List<Subarea> findNoAssociations();

    public void assignDecidedZone(@Param("subareaID")String[] subareaId, @Param("decidedZoneID")String decidedZoneID);
}

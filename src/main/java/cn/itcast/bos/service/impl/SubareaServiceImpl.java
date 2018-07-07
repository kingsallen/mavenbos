package cn.itcast.bos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.bos.dao.SubareaDAO;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.page.PaginationInfo;
import cn.itcast.bos.service.SubareaService;

@Service
public class SubareaServiceImpl implements SubareaService {

    @Autowired
    private SubareaDAO subareaDAO;

    public void saveSubarea(Subarea subarea) {
        subareaDAO.insert(subarea);
    }

    public void pageQuery(PaginationInfo<Subarea> paginationInfo) {
        // 查询 total
        paginationInfo.setTotal(subareaDAO.findTotalCountByCondition(paginationInfo));
        // 查询rows
        paginationInfo.setRows(subareaDAO.findPaginationDataByCondition(paginationInfo));
    }

    public List<Subarea> findSubareasByCondition(Map<String, Object> condition) {
        return subareaDAO.findSubareasByCondition(condition);
    }

    public List<Subarea> findNoAssociations() {
        return subareaDAO.findNoAssociations();

    }

}

package cn.itcast.bos.web.controller;

import cn.itcast.bos.domain.DecidedZone;
import cn.itcast.bos.page.PaginationInfo;
import cn.itcast.bos.service.CustomerService;
import cn.itcast.bos.service.DecidedZoneService;
import cn.itcast.bos.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wlee on 2018/7/7.
 */
@Controller
public class DecidedZoneController {

    @Autowired
    private DecidedZoneService decidedZoneService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/decidedZone_save.do")
    public String  save(DecidedZone decidedZone,String [] subareaId){
        decidedZoneService.saveDecidedZone(decidedZone, subareaId);
        return "base/decidedzone";
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/decidedZone_pageQuery.do")
    @ResponseBody
    public Object decidedZone_pageQuery(String id, String station, String subareaId, int page, int rows){
        PaginationInfo<DecidedZone> paginationInfo = new PaginationInfo<DecidedZone>();
        paginationInfo.setPagesize(rows);
        paginationInfo.setPageno(page);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("decidedzoneId", id);
        condition.put("station", station);
        condition.put("subareaId", subareaId);
        paginationInfo.setCondition(condition);
        decidedZoneService.pageQuery(paginationInfo);
        return paginationInfo;
    }

    /**
     * 加载未关联的定区
     */
    @RequestMapping("/decidedzone_findnoassociationCustomers.do")
    @ResponseBody
    public Object findnoassociationCustomers(){
        return customerService.findNoAssociationCustomers();
    }

    /**
     * 加载已关联的定区
     */
    @RequestMapping("/decidedzone_findhasassociationCustomers.do")
    @ResponseBody
    public Object findhasassociationCustomers(String decidedzoneid){
        return customerService.findHasAssociationCustomers(decidedzoneid);
    }

    @RequestMapping("/decidedzone_assigncustomerstodecidedzone.do")
    public String assigncustomerstodecidedzone(String [] customerIds, String id){
        customerService.assignCustomersToDecidedZone(customerIds, id);
        return "base/decidedzone";
    }
}

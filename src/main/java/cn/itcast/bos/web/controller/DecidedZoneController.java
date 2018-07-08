package cn.itcast.bos.web.controller;

import cn.itcast.bos.domain.DecidedZone;
import cn.itcast.bos.service.DecidedZoneService;
import cn.itcast.bos.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wlee on 2018/7/7.
 */
@Controller
public class DecidedZoneController {

    @Autowired
    private DecidedZoneService decidedZoneService;

    @RequestMapping("/decidedZone_save.do")
    public String  save(DecidedZone decidedZone,String [] subareaId){
        decidedZoneService.saveDecidedZone(decidedZone, subareaId);
        return "base/decidedzone";
    }
}

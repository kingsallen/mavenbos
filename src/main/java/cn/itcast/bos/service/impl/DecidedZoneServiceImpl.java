package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.DecidedZoneDao;
import cn.itcast.bos.dao.SubareaDAO;
import cn.itcast.bos.domain.DecidedZone;
import cn.itcast.bos.service.DecidedZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wlee on 2018/7/8.
 */
@Service
public class DecidedZoneServiceImpl implements DecidedZoneService {

    @Autowired
    private DecidedZoneDao decidedZoneDao;
    @Autowired
    private SubareaDAO subareaDAO;

    public void saveDecidedZone(DecidedZone decidedZone, String[] subareaId) {
        decidedZoneDao.insert(decidedZone);
        subareaDAO.assignDecidedZone(subareaId, decidedZone.getId());
    }
}

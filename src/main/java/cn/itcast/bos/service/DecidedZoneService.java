package cn.itcast.bos.service;

import cn.itcast.bos.domain.DecidedZone;

/**
 * Created by wlee on 2018/7/8.
 */
public interface DecidedZoneService {
    public void saveDecidedZone(DecidedZone decidedZone, String[] subareaId);
}

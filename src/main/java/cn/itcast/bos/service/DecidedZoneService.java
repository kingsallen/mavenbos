package cn.itcast.bos.service;

import cn.itcast.bos.domain.DecidedZone;
import cn.itcast.bos.page.PaginationInfo;

/**
 * Created by wlee on 2018/7/8.
 */
public interface DecidedZoneService {
    public void saveDecidedZone(DecidedZone decidedZone, String[] subareaId);

    public void pageQuery(PaginationInfo<DecidedZone> paginationInfo);
}

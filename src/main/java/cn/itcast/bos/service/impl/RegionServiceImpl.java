package cn.itcast.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.bos.dao.RegionDAO;
import cn.itcast.bos.domain.Region;
import cn.itcast.bos.page.PaginationInfo;
import cn.itcast.bos.service.RegionService;
import cn.itcast.bos.utils.PinYin4jUtils;

@Service("regionService")
public class RegionServiceImpl implements RegionService {
	// 注入DAO
	@Autowired
	private RegionDAO regionDAO;

	public void save(Region region) {
		// StringUtils
		if (StringUtils.isBlank(region.getCitycode())) {
			// 用户没有输入城市编码
			String city = region.getCity().substring(0, region.getCity().length() - 1);
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			region.setCitycode(citycode);
		}

		if (StringUtils.isBlank(region.getShortcode())) {
			// 用户没有输入 简码
			String province = region.getProvince().substring(0, region.getProvince().length() - 1);
			String city = region.getCity().substring(0, region.getCity().length() - 1);
			String district = region.getDistrict().substring(0, region.getDistrict().length() - 1);

			String[] array = PinYin4jUtils.getHeadByString(province + city + district);
			StringBuilder stringBuilder = new StringBuilder();
			for (String s : array) {
				stringBuilder.append(s);
			}
			String shortcode = stringBuilder.toString();
			region.setShortcode(shortcode);
		}

		regionDAO.insert(region);
	}

	public void pageQuery(PaginationInfo<Region> paginationInfo) {
		paginationInfo.setTotal(regionDAO.findTotalCount());
		// first 取不到，last 取到
		int first = (paginationInfo.getPageno() - 1) * paginationInfo.getPagesize();
		int last = paginationInfo.getPageno() * paginationInfo.getPagesize();

		paginationInfo.setRows(regionDAO.findPaginationData(first, last));
	}

	public void saveBatch(List<Region> regions) {
		regionDAO.insertBatch(regions);
	}

	public List<Region> findAllRegions() {
		return regionDAO.findAll();
	}

	public List<Region> findRegionsByCondition(String q) {
		return regionDAO.findRegionsByCondition(q);
	}

}

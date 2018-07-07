package cn.itcast.bos.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.page.PaginationInfo;
import cn.itcast.bos.service.RegionService;
import cn.itcast.bos.utils.PinYin4jUtils;

/**
 * 区域管理
 * 
 * @author seawind
 * 
 */
@Controller
public class RegionController {

	// 1、定义记录器
	private static final Logger Log = Logger.getLogger(RegionController.class);
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private RegionService regionService;

	/**
	 * 保存区域信息
	 * 
	 * @return
	 */
	@RequestMapping("/reqion_save.do")
	public String save(Region region) {
		// 调用业务层
		regionService.save(region);

		return "base/region";
	}

	/**
	 * 分页列表查询
	 */
	@RequestMapping("/region_pageQuery.do")
	@ResponseBody
	public Object pagequery(int page, int rows) {
		// 封装到 分页数据Bean
		PaginationInfo<Region> paginationInfo = new PaginationInfo<Region>();
		paginationInfo.setPagesize(rows);
		paginationInfo.setPageno(page);

		// 调用业务层 完成分页查询
		regionService.pageQuery(paginationInfo);
		return paginationInfo;
	}

	/**
	 * 批量导入
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/region_batchImport.do")
	@ResponseBody
	public Object batchImport(MultipartFile file) throws IOException {
		// 打开上传文件
		InputStream in = file.getInputStream();
		// 构造工作薄对象
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
		// 打开第一个Sheet
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
		// 解析sheet
		List<Region> regions = new ArrayList<Region>();
		for (Row row : sheet) { // 获取sheet每一行
			// 跳过第一行
			if (row.getRowNum() == 0) {
				continue;
			}
			// 每行数据 就是一个Region
			if (StringUtils.isBlank(row.getCell(0).getStringCellValue())) {
				continue; // 跳转区域编号为 空数据
			}
			Region region = new Region();
			region.setId(row.getCell(0).getStringCellValue());
			region.setProvince(row.getCell(1).getStringCellValue());
			region.setCity(row.getCell(2).getStringCellValue());
			region.setDistrict(row.getCell(3).getStringCellValue());
			region.setPostcode(row.getCell(4).getStringCellValue());

			// 使用pinyin4j 生成简码和城市编码
			// 用户没有输入城市编码
			String city = region.getCity().substring(0, region.getCity().length() - 1);
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			region.setCitycode(citycode);

			// 用户没有输入 简码
			String province = region.getProvince().substring(0, region.getProvince().length() - 1);
			String district = region.getDistrict().substring(0, region.getDistrict().length() - 1);

			String[] array = PinYin4jUtils.getHeadByString(province + city + district);
			StringBuilder stringBuilder = new StringBuilder();
			for (String s : array) {
				stringBuilder.append(s);
			}
			String shortcode = stringBuilder.toString();
			region.setShortcode(shortcode);

			regions.add(region);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 调用业务层，完成批量插入
			regionService.saveBatch(regions);
			// 结果
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			// 使用log4j 记录日志
			log.error("区域批量导入失败！", e);
		}
		return result;
	}

	@RequestMapping("/region_ajaxlist.do")
	@ResponseBody
	public Object ajaxlist(String q) {
		// 默认列表什么都没有
		if (StringUtils.isBlank(q)) {
			List<Region> regions = new ArrayList<Region>();
			return regions;
		} else {
			// 根据q查询
			// 调用业务层，查询所有区域
			List<Region> regions = regionService.findRegionsByCondition(q);
			return regions;
		}
	}
}

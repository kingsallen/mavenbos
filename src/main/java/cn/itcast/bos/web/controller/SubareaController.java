package cn.itcast.bos.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.page.PaginationInfo;
import cn.itcast.bos.service.SubareaService;

/**
 * 分区管理
 * 
 * @author seawind
 * 
 */
@Controller
public class SubareaController {

	@Autowired
	private SubareaService subareaService;

	/**
	 * 添加分区
	 *
	 * @param subarea
	 * @return
	 */
	@RequestMapping("/subarea_save.do")
	public String save(Subarea subarea) {
		// region.id 参数 封装 subarea对象 region属性 id 中
		// 调用业务层，保存subarea
		subareaService.saveSubarea(subarea);

		return "base/subarea";
	}

	/**
	 * 分页查询
	 * 
	 * @param subarea
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/subarea_pageQuery.do")
	@ResponseBody
	public Object pageQuery(Subarea subarea, int page, int rows, HttpSession httpSession) {
		// 封装分页查询参数
		PaginationInfo<Subarea> paginationInfo = new PaginationInfo<Subarea>();
		paginationInfo.setPageno(page);
		paginationInfo.setPagesize(rows);

		// 问题：如何封装查询条件？？？
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("addresskey", subarea.getAddresskey());
		condition.put("region", subarea.getRegion());
		condition.put("decidedZone", subarea.getDecidedZone());
		paginationInfo.setCondition(condition);
		// 将查询条件 缓存到Session
		httpSession.setAttribute("condition", condition);

		// 调用业务层 查询
		subareaService.pageQuery(paginationInfo);

		return paginationInfo;
	}

	/**
	 * 条件查询 导出
	 *
	 * @throws IOException
	 */
	@RequestMapping("/subarea_export.do")
	public String export(HttpSession httpSession, HttpServletResponse response, ModelMap map) throws IOException {
		Map<String, Object> condition = (Map<String, Object>) httpSession.getAttribute("condition");
		// 重新查询一次 （不分页）
		List<Subarea> subareas = subareaService.findSubareasByCondition(condition);
		map.put("subareas", subareas);
		return "subareaExcelData";
	}
	/**
	 * 条件查询 导出
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/subarea_export11.do")
	public String export_a(HttpSession httpSession, HttpServletResponse response, ModelMap map) throws IOException {
		Map<String, Object> condition = (Map<String, Object>) httpSession.getAttribute("condition");
		// 重新查询一次 （不分页）
		List<Subarea> subareas = subareaService.findSubareasByCondition(condition);
		map.put("subareas", subareas);
		return "subareaExcel";
	}

	public void export2(HttpSession httpSession, HttpServletResponse response) throws IOException {
		Map<String, Object> condition = (Map<String, Object>) httpSession.getAttribute("condition");
		// 重新查询一次 （不分页）
		List<Subarea> subareas = subareaService.findSubareasByCondition(condition);

		// 构造工作薄
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		// 创建sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("分区数据信息");

		// 表头
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("省");
		headRow.createCell(2).setCellValue("市");
		headRow.createCell(3).setCellValue("区");
		headRow.createCell(4).setCellValue("关键字");
		headRow.createCell(5).setCellValue("位置");

		// 表数据
		for (Subarea subarea : subareas) {
			// 每一个Subarea 就是一行数据
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getProvince());
			dataRow.createCell(2).setCellValue(subarea.getRegion().getCity());
			dataRow.createCell(3).setCellValue(subarea.getRegion().getDistrict());
			dataRow.createCell(4).setCellValue(subarea.getAddresskey());
			dataRow.createCell(5).setCellValue(subarea.getPosition());
		}

		// 提供下载
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=subarea.xls");
		hssfWorkbook.write(response.getOutputStream());
	}

	/**
	 * 查询未关联的分区
	 */
	@RequestMapping("/subarea_findNoAssociations.do")
	@ResponseBody
	public Object findNoAssociations(){
		List<Subarea> subareas = subareaService.findNoAssociations();
		return subareas;
	}
}

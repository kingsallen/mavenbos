package cn.itcast.bos.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.utils.FileUtils;

@SuppressWarnings("all")
public class SubareaExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 指定下载文件名称
		response.setHeader("Content-Disposition", "attachment;filename=" + FileUtils.encodeDownloadFilename("分区数据.xls", request.getHeader("user-agent")));

		// 获取数据
		List<Subarea> subareas = (List<Subarea>) model.get("subareas");
		// 创建sheet
		HSSFSheet sheet = workbook.createSheet("分区数据信息");
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
	}

}

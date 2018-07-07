package cn.itcast.bos.web.view;

import cn.itcast.bos.domain.Subarea;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by wlee on 2018/6/29.
 */
public class SubareaExcelDataView extends AbstractExcelView {
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook hssfWorkbook,
                                      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String("分区数据.xls".getBytes(),"iso8859-1"));
        List<Subarea> subareas = (List<Subarea>) map.get("subareas");
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据信息");
//        表头信息
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("省");
        headRow.createCell(2).setCellValue("市");
        headRow.createCell(3).setCellValue("区");
        headRow.createCell(4).setCellValue("关键字");
        headRow.createCell(5).setCellValue("位置");
        
        //表数据
        for (Subarea subarea : subareas) {
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

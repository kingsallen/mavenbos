package cn.itcast.bos.poi.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

public class POITest {
	// 解析Excel 
	@Test
	public void demo1() throws IOException{
		// 1、打开Excel文件 
		InputStream in = new FileInputStream("info.xls");
		// 2、解析Excel 构造工作薄对象
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
		// 3、找到对应sheet
		HSSFSheet sheet = hssfWorkbook.getSheet("Sheet1");// 通过名称
		HSSFSheet sheet2 = hssfWorkbook.getSheetAt(0);// 通过下标 0 代表第一个sheet
		// 4、读取表格行
		for (Row row : sheet) { // row代表Excel中一行
			// 5、 读取每行中单元格 
			for (Cell cell : row) {
				/*
				if(cell.getCellType()== Cell.CELL_TYPE_STRING){
					// 字符串
					System.out.println(cell.getStringCellValue());
				}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
					// 数字
					System.out.println(cell.getNumericCellValue());
				} 
				*/
				System.out.println(cell.getStringCellValue());
			}
		}
	}
	
	
	// 生成Excel
	@Test
	public void demo2() throws FileNotFoundException, IOException{
		// 1、 新建 Excel文档 
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		// 2、创建 sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("数据信息");
		// 3、向sheet写数据 
		HSSFRow row0 = sheet.createRow(0); // 第一行
		HSSFCell cell1 = row0.createCell(0); // 第一行 第一个单元格
		cell1.setCellValue("商品");
		HSSFCell cell2 = row0.createCell(1); // 第一行 第二个单元格
		cell2.setCellValue("价格"); 
		// 4、将内存Excel数据 写入硬盘
		hssfWorkbook.write(new FileOutputStream("c:/test.xls")); 
		
	}
}

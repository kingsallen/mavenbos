package cn.itcast.bos.poi.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class POITest {
    // 解析Excel

    /**
     * zTree树数据结构生成
     * [
     *  {"id":"1","label":"root1",
     *  children:[{"id":"1","label":"root1"},
     *      {"id":"1","label":"root1"}]},
     * {"id":"1","label":"root1",
     *  children:[{"id":"1","label":"root1"}]}]
     */
    @Test
    public void tsjs() {
        ArrayList<Map<String, String>> pl = new ArrayList<Map<String, String>>();
        HashMap<String, String> ts = new HashMap<String, String>();
        ts.put("id", "01");
        ts.put("pid", "");
        ts.put("name", "根节点");
        pl.add(ts);
        ts = new HashMap<String, String>();
        ts.put("id", "02");
        ts.put("pid", "");
        ts.put("name", "根节点2");
        pl.add(ts);
        ts = new HashMap<String, String>();
        ts.put("id", "021");
        ts.put("pid", "02");
        ts.put("name", "g2");
        pl.add(ts);
        ts = new HashMap<String, String>();
        ts.put("id", "011");
        ts.put("pid", "01");
        ts.put("name", "c1");
        pl.add(ts);

        ts = new HashMap<String, String>();
        ts.put("id", "012");
        ts.put("pid", "01");
        ts.put("name", "c2");
        pl.add(ts);

        ts = new HashMap<String, String>();
        ts.put("id", "0111");
        ts.put("pid", "011");
        ts.put("name", "c11");
        pl.add(ts);
        ts = new HashMap<String, String>();

        ts.put("id", "0112");
        ts.put("pid", "011");
        ts.put("name", "c12");
        pl.add(ts);
        ts = new HashMap<String, String>();
        ts.put("id", "0121");
        ts.put("pid", "012");
        ts.put("name", "c21");
        pl.add(ts);
        System.out.println(pl);
        JSONArray ja = new JSONArray();
        for (Map<String, String> m : pl) {
            if (StringUtils.isEmpty(m.get("pid"))) {//根节点
                JSONObject temp = new JSONObject();
                temp.put("id", m.get("id"));
                temp.put("label", m.get("name"));
                genTerr1(temp, pl);
                ja.put(temp);
            }
        }
        System.out.println(ja);
    }

    private void genTerr1(JSONObject jo, ArrayList<Map<String, String>> pl) {
        JSONArray ja = new JSONArray();
        for (Map<String, String> m : pl) {
            if (m.get("pid").equals(jo.getString("id"))) {
                JSONObject temp = new JSONObject();
                temp.put("id", m.get("id"));
                temp.put("label", m.get("name"));
                ja.put(temp);
                genTerr1(temp, pl);
            }
            if (ja.length() > 0)
                jo.put("children", ja);
        }
    }

    @Test
    public void demo1() throws IOException {
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
    public void demo2() throws FileNotFoundException, IOException {
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

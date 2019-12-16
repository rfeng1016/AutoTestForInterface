import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


import static io.restassured.RestAssured.given;

/**
 * Created by RuanFeng on 2017/5/7.
 */
public class APIForExcel {

    String filepath;

    @Before
    public void setUp() throws Exception{

        filepath = "Users/RuanFeng/RestAssuredProject/DemoForAPI/Excel/test.xlsx";

    }

    @Test
    public void readExcel() throws Exception{

        InputStream is = new FileInputStream(filepath);

        //是操作Excel2011的版本，扩展名是.xlsx
        XSSFWorkbook xssfSheets = new XSSFWorkbook(is);

        //获取有几个sheet
        int sheetcount = xssfSheets.getNumberOfSheets();

        //获取第一个sheet
        for (int i = 0; i < sheetcount;i++){
            XSSFSheet xssfSheet = xssfSheets.getSheetAt(i){
                if (xssfSheet == null){
                    continue;
                }
            }
        }

        //循环读取行
        for (int rownum = 1;rownum <= xssfSheet.getLastRowNum();rownum++){

            //第一列是用例名称
            XSSFRow xssfRow = xssfSheet.getCell(0) == null ? "":getStringCellVal(xssfRow.getCell(0));

            //第二列是接口URL
            String url = xssfRow.getCell(1) == null ? "":getStringCellVal(xssfRow.getCell(1));

            //第三列是参数
            String jsonparam = xssfRow.getCell(2) == null ? "":getStringCellVal(xssfRow.getCell(2));

            //执行接口测试
            int statuscode = sendUrl(casename,url,jsonparam);

            //更新执行结果
            xssfRow.createCell(3,1).setCellValue(statuscode == 200?"通过":"失败");
        }
    }

    FileOutputStream outputStream = null;
    try{
        outputStream = new FileOutputStream(filepath);
        xssfSheets.write(outputStream);
    }   catch (IOException e){
        e.printStackTrace();
    }   finally {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int sendUrl(String casename, String url,String jsonparam) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        //组装参数
        JSONObject obj = new JSONObject(jsonparam);
        Iterator objs = obj.keys();
        while (objs.hasNext()) {
            String key = objs.next().toString();
            map.put(key, obj.getString(key));
        }
        return given().relaxedHTTPSValidation().params(map).get(url).statusCode();

    }

    public static String getStringCellVal(XSSFCell cell){
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue()?"true":"false";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);//做了一个特殊处理，如果是number的先转换成String的再进行读取
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";

        }

    }

}





























































































































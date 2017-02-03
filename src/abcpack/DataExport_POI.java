package abcpack;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DataExport_POI {
	@Test
	public void dataExport() throws Exception
	{
		FileOutputStream fos=new FileOutputStream("D:\\Selenium_Scripts_Jul16\\Results\\DataExport_POI.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet  s1=wb.createSheet("R1");
		XSSFSheet  s2=wb.createSheet("R2");
		Row r= s1.createRow(0);
		r.createCell(0).setCellValue("Selenium");
		r.createCell(1).setCellValue("Appium");
		wb.write(fos);
		fos.close();
	}

}

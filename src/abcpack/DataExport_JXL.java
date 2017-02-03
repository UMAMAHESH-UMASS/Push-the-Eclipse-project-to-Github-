package abcpack;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.testng.annotations.Test;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DataExport_JXL {
	@Test
	public void dataExport() throws Exception
	{
		FileOutputStream fos=new FileOutputStream("D:\\Selenium_Scripts_Jul16\\Results\\DataExport_JXL.xls");
		WritableWorkbook wwb=Workbook.createWorkbook(fos);
		WritableSheet ws=wwb.createSheet("Results",0);
		/*Label l1=new Label(0,0,"Selenium");
		ws.addCell(l1);*/
		Label l2=new Label(0,1,"Appium");
		ws.addCell(l2);
		Label l3=new Label(0,2,"QTP");
		ws.addCell(l3);
		wwb.write();
		wwb.close();
		
	}

}

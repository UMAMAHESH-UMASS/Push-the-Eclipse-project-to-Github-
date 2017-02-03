package abcpack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class LoginStatus {
	WebDriver d;
	@Test
	public void testLogin() throws Exception
	{
		// Load web page
		d.get("http://49.206.196.246:8080/Cyclos/do/login");
		assertEquals("USABANK-NY Branch",d.getTitle());
		
		FileInputStream fis=new FileInputStream("D:\\Selenium_Scripts_Jul16\\Test Data\\Login.xls");
		Workbook wb=Workbook.getWorkbook(fis);
		Sheet s=wb.getSheet(0);
		
		FileOutputStream fos=new FileOutputStream("D:\\Selenium_Scripts_Jul16\\Results\\LoginResults.xls");
		WritableWorkbook wwb=Workbook.createWorkbook(fos);
		WritableSheet ws=wwb.createSheet("Results",0);
		//Copy old sheet data to new sheet
		for(int i=0;i<s.getRows();i++)
		{
			for(int j=0;j<s.getColumns();j++)
			{
				Label l=new Label(j,i,s.getCell(j,i).getContents());
				ws.addCell(l);
			}
		}
		
		//Reading data from excel using JXL
		for(int i=1;i<s.getRows();i++)
		{
			//Enter user name
			d.findElement(By.id("cyclosUsername")).clear();
			d.findElement(By.id("cyclosUsername")).sendKeys(s.getCell(0,i).getContents());
			String uname=d.findElement(By.id("cyclosUsername")).getAttribute("value");
			//Enter password
			d.findElement(By.id("cyclosPassword")).clear();
			d.findElement(By.id("cyclosPassword")).sendKeys(s.getCell(1,i).getContents());
			String pwd=d.findElement(By.id("cyclosPassword")).getAttribute("value");
			//Click on submit
			d.findElement(By.xpath("//input[@value='Submit']")).click();
			//Blank user name & Blank password
			if(uname.equals("") && pwd.equals(""))
			{
				Alert al=d.switchTo().alert();
				assertEquals("Login name is required\nPassword is required\n",al.getText());
				al.accept();
				Label l=new Label(2,i,"Fail");
				ws.addCell(l);
			}
			//Blank user name & valid/invalid password
			else if(uname.equals(""))
			{
				Alert al=d.switchTo().alert();
				assertEquals("Login name is required\n",al.getText());
				al.accept();
				Label l=new Label(2,i,"Fail");
				ws.addCell(l);
			}
			//Bank password & valid/invalid user name
			else if(pwd.equals(""))
			{
				Alert al=d.switchTo().alert();
				assertEquals("Password is required\n",al.getText());
				al.accept();
				Label l=new Label(2,i,"Fail");
				ws.addCell(l);
			}
			//Valid user name & Valid password
			else if(isElementPresent(d,By.xpath("//span[text()='Logout']")))
			{
				d.findElement(By.xpath("//span[text()='Logout']")).click();
				//Switch driver focus to alert
				Alert al=d.switchTo().alert();
				//Ok
				al.accept();
				Label l=new Label(2,i,"Pass");
				ws.addCell(l);
			}
			//Invalid user name & Invalid password
			else if(isElementPresent(d,By.id("btn")))
			{
				d.findElement(By.id("btn")).click();
				Label l=new Label(2,i,"Fail");
				ws.addCell(l);
			}
			Thread.sleep(1000);
		}
		
		wwb.write();
		wwb.close();
		Thread.sleep(3000);
	}
	
	 private boolean isElementPresent(WebDriver d,By by) 
	 {
		 d.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		 try {
		      d.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
	 }

	
	@BeforeMethod
	public void setUp()
	{
		// Launch browser
		d=new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "D:\\Driver Server\\chromedriver.exe");
		//d=new ChromeDriver();
		System.setProperty("webdriver.ie.driver", "D:\\Driver Server\\IEDriverServer.exe");
		//d=new InternetExplorerDriver();
		//Maximize browser 
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		d.manage().deleteAllCookies();
	}
	@AfterMethod
	public void tearDown()
	{
		//Close browser
		d.quit();
	}

}

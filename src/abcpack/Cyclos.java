package abcpack;

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

public class Cyclos {
	WebDriver d;
	@Test
	public void testLogin() throws Exception
	{
		// Load web page
		d.get("http://49.206.196.246:8080/Cyclos/do/login");
		assertEquals("USABANK-NY Branch",d.getTitle());
		//Enter user name
		d.findElement(By.id("cyclosUsername")).clear();
		d.findElement(By.id("cyclosUsername")).sendKeys("vcxbvcb");
		String uname=d.findElement(By.id("cyclosUsername")).getAttribute("value");
		//Enter password
		d.findElement(By.id("cyclosPassword")).clear();
		d.findElement(By.id("cyclosPassword")).sendKeys("dfdfxg");
		String pwd=d.findElement(By.id("cyclosPassword")).getAttribute("value");
		//Click on submit
		d.findElement(By.xpath("//input[@value='Submit']")).click();
		//Blank user name & Blank password
		if(uname.equals("") && pwd.equals(""))
		{
			Alert al=d.switchTo().alert();
			assertEquals("Login name is required\nPassword is required\n",al.getText());
			al.accept();
		}
		//Blank user name & valid/invalid password
		else if(uname.equals(""))
		{
			Alert al=d.switchTo().alert();
			assertEquals("Login name is required\n",al.getText());
			al.accept();
		}
		//Bank password & valid/invalid user name
		else if(pwd.equals(""))
		{
			Alert al=d.switchTo().alert();
			assertEquals("Password is required\n",al.getText());
			al.accept();
		}
		//Valid user name & Valid password
		else if(isElementPresent(d,By.xpath("//span[text()='Logout']")))
		{
			d.findElement(By.xpath("//span[text()='Logout']")).click();
			//Switch driver focus to alert
			Alert al=d.switchTo().alert();
			//Ok
			al.accept();
		}
		//Invalid user name & Invalid password
		else if(isElementPresent(d,By.id("btn")))
		{
			d.findElement(By.id("btn")).click();
		}
		Thread.sleep(4000);
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

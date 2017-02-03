package abcpack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTabDemo {
	WebDriver d;
	@Test
	public void testTab() throws Exception
	{
		// Load web page
		d.get("https://www.google.co.in");
		Assert.assertEquals("Google",d.getTitle());
		Actions a=new Actions(d);
		WebElement link1=d.findElement(By.linkText("Gmail"));
		a.moveToElement(link1).keyDown(Keys.CONTROL).click(link1).build().perform();
		Thread.sleep(2000);
		d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"\t");
		Thread.sleep(4000);
		d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"\t");
		Thread.sleep(2000);
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
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	@AfterMethod
	public void tearDown()
	{
		//Close browser
		d.quit();
	}


}

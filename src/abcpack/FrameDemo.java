package abcpack;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrameDemo {
	WebDriver d;
	@Test
	public void testFrame() throws Exception
	{
		//Load web page
		d.get("http://jqueryui.com/autocomplete/");
		//Verify page title
		assertEquals("Autocomplete | jQuery UI",d.getTitle());
		//Switch driver focus to frame
		d.switchTo().frame(d.findElement(By.className("demo-frame")));
		//Type text
		d.findElement(By.id("tags")).sendKeys("Selenium");
		//Switch driver focus outside frame
		d.switchTo().defaultContent();
		//Click on Button
		d.findElement(By.linkText("Button")).click();
		Thread.sleep(4000);
	}
	@Before
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
		d.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	@After
	public void tearDown()
	{
		//Close browser
		d.quit();
	}

}

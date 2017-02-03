package abcpack;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class SyncDemo {
	WebDriver d;
	@Test
	public void testSync() throws Exception
	{
		//Load web page
		d.get("https://www.google.co.in");
		//Verify page title
		assertEquals("Google",d.getTitle());
		//Type text
		d.findElement(By.id("lst-ib")).sendKeys("Selenium");
		//Click on search button
		d.findElement(By.name("btnG")).click();
		
		//Explicit Wait
		
		WebDriverWait wait=new WebDriverWait(d,60);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Downloads - Selenium")));
		
		//Click on Downloads - Selenium
		d.findElement(By.linkText("Downloads - Selenium")).click();
		//Click on Projects
		d.findElement(By.linkText("Projects")).click();
		Thread.sleep(4000);
	}
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws Exception
	{
		// Launch browser
		if(browser.equalsIgnoreCase("FF"))
		{
			d=new FirefoxDriver();
			//d = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),DesiredCapabilities.firefox());
		}
		else if(browser.equalsIgnoreCase("GC"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Driver Server\\chromedriver.exe");
			d=new ChromeDriver();
			//d = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),DesiredCapabilities.chrome());
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "D:\\Driver Server\\IEDriverServer.exe");
			d=new InternetExplorerDriver();
			//d = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),DesiredCapabilities.internetExplorer());
		}
		
		//Maximize browser 
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		d.manage().deleteAllCookies();
	}
	@AfterMethod
	public void tearDown()
	{
		//Close browser
		d.quit();
	}

}

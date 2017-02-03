package abcpack;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AutoCompleteDemo {
	WebDriver d;
	@Test
	public void testAutocomplete() throws Exception
	{
		// Load web page
		d.get("http://jqueryui.com/autocomplete/");
		Assert.assertEquals("Autocomplete | jQuery UI",d.getTitle());
		//Switch driver focus to frame
		d.switchTo().frame(0);
		//Type text
		d.findElement(By.id("tags")).sendKeys("a");
		List<WebElement> s=d.findElements(By.className("ui-menu-item"));
		System.out.println("No of suggestions:"+s.size());
		System.out.println("********* Suggestions are: **************");
		for(WebElement e:s)
		{
			System.out.println(e.getText());
		}
		if(s.size()>=3)
		{
			for(int i=0;i<3;i++)
			{
				d.findElement(By.id("tags")).sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(1000);
			}
			d.findElement(By.id("tags")).sendKeys(Keys.ENTER);
			
		}
	
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

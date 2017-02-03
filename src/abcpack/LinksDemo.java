package abcpack;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LinksDemo {
	WebDriver d;
	@Test
	public void testHistory() throws Exception
	{
		// Load web page
		d.get("http://docs.seleniumhq.org/");
		Assert.assertEquals("Selenium - Web Browser Automation",d.getTitle());
		List<WebElement> l=d.findElements(By.xpath("//li[starts-with(@id,'menu')]"));
		System.out.println("No of links:"+l.size());
		System.out.println("******* Links are *********");
		String links[]=new String[l.size()];
		int i=0;
		for(WebElement e:l)
		{
			System.out.println(e.getText());
			links[i]=e.getText();
			i++;
		}
		for(String t:links)
		{
			d.findElement(By.linkText(t)).click();
			if(d.getTitle().contains("404"))
			{
				System.out.println("Link:"+t+":is not working");
			}
			else
			{
				System.out.println("Link:"+t+":is working fine");
			}
		}
		Thread.sleep(2000);
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

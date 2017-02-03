package abcpack;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDemo {
	WebDriver d;
	@Before
	public void setUp()
	{
		// Launch browser
		//d=new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "D:\\Driver Server\\chromedriver.exe");
		//d=new ChromeDriver();
		System.setProperty("webdriver.ie.driver", "D:\\Driver Server\\IEDriverServer.exe");
		d=new InternetExplorerDriver();
		//Maximize browser 
		d.manage().window().maximize();
	}
	@Test
	public void testWebDemo() throws Exception
	{
		//Load web page
		d.get("https://www.facebook.com/");
		//Print page title
		//System.out.println("Page title is:"+d.getTitle());
		//Verify page title
		assertEquals("Facebook - Log In or Sign Up",d.getTitle());
		//Enter user name
		d.findElement(By.id("email")).sendKeys("Selenium");
		//Enter password
		d.findElement(By.id("pass")).sendKeys("Selenium");
		//Click on Login
		//System.out.println(d.findElement(By.id("u_0_m")).getAttribute("value"));
		d.findElement(By.id("u_0_m")).click();
		
		//Pause execution 4sec
		Thread.sleep(4000);
	}
	@After
	public void tearDown()
	{
		//Close browser
		d.quit();
	}

}

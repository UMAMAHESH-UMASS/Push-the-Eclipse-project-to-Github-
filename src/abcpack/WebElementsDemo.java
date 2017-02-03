package abcpack;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class WebElementsDemo {
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
	@After
	public void tearDown()
	{
		//Close browser
		d.quit();
	}
	@Test
	public void testWebElements() throws Exception
	{
		// Load web page
		d.get("http://book.theautomatedtester.co.uk/");
		//Type text
		d.findElement(By.id("q")).sendKeys("Selenium");
		//Click on Chapetr1
		d.findElement(By.linkText("Chapter1")).click();
		//Radio button
		WebElement radio=d.findElement(By.id("radiobutton"));
		if(radio.isSelected())
		{
			System.out.println("Radio button is already selected");
		}
		else
		{
			radio.click();
		}
		//Check box
		WebElement checkbox=d.findElement(By.name("selected(1234)"));
		if(checkbox.isSelected())
		{
			System.out.println("Check box is already selected");
		}
		else
		{
			checkbox.click();
		}
		//Drop Down
		Select dd=new Select(d.findElement(By.id("selecttype")));
		dd.selectByIndex(2);
		//System.out.println("Selected option is:"+dd.getFirstSelectedOption().getText());
		//Text area
		d.findElement(By.id("html5div")).clear();
		d.findElement(By.id("html5div")).sendKeys("Selenium\nAppium");
		//Pause 5sec
		Thread.sleep(5000);
	}

}

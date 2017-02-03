package abcpack;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.junit.Assert.*;

import org.junit.*;

public class BrowserSlider {
	WebDriver d;
	@Before
	public void startup(){
	   d=new FirefoxDriver();
	   d.manage().window().maximize();
	}
	@Test
	public void testBrowserSlider() throws InterruptedException{
	
    d.get("http://www.flipkart.com/");
      
       //Following is the code that scrolls through the page
         for (int n=0;n<10;n++)
         {
            ((RemoteWebDriver) d).executeScript("window.scrollBy(0,200)");
            Thread.sleep(1000);
         }
     }
@After
public void stop(){
    d.quit();
}
//for(int n=0;isElementPresent(d, By.linkText("Privacy");n++)
public boolean isElementPresent(WebDriver driver, By b){
    try{
        driver.findElement(b);
        return false;
    }catch(NoSuchElementException e){
        return true;
    }
}
}

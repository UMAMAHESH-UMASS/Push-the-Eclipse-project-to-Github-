package abcpack;

import static org.testng.Assert.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login_MySQL {
	WebDriver d;
	@Test
	public void testLogin() throws Exception
	{
		//Load the web page
		d.get("http://49.206.196.246:8080/Cyclos/do/login");
		assertEquals("USABANK-NY Branch",d.getTitle());
		//DB
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MySQL","root","password12");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from Login;");
			while (rs.next())
			{											
				String username = rs.getString("UserName");
				String upwd = rs.getString("Password");
				//Enter user name
				d.findElement(By.id("cyclosUsername")).clear();
				d.findElement(By.id("cyclosUsername")).sendKeys(username);
				String uname=d.findElement(By.id("cyclosUsername")).getAttribute("value");
				//Enter password
				d.findElement(By.id("cyclosPassword")).clear();
				d.findElement(By.id("cyclosPassword")).sendKeys(upwd);
				String password=d.findElement(By.id("cyclosPassword")).getAttribute("value");
				//Click on Submit
				d.findElement(By.xpath("//input[@value='Submit']")).click();
				//Blank user name & blank password
				if(uname.equals("") && password.equals(""))
				{
					Alert al=d.switchTo().alert();
					//System.out.println(al.getText());
					assertEquals("Login name is required\nPassword is required\n",al.getText());
					al.accept();
					String sql1="UPDATE Login SET STATUS='Fail' WHERE UserName='"+username+"';";
					PreparedStatement st1=con.prepareStatement(sql1);
					st1.executeUpdate();
				}
				//Blank user name & Invalid password
				else if(uname.equals(""))
				{
					Alert al=d.switchTo().alert();
					assertEquals("Login name is required\n",al.getText());
					al.accept();
					String sql1="UPDATE Login SET STATUS='Fail' WHERE UserName='"+username+"';";
					PreparedStatement st1=con.prepareStatement(sql1);
					st1.executeUpdate();
				}
				//Invalid user name & blank password
				else if(password.equals(""))
				{
					Alert al=d.switchTo().alert();
					assertEquals("Password is required\n",al.getText());
					al.accept();
					String sql1="UPDATE Login SET STATUS='Fail' WHERE UserName='"+username+"';";
					PreparedStatement st1=con.prepareStatement(sql1);
					st1.executeUpdate();
				}
				//Valid user name & valid password
				else if(isElementPresent(d, By.xpath("//span[text()='Logout']")))
				{
					//Click on Logout
					d.findElement(By.xpath("//span[text()='Logout']")).click();
					//Switch driver focus to confirmation
					Alert al=d.switchTo().alert();
					al.accept();
					String sql1="UPDATE Login SET STATUS='Pass' WHERE UserName='"+username+"';";
					PreparedStatement st1=con.prepareStatement(sql1);
					st1.executeUpdate();
				}
				//Invalid username & Invalid password
				else if(isElementPresent(d, By.id("btn")))
				{
					d.findElement(By.id("btn")).click();
					String sql1="UPDATE Login SET STATUS='Fail' WHERE UserName='"+username+"';";
					PreparedStatement st1=con.prepareStatement(sql1);
					st1.executeUpdate();
				}
				Thread.sleep(1000);
			}
			st.close();
			con.close();
		}
		catch(Exception ex)
		   {
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
		   } 
								
		Thread.sleep(4000);
	}
	public boolean isElementPresent(WebDriver driver, By by)
	{
	    d.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
		try{
	        driver.findElement(by);
	        return true;
	    }catch(NoSuchElementException e){
	        return false;
	    }
	}
		
	@BeforeMethod
	public void setUp()
	{
		//Launch browser
		d=new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium_Scripts_Dec15\\Lib\\chromedriver.exe");
		//d=new ChromeDriver();
		System.setProperty("webdriver.ie.driver", "F:\\Selenium_Scripts_Dec15\\Lib\\IEDriverServer.exe");
		//d=new InternetExplorerDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	}
	@AfterMethod
	public void tearDown()
	{
		//Close browser
		d.quit();
	}


}

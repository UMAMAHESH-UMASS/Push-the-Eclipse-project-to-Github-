package abcpack;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OR {
	WebDriver d;
	OR(WebDriver d)
	{
		this.d=d;
		PageFactory.initElements(d, this);
	}
	@FindBy(id="cyclosUsername") WebElement tfielduname;
	@FindBy(id="cyclosPassword") WebElement tfieldpwd;
	@FindBy(xpath="//input[@value='Submit']") WebElement btnsubmit;
	@FindBy(xpath="//span[text()='Logout']") WebElement btnlogout;
	
	//Method
	public void fillLogin(String uname,String password)
	{
		tfielduname.clear();
		tfielduname.sendKeys(uname);
		tfieldpwd.clear();
		tfieldpwd.sendKeys(password);
		btnsubmit.click();
	}
	public void clickOk()
	{
		Alert al=d.switchTo().alert();
		al.accept();
	}
	public void clickCancel()
	{
		Alert al=d.switchTo().alert();
		al.dismiss();
	}

}

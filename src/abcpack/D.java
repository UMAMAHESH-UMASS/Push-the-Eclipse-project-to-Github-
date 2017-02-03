package abcpack;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;


public class D {
	WebDriver d;
	
	@BeforeMethod
	public void Setup()
	{
		System.setProperty("webdriver.chrome.driver","D:\\123\\Lib\\chromedriver.exe");
		//d=new ChromeDriver();
		d=new FirefoxDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	}
	/*//@Test
	public void Links() throws Exception
	{
		d.get("http://jqueryui.com/");
		Assert.assertEquals("jQuery UI",d.getTitle());
		List<WebElement> Interactions_Links=d.findElements(By.xpath("//aside[1]/ul/li/a"));
		System.out.println("no of links are:"+Interactions_Links.size());
		System.out.println("********* Links are *********");
		String Links[]=new String[Interactions_Links.size()];
		int i=0;
		for(WebElement L:Interactions_Links)
		{
			System.out.println(L.getText());
			Links[i]=L.getText();
			i++;
		}
		for(String t:Links)
		{
			d.findElement(By.linkText(t)).click();
			
		}
		Thread.sleep(2000);
	}*/
	@Test
	public void Draggable() throws Exception
	{
		d.get("http://jqueryui.com/");
		Assert.assertEquals("jQuery UI",d.getTitle());
		Actions A=new Actions(d);
		WebElement Link_D=d.findElement(By.linkText("Draggable"));
		//A.moveToElement(Link_D).click(Link_D).build().perform();
		A.moveToElement(Link_D).keyDown(Keys.CONTROL).click(Link_D).build().perform();
		Thread.sleep(4000);
		d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"\t");
		//d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"\t");
		Thread.sleep(4000);
		//d.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"\t");
		//A.moveToElement(Link_D).click(Link_D).build().perform();
		//d.switchTo().frame(d.findElement(By.className("demo-frame")));
		d.switchTo().frame(0);
		WebElement Draggable=d.findElement(By.id("draggable"));
		A.dragAndDropBy(Draggable,100,0).build().perform();
		Thread.sleep(4000);
		//A.clickAndHold(Draggable).moveToElement(Draggable, 200, 150).perform();
		
	}
	//@Test
	public void DragAndDrop() throws Exception
	{
		d.get("http://jqueryui.com/");
		Assert.assertEquals("jQuery UI",d.getTitle());
		WebElement Dro=d.findElement(By.linkText("Droppable"));
		Actions A= new Actions(d);
		A.moveToElement(Dro).click(Dro).build().perform();
		Thread.sleep(4000);
		d.switchTo().frame(0);
		A.dragAndDrop(d.findElement(By.id("draggable")), d.findElement(By.id("droppable"))).perform();
		Thread.sleep(4000);
	}
	//@Test
	public void Sliders() throws Exception
	{
		d.get("http://jqueryui.com/");
		Assert.assertEquals("jQuery UI",d.getTitle());
		WebElement Slider=d.findElement(By.linkText("Slider"));
		Actions A= new Actions(d);
		A.moveToElement(Slider).click(Slider).build().perform();
		Thread.sleep(4000);
		d.switchTo().frame(0);
		//A.dragAndDropBy(d.findElement(By.xpath("//div[@id='slider']/span")), 200, 0).perform();
		Thread.sleep(4000);
		A.moveToElement(d.findElement(By.xpath("//div[@id='slider']/span")), 100, 0).perform();
		Thread.sleep(4000);
	}
	//@Test
	public void Resizable() throws Exception
	{
		d.get("http://jqueryui.com/");
		Assert.assertEquals("jQuery UI",d.getTitle());
		WebElement Link_R=d.findElement(By.linkText("Resizable"));
		Actions A= new Actions(d);
		A.moveToElement(Link_R).click(Link_R).build().perform();
		d.switchTo().frame(0);
		WebElement R=d.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		A.clickAndHold(R).moveToElement(R, 400,50).build().perform();
		Thread.sleep(4000);
		
	}
	//@Test
	public void Selectable() throws Exception
	{
		d.get("http://jqueryui.com/");
		Assert.assertEquals("jQuery UI",d.getTitle());
		WebElement Link_S=d.findElement(By.linkText("Selectable"));
		Actions A= new Actions(d);
		A.moveToElement(Link_S).click(Link_S).build().perform();
		d.switchTo().frame(0);
		List<WebElement> Items=d.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("no of Items are:"+Items.size());
		System.out.println("********* Items are *********");
		String I[]=new String[Items.size()];
		int i=0;
		for(WebElement L:Items)
		{
			System.out.println(L.getText());
			I[i]=L.getText();
			i++;
		}
		
		for(String N:I)
		{
			//Actions A=new Actions(d);
			d.findElement(By.xpath(N)).click();
			//A.moveToElement(d.findElement(By.xpath(N))).click();
			Thread.sleep(4000);
		}
	}
	//@Test
	public void Sortable() throws Exception
	{
		d.get("http://jqueryui.com/");
		Assert.assertEquals("jQuery UI",d.getTitle());
		WebElement Link_S=d.findElement(By.linkText("Sortable"));
		Actions A= new Actions(d);
		A.moveToElement(Link_S).click(Link_S).build().perform();
		d.switchTo().frame(0);
		WebElement Item=d.findElement(By.xpath("//ul[@id='sortable']/li[4]"));
		//A.dragAndDrop(d.findElement(By.xpath("//ul[@id='sortable']/li[4]/span")), d.findElement(By.xpath("//ul[@id='sortable']/li[1]/span")));
		//A.dragAndDropBy(d.findElement(By.xpath("//ul[@id='sortable']/li[4]")), 0, 100);
		A.moveToElement(Item, 0, 300).build().perform();
		//.clickAndHold(Item)
		Thread.sleep(4000);
	}
	//@Test
	public void Accordion() throws Exception
	{
		d.get("https://jqueryui.com/");
		Assert.assertEquals("jQuery UI",d.getTitle());
		Actions A=new Actions(d);
		A.moveToElement(d.findElement(By.linkText("Accordion"))).perform();
		d.findElement(By.linkText("Accordion")).click();
		d.switchTo().frame(0);
		List<WebElement> S=d.findElements(By.xpath("//h3[starts-with(@id,'ui-id')]"));
		System.out.println("the no of sections are:"+S.size());
		String Sections[]=new String[S.size()];
		/*int i=0;
		for(WebElement s:S)
		{
			System.out.println(s.getText());
			Sections[i]=s.getText();
			i++;
		}
		for(String s1:Sections)
		{
			d.findElement(By.(s1)).click();
			
			Thread.sleep(4000);
		}*/
		
	}
	//@Test
		public void Datepicker() throws Exception
		{
			d.get("https://jqueryui.com/");
			Assert.assertEquals("jQuery UI",d.getTitle());
			Actions A=new Actions(d);
			A.moveToElement(d.findElement(By.linkText("Datepicker"))).perform();
			d.findElement(By.linkText("Datepicker")).click();
			d.switchTo().frame(0);
			d.findElement(By.xpath("//input[@id='datepicker']")).click();
			/*d.findElement(By.linkText("26")).click();
			Thread.sleep(4000);
			WebElement date=d.findElement(By.xpath("//input[@id='datepicker']"));
			System.out.println("the text in the date box is:"+date.getAttribute("value"));*/
			List<WebElement> Tr_Collection=d.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr"));
			System.out.println("the no of rows in a table is :"+Tr_Collection.size());
			List<WebElement> Td_Collection=d.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));
			System.out.println("the no of coloumns in a table is :"+Td_Collection.size());
			System.out.println("the no of coloumns are:"+Td_Collection.size()/Tr_Collection.size());
			int row_num,Col_num;
			row_num=1;
			for(WebElement TR:Tr_Collection)
			{
				List<WebElement> td_Collection=TR.findElements(By.xpath("td"));
				//System.out.println("the no of coloumns in a row are:"+td_Collection.size());
				Col_num=1;
				for(WebElement TD:td_Collection)
				{
					System.out.println(" Row "+row_num+" ,Col "+Col_num+ " Text "+TD.getText());
					Col_num++;
				}
				row_num++;
			}
			Thread.sleep(4000);
		}
	
	@AfterMethod
	public void TearDown() 
	{
		//d.quit();
	}

}


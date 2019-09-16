package com.selenium.week4;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserCommands {
	public WebDriver driver;// Insets variable;

	@BeforeMethod
	public void beforeEachTest() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
		// 1.\\this is windows operating system.---->relative Path
		// 2./ this works any Operating system---------->Absolute Path
		driver = new ChromeDriver();// Assigning the driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//implicitlyWait:
		driver.manage().window().maximize();

	}

	@Test(enabled = false)
	public void basicBrowserSeleniumCommande() throws Exception {

		driver.get("http://www.walmart.com/");// go to website
		String pageTitle = driver.getTitle();
		System.out.println("webite title is: " + pageTitle);
		String expectedTitle = "Walmart.com | Save Money. Live Better.";
		String webURL = driver.getCurrentUrl();
		System.out.println("Website URL is: " + webURL);
		assertEquals(pageTitle, expectedTitle);// some how?

		driver.get("http://www.costco.com/");
		System.out.println("website titel is :" + driver.getTitle());
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
		Thread.sleep(2000);
		driver.navigate().refresh();

	}

	@Test(enabled = false)
	public void findAllTheLinks() {
		driver.get("http://www.costco.com/");
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));// webElement locaters

		// System.out.println("All links are: "+allLinks.size());
		int counter = 0;
		for (WebElement link : allLinks) {
			String linkTxt = link.getText().trim();

			if (linkTxt.length() > 0) {
				counter++;
				System.out.println("Link text is: [ " + linkTxt + "]");
			}
		}
		int totalLinkNumber = allLinks.size();
		System.out.println("Total link number is: " + totalLinkNumber);
		System.out.println("Total link number is: " + counter);
	}

	// a is link perchis?
	@Test
	public void webElementCommands() throws Exception {
		driver.get("https://www.mortgagecalculator.net/");

		// locate currency icon/symbol

		WebElement currencySymbleElem = driver.findElement(By.id("currency"));
		currencySymbleElem.click();
		Select select =new Select(currencySymbleElem); 
		 
		select.selectByValue("pound");
		Thread.sleep(3000);
		select.selectByIndex(0);
		
		/*WebElement euroSymbleElem = driver.findElement(By.xpath("//option[@value='pound']"));
		//System.out.println("eeuro text: " + euroSymbleElem.getText());
		euroSymbleElem.click();*/
		
		WebElement amountElem = driver.findElement(By.id("amount"));
		amountElem.clear();
		amountElem.sendKeys("450000");
		
		WebElement yearsElem= driver.findElement(By.id("amortizationYears"));
		yearsElem.clear();
		yearsElem.sendKeys("29");
		
		WebElement monthsElem= driver.findElement(By.id("amortizationMonths"));
		//monthsElem.clear();
		monthsElem.sendKeys("11");
		
		Select interstType =new Select(driver.findElement(By.id("interestType")));//sansitive
	    interstType.selectByVisibleText("Fixed");
	    
	    WebElement rateElem = driver.findElement(By.id("rate"));
	    rateElem.clear();
	    rateElem.sendKeys("3.84");
		
	    Select startMonthsElem =new Select(driver.findElement(By.id("startMonth")));
	   startMonthsElem.selectByVisibleText("10");
	  
	   Select startYearsElem =new Select(driver.findElement(By.id("startYear")));
	   startYearsElem.selectByVisibleText("2019");
	   
	   Select  paymentModeElem=new Select(driver.findElement(By.id("paymentMode")));
	   paymentModeElem.selectByVisibleText("Monthly");
	   
	   WebElement submitBtn =driver.findElement(By.id("button"));
	   submitBtn.click();
	
	   Thread.sleep(5000);
	   
	   WebElement monthlyPayment= driver.findElement(By.id("summaryMonthly"));
	  String monthlyPaymentAmount= monthlyPayment.getAttribute("value");
	  System.out.println("Monthly payment is : "+monthlyPaymentAmount);
	  
	  assertEquals(monthlyPaymentAmount, "$2,110.07");
	  
	   
	   
	   
	   
	}

	@Test(enabled = false) // random order?
	public void amazonTest() {
		// navigate to another website
		driver.navigate().to("http://www.amazon.com/");
		String pageTitle2 = driver.getTitle();
		System.out.println("webite title2 is: " + pageTitle2);
		String webURL2 = driver.getCurrentUrl();
		System.out.println("Website URL2 is: " + webURL2);
		assertEquals(webURL2, "https://www.amazon.com/");
	}

	@AfterMethod
	public void afterEachTest() throws Exception {
		Thread.sleep(5000);
		driver.close();// close the current browser window having focus. In case there is only one
						// browser open then calling driver.close() quits the whole browser session.
		driver.quit();

	}

}
//code error;
//

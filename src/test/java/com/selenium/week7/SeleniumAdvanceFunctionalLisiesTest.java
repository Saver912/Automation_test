package com.selenium.week7;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;


import org.testng.annotations.Test;

import com.selenium.library.Base;

public class SeleniumAdvanceFunctionalLisiesTest extends Base{
	

	@Test(priority=0)
	public void learningSeleniumAdvance1() {
		driver.get("https://www.usps.com/");
		// WebElement mailShipMenu = driver.findElement(By.partialLinkText("Mail
		// & Ship"));
		// Actions action = new Actions(driver);
		// action.moveToElement(mailShipMenu).build().perform();
		myLibrary.moveMouseToElement(By.partialLinkText("Mail & Ship"));
		myLibrary.highlightElement(By.partialLinkText("Mail & Ship"));

		myLibrary.customWait(3);

		// WebElement clickNShipElem =
		// driver.findElement(By.partialLinkText("Click-N-Ship"));
		// action.moveToElement(clickNShipElem).build().perform();
		myLibrary.moveMouseToElement(By.partialLinkText("Click-N-Ship"));
		myLibrary.highlightElement(By.partialLinkText("Click-N-Ship"));

		//myLibrary.captureScreenshot("usps", "");
		//myLibrary.captureScreenshot("usps", "c:/Frank_USPS/");
	}
	
	@Test(priority=1)
	public void costcoTest() {
		driver.get("https://www.costco.com/");
		String websiteTitle = driver.getTitle();
		assertEquals(websiteTitle, "Welcome to Costco Wholesale");		
	}
}













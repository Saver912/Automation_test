package com.week5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.library.Base;

public class LearningWebElementsTest extends Base {

	@Test(enabled = false)
	public void checkBoxRadioBtn() throws Exception {
		driver.get("http://www.coolfields.co.uk/2011/04/accessible-forms-checkboxes-and-radio-buttons/");
		myLibrary.handleCheckBox(true, By.id("pizza1"));
		myLibrary.customWait(2);
		myLibrary.handleCheckBox(true, By.id("pizza2"));
		myLibrary.customWait(2);
		myLibrary.handleCheckBox(true, By.id("pizza3"));
		myLibrary.customWait(2);
		/*
		 * WebElement pepElem =driver.findElement(By.id("pizza1")); boolean
		 * pebElemState=pepElem.isSelected();
		 * System.out.println("Peb checkbox state is: "+ pebElemState); pepElem.click();
		 * 
		 * pebElemState =pepElem.isSelected();
		 * System.out.println("After select Peb checkbox state is: "+ pebElemState);
		 * 
		 * WebElement actElem =driver.findElement(By.id("pizza2")); actElem.click();
		 * 
		 * WebElement exgElem =driver.findElement(By.id("pizza3")); exgElem.click();
		 */
	}


	@Test(enabled=true)
	public void radioButtnoTesting() throws Exception {
		driver.get("http://www.coolfields.co.uk/2011/04/accessible-forms-checkboxes-and-radio-buttons/");
		// String agee3css=driver.findElement(By.cssSelector("#content >
		// table:nth-child(22) > tbody > tr:nth-child(4) > td > label"));
		WebElement radioBtnElem = driver.findElement(By.cssSelector("#age3"));
		myLibrary.scrollToWebElement(radioBtnElem);
		driver.findElement(By.cssSelector("#age3")).click();
		// radioBtnElem.click();

	}

	// code error;
	//

}

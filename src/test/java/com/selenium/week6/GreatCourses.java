package com.selenium.week6;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.library.Base;

public class GreatCourses extends Base {
	@Test
	public void purchaseACourseTest() {
		driver.get("https://www.thegreatcourses.com/");
		           
		selectCoursesCategory("Science");
		selectAProductName("Our Night Sky");
		myLibrary.waitUntilElementPresence(By.cssSelector(""));
		

	}
	
	// below are helper method
	private void selectAProductName(String productName) {
		WebElement courseRegion=myLibrary.waitUntilElementPresence(By.className("category-products-container"));
		Assert.assertNotNull(courseRegion);
		
		List<WebElement> liElems= courseRegion.findElements(By.tagName("li"));
		for(WebElement li :liElems) {
			WebElement productNameElem =li.findElement(By.tagName("h2"));
			System.out.println("Product name: "+ productNameElem.getTagName());
			if(productNameElem.getText().contains(productName)) {
			productNameElem.click();
			break;
			
			
		}
	}
		
	}
	private void selectCoursesCategory(String catogoryCoursesName) {
		WebElement parentElem = driver.findElement(By.cssSelector(".itemslider-wrapper.itemslider-categories"));
		List<WebElement> catogoryCourses = parentElem.findElements(By.tagName("li"));

		for (WebElement li : catogoryCourses) {
			List<WebElement> imgElems = li.findElements(By.tagName("img"));
			String altText = imgElems.get(0).getAttribute("alt");
			System.out.println("Alt text is: " + altText);
			if (altText.contains(catogoryCoursesName)) {
				imgElems.get(0).click();
				break;

			}

		}
	}
}


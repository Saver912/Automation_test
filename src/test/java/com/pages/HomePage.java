package com.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.library.Base;


public class HomePage extends Base{
	public HomePage goToOnlineCourses() {
		driver.get("http://www.thegreatcourses.com");
		String webTitle=driver.getTitle();
		System.out.println("Web title is: "+webTitle);
		assertEquals(webTitle, "dfnoge");
		return this;
	}
	public HomePage science_Categories() {
		WebElement categoriesElem = driver.findElement(By.cssSelector(".itemslider-wrapper.itemslider-categories"));
		List<WebElement> courses = categoriesElem.findElements(By.tagName("a"));
		myLibrary.clickBtn((By) courses.get(1));			
		
		return this;
	}

}

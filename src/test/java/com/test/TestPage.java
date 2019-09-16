package com.test;

import org.testng.annotations.Test;

import com.pages.HomePage;
import com.selenium.library.Base;

public class TestPage extends Base{
	HomePage myHomePage =new HomePage();
	
	@Test
	public void test() {
		myHomePage.goToOnlineCourses();
		myHomePage.science_Categories();
	}

}

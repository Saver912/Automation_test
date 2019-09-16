package com.selenium.library;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.io.Files;

public class GlobalSeleniumLibrary {

	final static Logger logger = Logger.getLogger(GlobalSeleniumLibrary.class);
	private WebDriver driver;
	public boolean isDemoMode = true;

	public GlobalSeleniumLibrary(WebDriver _driver) {
		driver = _driver;

	}

	public WebDriver startChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		// 1.\\this is windows operating system.---->relative Path
		// 2./ this works any Operating system---------->Absolute Path
		driver = new ChromeDriver();// Assigning the driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		// implicitlyWait:
		driver.manage().window().maximize();
		return driver;
	}

	/*
	 * This is a custom hard coded wait using thrads.sleep
	 * 
	 * @param isSeconds
	 */
	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This scrolls browser view and make the element in the center
	 */

	public void scrollToWebElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		customWait(0.5);

	}

	/*
	 * This handles check-boxs and radio buttons.
	 * 
	 * @param is user isUserWantsToCheckTheBox
	 */
	public void handleCheckBox(boolean isUserWantsToCheckTheBox, By by) {
		WebElement checkBoxElem = driver.findElement(by);
		if (isUserWantsToCheckTheBox == true) {
			boolean checkBoxState = checkBoxElem.isSelected();
			if (checkBoxState == true) {

			} else {
				checkBoxElem.click();
			}
		} else {
			boolean checkBoxState = checkBoxElem.isSelected();
			if (checkBoxState == true) {

				if (checkBoxState == true) {
					checkBoxElem.click();
				} else {

				}
			}

		}
	}
	 @SuppressWarnings("deprecation")
	    public WebDriver startIEBrowser() {
	        System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
	        //driver = new InternetExplorerDriver();
	        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
	        cap.setCapability("ignoreProtectedModeSettings", true);
	        cap.setCapability("ie.ensureCleanSession", true);
	        
	        driver = new InternetExplorerDriver(cap);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	        return driver;
	    }
	    // Homework, please complete this method before 08/02/2019
	    public WebDriver startFirefoxBrowser() {
	        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
	        driver = new FirefoxDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	        return driver;
	    }


	public WebDriver startLocalBrowser(String browser) {
        if(browser.contains("chrome"))
        {
            driver = startChromeBrowser();
        }else if(browser.contains("firefox")){
            driver = startFirefoxBrowser();
        }else if(browser.contains("ie")){
            driver = startIEBrowser();
        }else {
            // default browser
            driver = startIEBrowser();
        }        
        return driver;
    }
	/*
	 * This is clickBtn, select,enterText
	 * 
	 * @Ater Abiba
	 * 
	 */

	public void clickBtn(By by) {
		WebElement BtnElm = driver.findElement(by);
		BtnElm.click();
	}

	/***
	 * This is a fluent wait, waits dynamically for a WebElement and polls the
	 * source html
	 * 
	 * @param by
	 * @return WebElement
	 */
	public WebElement fluentWait(final By by) {
		WebElement targetElem = null;
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		targetElem = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
		return targetElem;
	}

	public void selectDropDown(By by, String optionValue) {
		Select dropDown = new Select(driver.findElement(By.id("paymentMode")));
		dropDown.selectByVisibleText(optionValue);
	}

	public void selectDropDown(By by, int optionValueIndex) {
		Select dropDown = new Select(driver.findElement(By.id("paymentMode")));
		dropDown.selectByIndex(optionValueIndex);
	}

	public void enterText(By by, String text) {
		WebElement enterTextElem = driver.findElement(by);
		enterTextElem.clear();
		enterTextElem.sendKeys(text);
	}

	/***
	 * This is an explicitWait for element presence
	 * 
	 * @param by
	 * @return
	 */
	public WebElement waitUntilElementVisibility(By by) {
		WebElement dynamicElem = null;
		dynamicElem = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
		return dynamicElem;
	}

	public WebElement waitUntilElementPresence(By by) {
		WebElement dynamicElemnt = null;
		dynamicElemnt = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(by));
		return dynamicElemnt;
	}

	/***
	 * This is an explicitWait for element to be clickable
	 * 
	 * @param by
	 * @return
	 */
	public WebElement waitUntilElementClickable(By by) {
		WebElement dynamicElem = null;
		dynamicElem = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
		return dynamicElem;
	}

	/**
	 * This method moves mouse to the given element by the locator
	 *
	 * @param by
	 * @return
	 */
	public WebElement moveMouseToElement(By by) {
		WebElement element = null;
		try {
			element = driver.findElement(by);
			Actions action = new Actions(driver);
			action.moveToElement(element).click().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	/**
	 * This Method moves mouse to given web element
	 *
	 * @param elem
	 */
	public void moveMouseToElement(WebElement elem) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(elem).click().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @SuppressWarnings("deprecation")
	public WebElement highlightElement(By by) {
		WebElement element = null;
		if (isDemoMode) {
			element = driver.findElement(by);
			WrapsDriver wrappedElement = (WrapsDriver) element;
			JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
			for (int i = 0; i < 3; i++) {

				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: red; border: 2px solid yellow;");
				customWait(1);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

			}
		}
		return element;

	}

	public void highlightElement(WebElement element) {
		if (isDemoMode) {

			WrapsDriver wrappedElement = (WrapsDriver) element;
			JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
			for (int i = 0; i < 3; i++) {

				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: red; border: 2px solid yellow;");
				customWait(1);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

			}

		}

	}

	public String getCurrentTime() {
		String currentTime = null;
		Date date = new Date();
		logger.debug("Date: " + date);
		String tempTime = new Timestamp(date.getTime()).toString();
		currentTime = tempTime.replace(" ", "").replace("-", "").replace(":", "").replace(".", "");
		logger.debug("tempTime: " + tempTime);
		return currentTime;
	}

	public String captureScreenshot(String screenshotFileName, String filePath) {
		String screenshotFilePath = null;
		String timeStamp = getCurrentTime();
		try {
			if (filePath.isEmpty()) {
				checkDirectory("target/screenshots/");
				screenshotFilePath = "target/screenshots/" + screenshotFileName + timeStamp + ".png";
			} else {
				checkDirectory(filePath);
				screenshotFilePath = filePath + screenshotFileName + timeStamp + ".png";
			}

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File(screenshotFilePath));

		} catch (Exception e) {
			e.printStackTrace();
		}

		String finalPath = getAbsulatePath(screenshotFilePath);
		logger.info("Screenshot captured: " + finalPath);
		return finalPath;
	}

	private String getAbsulatePath(String filePath) {
		String finalPath = null;
		File file = new File(filePath);
		finalPath = file.getAbsolutePath();
		return finalPath;
	}

	private void checkDirectory(String inputPath) {
		File file = new File(inputPath);
		String abPath = file.getAbsolutePath();
		File file2 = new File(abPath);

		if (!file2.exists()) {
			if (file2.mkdirs()) {
				System.out.println("Directories are created...");
			} else {
				System.out.println("Directories not created...");
			}
		}
	}
}
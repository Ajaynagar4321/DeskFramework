package com.autodest.genericUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * contains Webdriver specific reusable utility
 * 
 * @author Deepak
 *
 */
public class WebDriverUtility {
	/**
	 * used to POLL for element while loading the DOM document
	 * 
	 * @param driver
	 */
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/**
	 * used to wait for expected element visibility in GUI
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitForElemnetVisibality(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * used to wait & click for expected element in GUI
	 * 
	 * @param element
	 * @throws Throwable
	 */
	public void waitAndClick(WebElement element) throws Throwable {
		int count = 0;
		while (count < 40) {
			try {
				element.click();
				break;
			} catch (Throwable e) {
				Thread.sleep(500);
				count++;
			}
		}
	}

	/**
	 * used to wait for expected element in GUI
	 * 
	 * @param element
	 * @throws Throwable
	 */
	public void waitforElement(WebElement element) throws Throwable {
		int count = 0;
		while (count < 40) {
			try {
				element.isDisplayed();
				break;
			} catch (Throwable e) {
				Thread.sleep(500);
				count++;
			}
		}
	}

	/**
	 * select the value from the dropDown based on Visible text
	 * 
	 * @param element
	 * @param options
	 */
	public void select(WebElement element, String options) {
		Select sel = new Select(element);
		sel.selectByVisibleText(options);
	}

	/**
	 * select the value from the dropDown based on index
	 * 
	 * @param element
	 * @param options
	 */
	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	/**
	 * used to switch to another browser window based on browser partial / complete
	 * title
	 * 
	 * @param driver
	 * @param browserTitle
	 */
	public void switchToBrowser(WebDriver driver, String browserTitle) {
		Set<String> set = driver.getWindowHandles();

		for (String act : set) {
			driver.switchTo().window(act);
			String actPageTile = driver.getTitle();
			if (actPageTile.contains(browserTitle)) {
				break;
			}
		}
	}

	/**
	 * used to Switch to Alert Popup & click on OK button
	 * 
	 * @param driver
	 */
	public void alerttOK(WebDriver driver) {

		driver.switchTo().alert().accept();
	}

	/**
	 * used to Switch to Alert Popup & click on cancel button
	 * 
	 * @param driver
	 */
	public void alerttCancel(WebDriver driver) {

		driver.switchTo().alert().dismiss();
	}

	/**
	 * used to take mouse cursor on expected element on the browser
	 * 
	 * @param driver
	 * @param element
	 */
	public void moveToExpectedElemnet(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	/**
	 * used to check the displayed message
	 * 
	 * @param driver
	 * @param msg
	 */

	public void checkDisplayMsg(WebDriver driver, String expectedMsg, WebElement element) {
		String actualMsg = element.getText();

		if (actualMsg.contains(expectedMsg)) {
			System.out.println("Contact succefully deleted");
		} else {
			System.out.println("contact not deleted");
		}
	}

}

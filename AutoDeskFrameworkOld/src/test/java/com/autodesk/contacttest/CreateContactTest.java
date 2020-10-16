package com.autodesk.contacttest;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.autodest.genericUtils.JavaUtility;
import com.autodest.genericUtils.WebDriverUtility;
/**
 *  Test script for Contact Module
 * @author Deepak
 *
 */
public class CreateContactTest {
	/*Create an Object Lib*/
	JavaUtility javaUtil = new JavaUtility();
	WebDriverUtility wlib = new WebDriverUtility();
	
	@Test
	public void createContactWithOrg() throws Throwable {


		/* read the test data */
		String orgName = "TestYantra-"+javaUtil.getRanDomData();   //TestYAntra-989
		String industry = "Energy";
		String lastName = "deepak";
		String orgamizationSearchOptions = "Organization Name";
		String leadSource = "Partner";
		
		
		
		/*step 1 : launch the browser*/
		WebDriver driver = new ChromeDriver();
		wlib.waitForPageToLoad(driver);
		driver.get("http://localhost:8888");
		
		/*step 2 : login to app*/
		  driver.findElement(By.name("user_name")).sendKeys("admin");
		  driver.findElement(By.name("user_password")).sendKeys("admin");
		  driver.findElement(By.id("submitButton")).click();
		  
		/*step 3 : navigate to Org module*/
		  driver.findElement(By.linkText("Organizations")).click();
		
		/*step 4 : create Organization*/
		  driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		  driver.findElement(By.name("accountname")).sendKeys(orgName); 
		  WebElement induWeb = driver.findElement(By.name("industry"));		  
	      wlib.select(induWeb, industry);	  
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();               //Save  TestYAntra-989
		  

		/*step 5 : navigate to Contact Module*/
		  WebElement elemnet = driver.findElement(By.linkText("Contacts"));
	     wlib.waitAndClick(elemnet);
		
		/*step 5 : click on create Contact Button*/
		  driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		  
		/*step 6 : Enter the lastName & click on Organization lookUp Image*/
		  driver.findElement(By.name("lastname")).sendKeys(lastName);
		  
		
		/*Step 7 : select the created Organization from new Window & click on Save*/
		  String expectedChildTile = "Accounts&action";
		  
		  driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
          wlib.switchToBrowser(driver, expectedChildTile);
		  
		  WebElement searchInWb = driver.findElement(By.name("search_field"));
	     wlib.select(searchInWb, orgamizationSearchOptions);
		  
		  driver.findElement(By.name("search_text")).sendKeys(orgName);
		  
		  driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		  String expectedParentTile = "Contacts";
		  //switch back to parent Window
		  wlib.switchToBrowser(driver, expectedParentTile);
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); 
		  
		/*Step 8 : verify */
		
		  
		/*step 9: logout */
		  WebElement ele = driver.findElement(By.xpath("//img[contains(@src,'user.PNG')]"));
		  wlib.moveToExpectedElemnet(driver, ele);
		  
		/*step 10: close the browser */
		  driver.close();
	}
	

}







package com.autodesk.contacttest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.autodest.genericUtils.JavaUtility;
import com.autodest.genericUtils.WebDriverUtility;

/**
 * Test script to delete contact details
 * 
 * @author yogesh
 *
 */
public class DeleteContactTest {

	int count = 3;
	/* Create an Object Lib */
	JavaUtility javaUtil = new JavaUtility();
	WebDriverUtility wlib = new WebDriverUtility();

	@Test
	public void createContactWithOrg() throws Throwable {

		/* read the test data */
		String orgName = "TestYantra-" + javaUtil.getRanDomData(); // TestYAntra-989
		String industry = "Energy";
		String lastName = "deepak";
		String orgamizationSearchOptions = "Organization Name";
		String leadSource = "Partner";

		/* step 1 : launch the browser */
		WebDriver driver = new ChromeDriver();
		wlib.waitForPageToLoad(driver);
		driver.get("http://localhost:8888");

		/* step 2 : login to app */
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("manager");
		driver.findElement(By.id("submitButton")).click();

		/* step 3 : navigate to Org module */
		driver.findElement(By.linkText("Organizations")).click();

		/* step 4 : create Organization */
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement induWeb = driver.findElement(By.name("industry"));
		wlib.select(induWeb, industry);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); // Save TestYAntra-989

		/* step 5 : navigate to Contact Module */
		WebElement editOrganisation = driver.findElement(By.xpath("(//input[@title='Edit [Alt+E]'])[1]"));
		wlib.waitForElemnetVisibality(driver, editOrganisation);
		driver.findElement(By.linkText("Contacts")).click();

		/* step 5 : click on create Contact Button */
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		/* step 6 : Enter the lastName & click on Organization lookUp Image */
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		/*
		 * Step 7 : select the created Organization from new Window & click on Save &
		 * store contactID
		 */
		String expectedChildTile = "Accounts&action";

		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
		wlib.switchToBrowser(driver, expectedChildTile);

		WebElement searchInWb = driver.findElement(By.name("search_field"));
		wlib.select(searchInWb, orgamizationSearchOptions);

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@name='search']")).click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		String expectedParentTile = "Contacts";
		// switch back to parent Window
		wlib.switchToBrowser(driver, expectedParentTile);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String contactID = driver.findElement(By.xpath("(//td[@class='dvtCellInfo'])[2]")).getText();

		/* Step 8 : Delete the created contact */
		WebElement editContact = driver.findElement(By.xpath("(//input[@title='Edit [Alt+E]'])[1]"));
		wlib.waitForElemnetVisibality(driver, editContact);
		driver.findElement(By.linkText("Contacts")).click();

		List<WebElement> actualContactID = driver
				.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[position()>1]/td[2]"));

		for (WebElement webElement : actualContactID) {

			if (webElement.getText().trim().equals(contactID.trim())) {
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[" + count + "]/td[1]")).click();

				break;
			}
			count++;
		}
		driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();
		wlib.alerttOK(driver);

		/* Step 9: Searching for deleted contact */
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(contactID);
		WebElement contactIN = driver.findElement(By.id("bas_searchfield"));
		wlib.select(contactIN, "Contact Id");
		driver.findElement(By.xpath("//input[@name='submit']")).click();

		WebElement contactSearchElement = driver.findElement(By.xpath("//td//span[@class='genHeaderSmall']"));
		wlib.checkDisplayMsg(driver, "No Contact Found !", contactSearchElement);

		/* Step 10: Searching for created organization Name */
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		WebElement organizationIN = driver.findElement(By.id("bas_searchfield"));
		wlib.select(organizationIN, "Organization Name");
		driver.findElement(By.xpath("//input[@name='submit']")).click();

		WebElement orgSearchOutput = driver.findElement(By.xpath("//table[@class='layerPopupTransport']/tbody/tr/td[contains(text(),'Records 1 - 1 of 1')]"));
		wlib.waitForElemnetVisibality(driver, orgSearchOutput);
		WebElement actualOrganizationName = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[position()>1]/td[3]/a"));

		if (actualOrganizationName.getText().trim().equals(orgName)) {
			System.out.println(orgName + " is present");
		}
		else {
			System.out.println(orgName + " is not  present");
		}
	}

	/*
	 * step 9: logout WebElement ele =
	 * driver.findElement(By.xpath("//img[contains(@src,'user.PNG')]"));
	 * wlib.moveToExpectedElemnet(driver, ele);
	 * 
	 * step 10: close the browser driver.close();
	 */
}

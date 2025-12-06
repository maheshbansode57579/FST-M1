package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity1 {
	// Driver object
	WebDriver driver;

	// Setup function
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		// Initialize driver
		driver = new FirefoxDriver();
		// Open the test page
		driver.get("http://alchemy.hguy.co/crm");
	}

	// Verify title
	@Test
	public void verifyPageTitle() {
		// Get the page title
		String pageTitle = driver.getTitle();
		// Assertion
		Assert.assertEquals(pageTitle, "SuiteCRM");
		// This line will execute only if assertion passes
		driver.quit();

	}

	// Teardown function
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}

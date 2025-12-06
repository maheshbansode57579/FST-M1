package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2 {
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

	// Print the url of the header image
	@Test
	public void headerImgUrlTest() {
		String headerImageUrl = driver.findElement(By.xpath("//img[@alt='SuiteCRM']")).getDomProperty("src");
		System.out.println("Header image URL: " + headerImageUrl);
	}

	// Teardown function
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}

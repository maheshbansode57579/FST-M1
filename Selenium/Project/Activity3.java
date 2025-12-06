package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity3 {
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

	// Print the first copyright text in the footer
	@Test
	public void copyRightTest() {
		String copyRightText = driver.findElement(By.xpath("//a[@id='admin_options']")).getText();
		System.out.println("First copy right text: " + copyRightText);
	}

	// Teardown function
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}

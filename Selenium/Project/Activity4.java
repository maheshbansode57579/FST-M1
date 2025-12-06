package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity4 {
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

	// Enter credentials and login
	@Test
	public void loginTest() {
		WebElement username = driver.findElement(By.id("user_name"));
		WebElement password = driver.findElement(By.id("username_password"));
		username.sendKeys("admin");
		password.sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		// Verify that the homepage has opened
		boolean homePagePresence = driver.findElement(By.cssSelector("a.with-home-icon")).isDisplayed();
		if(homePagePresence == true) {
			System.out.println("User is on the home page");
		}
		else {
			System.out.println("Home page not loaded");
		}
	}

	// Teardown function
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}

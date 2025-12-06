package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity6 {
	// Driver object
	WebDriver driver;
	// Declaring the wait object
	WebDriverWait wait;

	// Setup function
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		// Initialize driver
		driver = new FirefoxDriver();
		// Initialize the wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Open the test page
		driver.get("http://alchemy.hguy.co/crm");
	}

	// Enter credentials and login
	@Test(priority = 1)
	public void loginTest() {
		WebElement username = driver.findElement(By.id("user_name"));
		WebElement password = driver.findElement(By.id("username_password"));
		username.sendKeys("admin");
		password.sendKeys("pa$$w0rd");
		driver.findElement(By.xpath("//input[@name='Login']")).click();
	}

	// Make sure that the “Activities” menu item exists and is clickable
	@Test(dependsOnMethods = "loginTest")
	public void activitiesTest() {
		WebElement activitiesIcon = driver.findElement(By.id("grouptab_3"));
		try {
			WebElement aIcon = wait.until(ExpectedConditions.elementToBeClickable(activitiesIcon));
			aIcon.click();
			System.out.println("Activities icon is clicked");
		}
		catch(TimeoutException e) {
			System.out.println("Activities icon is not clickable");
		}
	}

	// Teardown function
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}

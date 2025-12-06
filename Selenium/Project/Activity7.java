package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity7 {
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

	// Reading additional information
	@Test(dependsOnMethods = "loginTest")
	public void informationTest() {
		//  Navigate to Sales -> Leads 
		driver.findElement(By.id("grouptab_0")).click();
		driver.findElement(By.xpath("//a[@id='grouptab_0']/following::a[contains(text(), 'Leads')][1]")).click();
		String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.module-title-text"))).getText();
		Assert.assertEquals(title, " LEADS");
		
		// Find and click the Additional information icon at the end of the row
		WebElement addtlInfo = driver.findElement(By.xpath("(//span[@title='Additional Details'])[1]"));
		addtlInfo.click();
		
		// Print the phone number
		WebElement phone = driver.findElement(By.xpath("//span[@class='phone']"));
		String phoneNumber = wait.until(ExpectedConditions.visibilityOf(phone)).getText();
		System.out.println("Phone number: " + phoneNumber);
	}

	// Teardown function
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Close the browser
		driver.quit();
	}

}

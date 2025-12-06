package project;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

public class Activity9 {
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

	// Open the accounts page and print the contents of the table
	@Test(dependsOnMethods = "loginTest")
	public void traverseTableTest() {
		// Navigate to Sales -> Leads
		driver.findElement(By.id("grouptab_0")).click();
		driver.findElement(By.xpath("//a[@id='grouptab_0']/following::a[contains(text(), 'Leads')][1]")).click();
		String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.module-title-text")))
				.getText();
		Assert.assertEquals(title, " LEADS");

		// Print the first 10 values in the ​ Name​ column and the ​ User​ column of the table
		List<WebElement> names = driver.findElements(By.xpath("//td[@field='name']//b//a"));
		List<WebElement> users = driver.findElements(By.xpath("//td[@field='assigned_user_name']//a"));
		List<String> tenNames = new ArrayList<>();
		List<String> tenUsers = new ArrayList<>();
		for (int i = 0; i < names.size(); i++) {
			tenNames.add(names.get(i).getText());
			tenUsers.add(users.get(i).getText());
			if (i == 9) {
				break;
			}
		}
		System.out.println("Names" + tenNames);
		System.out.println("Users" + tenUsers);
	}

	// Teardown function
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}

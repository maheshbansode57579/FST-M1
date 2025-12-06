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

public class Activity8 {
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
		// Navigate to Sales -> Accounts
		driver.findElement(By.id("grouptab_0")).click();
		driver.findElement(By.xpath("//a[@id='grouptab_0']/following::a[contains(text(), 'Accounts')][1]")).click();
		String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.module-title-text")))
				.getText();
		Assert.assertEquals(title, " ACCOUNTS");
		
		// Print the names of the first 5 odd-numbered rows of the table
		List<WebElement> names = driver.findElements(By.xpath("//td[@field='name']//b//a"));
		List<String> fiveNames = new ArrayList<>();
		for(int i=0; i<names.size(); i++) {
			if(i%2 == 0) {
				fiveNames.add(names.get(i).getText());
			}
			if(fiveNames.size()==5) {
				break;
			}
		}		
		System.out.println(fiveNames);
	}

	// Teardown function
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}

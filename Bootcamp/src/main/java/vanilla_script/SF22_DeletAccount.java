package vanilla_script;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF22_DeletAccount {

	public static void main(String[] args) throws InterruptedException {


		// Lunch  Chrome 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		//Load URL:

		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//	    1. Login to https://login.salesforce.com

		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();

		//		2. Click on toggle menu button from the left corner

		driver.findElementByClassName("slds-icon-waffle").click();

		//		3. Click view All and click Sales from App Launcher

		driver.findElementByXPath("//button[text()='View All']").click();
		driver.findElementByXPath("//p[text()='Sales']").click();

		//			4. Click on Opportunity tab (using java scrpit)

		WebElement element = driver.findElementByXPath("//span[text()='Accounts']");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		//	5. Search the account 'Your Name'
		String Acc_Name="test";
		System.out.println("Account which name"+Acc_Name+"need to be deleted");
		driver.findElementByXPath("//span[text()='Accounts']/following::input[@name='Account-search-input']").sendKeys(Acc_Name,Keys.ENTER);
		//	6. Click on  the Dropdown icon and Select Delete
		Thread.sleep(3000);
		driver.findElementByXPath("//a[contains(@class,'slds-button slds-button--icon-x-small')]").click();
		Thread.sleep(3000);

		driver.findElementByXPath("//a[@title='Delete']").click();

		driver.findElementByXPath("//span[text()='Delete']").click();
		System.out.println("Acccount is deleted successfully");
		Thread.sleep(5000);
		//			Expected Result:Account should be created Successfully
		String text =driver.findElementByXPath("//a[contains(@class,'slds-truncate outputLookupLink')]").getText();


		if (text.contains(Acc_Name))
		{
			System.out.println("Account is still avaible");

		}

		else {
			System.out.println("Account is not shown");
		}

		driver.close();

	}

}



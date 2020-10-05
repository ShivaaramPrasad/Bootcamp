package vanilla_script;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF20_CreateAccount {

	public static void main(String[] args) {



		// Lunch  Chrome 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		//Load URL:

		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//	    1. Login to https://login.salesforce.com

		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();


		//			2. Click on toggle menu button from the left corner

		driver.findElementByClassName("slds-icon-waffle").click();

		//			3. Click view All and click Sales from App Launcher

		driver.findElementByXPath("//button[text()='View All']").click();
		driver.findElementByXPath("//p[text()='Sales']").click();


		//			4. Click on Accounts tab (using java scrpit)

		WebElement element = driver.findElementByXPath("//span[text()='Accounts']");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		//			5. Click on New button

		driver.findElementByXPath("//div[text()='New']").click();

		//			6. Enter 'your name' as account name
		String Acc_Name="vignesh";
		System.out.println("Account Name used for Create Account : "+Acc_Name);
		driver.findElementByXPath("//span[text()='*']/following::input").sendKeys(Acc_Name);
		//			7. Select Ownership as Public         
		driver.findElementByXPath("(//a[@class='select'])[3]").click();

	    driver.findElementByXPath("(//a[text()='Public'])").click();

		//Select dd = new Select(dropdown1);
		//List<WebElement> Ownership = dd.getOptions();
		//int count = Ownership.size();
		//dd.selectByIndex(0);

		//			8. Click save and verify Account name 

		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
		WebElement element_two = driver.findElementByXPath("//span[text()='Accounts']");
		JavascriptExecutor executor_two = (JavascriptExecutor)driver;
		executor_two.executeScript("arguments[0].click();", element_two);

		driver.findElementByXPath("//span[text()='Accounts']/following::input[@name='Account-search-input']").sendKeys(Keys.ENTER,Acc_Name,Keys.ENTER);


		//			Expected Result:Account should be created Successfully
		String text =driver.findElementByXPath("//a[contains(@class,'slds-truncate outputLookupLink')]").getText();
		System.out.println("Account Created in the name of "+text);

		if (text.contains(Acc_Name))
		{
			System.out.println("Account is created  successfully ");

		}

		else {
			System.out.println("Account is not created successfully");
		}
		
	driver.close();

	}

}

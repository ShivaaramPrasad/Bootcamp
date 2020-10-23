package vanilla_script;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF96_CreateOpportunityinContent {

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

		//			2. Click on toggle menu button from the left corner

		driver.findElementByClassName("slds-icon-waffle").click();

		//			3. Click view All and click Sales from App Launcher

		driver.findElementByXPath("//button[text()='View All']").click();
		driver.findElementByXPath("//p[text()='Content']").click();

		//			4. Click on Content tab 

		//WebElement element = driver.findElementByXPath("//span[text()='Contacts']");
		//JavascriptExecutor executor = (JavascriptExecutor)driver;
		// executor.executeScript("arguments[0].click();", element);

		//			5. Click View All Key Deals in Key Deals
		driver.findElementByXPath("//span[text()='View All Key Deals']").click();
		//			6. Click the dropdown from Opportunities and select All Opportunities
		driver.findElementByXPath("//span[text()='Opportunities']/following-sibling::a").click();
		driver.findElementByXPath("//span[text()='All Opportunities']").click();
		//			7. Click on New
		Thread.sleep(5000);
		driver.findElementByXPath("//div[text()='New']").click();
		//			8. Give Opportunity Name as SRM Steels
		String Opportunity_Name="SRM Steels";
		driver.findElementByXPath("//div[contains(@class,'uiInput uiInputText')]//input").sendKeys(Opportunity_Name);
		//			9. Select Type as New Customer and Lead Source as Partner Referral
		Thread.sleep(5000);

		driver.findElementByXPath("(//a[@class='select'])[2]").click();
		driver.findElementByXPath("(//a[text()='New Customer'])").click();
		driver.findElementByXPath("(//a[@class='select'])[3]").click();
		driver.findElementByXPath("(//a[text()='Partner Referral'])").click();

		//			10. Give Amount as 75000 and Select Close Date as Next month 20th day 
		driver.findElementByXPath("//label[contains(@class,'label inputLabel')]/following-sibling::input").sendKeys("75000");
		
		driver.findElementByXPath("(//span[text()='Close Date'])[2]/following::input").sendKeys("11/20/2020");
		
		
			//			11. Select Stage as Needs Analysi
		driver.findElementByXPath("//a[@class='select']").click();
		driver.findElementByXPath("(//a[text()='Needs Analysis'])").click();

		//			12. Click in Primary Campaign  Source and Select first option
		driver.findElementByXPath("//span[text()='Primary Campaign Source']/following::input").click();
		driver.findElementByXPath("//div[contains(@class,'primaryLabel slds-truncate')]").click();
		//			13. Click Save and Verify the SRM Steels opportunity is created
		driver.findElementByXPath("//button[@title='Save']//span").click();
		Thread.sleep(2000);
		String opportunity_mes = driver.findElementByXPath("//span[text()='Opportunity']").getText();

		System.out.println(opportunity_mes);

		if (opportunity_mes.contains(Opportunity_Name))
		{
			System.out.println("Opportunity is created successfully ");

		}

		else {
			System.out.println("Opportunity is created is not created successfully");
		}

		driver.close();



	}

}

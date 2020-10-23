package vanilla_script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SFO89_CreateTaskForContent {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//Step 1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com/"); 
		driver.findElementById("Login").click(); 
	
		driver.findElementById("username").sendKeys("nupela@testleaf.com"); 
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click(); 
		
		//Step 2. Click on toggle menu button from the left corner
		driver.findElementByXPath("//button[contains(@class,'salesforceIdentityAppLauncherHeader')]").click();
		
		//Step 3. Click view All 
		driver.findElementByXPath("//lightning-button[@class='slds-button slds-p-horizontal--small']/button[text()='View All']").click();
		
		//Step 4. Click on Content tab 
		driver.findElementByXPath("//p[text()='Salesforce CRM Content']").click();
		
		//Step 5. Click View All from Today's Task
		driver.findElementByXPath("//span[text()='Today’s Tasks']/following::span[text()='View All']").click();
		
		//STep 6. Click on New Task
		driver.findElementByXPath("//a[contains(@class,'icon-border-filled')]").click();
		driver.findElementByXPath("//a[@title='New Task']").click();
		
		//Step 7. Select a Account Name in Assigned to field 
		driver.findElementByXPath("//input[@aria-describedby='taskCountLabelDiv']").click();
		driver.findElementByXPath("//input[@aria-describedby='taskCountLabelDiv']").sendKeys("integration");
		driver.findElementByXPath("//div[@title='Integration User']").click();
		
		//Step 8. Select a subject as Email
		driver.findElementByXPath("//label[text()='Subject']/following-sibling::div//input").click();
		driver.findElementByXPath("//span[@title='Email']").click();
		
		//Step 9. Set Priority as High and Status as In Progress
		driver.findElementByXPath("//span[text()='Priority']/../following-sibling::div//a").click();
		driver.findElementByXPath("//a[@title='High']").click();
		
		driver.findElementByXPath("(//h2[text()='New Task']/..//span[text()='Status']/../following-sibling::div//a").click();
		driver.findElementByXPath("//a[text()='In Progress']").click();
		
		//Step 10. Click on the image of Name field, click on Contacts and select Contact
		driver.findElementByXPath("//a[contains(@aria-label,'Name')]//img").click();
		driver.findElementByXPath("//span[@title='Contacts']").click();
		
		driver.findElementByXPath("//input[@title='Search Contacts']").click();
		driver.findElementByXPath("(//li[contains(@class,'lookup__item  default ui')]//div[contains(@class,'primaryLabel')])[1]").click();
		
		//Step 11. Click on the image of Related To field, click on Product and Select Product
		driver.findElementByXPath("//a[contains(@aria-label,'Related To')]//img").click();
		driver.findElementByXPath("//li[contains(@class,'entityMenuItemProduct')]//span[contains(@class,'truncate entityLabel')]").click();
		
		//Step 12. Click Save
		driver.findElementByXPath("//button[contains(@class,'brand uiButton forceActionButton')]/span").click();
		
		System.out.println(driver.findElementByXPath("//span[contains(@class,'toastMessage')]").getText());
		
		
		
			
	}

}

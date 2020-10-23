package vanilla_script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF02_TestCase {

	public static void main(String[] args) throws InterruptedException {
		
		
		// Lunch  Chrome 
		
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				WebDriverManager.chromedriver().setup();
				ChromeDriver driver = new ChromeDriver(options);
				driver.manage().window().maximize();

				//Load URL:

				driver.get("https://login.salesforce.com/");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				//        1) Launch the app
				driver.findElementById("username").sendKeys("nupela@testleaf.com");
				driver.findElementById("password").sendKeys("Bootcamp$123");
				driver.findElementById("Login").click();


				//			2. Click on toggle menu button from the left corner

				driver.findElementByClassName("slds-icon-waffle").click();

				//			3. Click view All and click Sales from App Launcher

				driver.findElementByXPath("//button[text()='View All']").click();

		
//			4. Click Service Console from App Launcher
				
				driver.findElementByXPath("//p[text()='Service Console']").click();

//			5. Select Home from the DropDown
				Thread.sleep(4000);
				driver.findElementByXPath("//button[@title='Show Navigation Menu']").click();
				
				driver.findElementByXPath("//a[@title='Home'][@role='option']").click();
				

//			6. Add CLOSED + OPEN values and result should set as the GOAL. If the result is less than 10000, then set the GOAL as 10000.
				String closedValue = driver.findElementByXPath("//span[text()='Closed'][@class='metricLabel']//following-sibling::span").getText();
				String openValue = driver.findElementByXPath("//span[contains(text(),'Open')][@class='metricLabel']//following-sibling::span").getText();
				String closedFinalValue=closedValue.replaceAll("[^0-9]", "");
				String openFinalValue=openValue.replaceAll("[^0-9]", "");
				String goalValue="";
				System.out.println("Goal Value Before: "+goalValue);
				if(Integer.parseInt(closedFinalValue)+Integer.parseInt(openFinalValue)<10000) {
					goalValue="10000";
				}else
				{
					int addValue=Integer.parseInt(closedFinalValue)+Integer.parseInt(openFinalValue);
					goalValue=Integer.toString(addValue);
				}
				System.out.println("Goal Value After: "+goalValue);
				driver.findElementByXPath("//button[@title='Edit Goal']").click();
				WebElement goalElement = driver.findElementByXPath("//input[@aria-describedby=\"currencyCode\"]");
				goalElement.clear();
				goalElement.sendKeys(goalValue);
				driver.findElementByXPath("//span[text()='Save']").click();

					
				//			7. Select Dashboards
				
				driver.findElementByXPath("//button[@title='Show Navigation Menu']").click();
				
				driver.findElementByXPath("//a[@title='Dashboards'][@role='option']").click();
				
//			8. Click on Private Dashboards
				driver.findElementByXPath("//a[text()='Private Dashboards'][@class='slds-nav-vertical__action']").click();
				//			9. Click on New Dashboard
				driver.findElementByXPath("//a[@title='New Dashboard']").click();
//			10. Enter the Dashboard name as "YourName_Bootcamp"
				String name="Shivaaram_Bootcamp";
				driver.findElementById("dashboardNameInput").sendKeys(name);

//			11. Enter Description as Testing and Click on Save
				driver.findElementById("dashboardDescriptionInput").sendKeys("Testing");
				driver.findElementByXPath("//button[text()='Create']").click();

//			12. Click on Done
				driver.findElementByXPath("//button[text()='Done']").click();
//			13. Click on Subscribe
				driver.findElementByXPath("//button[text()='Subscribe']").click();
//			14. Select Frequency as "Daily"
				driver.findElementByXPath("//span[text()='Daily']").click();
				
//			15. Time as 10:00 AM
				WebElement dropdown1 = driver.findElementById("time");
				Select dd = new Select(dropdown1);
				dd.selectByIndex(10);
//			16. Click on Save
				driver.findElementByXPath("//span[text()='Save']").click();
//			17. Verify "You started Dashboard Subscription" message displayed or not
				String Subscription = driver.findElementByXPath("//span[contains(@class,'toastMessage slds-text-heading--small')]").getText();
				if (Subscription.contains("Updated"))
				{
					System.out.println("You started Dashboard Subscription message displayed");

				}

				else {
					System.out.println("You are not started Dashboard Subscription ");
				}

//			18. Close the "YourName_Bootcamp" tab
				driver.findElementByXPath("//button[@title='Close Shivaaram_Bootcamp']//lightning-primitive-icon[1]");
//			19. Click on Private Dashboards
//			20. Verify the newly created Dashboard available
//			21. Click on dropdown for the item
//			22. Select Delete
//			23. Confirm the Delete
//			24. Verify the item is not available under Private Dashboard folder

	}

}

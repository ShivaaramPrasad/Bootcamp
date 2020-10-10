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
				Thread.sleep(10000);
				driver.findElementByXPath("//span[text()='Dashboards']/following::div").click();
				Thread.sleep(2000);
				driver.findElementByXPath("(//span[text()='Home'])[2]").click();
			//	Thread.sleep(2000);

//			6. Add CLOSED + OPEN values and result should set as the GOAL. If the result is less than 10000, then set the GOAL as 10000.
				String CLOSED = driver.findElementByXPath("//span[@class='metricAmount uiOutputText']").getText();
				System.out.println(CLOSED);
				
				String OPEN  = driver.findElementByXPath("(//span[@class='metricAmount uiOutputText'])[2]").getText();
				System.out.println(OPEN);
				int CLOSED_i=Integer.parseInt(CLOSED);  
				int OPEN_i=Integer.parseInt(OPEN);  
				int Result= OPEN_i+CLOSED_i;
				
				if (Result<10000)
				{
					int Goal=10000;
					System.out.println("Goal is seta as" +Goal);
				}
				else {
					System.out.println("Goal is not set");

				}
					
				//			7. Select Dashboards
				
		driver.findElementByXPath("//span[text()='Dashboards']").click();

//			8. Click on Private Dashboards
				driver.findElementByXPath("//a[text()='Private Dashboards']").click();
//			9. Click on New Dashboard
				driver.findElementByXPath("//select dashboards").click();
//			10. Enter the Dashboard name as "YourName_Bootcamp"
				driver.findElementById("dashboardNameInput").sendKeys("Shivaaram_Bootcamp");

//			11. Enter Description as Testing and Click on Save
				driver.findElementById("dashboardDescriptionInput").sendKeys("Testing");
//			12. Click on Done
				driver.findElementByXPath("//button[text()='Create']").click();
				driver.findElementByXPath("//button[text()='Done']").click();
//			13. Click on Subscribe
				driver.findElementByXPath("//button[text()='Subscribe']").click();
				driver.findElementByXPath("//span[text()='OK']").click();
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
//			19. Click on Private Dashboards
//			20. Verify the newly created Dashboard available
//			21. Click on dropdown for the item
//			22. Select Delete
//			23. Confirm the Delete
//			24. Verify the item is not available under Private Dashboard folder

	}

}

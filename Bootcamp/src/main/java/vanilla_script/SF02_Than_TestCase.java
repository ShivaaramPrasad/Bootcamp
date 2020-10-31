package vanilla_script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF02_Than_TestCase {

	public static void main(String[] args) throws InterruptedException {
		
	    /*    1. Login to https://login.salesforce.com
			2. Click on toggle menu button from the left corner
			3. Click view All 
			4. Click Service Console from App Launcher
			5. Select Home from the DropDown
			6. Add CLOSED + OPEN values and result should set as the GOAL. If the result is less than 10000, then set the GOAL as 10000.
			7. Select Dashboards
			8. Click on Private Dashboards
			9. Click on New Dashboard
			10. Enter the Dashboard name as "YourName_Bootcamp"
			11. Enter Description as Testing and Click on Save
			12. Click on Done
			13. Click on Subscribe
			14. Select Frequency as "Daily"
			15. Time as 10:00 AM
			16. Click on Save
			17. Verify "You started Dashboard Subscription" message displayed or not
			18. Close the "YourName_Bootcamp" tab
			19. Click on Private Dashboards
			20. Verify the newly created Dashboard available
			21. Click on dropdown for the item
			22. Select Delete
			23. Confirm the Delete
			24. Verify the item is not available under Private Dashboard folder */
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://login.salesforce.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Login
		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();
		//click tonggle
		driver.findElementByClassName("slds-icon-waffle").click();
		//clcik view all
		driver.findElementByXPath("//button[text()='View All']").click();

		//click Service Console from App launcher
		Thread.sleep(10000);
		driver.findElementByXPath("//p[text()='Service Console']").click();
		//select home from the dropdown
		Thread.sleep(20000);
		driver.findElementByXPath("//button[@title='Show Navigation Menu']//lightning-primitive-icon[1]").click();
		Thread.sleep(10000);
		driver.findElementByXPath("//span[text()='Home']").click();

		//Get the value of Closed 
		Thread.sleep(3000);
		String ClosedValue = driver.findElementByXPath("(//span[@class='metricAmount uiOutputText'])[1]").getText();
		System.out.println(ClosedValue);

		//Get the value of Open
		String OpenValue = driver.findElementByXPath("(//span[@class='metricAmount uiOutputText'])[2]").getText();
		System.out.println(OpenValue);

		//Converting ClosedValue string into number
		int closedvalueNumber = Integer.parseInt(ClosedValue.replaceAll("[^0-9]", ""));
		System.out.println(closedvalueNumber);

		//Converting ClosedValue string into number
		int OpenValueNumber = Integer.parseInt(OpenValue.replaceAll("[^0-9]", ""));
		System.out.println(OpenValueNumber);

		int closeandOpenValue = closedvalueNumber+OpenValueNumber;
		System.out.println(closeandOpenValue);
		//Click on Goal Edit Icon
		driver.findElementByXPath("//*[@data-key='edit']").click();
		System.out.println("Goal Edit Icon is clicked");

		if(closeandOpenValue > 10000)

		{
			driver.findElementByXPath("//div[@class='inputContainer']//input[@type='text']").clear();
			driver.findElementByXPath("//div[@class='inputContainer']//input[@type='text']").sendKeys(String.valueOf(closeandOpenValue));
		}
		else {
			driver.findElementByXPath("//div[@class='inputContainer']//input[@type='text']").clear();
			driver.findElementByXPath("//div[@class='inputContainer']//input[@type='text']").sendKeys("10000");
		}

		System.out.println("USD Goal value is typed successfully");
		//Click on Save Button
		driver.findElementByXPath("//span[contains(text(),'Save')]").click();
		System.out.println("Goal is Edited and Daved Successfully");

		//select dashboards
		Thread.sleep(10000);
		driver.findElementByXPath("//button[@title='Show Navigation Menu']//lightning-primitive-icon[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//a[@title='Dashboards']").click();
		//select private dashboards
		Thread.sleep(10000);
		driver.findElementByXPath("//a[text()='Private Dashboards']").click();
		//select new Dashboards
		Thread.sleep(10000);
		driver.findElementByXPath("//div[text()='New Dashboard']").click();
		//eneter dash board name as ur name
		Thread.sleep(10000);
		WebElement ele = driver.findElementByXPath("//iframe");
		driver.switchTo().frame(ele);
		Thread.sleep(2000);
		driver.findElementById("dashboardNameInput").sendKeys("Thanveer_Bootcamp");
		//enter description as testing
		driver.findElementById("dashboardDescriptionInput").sendKeys("Testing");
		//click on create
		driver.findElementByXPath("//button[text()='Create']").click();

		//	driver.switchTo().defaultContent();
		//click on done
		Thread.sleep(4000);
		WebElement elem = driver.findElementByXPath("//iframe");
		driver.switchTo().frame(elem);
		Thread.sleep(1000);
		driver.findElementByXPath("//button[text()='Done']").click();
		// driver.findElementByXPath("//button[text()='Done']").click();
		//click on subscribe
		Thread.sleep(2000);
		driver.findElementByXPath("//button[text()='Subscribe']").click();
		//click on daily
		Thread.sleep(20000);
		driver.switchTo().defaultContent();
		driver.findElementByXPath("//span[text()='Daily']").click();
		//select time as 10 Am by using select class
		Thread.sleep(5000);
		WebElement source = driver.findElementById("time");
		Select time = new Select(source);
		time.selectByVisibleText("10:00 AM");
		//select save
		Thread.sleep(10000);
		driver.findElementByXPath("//span[text()='Save']").click();
		//verify "You started Dashboard Subscription" message displayed or not
		WebDriverWait wait = new WebDriverWait(driver, 20);

		String text = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='You started a dashboard subscription.']"))).getText();
		if (text.contains("You started a dashboard")) {
			System.out.println("subscription msg displayed");

		}else {
			System.out.println("not displayed");
		}
		//close your name Bootcamp
		Thread.sleep(5000);
		driver.findElementByXPath("//*[@data-key='close']/..").click();
		//select new Dashboards
		Thread.sleep(20000);
		driver.findElementByXPath("//button[@title='Show Navigation Menu']//lightning-primitive-icon[1]").click();
		Thread.sleep(10000);
		driver.findElementByXPath("(//span[text()='Dashboards'])[2]").click();
		//select private dashboards
		Thread.sleep(10000);
		driver.findElementByXPath("//a[text()='Private Dashboards']").click();
		Thread.sleep(10000);
		String text3 = driver.findElementByXPath("((//table)[1]//a)[10]").getText();
		System.out.println(text3);
		//Click your name dropdwn icon
		Thread.sleep(10000);
		driver.findElementByXPath("//table//tbody//tr[2]//td//button/*").click();
		//clik delete
		Thread.sleep(5000);
		driver.findElementByXPath("//span[text()='Delete']").click();
		//confirm delete
		Thread.sleep(5000);
		driver.findElementByXPath("(//span[text()='Delete'])[2]").click();
		Thread.sleep(5000);
		for (int i = 1; i <=2; i++) {
			String text2 = driver.findElementByXPath("(//table//tbody//tr//th//a[1])["+i+"]").getText();
			System.out.println(text2);

			if (text2.contains("Thanveer")) {
				System.out.println("Dashboard name was not deleted");

			}else {
				System.out.println("Dashboard name was  deleted");

			}
		}

	}}
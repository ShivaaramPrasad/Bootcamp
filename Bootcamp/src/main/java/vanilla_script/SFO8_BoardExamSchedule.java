package vanilla_script;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SFO8_BoardExamSchedule {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*	JIRA TEST CASE	-	SFO8
		 * 1. Launch Salesforce application https://login.salesforce.com/
		 * 2. Login with username as ""hari.radhakrishnan@testleaf.com"" and password as ""India@123""
		 * 3. Click on Learn More link in Mobile Publisher
		 * 4. Navigate to the Salesforce Customer Service
		 * 5. Mouse hover on Support & Services
		 * 6. Click on Certifications link
		 * 7. Navigate to Certification - Administrator Overview window
		 * 8. Select Salesforce Architect
		 * 9. Click on More Details link for Technical Architect Certification
		 * 10. Navigate to Architect Review Board window
		 * 11. Verify Location and Dates for Review Board Exam Schedule for next month with only status as Planning as unique
		 * 
		 * Expected output:
		 * Each schedule should have unique location
		 */


		// disable Chrome notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		// Launch  Chrome Web Browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);

		// Maximise browser window
		driver.manage().window().maximize();			

		// 1. Launch Salesforce application https://login.salesforce.com/
		driver.get("https://login.salesforce.com/");

		// Implicit wait - wait for login page to load
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


		// 2. Login with username as ""hari.radhakrishnan@testleaf.com"" and password as ""India@123"" 	
		driver.findElementById("username").sendKeys("puppetter@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();

		// Implicit wait - wait for login credentials to validate and load landing page


		// 3. Click on Learn More link in Mobile Publisher
		driver.findElementByXPath("//span[text()='Learn More']").click();


		//	4. Navigate to the Salesforce Customer Service
		String parentWindow = driver.getWindowHandle();
		Set<String> winHandle =  driver.getWindowHandles();
		for(String windowHandle  : winHandle)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
			}
		}


		//	5. Mouse hover on Support & Services
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		WebElement menu = driver.findElementById("support-service_menu_item");
		actions.moveToElement(menu);


		//	6. Click on Certifications link
		WebElement subMenu = driver.findElementByXPath("//*[@id='drawer_support-service']/div/div/div[2]/ul/li/div/ul/li[4]/a");
		actions.moveToElement(subMenu);
		actions.click().build().perform();


		//	7. Navigate to Certification - Administrator Overview window
		String parentWindows = driver.getWindowHandle();
		Set<String> winHandles =  driver.getWindowHandles();
		for(String windowHandles  : winHandles)
		{
			if(!windowHandles.equals(parentWindows))
			{
				driver.switchTo().window(windowHandles);
			}
		}


		// 8. Select Salesforce Architect
		driver.findElementByPartialLinkText("Architect").click();

		// 9. Click on More Details link for Technical Architect Certification
		driver.findElementByLinkText("Technical Architect").click();   

		// 10. Navigate to Architect Review Board window
		String technicalArchitectReview = driver.findElementByXPath("//div[text()='Technical Architect Review Boards']").getText();
		System.out.println(technicalArchitectReview);

		// 11. Verify Location and Dates for Review Board Exam Schedule for next month with only status as Planning as unique

		/* Get schedule and month in map
		        For loop and add matching values.
		        Use Map concept

		        date range key, location as a value

		       Count how many values are present
		       FindElements.size();

		       For loop (based on number of items){

		       Index based Location XPath - 1st one , 2nd one, 3rd one
		       details.put( dateRange.getText(),location.getText();

		       Location and key identifier

		       }*/

		//Map <String, String> details = new LinkedHashMap 
		Map<String, Integer> fullDetails = new HashMap<String, Integer>();
		int rowcount = driver.findElementsByXPath("//table[@class='cs-review-boards-table']/tbody[1]/tr").size();
		int columcount = driver.findElementsByXPath("//table[@class='cs-review-boards-table']/tbody[1]/tr[1]/*").size();
		System.out.println("Number of rows: "+rowcount);

		System.out.println("Number of rows: "+columcount);

		for (int i = 2; i < rowcount; i++) {
			for (int j = 1; j < columcount; j++) {

				// System.out.print(driver.findElementByXPath("//table[@class='cs-review-boards-table']/tbody[1]/tr["+i+"]/td["+j+"]").getText()+"|");
				String text = driver.findElementByXPath("//table[@class='cs-review-boards-table']/tbody[1]/tr["+i+"]/td["+j+"]").getText();
				String alltext=text+"|";
				System.out.print(alltext);
				//System.out.print(text+"|"+text2+"|");
			}

			System.out.println();

		}
		for (int k = 2; k < rowcount; k++) {
			for (int l = 1; l < columcount; l++) {


		String  text2= driver.findElementByXPath("//table[@class='cs-review-boards-table']/tbody[1]/tr["+k+"]/td[3]/div[1]").getText();
		System.out.print(text2);

			}
			System.out.println();

			}
	}



}



package vanilla_script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF16_CertificationsSortOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Lunch  Chrome 
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				WebDriverManager.chromedriver().setup();
				ChromeDriver driver = new ChromeDriver(options);
				driver.manage().window().maximize();

				//Load URL:

				driver.get("https://login.salesforce.com/");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			    //1.Launch Salesforce application https://login.salesforce.com/
				
				driver.findElementById("username").sendKeys("nupela@testleaf.com");
				driver.findElementById("password").sendKeys("Bootcamp$123");
				driver.findElementById("Login").click();

			// 3. Click on the sliding icon until "See System Status" is displayed
				
				
			// 4. Click on Get Started link
				
			// 5. Navigate to SalesForce Trust new Window
				
			// 6. Select Trust Compliance from the dropdown
				
			// 7.  Change the Sort order by "Sort alphabetically"
				
			// 8. Verify the Services are displayed in alphabetical order

	}

}

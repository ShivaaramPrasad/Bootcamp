package vanilla_script;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF10_AdminCert {

	public static void main(String[] args) {
					
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			
			// Launch the browser first
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver(options);	
			
			//1. Launch Salesforce application https://login.salesforce.com/
			 driver.get("https://login.salesforce.com");
				
				// Implicit wait
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				// To maximise the browser
				driver.manage().window().maximize();
				
				
				//2. Login with username as "hari.radhakrishnan@testleaf.com" and password as "India@123"
				driver.findElementById("username").sendKeys("nupela@testleaf.com");
				
				driver.findElementById("password").sendKeys("Bootcamp$123");
				
				driver.findElementById("Login").click();
			//	3. Click on Learn More link in Mobile Publisher
				driver.findElementByXPath("//span[text()='Learn More']").click();
			//	4. Navigate to the Salesforce Customer Service
				String parentWindow = driver.getWindowHandle();
				Set<String> handle =  driver.getWindowHandles();
				   for(String windowHandle  : handle)
				       {
				       if(!windowHandle.equals(parentWindow))
				          {
				          driver.switchTo().window(windowHandle);
				          }
				       }
			//	5. Mouse hover on Support & Services
				   
				   Actions actions = new Actions(driver);
				   WebElement menu = driver.findElementById("support-service_menu_item");
				   actions.moveToElement(menu);

				  
			//	6. Click on Ceritifications link
				   WebElement subMenu = driver.findElementByXPath("//*[@id=\"drawer_support-service\"]/div/div/div[2]/ul/li/div/ul/li[4]/a");
				   actions.moveToElement(subMenu);
				   actions.click().build().perform();
			//	7. Navigate to Certification - Administrator Overview window
				   String parentWindows = driver.getWindowHandle();
					Set<String> handles =  driver.getWindowHandles();
					   for(String windowHandles  : handles)
					       {
					       if(!windowHandles.equals(parentWindows))
					          {
					          driver.switchTo().window(windowHandles);
					          }
					       }
			//	8. Verify the Certifications available for Administrator
					   List<WebElement> path = driver.findElementsByXPath("//div[@class='cs-card tile']/div[3]/a");
				         for (WebElement cell: path) {
					          System.out.println(cell.getText());
						
				        }

		}

	}

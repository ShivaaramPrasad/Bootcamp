package vanilla_script;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SFO_08_Sri_BoardExamSchedule {

	public static void main(String[] args) {

		// 1. Launch Salesforce application https://login.salesforce.com/
				WebDriverManager.chromedriver().setup();
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				
				ChromeDriver driver = new ChromeDriver(options);
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				
				driver.get("https://login.salesforce.com/");

				// 2. Login with username as nupela@testleaf.com / Bootcamp$123
				//Enter the Username
				driver.findElementByXPath("//input[@id='username']").sendKeys("nupela@testleaf.com");

				//Enter the Password
				driver.findElementById("password").sendKeys("Bootcamp$123");

				//Click Login Button 
				driver.findElementById("Login").click();
				
				// 3. Click on Learn More link in Mobile Publisher
				driver.findElementByXPath("//span[text()='Learn More']").click();
				System.out.println(driver.getTitle());
				// 4. Navigate to the Salesforce Customer Service
				String parentWindow = driver.getWindowHandle();
				Set<String> childWindows = driver.getWindowHandles();
				
				for(String child : childWindows) {
					if(!parentWindow.equalsIgnoreCase(child)) {
						driver.switchTo().window(child);
					}
				}
				
				
				// 5. Mouse hover on Support & Services
				WebElement eleSupportService = driver.findElementByXPath("//span[text()='Support & Services']");
				Actions act = new Actions(driver);
				act.moveToElement(eleSupportService).perform();		
				
				// 6. Click on Certifications link
				WebElement eleCertification = driver.findElementByXPath("//span[text()='Certification ']/..");
				act.click(eleCertification).build().perform();
				System.out.println(driver.getTitle());
				
				// 7. Navigate to Certifications window
				String parentWindow1 = driver.getWindowHandle();
				Set<String> childWindows1 = driver.getWindowHandles();
				for(String child1 : childWindows1) {
					if(!child1.equalsIgnoreCase(parentWindow1)) {
						driver.switchTo().window(child1);
					}
				}
				System.out.println(driver.getTitle());
				
				// 8. Select Salesforce Architect
				driver.findElementByXPath("//div[text()='Salesforce Architect']").click();
				
				// 9. Click on More Details link for Technical Architect Certification
				driver.findElementByXPath("//a[text()='Technical Architect']//..//preceding-sibling::div/child::a").click();
				
				// 10. Navigate to Architect Review Board window
				String parentWindow2 = driver.getWindowHandle();
				Set<String> childWindows2 = driver.getWindowHandles();
				
				for(String child2 : childWindows2) {
					if(!child2.equalsIgnoreCase(parentWindow2)) {
						driver.switchTo().window(child2);
					}
				}
				System.out.println(driver.getTitle());
				
				// 11. Verify Location and Dates for Review Board Exam Schedule for next month with only status as Planning as unique
				
				List<WebElement> listLocations = driver.findElementsByXPath("//div[contains(@class,'cs-exam-table__line')]/div[2]");
				List<WebElement> listDates = driver.findElementsByXPath("//div[contains(@class,'cs-exam-table__line')]/div[4]");
				List<WebElement> listStatus = driver.findElementsByXPath("//div[contains(@class,'cs-exam-table__line')]/div[6]");
				
				for(int i=0;i<listLocations.size();i++) {
					if(listStatus.get(i).getText().equalsIgnoreCase("Planning") && listDates.get(i).getText().contains("Nov")) {
						System.out.println("Location: "+listLocations.get(i).getText()+ " Dates Available: "+listDates.get(i).getText()+ " Status: "+listStatus.get(i).getText());
					}
				}

			}

		}

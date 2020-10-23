package vanilla_script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF16_CertificationsSortOrder {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("To Invok on Chrome Browser type - 1, Firefoxd-Browser - 2, IE or Edge -3 ");
		Scanner scan = new  Scanner(System.in);
		String Browser_num= scan.nextLine();
		int Browser=Integer.parseInt(Browser_num);

		switch (Browser) {
		case 1:

			System.out.println("User Option is 1, So Invoking Chrome Browser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

			break;

		case 2:
			System.out.println("User Option is 2, So Invoking FF Browser");
			FirefoxOptions options_firfox = new FirefoxOptions();
			options_firfox.setProfile(new FirefoxProfile());
			options_firfox.addPreference("dom.webnotifications.enabled", false);
	        WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options_firfox);

			break;

		case 3:
			System.out.println("User Option is 3, So Invoking Edge Browser");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
			break;
	

		}

				driver.manage().window().maximize();

				//Load URL:

				driver.get("https://login.salesforce.com/");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			    //1.Launch Salesforce application https://login.salesforce.com/
				
				driver.findElement(By.id("username")).sendKeys("nupela@testleaf.com");
				driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
				driver.findElement(By.id("Login")).click();

			// 3. Click on the sliding icon until "See System Status" is displayed
				
				List<WebElement> num_scroll = driver.findElements(By.xpath("//li[@data-aura-class='uiCarouselPageIndicatorItem']//a"));
				int num =num_scroll.size();
				System.out.println("Number of Scroll "+num);

				int x=0;

				while ( x <num) {

					boolean displayed = driver.findElement(By.xpath("//span[text()='See System Status']")).isDisplayed(); 

					if (!displayed) {


						WebDriverWait wait = new WebDriverWait(driver,5);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rightScroll']//button[1]")));
						driver.findElement(By.xpath("//div[@class='rightScroll']//button[1]")).click();
						System.out.println(displayed+" element is not visble ");

						x++;

					} 
					else {

						System.out.println(displayed+" element is visble ");
						break;
					}

				}

			// 4. Click on Get Started link
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//span[text()='See System Status']/following::span)[2]")).click();
			// 5. Navigate to SalesForce Trust new Window
				String parentWindow = driver.getWindowHandle();
				Set<String> handle =  driver.getWindowHandles();
				for(String windowHandle  : handle)
				{
					if(!windowHandle.equals(parentWindow))
					{
						driver.switchTo().window(windowHandle);
					}
				}


			// 6. Select Trust Compliance from the dropdown
				driver.findElement(By.linkText("see here")).click();
			// 7.  Change the Sort order by "Sort alphabetically"
				String parentWindow_two = driver.getWindowHandle();
				Set<String> handle_two =  driver.getWindowHandles();
				for(String windowHandle_two  : handle_two)
				{
					if(!windowHandle_two.equals(parentWindow))
					{
						driver.switchTo().window(windowHandle_two);
					}
				}

				
			// 8. Verify the Services are displayed in alphabetical order					
					
					List<WebElement> services = driver.findElements(By.xpath("//h2[contains(@class,'slds-p-top--x-small')]"));
					System.out.println("List of certification ");
					
					List<String> a = new ArrayList<String>();

					for (WebElement webElement : services) {
						String services_name = webElement.getText();
						a.add(services_name);

					}
					System.out.println(a); 
					
					
					List<String> sort_services = new ArrayList<>(a);
					
					for (String services_list : sort_services) {
						sort_services.add(services_list);
						Collections.sort(sort_services);

					}
					System.out.println("Services are displayed in alphabetical order");

					System.out.println(sort_services); 


	
					

}
}

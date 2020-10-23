package vanilla_script;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF13_VerifyApp_StoreLoadedToDownload_SalesForce {

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
		//		3. Click on the sliding icon until "Download SalesForceA" is displayed

		List<WebElement> num_scroll = driver.findElementsByXPath("//li[@data-aura-class='uiCarouselPageIndicatorItem']//a");
		int num =num_scroll.size();
		System.out.println("Number of Scroll "+num);

		int x=0;

		while ( x <=num) {

			boolean displayed = driver.findElementByXPath("//span[text()='App Store']").isDisplayed(); 


			if (!displayed) {
				
				System.out.println(displayed+" element is not visble ");
				
				WebDriverWait wait = new WebDriverWait(driver,5);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rightScroll']//button[1]")));
				driver.findElementByXPath("//div[@class='rightScroll']//button[1]").click();

				x++;

			} 
			else {
				
				System.out.println(displayed+" element is visble ");
                break;
			}

		}


		//		4. Click on App Strore link
		Thread.sleep(5000);
		driver.findElementByXPath("//span[text()='App Store']").click();

		//		5. Navigate to the new window

		String parentWindow = driver.getWindowHandle();
		Set<String> handle =  driver.getWindowHandles();
		for(String windowHandle  : handle)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
			}
		}

		//		6. Get the link displayed and click confirm

		String genrated_url = driver.findElementByClassName("warning").getText();

		System.out.println("Genrated Url "+genrated_url);
		String genrated_id=genrated_url.substring(46, 55);
		System.out.println("Genrated Sesssion ID "+genrated_id);

		driver.findElementByLinkText("Confirm").click();

		//		7. Verify url https://apps.apple.com/us/app/salesforcea/<id> loaded 
		String launch_url = driver.getCurrentUrl();
		String launch_id=launch_url.replaceAll("\\D","");
		System.out.println("Launced Url "+launch_url);
		System.out.println("Launced Sesssion ID "+launch_id);
		if (launch_id.equals(genrated_id)) {

			System.out.println("URL is same and verifed  successfully ");

		} else {
			System.out.println("URL is not same and need be verifed  ");} 

	}
}



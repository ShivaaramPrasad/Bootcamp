package bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF05_ToggleMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Lunch  Chrome 
		
	
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//Load URL:
			
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Login in Salesforce 
		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();
		
		// Click on toggle menu button from the left Corner
		
		driver.findElementByClassName("slds-icon-waffle").click();
		
		// Click view All and click Sales from App Launcher 
		
		driver.findElementByXPath("//button[text()='View All']").click();
		
		

		 
		
	}

}

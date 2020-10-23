package vanilla_script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001_CreateNewEvent {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(options);
		
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

//		Authentication
		driver.findElementById("username").sendKeys("dhivya@testleaf.com");
		driver.findElementById("password").sendKeys("India@123");
		driver.findElementById("Login").click();

		Thread.sleep(20000);
		
//		Click SVG Icon
		WebElement svg = driver.findElementByXPath("//div[@class='slds-global-header__item']//li[4]//a");
		svg.sendKeys(Keys.ENTER);
		
//		Click New Event and maximize it
		Actions builder = new Actions(driver);
		builder.click(driver.findElementByXPath("(//span[text()='New Event'])[1]")).perform();
		Thread.sleep(2000);
		builder.click(driver.findElement(By.xpath("//button[@title='Maximize']"))).perform();
		
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
      String text = driver.findElementByXPath("//span[text()=' was created.']").getText();
      
      System.out.println(text);
		
      driver.close();
		
	}

	}



package vanilla_script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC09_CreateNewSales {

	public static void main(String[] args) throws InterruptedException {
		
		

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

//		Authentication
		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();

		driver.findElement(By.className("slds-r5")).click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		WebElement element = driver.findElementByXPath("//span[text()='Calendar']");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		Thread.sleep(5000);
		
        driver.findElement(By.xpath("(//ul[@class='eventListContainer'])[3]")).click();
				
		Thread.sleep(5000);

		driver.findElement(By.xpath("//textarea[@role='textbox']")).sendKeys("Specimen");
		
		driver.findElementByXPath("//label[text()='Subject']/following::input").sendKeys("Email",Keys.ENTER);
		
		driver.findElement(By.xpath("(//span[@class=' label bBody'])[3]")).click();
		
		Thread.sleep(2000);

		String email = driver.findElement(By.xpath("//span[text()='Event']")).getText();
		
		System.out.println(email);
		
		
		
		

		

	}

}

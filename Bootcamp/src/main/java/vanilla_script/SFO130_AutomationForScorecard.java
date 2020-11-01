package vanilla_script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SFO130_AutomationForScorecard {


	public static void main(String[] args) throws InterruptedException {

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

		driver.findElement(By.className("slds-icon-waffle")).click();

		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Scorecards");
		Thread.sleep(3000);
		WebElement elemente = driver.findElementByXPath("//mark[text()='Scorecards']");
		JavascriptExecutor element = (JavascriptExecutor)driver;
		element.executeScript("arguments[0].click();", elemente);

		driver.findElement(By.xpath("//div[@title='New']")).click();

		Thread.sleep(3000);
		String scoreName="Shivaaram";
		driver.findElement(By.xpath("//span[text()='*']/following::input")).sendKeys(scoreName);



		driver.findElement(By.tagName("textarea")).sendKeys("Assessment for an individual employee");


		driver.findElement(By.xpath("//button[@title='Save']")).click();


		Thread.sleep(3000);

		String success_message = driver.findElement(By.xpath("//span[text()='Scorecard']")).getText();
		System.out.println(success_message);

		if (success_message.contains(scoreName))
		{
			System.out.println("Account is created  successfully ");

		}

		else {
			System.out.println("Account is not created successfully");
		}
		Thread.sleep(5000);


		driver.findElement(By.xpath("(//div[@title='Clone'])")).click();

		driver.findElement(By.xpath("//button[@title='Save']")).click();


		Thread.sleep(3000);

		String Clone_success_message = driver.findElement(By.xpath("//span[text()='Scorecard']")).getText();
		System.out.println(Clone_success_message);
		if (Clone_success_message.contains(Clone_success_message))
		{
			System.out.println("Account is created  successfully ");

		}

		else {
			System.out.println("Account is not created successfully");
		}


		Thread.sleep(3000);



		driver.findElement(By.xpath("(//div[@title='Delete'])")).click();



		driver.findElement(By.xpath("(//div[@title='Delete'])")).click();

		Thread.sleep(3000);

		String messagefordeleted = driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small')]")).getText();
		System.out.println("Account Deleteed in the name of "+messagefordeleted);

		if (messagefordeleted.contains(scoreName))
		{
			System.out.println("Account is Deleteed  successfully ");

		}

		else {
			System.out.println("Account is not Deleteed successfully");
		}



		driver.close();


	}

}

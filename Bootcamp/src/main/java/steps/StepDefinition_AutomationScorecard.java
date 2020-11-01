package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinition_AutomationScorecard {

	
		public  WebDriver driver;
	
		@Given("Launch Browser") 
		public void lunch_browser_chrome() {
			System.out.println("User Option is 1, So Invoking Chrome Browser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);		
			driver.manage().window().maximize();
			driver.get("https://login.salesforce.com/");
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		}
	
		@And("Login with the credentials username (.*)$")
		public  void Login_with_the_credentials_username (String userName ) {
			driver.findElement(By.id("username")).sendKeys(userName);
	
		}
		
		@And("Login with the credentials password (.*)$")
		public  void Login_with_the_credentials_password (String password ) {
			driver.findElement(By.id("password")).sendKeys(password);
	
		}
		/*@And("Login with the credentials username ")
		public  void Login_with_the_credentials_username () {
			driver.findElement(By.id("username")).sendKeys("nupela@testleaf.com");
	
		}
		
		@And("Login with the credentials password ")
		public  void Login_with_the_credentials_password ( ) {
			driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
	
		}*/
	
		@Then("Click Login")
		public void click_login() {
	
			driver.findElement(By.id("Login")).click();
	
		}
	
	
		@When("Click on the App Launcher Icon left to Setup")
		public void click_on_the_app_launcher_icon_left_to_setup() {
			driver.findElement(By.className("slds-icon-waffle")).click();
	
	
		}
	
		@Then("Click on View All")
		public void click_on_view_all() throws InterruptedException {
			driver.findElement(By.xpath("//button[text()='View All']")).click();
	
			Thread.sleep(3000);
	
		}
	
		@When("Click on Scorecards")
		public void click_on_scorecards() throws InterruptedException {
			driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Scorecards");
			Thread.sleep(3000);
			WebElement elemente = driver.findElement(By.xpath("//mark[text()='Scorecards']"));
			JavascriptExecutor element = (JavascriptExecutor)driver;
			element.executeScript("arguments[0].click();", elemente);
	
	
		}
	
		@And("Click on New")
		public void click_on_new() throws InterruptedException {
			driver.findElement(By.xpath("//div[@title='New']")).click();
			Thread.sleep(3000);
	
	
		}
	
		@And("Enter Individual Assessment for Name (.*)$")
		public void Enter_Individual_Assessment_for_Name(String scoreName ) throws InterruptedException {
	
			driver.findElement(By.xpath("//span[text()='*']/following::input")).sendKeys(scoreName);
			Thread.sleep(5000);
		}
	
		@And("Enter the Description as (.*)$")
		public void enter_the_description_as(String Description) throws InterruptedException {
	
			driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys(Description);
			Thread.sleep(5000);
	
		}
	
		@When("Click on Saves")
		public void Click_on_Save( ) throws InterruptedException {
	
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Thread.sleep(5000);
	
		}
	
		@Then("Verify the success message (.*)$")
		public void Verify_the_success_message( String scoreName) throws InterruptedException {
			String success_message = driver.findElement(By.xpath("//span[text()='Scorecard']")).getText();
			System.out.println(success_message);
			if (success_message.contains(scoreName))
			{
				System.out.println("Scorecard is created  successfully ");
	
			}
	
			else {
				System.out.println("Scorecard is not created successfully");
			}
			
			Thread.sleep(5000);
	
	
		}
		@And("Click on Clone")
		public void Click_on_Clone( ) throws InterruptedException {
	
			driver.findElement(By.xpath("(//div[@title='Clone'])")).click();
			Thread.sleep(5000);
	
		}
	
		@And("Click on CloneSave")
		public void Click_on_CloneSave( ) {
	
			driver.findElement(By.xpath("//button[@title='Save']")).click();
	
		}
	
		@Then("Verify the Clone success message (.*)$")
		public void Verify_the_Clone_success_message(String scoreName ) throws InterruptedException {
	
			String Clone_success_message = driver.findElement(By.xpath("//span[text()='Scorecard']")).getText();
			System.out.println(Clone_success_message);
			if (Clone_success_message.contains(scoreName))
			{
				System.out.println("Scorecard is Cloned  successfully ");
	
			}
	
			else {
				System.out.println("Scorecard is not Cloned successfully");
			}
			
			Thread.sleep(5000);
	
	
		}
	
	
	
		@And("Click on Delete")
		public void Click_on_Delete() throws InterruptedException {
			
			WebElement elemente = driver.findElement(By.xpath("//div[text()='Delete']"));
			JavascriptExecutor element = (JavascriptExecutor)driver;
			element.executeScript("arguments[0].click();", elemente);
	
			Thread.sleep(5000);
	
		}
	
		@And("Click on DeleteAgain")
		public void Click_on_DeleteAgain() throws InterruptedException {
			driver.findElement(By.xpath("//span[text()='Delete']")).click();
	
		}
		@Then("Verify the success message for deleted")
		public void Verify_the_success_message_for_deleted() throws InterruptedException {
			
			String messagefordeleted = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
			System.out.println(messagefordeleted);
			
			if (messagefordeleted.contains("deleted"))
			{
				System.out.println("Scorecard is deleted  successfully ");
	
			}
	
			else {
				System.out.println("Scorecard is not deleted successfully");
			}
			Thread.sleep(5000);
	
		}
	
		@And("close Browser")
		public void close_browser() {
			driver.close();
		}
	
	
	}

	
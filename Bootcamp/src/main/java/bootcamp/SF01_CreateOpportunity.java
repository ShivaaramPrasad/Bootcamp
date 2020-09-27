package bootcamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF01_CreateOpportunity {

	public static void main(String[] args) throws InterruptedException {
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

		//	    1. Login to https://login.salesforce.com
		
		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();

		//		2. Click on toggle menu button from the left corner

		driver.findElementByClassName("slds-icon-waffle").click();

		//		3. Click view All and click Sales from App Launcher

		driver.findElementByXPath("//button[text()='View All']").click();
		driver.findElementByXPath("//p[text()='Sales']").click();

		//			4. Click on Opportunity tab (using java scrpit)
			
		WebElement element = driver.findElementByXPath("//span[text()='Opportunities']");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		//			5. Click on New button

		driver.findElementByXPath("//div[text()='New']").click();
		
		//			6. Choose Close date as Tomorrow Date
		//driver.findElementByXPath("(//span[text()='Close Date'])[2]/following::input").sendKeys("9/28/2020");

			//Extract Tomorrow Date 
		
		LocalDate date = LocalDate.now();
		int dayOfMonth = date.getDayOfMonth();
		int tomorrowDate = dayOfMonth+1;
		System.out.println(tomorrowDate);

		//Click Date Picker using Javascript executor 
		WebElement datePicker = driver.findElementByXPath("//span[contains(text(),'Date Picker')]");
		executor.executeScript("arguments[0].click()", datePicker);
		
		//Select the tomorrow date using Action class 
		WebElement dateValue = driver.findElementByXPath("//tr[@class=\"calRow\"]/td/span[contains(text(),'"+tomorrowDate+"')]");
		Actions builder = new Actions(driver);
		builder.moveToElement(dateValue).click().build().perform();

   		//			7. Click on save 
		
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
		//			8. Verify the Alert message (Complete this field) displayed for Name and Stage
		
		String error_mes = driver.findElementByXPath("//ul[@class='errorsList']//li[1]").getText();
		
		System.out.println(error_mes);
		
if (error_mes.contains("Opportunity Name, Stage"))
{
	System.out.println("Alert message displayed for Name and Stage ");

}

else {
	System.out.println("Alert message is not triggred ");
}
	}

}

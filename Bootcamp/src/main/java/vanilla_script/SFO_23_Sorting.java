package vanilla_script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SFO_23_Sorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1. Login to https://login.salesforce.com
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://login.salesforce.com/");

		//Enter the Username
		driver.findElementByXPath("//input[@id='username']").sendKeys("nupela@testleaf.com");

		//Enter the Password
		driver.findElementById("password").sendKeys("Bootcamp$123");

		//Click Login Button 
		driver.findElementById("Login").click();
		
		// 2. Click on toggle menu button from the left corner
		driver.findElementByClassName("slds-icon-waffle").click();
		
		// 3. Click view All and click Sales from App Launcher
		driver.findElementByXPath("//button[contains(text(),'View All')]").click();
		driver.findElementByXPath("//p[text()='Sales']").click();
		
		// 4. Click on Accounts tab 
		WebElement eleAccounts = driver.findElementByXPath("//a[@title='Accounts']");
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(eleAccounts));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", eleAccounts);
		
		
		
		// 5. Click sort arrow in the Account Name to sort in ascending order
		//Thread.sleep(5000);
		List<WebElement> listAccNames = driver.findElementsByXPath("//a[@data-aura-class=\"forceOutputLookup\"][@title!='']");
		List<String> accNames = new ArrayList<>();
		System.out.println("**************Before Sorting*******************");
		for(int i=0;i<listAccNames.size();i++) {
			System.out.println(listAccNames.get(i).getAttribute("title"));
			accNames.add(listAccNames.get(i).getAttribute("title"));
		}
		System.out.println();
		Collections.sort(accNames);
		System.out.println("**************After Sorting***********************");
		for(String sort : accNames) {
			System.out.println(sort);
		}
		
		driver.findElementByXPath("//span[text()='Account Name']/..").click();
	}

}

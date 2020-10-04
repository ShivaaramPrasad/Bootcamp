package vanilla_script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF23_SortAccount {

	public static void main(String[] args) throws InterruptedException {


		//3. Click view All and click Sales from App Launcher
		//4. Click on Accounts tab 
		//5. Click sort arrow in the Account Name to sort in ascending order
		//Expected Result:All the accounts should be displayed ascending order by Account Name


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

		//		2. Click on toggle menu button from the left corner

		driver.findElementByClassName("slds-icon-waffle").click();

		//		3. Click view All and click Sales from App Launcher

		driver.findElementByXPath("//button[text()='View All']").click();
		driver.findElementByXPath("//p[text()='Sales']").click();

		//			4. Click on Opportunity tab (using java scrpit)

		WebElement element = driver.findElementByXPath("//span[text()='Accounts']");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		//5. Click sort arrow in the Account Name to sort in ascending order


		//Expected Result:All the accounts should be displayed ascending order by Account Name

		List<WebElement> account_names = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr/th/span");
		int list_size= account_names.size();
		System.out.println("Number of Accounts "+list_size);
		System.out.println("Manual sorted Account Name ");

		List<String> m = new ArrayList<String>();

		for (WebElement webElement : account_names) {
			String name = webElement.getText();
			m.add(name);
			Collections.sort(m);

		}

		System.out.println(m);

		Thread.sleep(5000);

		driver.findElementByXPath("//a[contains(@class,'toggle slds-th__action')]").click();

		Thread.sleep(5000);

		List<WebElement> account_names_sorted = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr/th/span");
		System.out.println("Web sorted Account Name ");
		
		List<String> a = new ArrayList<String>();

		for (WebElement webElement_sort : account_names_sorted) {
			String namesorted = webElement_sort.getText();
			a.add(namesorted);

		}
		System.out.println(a); 
		
		if (m.equals(a)) 
            System.out.println("Manual sort and Web sort Accounts are Equal"); 
        else
            System.out.println("Manual sort and Web sort Accounts are not Equal"); 
		

	}
}

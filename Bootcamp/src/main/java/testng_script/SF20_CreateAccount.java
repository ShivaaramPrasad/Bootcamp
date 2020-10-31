package testng_script;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF20_CreateAccount {


		public  WebDriver driver;
		public  String fileName="SFO_data";
		public  String url="https://login.salesforce.com/";

		@Test(dataProvider = "fetchData")
		public void testcase(String userName,String password, String Acc_Name ) throws InterruptedException{
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
				System.out.println("User Option is 3, So Invoking IE Browser");
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();

				break;
			}

			//Load URL:
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//	    1. Login to https://login.salesforce.com

		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();


		//			2. Click on toggle menu button from the left corner

		driver.findElement(By.className("slds-icon-waffle")).click();

		//			3. Click view All and click Sales from App Launcher

		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();


		//			4. Click on Accounts tab (using java scrpit)

		WebElement element = driver.findElement(By.xpath("//span[text()='Accounts']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		//			5. Click on New button

		driver.findElement(By.xpath("//div[text()='New']")).click();

		//			6. Enter 'your name' as account name
		System.out.println("Account Name used for Create Account : "+Acc_Name);
		driver.findElement(By.xpath("//span[text()='*']/following::input")).sendKeys(Acc_Name);
		//			7. Select Ownership as Public         
		driver.findElement(By.xpath("(//a[@class='select'])[3]")).click();

	    driver.findElement(By.xpath("(//a[text()='Public'])")).click();

		//Select dd = new Select(dropdown1);
		//List<WebElement> Ownership = dd.getOptions();
		//int count = Ownership.size();
		//dd.selectByIndex(0);

		//			8. Click save and verify Account name 

		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		
		WebElement element_two = driver.findElement(By.xpath("//span[text()='Accounts']"));
		JavascriptExecutor executor_two = (JavascriptExecutor)driver;
		executor_two.executeScript("arguments[0].click();", element_two);

		driver.findElement(By.xpath("//span[text()='Accounts']/following::input[@name='Account-search-input']")).sendKeys(Keys.ENTER,Acc_Name,Keys.ENTER);


		//			Expected Result:Account should be created Successfully
		String text =driver.findElement(By.xpath("//a[contains(@class,'slds-truncate outputLookupLink')]")).getText();
		System.out.println("Account Created in the name of "+text);

		if (text.contains(Acc_Name))
		{
			System.out.println("Account is created  successfully ");

		}

		else {
			System.out.println("Account is not created successfully");
		}
		
	     driver.close();

	}
		@DataProvider(name = "fetchData")

		public String[][] sendData() throws IOException{

			ReadExcelData read= new ReadExcelData();
			String[][] data = read.readData(fileName);

			return data;

		}
}

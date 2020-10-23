package vanilla_script;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SFO104_ProcessBuilder {
	static WebDriver driver;


	public static void main(String[] args) throws InterruptedException {

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
			System.out.println("User Option is 3, So Invoking Edge Browser");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

			break;


		}


		driver.manage().window().maximize();

		//Load URL:

		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//1.Launch Salesforce application https://login.salesforce.com/

		driver.findElement(By.id("username")).sendKeys("nupela@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();


		//			2) Click on Create button on the right and select Workflow Process
		driver.findElement(By.xpath("//span[text()='Create']")).click();
		driver.findElement(By.xpath("//span[text()='Workflow Process']")).click();
		//			3) Click on New Button
		int size = driver.findElements(By.xpath("//iframe[@title='accessibility title']")).size();
		System.out.println("How many frames "+size);
		driver.switchTo().frame(0); 

		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='New']")));
		driver.findElement(By.xpath("//span[text()='New']")).click();
		//			4) Give Process Name as "Create Account"
		String processName="Create Account";
		driver.findElement(By.id("processName")).sendKeys(processName);
		driver.findElement(By.id("apiName")).click();

		//			5) Select "A record changes" under The process starts when

		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//select[contains(@class,'select uiInput')]")));

		WebElement theProcessStarts =driver.findElement(By.xpath("//select[contains(@class,'select uiInput')]")); 
		Select value = new Select(theProcessStarts);
		value.selectByVisibleText("A record changes");		

		//			6) click on save button
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[text()='Save']")).click();
		driver.switchTo().defaultContent();

		//			7) click on Add object
		int size_one = driver.findElements(By.xpath("//iframe[@title='accessibility title']")).size();
		System.out.println("How many frames "+size_one);
		driver.switchTo().frame(0); 

		driver.findElement(By.xpath("//span[@title='Add Object']")).click();

		//			8) Select Object as Account
		driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("Account",Keys.ENTER);
		driver.findElement(By.linkText("Account")).click();	

		//			9) Click on Save button
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		// driver.switchTo().defaultContent();

		//			10) Click on Add Criteria and  Enter criteria Name as Always
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Add Criteria']//div")).click();
		driver.findElement(By.xpath("(//input[contains(@class,'input uiInput')])[2]")).sendKeys("Always");

		//			11) select Criteria for Executing Actions as "No criteria—just execute the actions!"
		driver.findElement(By.xpath("//span[text()='No criteria—just execute the actions!']/parent::label")).click();

		//			12) click on Save button
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();

		//			13) click on Add Action under immediate Actions
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[@title='Add Action']")).click();
		driver.switchTo().defaultContent();
		int size_two = driver.findElements(By.xpath("//iframe[@title='accessibility title']")).size();
		System.out.println("How many frames "+size_one);
		driver.switchTo().frame(0); 


		//			14) select "Create a Record" under Action Type
		WebElement actionType =driver.findElement(By.xpath("//span[text()='Action Type']/following::select")); 
		Select value_two = new Select(actionType);
		value_two.selectByVisibleText("Create a Record");	

		//			15) Enter Action Name as "Create a Contract Record"
		driver.findElement(By.xpath("//span[text()='Action Name']/following::input")).sendKeys("Create a Contract Record");

		//			16) Select Record Type as "Contract"
		WebElement  recordType= driver.findElement(By.xpath("//span[text()='Record Type']/following::input"));
		recordType.click();
		driver.findElement(By.linkText("Contract")).click();

		//			17) under Set Field Values set Field as AccountID and Type as "Field Reference" 

		WebElement type =driver.findElement(By.xpath("//span[text()='Action Type']/following::select[2]"));
		Select value_three = new Select(type);
		List<WebElement> options=value_three.getOptions();
		//int count =options.size();
		value_three.selectByVisibleText("Field Reference");

		driver.findElement(By.xpath("(//th[text()='Value'])[2]/following::span[text()='Find a field...']")).click();

		//			18) Click on Value and Select the Field as "AccountID"
		Thread.sleep(2000);

		WebElement  accountC= driver.findElement(By.xpath("//input[@placeholder='Type to filter list…']"));
		accountC.sendKeys("Account ID");
		//accountC.click();
		driver.findElement(By.linkText("Account ID")).click();


		//			19) Click on Choose button
		driver.findElement(By.xpath("//span[text()='Choose']")).click();
		//           19.a) click add Row
		//driver.findElement(By.xpath("(//a[@class='addRow']//span)[2]")).click();

		//			20) Select Field as "Contract Start Date" and select Type as "Formula"\
		driver.findElement(By.xpath("(//input[contains(@class,'hasButton default')])[3]")).sendKeys("Contract Start Date",Keys.ENTER);
		driver.findElement(By.linkText("Contract Start Date")).click();

		WebElement typeContract =driver.findElement(By.xpath("//span[text()='Action Type']/following::select[3]"));
		Select value_six = new Select(typeContract);
		List<WebElement> options_two=value_six.getOptions();
		//int count =options.size();
		value_six.selectByVisibleText("Formula");

		//			21) Click on Value and Enter " DATEVALUE([Account].CreatedDate ) +90 " in TextArea
		driver.findElement(By.xpath("//span[text()='Build a formula...']")).click();
		driver.findElement(By.xpath("(//textarea[@role='textbox'])[2]")).sendKeys("DATEVALUE([Account].CreatedDate ) +90");

		//			22) click on "Use this Formula"
		driver.findElement(By.xpath("//span[text()='Use this Formula']")).click();
		//			23) click on Add Row
		driver.findElement(By.xpath("(//a[@class='addRow']//span)[2]")).click();

		//			24) select Field as "Status" and value as "Draft"
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[contains(@class,'hasButton default')])[4]")).sendKeys("Status");
		Thread.sleep(2000);

		driver.findElement(By.linkText("Status")).click();	

		WebElement  statusValue=driver.findElement(By.xpath("//span[text()='Action Type']/following::select[5]"));
		Select statusSelect = new Select(statusValue);
		List<WebElement> statusOption=statusSelect.getOptions();
		//int count =options.size();
		statusSelect.selectByVisibleText("Draft");

		//			25) Click on Add Row 
		driver.findElement(By.xpath("(//a[@class='addRow']//span)[2]")).click();

		//			26) Select Field as "Contract Term" and Enter value as 12
		driver.findElement(By.xpath("(//input[contains(@class,'hasButton default')])[5]")).sendKeys("Contract Term");
		driver.findElement(By.linkText("Contract Term")).click();

		driver.findElement(By.xpath("//span[text()='Set Field Values']/following::input[contains(@class,'input uiInputSmartNumber')]"))
		.sendKeys("12");
		//			27) Click on Save Button
		driver.findElement(By.xpath("(//span[text()='Save'])[3]")).click();
		// driver.switchTo().defaultContent();

		//			28) Click on Activate button and click Confirm
		//	int size_three = driver.findElements(By.xpath("//iframe[@title='accessibility title']")).size();
		//	System.out.println("How many frames "+size_three);
		//	driver.switchTo().frame(0); 
		Thread.sleep(2000);

		WebDriverWait wait_one = new WebDriverWait(driver,5);
		wait_one.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Activate']")));
		driver.findElement(By.xpath("//span[text()='Activate']")).click();

		driver.findElement(By.xpath("//span[text()='Confirm']")).click();
		driver.switchTo().defaultContent();

		//			29) Click on App Launcher on the left top and click on View All
		driver.findElement(By.className("slds-icon-waffle")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();

		//			30) Enter "Account" in the Search bar and click on the "Account" listed below
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Accounts");
		driver.findElement(By.xpath("//mark[text()='Accounts']")).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//span[text()='Files']//following::span[text()='Accounts']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		//			31) Click on New button
		
		WebDriverWait wait_ = new WebDriverWait(driver,7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='forceActionLink']//div")));
		driver.findElement(By.xpath("//a[@class='forceActionLink']//div")).click();
		
		//			32) Enter Account Name as "Bootcamp" and click save
		driver.findElement(By.xpath("//input[@class=' input']")).sendKeys("Bootcamp");
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		//			33) Store the "AccountName" from the Success Message
		WebDriverWait wait_toastMessage = new WebDriverWait(driver,3);
		wait_toastMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small')]")));

		String toastMessage = driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small')]")).getText();
	    String[] arrOfaccountName = toastMessage.split(" ", 3); 
	    String accountNamevalue = arrOfaccountName[1];
	    String accountName=accountNamevalue.replace('"', ' ').trim();
	    
		System.out.println("Stored Account Value "+accountName);
		Thread.sleep(5000);
		//			34) Click on App Launcher on the left top and click on View All
		WebDriverWait wait_two = new WebDriverWait(driver,10);
		wait_two.until(ExpectedConditions.visibilityOfElementLocated(By.className("slds-icon-waffle")));
		driver.findElement(By.className("slds-icon-waffle")).click();

		driver.findElement(By.xpath("//button[text()='View All']")).click();


		//			35) Enter "Contract" on the Search bar and click the "Contract" listed below
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Contract");
		driver.findElement(By.xpath("//mark[text()='Contract']")).click();


		//			36) verify the newly created contract with the AccountName is displayed
		String contract_name =driver.findElement(By.xpath("(//a[contains(@class,'slds-truncate outputLookupLink')])[2]")).getText();


		if (contract_name.contains(accountName))
		{
			System.out.println(" Contract is created with the AccountName as displayed sucessfully");

		}

		else {
			System.out.println("Contract is not created with the AccountName as displayed sucessfully");
		}

		driver.close();

	}

}

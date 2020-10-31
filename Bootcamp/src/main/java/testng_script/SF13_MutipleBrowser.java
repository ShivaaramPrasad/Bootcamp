package testng_script;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SF13_MutipleBrowser {

	public  WebDriver driver;
	public  String fileName="SFO_data2";
	//public static String url="https://login.salesforce.com/";

	@BeforeMethod
	@Parameters("url")
	public void setup(String url) throws InterruptedException{

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

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test(dataProvider = "fetchData")
	public void loadedToDownloadSalesForce(String userName,String password) throws InterruptedException{

		//	    1. Login to https://login.salesforce.com
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();


		//			2. Click on toggle menu button from the left corner
		//		3. Click on the sliding icon until "Download SalesForceA" is displayed
		Thread.sleep(5000);
		List<WebElement> num_scroll = driver.findElements(By.xpath("//li[@data-aura-class='uiCarouselPageIndicatorItem']//a"));
		int num =num_scroll.size();
		System.out.println("Number of Scroll "+num);

		int x=0;

		while ( x <num) {

			boolean displayed = driver.findElement(By.xpath("//span[text()='Download SalesforceA']")).isDisplayed(); 

			if (!displayed) {

				System.out.println(displayed+" element is not visble ");

				WebDriverWait wait = new WebDriverWait(driver,1);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rightScroll']//button[1]")));
				driver.findElement(By.xpath("//div[@class='rightScroll']//button[1]")).click();

				x++;

			} 
			else {

				System.out.println(displayed+" element is visble ");
				break;
			}

		}


		//		4. Click on App Strore link
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='App Store']")).click();

		//		5. Navigate to the new window

		String parentWindow = driver.getWindowHandle();
		Set<String> handle =  driver.getWindowHandles();
		for(String windowHandle  : handle)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
			}
		}

		//		6. Get the link displayed and click confirm

		String genrated_url =driver.findElement(By.className("warning")).getText();
		System.out.println("Genrated Url "+genrated_url);
		String genrated_id=genrated_url.substring(46, 55);
		System.out.println("Genrated Sesssion ID "+genrated_id);

		driver.findElement(By.linkText("Confirm")).click();
		//		7. Verify url https://apps.apple.com/us/app/salesforcea/<id> loaded 
		String launch_url = driver.getCurrentUrl();
		String launch_id=launch_url.replaceAll("\\D","");
		System.out.println("Launced Url "+launch_url);
		System.out.println("Launced Sesssion ID "+launch_id);
		if (launch_id.equals(genrated_id)) {

			System.out.println("URL is same and verifed  successfully ");

		} else {

			System.out.println("URL is not same and need be verifed  ");
		} }
	@AfterMethod
	public void logout() {
		driver.close();
		driver.quit();

	}

	@DataProvider(name = "fetchData")

	public String[][] sendData() throws IOException{

		ReadExcelData read= new ReadExcelData();
		String[][] data = read.readData(fileName);

		return data;
	}


}



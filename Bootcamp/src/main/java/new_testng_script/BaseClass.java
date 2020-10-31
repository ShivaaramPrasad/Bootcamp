package new_testng_script;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public  WebDriver driver;
	
	public String excelFileName;
	
	@BeforeMethod(alwaysRun=true)
	@Parameters({"Browser","url","userName","password"})
	public void preCondition(int Browser,String url, String userName,String password) {
		
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
			EdgeOptions opt =new EdgeOptions();
			opt.addArguments("--disable-notifications");
			driver = new EdgeDriver();

			break;
		}



		//Load URL:

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();

		
	}
	
	@AfterMethod(alwaysRun=true)
	public void postCondition() {
		driver.close();

	}
	
	@DataProvider(name = "fetchData")
	public String[][] sendData() throws IOException {
		
		ReadExcelData read = new ReadExcelData();
		
		String[][] data = read.readData(excelFileName);
		return data;
	}
		
	


}

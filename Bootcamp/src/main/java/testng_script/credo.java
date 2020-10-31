package testng_script;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class credo {

	public static WebDriver driver;
	public static int iBrtype=1;//1-Chrome,2-FF,3-IE,4-HTML Unit Driver
	public static String sURL = "https://www.ebay.in/";

	@BeforeTest
	public static void browserInvoke(){
		switch (iBrtype) {
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

		default:
			System.err.println("User Option is wrong, So Invoking default Chrome Browser");
			ChromeOptions options_default = new ChromeOptions();
			options_default.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options_default);
			break;

		}

	}
	@Test(priority=1)
	public static void changeBrowserSittings(){
		if(iBrtype==2){
			driver.manage().deleteAllCookies();
		}else{
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
	}
	@Test(priority=2)
	public static void navigateURL(){
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	@Test(priority=3)
	public static void getPageInfo(){
		System.out.println("Page Title is : "+driver.getTitle());
		System.out.println("Page Current URL is : "+driver.getCurrentUrl());
		String sTitle = driver.getTitle();
		Assert.assertEquals(sTitle, "ebay");
	}
	@Test(priority=4)
	public static void uiInteract(){
		WebElement oText,oBtn,oDropdown;
		oText = driver.findElement(By.id("gh-ac"));
		oText.sendKeys("iPhone 7");

		oDropdown = driver.findElement(By.id("gh-cat"));
		Select oSelect = new Select(oDropdown);
		oSelect.selectByVisibleText("Mobile Phones");

		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}
	@Test(priority=5)
	public static void getListValue(){
		WebElement oListText,oElement;
		oListText = driver.findElement(By.xpath("//*[@class='listingscnt']"));
		String sText = oListText.getText();
		sText=sText.replaceAll("[^0-9]", "").trim();
		System.out.println("Value After Replace : "+sText);
		int iText = Integer.parseInt(sText);
		if(iText>0){
			Assert.assertTrue(true);
			System.out.println("List has Values ");
			List<WebElement> oList = driver.findElements(By.xpath("//*[@id='ListViewInner']/li"));
			int iSize = oList.size();
			for(int i=0;i<iSize;i++){
				oElement = oList.get(i);
				System.out.println(oElement.findElement(By.className("vip")).getText());
			}
		}else{
			System.out.println("List not having Values");
		}
	}
	@AfterTest
	public static void closeBrowser(){
		//driver.close();
	}
}


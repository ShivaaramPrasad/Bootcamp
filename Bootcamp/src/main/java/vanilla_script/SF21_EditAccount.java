package vanilla_script;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SF21_EditAccount {

	public static void main(String[] args) throws InterruptedException {


		// Lunch  Chrome 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		//Load URL:

		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//        1) Launch the app
		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();


		//			2. Click on toggle menu button from the left corner

		driver.findElementByClassName("slds-icon-waffle").click();

		//			3. Click view All and click Sales from App Launcher

		driver.findElementByXPath("//button[text()='View All']").click();

		WebElement element = driver.findElementByXPath("//span[text()='All Items']");
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", element);

		//        	4. Click on Accounts

		WebElement elemente = driver.findElementByXPath("//p[text()='Accounts']");
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor2.executeScript("arguments[0].click();", element);

		driver.findElementByXPath("//p[text()='Accounts']").click();

		String Acc_Name="Kumar";

		//       5. Search for the Account Using the unique account name created by you 
		driver.findElementByXPath("//span[text()='Accounts']/following::input[@name='Account-search-input']").sendKeys(Acc_Name);
		driver.findElementByXPath("//span[text()='Accounts']/following::input[@name='Account-search-input']").sendKeys(Keys.ENTER);

		//      6. Click on the displayed Account Dropdown icon and select Edit
		Thread.sleep(3000);
		driver.findElementByXPath("//a[contains(@class,'slds-button slds-button--icon-x-small')]").click();
		driver.findElementByXPath("//a[@title='Edit']").click();

		//       7. Select Type as Technology Partner
		driver.findElementByXPath("(//a[@class='select'])[2]").click();

		driver.findElementByXPath("(//a[text()='Technology Partner'])").click();

		//                 8. Select Industry as Healthcare
		driver.findElementByXPath("(//a[@class='select'])[4]").click();
		driver.findElementByXPath("(//a[text()='Healthcare'])").click();

		//                      9. Enter Billing Address		

		driver.findElementByXPath("//span[text()='Billing Street']/following::textarea").sendKeys("Billing Street");
		driver.findElementByXPath("//span[text()='Billing City']/following::input").sendKeys("Billing City");
		driver.findElementByXPath("//span[text()='Billing State/Province']/following::input").sendKeys("Tamil Nadu");
		driver.findElementByXPath("//span[text()='Billing Zip/Postal Code']/following::input").sendKeys("600037");
		driver.findElementByXPath("//span[text()='Billing Country']/following::input").sendKeys("India");

		//                      10. Enter Shipping Address
		driver.findElementByXPath("//span[text()='Shipping Street']/following::textarea").sendKeys("Shipping Street");
		driver.findElementByXPath("//span[text()='Shipping City']/following::input").sendKeys("Shipping City");
		driver.findElementByXPath("//span[text()='Shipping State/Province']/following::input").sendKeys("Tamil Nadu");
		driver.findElementByXPath("//span[text()='Shipping Zip/Postal Code']/following::input").sendKeys("600037");
		driver.findElementByXPath("//span[text()='Shipping Country']/following::input").sendKeys("India");

		//                          11. Select Customer Priority as Low

		driver.findElementByXPath("(//a[@class='select'])[5]").click();
		driver.findElementByXPath("(//a[text()='Low'])").click();

		//                                 12. Select SLA as Silver
		driver.findElementByXPath("(//a[@class='select'])[6]").click();
		driver.findElementByXPath("(//a[text()='Silver'])").click();

		//                                13. Select Active as NO 
		driver.findElementByXPath("(//a[@class='select'])[8]").click();
		driver.findElementByXPath("(//a[text()='No'])").click();

		//                             14. Enter Unique Number in Phone Field
		String PhNUM="9876543210";
		System.out.println("Phone Number used in Edit Account: "+PhNUM);
		driver.findElementByXPath("//input[@type='tel']").clear();
		driver.findElementByXPath("//input[@type='tel']").sendKeys(PhNUM);
		//                            15. Select Upsell Oppurtunity as No
		driver.findElementByXPath("(//a[@class='select'])[6]").click();
		driver.findElementByXPath("(//a[text()='No'])").click();

		//                               16. Click on save and verfiy Phone number
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		driver.findElementByXPath("//span[@data-aura-class='uiOutputPhone']").getText();
		
		String Verify = driver.findElementByXPath("//span[text()='Account']").getText();
		System.out.println(Verify);

		//			17.Expected Result:Account should be created Successfully
		String Verify_PhNum = driver.findElementByXPath("//span[@dir='ltr']").getText();
		System.out.println("Displaying the saved phone number: "+Verify_PhNum);
		String trim_PhNum = Verify_PhNum.replace(" ", "").replace("(", "").replace(")", "").replaceAll("-", "");
		System.out.println("Trimed Phone number: "+trim_PhNum);

		if (PhNUM.contains(trim_PhNum))
		{
			System.out.println("Edited successfully and Phone Number is Matched ");

		}

		else {
			System.out.println("Edit function is unsuccess and Phone Number is not Matched");
		}

		driver.close();


	}

}

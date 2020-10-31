package new_testng_script;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditAccount extends BaseClass  {

	@BeforeTest(alwaysRun=true)
	public void setFileName() {
		
		excelFileName = "SFO_EditAcc";
	}
	
	@Test(dataProvider="fetchData",groups= {"Shivaa","Thanveer"},dependsOnMethods= {"new_testng_script.CreateAccount.runCreateAccount"})
	
	public void runEditAccount(String Acc_Name) throws InterruptedException {
	
		driver.findElement(By.className("slds-icon-waffle")).click();

		//			3. Click view All and click Sales from App Launcher

		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		//        	4. Click on Accounts

		WebElement element = driver.findElement(By.xpath("//span[text()='Accounts']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);


		//       5. Search for the Account Using the unique account name created by you 
		driver.findElement(By.xpath("//span[text()='Accounts']/following::input[@name='Account-search-input']")).sendKeys(Acc_Name);
		driver.findElement(By.xpath("//span[text()='Accounts']/following::input[@name='Account-search-input']")).sendKeys(Keys.ENTER);

		//      6. Click on the displayed Account Dropdown icon and select Edit
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small')]")).click();
		driver.findElement(By.xpath("//a[@title='Edit']")).click();

		//       7. Select Type as Technology Partner
		driver.findElement(By.xpath("(//a[@class='select'])[2]")).click();

		driver.findElement(By.xpath("(//a[text()='Technology Partner'])")).click();

		//                 8. Select Industry as Healthcare
		driver.findElement(By.xpath("(//a[@class='select'])[4]")).click();
		driver.findElement(By.xpath("(//a[text()='Healthcare'])")).click();

		//                      9. Enter Billing Address		

		driver.findElement(By.xpath("//span[text()='Billing Street']/following::textarea")).sendKeys("Billing Street");
		driver.findElement(By.xpath("//span[text()='Billing City']/following::input")).sendKeys("Billing City");
		driver.findElement(By.xpath("//span[text()='Billing State/Province']/following::input")).sendKeys("Tamil Nadu");
		driver.findElement(By.xpath("//span[text()='Billing Zip/Postal Code']/following::input")).sendKeys("600037");
		driver.findElement(By.xpath("//span[text()='Billing Country']/following::input")).sendKeys("India");

		//                      10. Enter Shipping Address
		driver.findElement(By.xpath("//span[text()='Shipping Street']/following::textarea")).sendKeys("Shipping Street");
		driver.findElement(By.xpath("//span[text()='Shipping City']/following::input")).sendKeys("Shipping City");
		driver.findElement(By.xpath("//span[text()='Shipping State/Province']/following::input")).sendKeys("Tamil Nadu");
		driver.findElement(By.xpath("//span[text()='Shipping Zip/Postal Code']/following::input")).sendKeys("600037");
		driver.findElement(By.xpath("//span[text()='Shipping Country']/following::input")).sendKeys("India");

		//                          11. Select Customer Priority as Low

		driver.findElement(By.xpath("(//a[@class='select'])[5]")).click();
		driver.findElement(By.xpath("(//a[text()='Low'])")).click();

		//                                 12. Select SLA as Silver
		driver.findElement(By.xpath("(//a[@class='select'])[6]")).click();
		driver.findElement(By.xpath("(//a[text()='Silver'])")).click();

		//                                13. Select Active as NO 
		driver.findElement(By.xpath("(//a[@class='select'])[8]")).click();
		driver.findElement(By.xpath("(//a[text()='No'])")).click();

		//                             14. Enter Unique Number in Phone Field
		String PhNUM="9876543210";
		System.out.println("Phone Number used in Edit Account: "+PhNUM);
		driver.findElement(By.xpath("//input[@type='tel']")).clear();
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(PhNUM);
		//                            15. Select Upsell Oppurtunity as No
		driver.findElement(By.xpath("(//a[@class='select'])[6]")).click();
		driver.findElement(By.xpath("(//a[text()='No'])")).click();

		//                               16. Click on save and verfiy Phone number
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		driver.findElement(By.xpath("//span[@data-aura-class='uiOutputPhone']")).getText();
		
		String Verify = driver.findElement(By.xpath("//span[text()='Account']")).getText();
		System.out.println(Verify);

		//			17.Expected Result:Account should be created Successfully
		String Verify_PhNum = driver.findElement(By.xpath("//span[@dir='ltr']")).getText();
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


	}
		@DataProvider(name = "fetchData")

		public String[][] sendData() throws IOException{

			ReadExcelData read= new ReadExcelData();
			String[][] data = read.readData(excelFileName);

			return data;

		}

	}



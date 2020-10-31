package new_testng_script;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateAccount extends BaseClass  {

	@BeforeTest(alwaysRun=true)
	public void setFileName() {
		
		excelFileName = "SFO_CreAcc";
	}
	
	@Test(dataProvider="fetchData",groups= {"Shivaa","Haja"})
	public void runCreateAccount(String Acc_Name, String Ownership) {
	
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

	    driver.findElement(By.xpath("(//a[text()='"+Ownership+"'])")).click();
	    driver.findElement(By.xpath("(//a[text()='"+Ownership+"'])")).click();

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
		

	}
		@DataProvider(name = "fetchData")

		public String[][] sendData() throws IOException{

			ReadExcelData read= new ReadExcelData();
			String[][] data = read.readData(excelFileName);

			return data;

		}

	}



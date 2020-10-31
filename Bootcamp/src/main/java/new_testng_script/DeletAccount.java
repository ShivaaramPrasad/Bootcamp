package new_testng_script;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeletAccount extends BaseClass {



		@BeforeTest(alwaysRun=true)
		public void setFileName() {
			
			excelFileName = "SFO_DelAcc";
		}
		
		@Test(dataProvider="fetchData",groups= {"Shivaa","Srikant","Haja"},dependsOnMethods= {"new_testng_script.CreateAccount.runCreateAccount"})
		public void runEditAccount(String Acc_Name) throws InterruptedException {
		

		//		2. Click on toggle menu button from the left corner

		driver.findElement(By.className("slds-icon-waffle")).click();

		//		3. Click view All and click Sales from App Launcher

		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();

		//			4. Click on Opportunity tab (using java scrpit)

		WebElement element = driver.findElement(By.xpath("//span[text()='Accounts']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		//	5. Search the account 'Your Name'
		System.out.println("Account which name"+Acc_Name+"need to be deleted");
		driver.findElement(By.xpath("//span[text()='Accounts']/following::input[@name='Account-search-input']")).sendKeys(Acc_Name,Keys.ENTER);
		//	6. Click on  the Dropdown icon and Select Delete
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small')]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='Delete']")).click();

		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		System.out.println("Acccount is deleted successfully");
		Thread.sleep(5000);
		//			Expected Result:Account should be created Successfully
		String text =driver.findElement(By.xpath("//a[contains(@class,'slds-truncate outputLookupLink')]")).getText();


		if (text.contains(Acc_Name))
		{
			System.out.println("Account is still avaible");

		}

		else {
			System.out.println("Account is not shown");
		}

	}



@DataProvider(name = "fetchData")

public String[][] sendData() throws IOException{

	ReadExcelData read= new ReadExcelData();
	String[][] data = read.readData(excelFileName);

	return data;

}

}







package steps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.ThreeDEval;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinition_Assessment {


	public  WebDriver driver;

	@Given("Login to the application")
	public void login_to_the_application() {

		System.out.println("User Option is 1, So Invoking Chrome Browser");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);		
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


	}
	@And("Login with the username (.*)$")
	public  void Login_with_the__username (String userName ) {
		driver.findElement(By.id("username")).sendKeys(userName);

	}

	@And("Login with the password (.*)$")
	public  void Login_with_the_password (String password ) {
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();

	}

	@When("Click on Community under Most Recently Used")
	public void click_on_community_under_most_recently_used() throws InterruptedException {
		boolean displayed = driver.findElement(By.className("switch-to-lightning")).isDisplayed();
		if(displayed)
		{
			driver.findElement(By.className("switch-to-lightning")).click();
			Thread.sleep(5000);

		}
		else {
			System.out.println("Switch To Lightning is not shown");
		}
		Thread.sleep(5000);
		driver.findElement(By.linkText("Community")).click();

	}

	@Then("Print the Application Name and Developer Name for the App Type (.*)$")
	public void print_the_application_name_and_developer_name_for_the_app_type(String appType) throws InterruptedException {
		Thread.sleep(5000);

		List<WebElement> listOfAppNames = driver.findElements(By.xpath("//table//descendant::tbody/tr/th/span/span"));
		List<WebElement> listOsDeveloperNames = driver.findElements(By.xpath("//table//descendant::tbody/tr/td[2]/span/span"));
		List<WebElement> listAppTypes = driver.findElements(By.xpath("//table//descendant::tbody/tr/td[5]/span/div"));
		System.out.println("App Name      Developer Name");
		for(int i=0;i<listAppTypes.size();i++) {
			if((listAppTypes.get(i).getText()).contains(appType)) {
				System.out.println(listOfAppNames.get(i).getText()+"    "+listOsDeveloperNames.get(i).getText());
			}
		}
		Thread.sleep(10000);

	}

	@And("Click on View Profile icon")
	public void click_on_view_profile_icon() throws InterruptedException {

		driver.findElement(By.xpath("//span[@data-aura-class='oneUserProfileCardTrigger']")).click();
		Thread.sleep(5000);
	}

	@Then("Click on Switch to Salesforce Classic")
	public void click_on_switch_to_salesforce_classic() {

		driver.findElement(By.linkText("Switch to Salesforce Classic")).click();
	}

	@When("Click on Create New and Select Event")
	public void click_on_create_new_and_select_event() {

		driver.findElement(By.xpath("//span[contains(text(),'Create New')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Event')]")).click();
	}

	@And("Enter the Subject as Call Email Meeting")
	public void enter_the_subject_as_call_email_meeting() {

		driver.findElement(By.xpath("//label[text()='Subject']/following::input")).sendKeys("Call Email Meeting");

	}

	@And("Enter Start date as tomorrow")
	public void enter_start_date_as_tomorrow() {
		//Extract Tomorrow Date 
		//LocalDate tommorowdate = LocalDate.now().plusDays(1);

		LocalDate date = LocalDate.now();
		int dayOfMonth = date.getDayOfMonth();
		int tomorrowDate = dayOfMonth+1;

		driver.findElement(By.xpath("//input[@name='StartDateTime']")).click();
		driver.findElement(By.xpath("//table[@id='datePickerCalendar']//descendant::td[text()="+tomorrowDate+"][not(contains(@class,'nextMonth'))]")).click();

	}

	@And("Enter End date as tomorrow+one")
	public void enter_end_date_as_tomorrow_one() {
		//Extract Tomorrow Date 
		//LocalDate dayAfterTommorowdate = LocalDate.now().plusDays(2);

		LocalDate date = LocalDate.now();
		int dayOfMonth = date.getDayOfMonth();
		int dayAfterTommorowdate = dayOfMonth+2;

		//Click Date Picker using Javascript executor
		

		driver.findElement(By.xpath("//input[@name='EndDateTime']")).click();
		driver.findElement(By.xpath("//table[@id='datePickerCalendar']//descendant::td[text()="+dayAfterTommorowdate+"][not(contains(@class,'nextMonth'))]")).click();

	}

	@Then("Add invitees as Contact: Click on the LookUp icon")
	public void add_invitees_as_contact_click_on_the_look_up_icon() {
}

	@When("Search for Sarath and select first result")
	public void search_for_sarath_and_select_first_result() {

		String parentWindow = driver.getWindowHandle();
		Set<String> handle =  driver.getWindowHandles();
		for(String windowHandle  : handle)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
			}
		}

		driver.findElement(By.id("lksrch")).sendKeys("Sarath");
		driver.findElement(By.name("go")).click();
		driver.findElement(By.xpath("//th[text()='Name']/following::a")).click();
		driver.switchTo().window(parentWindow);

	}

	@And("Attach a file to the event Choose File, Attach File and Done")
	public void attach_a_file_to_the_event_choose_file_attach_file_and_done() {
		driver.findElement(By.xpath("//input[@title='Attach File']")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> handle =  driver.getWindowHandles();
		for(String windowHandle  : handle)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
			}
		}
		String fileName_one ="Se.pdf";
		driver.findElement(By.id("file")).click();

		WebElement upload_file = driver.findElement(By.id("file"));
		upload_file.sendKeys("C:/Users/DELL/Desktop/"+fileName_one+"");
		driver.findElement(By.id("Attach")).click();
		driver.findElement(By.xpath("//input[@title='Done']")).click();
		driver.switchTo().window(parentWindow);

	}

	@Then("Verify that the file is attached to the file")
	public void verify_that_the_file_is_attached_to_the_file() {

	 String fileaAttached = driver.findElement(By.xpath("//td[text()='File Name']/following-sibling::td")).getText();
	String fileName_two ="Se.png";

	 if (fileaAttached.contains(fileName_two))
		{
			System.out.println("File is still avaible");

		}

		else {
			System.out.println("File is not shown");
		}

	}

	@And("Click on Save")
	public void click_on_save() {


		driver.findElement(By.xpath("//input[@name='save']")).click();

	}




}

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
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinition_Assessment {


	public  WebDriver driver;

	@Given("Login to eeethe application")
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
		List<WebElement> light = driver.findElements(By.xpath("//a[text()='Switch to Lightning Experience']"));
		if(!(light.isEmpty())) {
			light.get(0).click();
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
	public void add_invitees_as_contact_click_on_the_look_up_icon() throws InterruptedException {

		WebElement name = driver.findElement(By.xpath("//select[@title='Search scope']"));
		Select nameoptions = new Select(name);
		nameoptions.selectByVisibleText("Contact");
		driver.findElement(By.xpath("//img[@title='Name Lookup (New Window)']")).click();

		Set<String> windowhandles = driver.getWindowHandles();
		List<String> lwindowhandles = new ArrayList<>();
		lwindowhandles.addAll(windowhandles);
		driver.switchTo().window(lwindowhandles.get(1));
		driver.switchTo().frame("searchFrame");
		driver.findElement(By.id("lksrch")).sendKeys("Sarath");
		driver.findElement(By.name("go")).click();
		Thread.sleep(2000);
		driver.switchTo().window(lwindowhandles.get(1));
		driver.switchTo().frame("resultsFrame");
		driver.findElement(By.xpath("//a[text()='Sarath M']")).click();
		driver.switchTo().window(lwindowhandles.get(0));


	}
	
	@And("Attach a file to the event Choose File, Attach File and Done")
	public void attach_a_file_to_the_event_choose_file_attach_file_and_done() throws InterruptedException {
		String fileName_one ="error";

		WebElement attachfile = driver.findElement(By.name("attachFile"));
		JavascriptExecutor ajse = (JavascriptExecutor)driver;
		ajse.executeScript("arguments[0].scrollIntoView(true)", attachfile);
		attachfile.click();

		Set<String> whandles = driver.getWindowHandles();
		List<String> lwhandles = new ArrayList<>();
		lwhandles.addAll(whandles);
		System.out.println(lwhandles.size());
		driver.switchTo().window(lwhandles.get(1));
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//input[@name='file']")).sendKeys("C:/Users/DELL/Desktop/"+fileName_one+".txt");
		driver.findElement(By.name("Attach")).click();
		Thread.sleep(2000);
	}

	@Then("Verify that the file is attached to the file")
	public void verify_that_the_file_is_attached_to_the_file() {

		String fileaAttached = driver.findElement(By.xpath("//td[text()='File Name']/following-sibling::td")).getText();
	String fileName_two ="error";

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

		Set<String> shandles = driver.getWindowHandles();
		List<String> lshandles = new ArrayList<>();
		lshandles.addAll(shandles);
		driver.switchTo().window(lshandles.get(0));
		driver.findElement(By.xpath("(//input[@name='save'])[2]")).click();
	}




}

package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.aventstack.extentreports.ExtentTest;

import base.PreAndPost;
import io.cucumber.java.en.When;

public class HomePage extends PreAndPost{
	
	
	@When("The logout is clicked")
	public LoginPage clickLogOut() {
		getDriver().findElementByClassName("decorativeSubmit").click();
		reportStep("The logout is successful ","pass");
		return new LoginPage();
	}

	@When("CRM is clicked")
	public LoginPage clickCRMSFA() {
		getDriver().findElementByLinkText("CRM/SFA").click();
		reportStep("The CRM/SFA is clicked successfully","pass");
		return new LoginPage();
	}

	
	
	
}

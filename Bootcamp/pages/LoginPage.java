package pages;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import base.PreAndPost;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage extends PreAndPost{

	@Given("Type username as (.*)$")
	public LoginPage typeUserName(String username) {
		getDriver().findElementById("username").sendKeys(username);
		try {
			reportStep("The Username "+username+" was entred ","pass");
		} catch (NoSuchElementException e) {
			reportStep("The Username "+username+" was not entred ","fail");
		}

		return this;
	}

	@Given("Type password as (.*)$")
	public LoginPage typePassword(String password) {
		getDriver().findElementById("password").sendKeys(password);
		try {
			reportStep("The password "+password+" was entred ","pass");
		} catch (NoSuchElementException e) {
			reportStep("The password "+password+" was not entred ","fail");
		}
		return this;
	}

	@When("Click Login button")
	public HomePage clickLogin() {
		getDriver().findElementByClassName("decorativeSubmit").click();
		try {
			reportStep( "The login button was clicked Successfully ","pass");
		} catch (NoSuchElementException e) {
			reportStep("The login button was not clicked ","fail");
		}
		return new HomePage();
	}




}

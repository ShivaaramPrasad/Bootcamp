package pages;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.PreAndPost;

public class AppStorePage extends PreAndPost {
		
	public AppStorePage genrateUrl() {
		//		6. Get the link displayed and click confirm

		String genrated_url =driver.findElement(By.className("warning")).getText();
		System.out.println("Genrated Url "+genrated_url);
		 genrated_id =genrated_url.substring(46, 55);
		System.out.println("Genrated Sesssion ID "+genrated_id);
		
		return this;

	}
	public SalesforceAppPage clickConfirm() {

	driver.findElement(By.linkText("Confirm")).click();
	
	 return new SalesforceAppPage();
	}
	
}

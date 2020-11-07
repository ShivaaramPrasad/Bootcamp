package pages;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.PreAndPost;

public class SalesforceAppPage extends PreAndPost  {
	
	
	public SalesforceAppPage validateUrl() {

		String launch_url = driver.getCurrentUrl();
		String launch_id=launch_url.replaceAll("\\D","");
		System.out.println("Launced Url "+launch_url);
		System.out.println("Launced Sesssion ID "+launch_id);
		if (launch_id.equals(genrated_id)) {

			System.out.println("URL is same and verifed  successfully ");

		} else {
			System.out.println("URL is not same and need be verifed  ");} 
			
			 return new SalesforceAppPage();
			}

	}


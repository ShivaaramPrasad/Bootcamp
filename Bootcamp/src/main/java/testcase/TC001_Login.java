package testcase;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PreAndPost;
import pages.HomePage;

public class TC001_Login extends PreAndPost{
	
	
	@BeforeTest
	public void setData() {
		excelFileName = "SFO_DowloadSales";
	}
	
	@Test(dataProvider="fetchData")
	public void login(String scrollString) throws InterruptedException {
		new HomePage()
		.searchForAppStor(scrollString)
		.clcikForAppStore()
		.genrateUrl()
		.clickConfirm()
		.validateUrl();
	}
	
	
	
	
	
}

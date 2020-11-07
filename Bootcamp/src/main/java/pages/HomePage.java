package pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.PreAndPost;

public class HomePage extends PreAndPost{
	
	
	public HomePage searchForAppStor (String scrollString) throws InterruptedException {
		
		List<WebElement> num_scroll = driver.findElements(By.xpath("//li[@data-aura-class='uiCarouselPageIndicatorItem']//a"));
		int num =num_scroll.size();
		System.out.println("Number of Scroll "+num);

		int x=0;

		while ( x <num) {

			boolean displayed = driver.findElement(By.xpath("//span[text()='"+scrollString+"']")).isDisplayed(); 
			
			if (!displayed) {

				System.out.println(displayed+" element is not visble ");

				WebDriverWait wait = new WebDriverWait(driver,1);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rightScroll']//button[1]")));
				driver.findElement(By.xpath("//div[@class='rightScroll']//button[1]")).click();

				x++;

			} 
			else {

				System.out.println(displayed+" element is visble ");
				break;
			}
		}
		return this;
	}


		//		4. Click on App Strore link
		public AppStorePage clcikForAppStore() throws InterruptedException {

		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='App Store']")).click();
		
		
		//		5. Navigate to the new window

		String parentWindow = driver.getWindowHandle();
		Set<String> handle =  driver.getWindowHandles();
		for(String windowHandle  : handle)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
			}
		}
		
        return new AppStorePage();

}
}
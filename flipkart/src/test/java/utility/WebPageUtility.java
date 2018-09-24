package utility;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebPageUtility {
	
	static WebDriverWait wait;
	
	public static void goToCartButton(WebDriver driver) {
		System.out.println("Go to cart");
		
		wait=new WebDriverWait(driver, 20);
			
		By addCart = By.xpath(".//*[text()='Cart']");
		//Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(addCart));
		
		driver.findElement(addCart).click();
	}
	
	public static void removeButton(WebDriver driver) throws Throwable {
		
		wait=new WebDriverWait(driver, 20);
		
		System.out.println("Remove all product from cart");
		List<WebElement> remButton = new ArrayList<WebElement>();
		
		remButton = driver.findElements(By.xpath(".//*[text()='Remove']"));
		
		Thread.sleep(4000);
		
		System.out.println(remButton.size());
		
		for(WebElement wb:remButton) {
			
			wb.click();
			
			Thread.sleep(3000);
		}
		
		/*for (int i=0;i<remButton.size()+1;i++) {   // another way to use List
			remButton.get(i).click();
		}*/
	}
}

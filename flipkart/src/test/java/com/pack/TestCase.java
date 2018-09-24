package com.pack;
import utility.*;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestCase{

	String baseUrl="https://www.flipkart.com/";
	String driverPath="E:/study/selenium/geckodriver/geckodriver.exe";
	WebDriver driver;
	WebDriverWait wait;
	public double intPrice;
	static String testDataPath="D:/Workspace_s/flipkart/src/test/java/testData/Test_Data.xlsx";
	//ArrayList<String> tabs = new ArrayList (driver.getWindowHandles());
	
	@BeforeTest
	
	public void browserInitialization() throws InterruptedException {
		System.out.println("launching firefox browser");
		System.setProperty("webdriver.gecko.driver",driverPath);
		
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		driver=new FirefoxDriver();
		driver.get(baseUrl);
		//Thread.sleep(20000);
	}
	
	@Test
	public void titleVerification(){
		//Thread.sleep(10000);
		By abc = By.xpath("//*[@class='_2AkmmA _29YdH8']"); // we can initialize and find web element
		wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(abc));
		driver.findElement(abc).click();
		System.out.println("verfication of webpage title");
		String expectedTitle="Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More";
		String actualTitle=driver.getTitle();
		//Assert.assertEquals(actualTitle, expectedTitle);
		if(actualTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Valid Site");
		}
		else {
			System.out.println("invalid site");
		}
		}
	
	@Test
	public void loginProfile() {
		System.out.println("login to flipkart");
		WebElement login = driver.findElement(By.xpath(".//*[text()='Login & Signup']")); // another way to initialize and find web element
		login.click();
		
		wait= new WebDriverWait(driver, 20);
		
		By userName=By.xpath(".//*[@class='_2zrpKA']");
		By password =By.xpath(".//*[@class='_2zrpKA _3v41xv']");
		By submit=By.xpath(".//*[@class='_2AkmmA _1LctnI _7UHT_c']");
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(userName));
		
		driver.findElement(userName).sendKeys("abhishek.niche@gmail.com");
		driver.findElement(password).sendKeys("loverluckee");
		driver.findElement(submit).click();
		
	}
	
	@Test(dependsOnMethods= {"loginProfile"})
	public void validateProfile() {
		System.out.println("validating profile");
		wait=new WebDriverWait(driver, 20);
		String actualAccountName="ABHISHEK KUMAR";
		
		By profileName = By.xpath(".//*[contains(text(),'Abhishek')]");
		
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(profileName));
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		String accountName = driver.findElement(profileName).getText();
		
		System.out.println(accountName);
		
		if (accountName.contains("Abhishek Kumar")) {
			
			System.out.println("Account Match");
		}
		else {
			System.out.println("Account does not match");
		}
	}
	
	@DataProvider(name = "iphone")
	public static Object[][] getTestData() throws IOException{
		
		//ExcelIntraction excel=new ExcelIntraction(testDataPath);
		Object[][] testData=null;
		
		testData=ExcelIntraction.excelAllData(testDataPath,"testdata");
		return testData;
		
	}
	
	@Test (dependsOnMethods={"validateProfile"},dataProvider="iphone")
	public void searchProduct(String product,String checkPrice) throws Throwable {
		System.out.println("search iphone");
		wait=new WebDriverWait(driver, 20);
		
		By search = By.xpath(".//*[@title='Search for products, brands and more']");
		By searchButton=By.xpath(".//*[@class='vh79eN']");
		By productName = By.xpath(".//*[text()='Apple iPhone X (Space Gray, 64 GB)']");
		By productPrice = By.xpath(".//*[@class='_1vC4OE _3qQ9m1']");
		By addToCart = By.xpath(".//*[@class='_2AkmmA _2Npkh4 _2MWPVK']");
		
		driver.findElement(search).sendKeys(product);
		driver.findElement(searchButton).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(productName));
		
		driver.findElement(productName).click();
		ArrayList<String> tabs = new ArrayList (driver.getWindowHandles());
		//System.out.println(tabs.size());
	    driver.switchTo().window(tabs.get(1));
	    
		wait.until(ExpectedConditions.presenceOfElementLocated(productPrice));
		
		String price = driver.findElement(productPrice).getText();
		
		price = price.substring(1, 3) + price.substring(4,price.length());
		
		intPrice = Double.parseDouble(price);
		double finalPrice = Double.parseDouble(checkPrice);
		System.out.println(intPrice);
		//wait.until(ExpectedConditions.elementToBeClickable(addToCart));
		Thread.sleep(3000);
		if(intPrice<finalPrice) {
			
			driver.findElement(addToCart).click();
		}
		else {
			System.out.println("price is still high");
		}
		driver.switchTo().window(tabs.get(1)).close();
	}
	
	@DataProvider(name = "applewatch")
	public static Object[][] getWatchTestData() throws IOException{
		
		//ExcelIntraction excel=new ExcelIntraction(testDataPath);
		Object[][] testData=null;
		
		testData=ExcelIntraction.excelAllData(testDataPath,"testdata2");
		return testData;
		
	}
	
	@Test(dependsOnMethods= {"searchProduct"},dataProvider="applewatch")
	public void purchaseIphone(String product,String checkPrice) throws Throwable {
		
		System.out.println("search watch");
		wait=new WebDriverWait(driver, 20);
		
		By addToCart = By.xpath(".//*[@class='_2AkmmA _2Npkh4 _2MWPVK']");
		By search = By.xpath(".//*[@title='Search for products, brands and more']");
		By searchButton=By.xpath(".//*[@class='vh79eN']");
		By searchWatch=By.xpath(".//*[text()='Apple Watch Series 3 GPS + Cellular - 38 mm Space Grey Aluminium Case with Sport Loop']");
		By watchPrice=By.xpath(".//*[@class='_1vC4OE _3qQ9m1']");
		By size = By.xpath(".//*[@class='_2_26Ng _3-ViMg']");
		By sizeCheck=By.xpath(".//*[text()='Coming Soon']");
		
		//wait.until(ExpectedConditions.elementToBeClickable(addToCart));
		
		ArrayList<String> tabs = new ArrayList (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(search));
		driver.findElement(search).clear();
		driver.findElement(search).sendKeys(product);
		driver.findElement(searchButton).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(searchWatch));
		driver.findElement(searchWatch).click();
		ArrayList<String> tabs1 = new ArrayList (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(watchPrice));
		String price = driver.findElement(watchPrice).getText();
		price = price.substring(1, 3) + price.substring(4,price.length());
		double finalPrice = Double.parseDouble(checkPrice);
		intPrice = Double.parseDouble(price);
		
		System.out.println(intPrice);
		Thread.sleep(4000);
		if(driver.findElement(size).isSelected()) {
			System.out.println("inside");
			if(intPrice<finalPrice) {
				driver.findElement(addToCart).click();
			}
			else {
				System.out.println("price is still high");
			}
		}
		else {
			driver.findElement(size).click();
			if(driver.findElement(sizeCheck).isDisplayed()) {
				System.out.println("size not available");
			}
			else {
				if(intPrice<finalPrice) {
					driver.findElement(addToCart).click();
				}
				else {
					System.out.println("price is still high");
				}
			}
		}
		
		Thread.sleep(3000);
		driver.switchTo().window(tabs1.get(1)).close();
	}
	
	@Test(dependsOnMethods= {"purchaseIphone"})
	public void goToCart() throws Throwable {
		
		ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		WebPageUtility.goToCartButton(driver);
		
		WebPageUtility.removeButton(driver);
		/*System.out.println("Go to cart");
		
		ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		wait=new WebDriverWait(driver, 20);
			
		By addCart = By.xpath(".//*[text()='Cart']");
		//Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(addCart));
		
		driver.findElement(addCart).click();*/
	}
	
	@AfterTest	
	public void terminateBrowser(){
		driver.quit();
	}
	
}

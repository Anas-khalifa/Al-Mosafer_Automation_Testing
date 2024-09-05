package homePage;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
	
	WebDriver driver=new ChromeDriver();
	String AlmosaferURL="https://sa.almosafer.com/en";
	String ExpectedLanguage="en";
	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(AlmosaferURL);
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']")).click();
	}
	@Test(priority=1)
	public void defaultLanguageEnglishTest() {
		String ActualLanguage =driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}
	@Test(priority=2)
	public void defaultCurrencyTest() {
		String actualCurrency=driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		String expectedCurrency="SAR";
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}
	@Test(priority=3)
	public void contactNumberTest() {
		String actualContactNumber=driver.findElement(By.tagName("strong")).getText();
		String expectedContactNumber="+966554400000";
		Assert.assertEquals(actualContactNumber,expectedContactNumber);
	}
	@Test(priority=4)
	public void qitafLogoTest() {
		boolean actualQitafLogo=driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();
		boolean expectedQitafLogo=true;
	}
	@Test(priority=5)
	public void hotelSearchIsNotSelected() {
		String actualHotelButtonResult=driver.findElement(By.cssSelector("#uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		String expectedHotelButton="false";
//		System.out.println(actualHotelButtonResult);
		Assert.assertEquals(actualHotelButtonResult, expectedHotelButton);
	}
	@Test(priority=6)
	public void flightDepartureDate() {
		Date todaysDate=new Date();
		String actualDepartureDate=driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']")).getText();
		int tempDate=todaysDate.getDate()+1;
		String expectedDepartureDate="";
		if(tempDate<10)
			expectedDepartureDate="0"+tempDate;
		else
			expectedDepartureDate=tempDate+"";
//		System.out.println(expectedDepartureDate);
		
		Assert.assertEquals(actualDepartureDate, expectedDepartureDate);
	}
	
	@Test(priority=6)
	public void flightReturnDate() {
		Date todaysDate=new Date();
		String actualDepartureDate=driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']")).getText();
		int tempDate=todaysDate.getDate()+2;
		String expectedDepartureDate="";
		if(tempDate<10)
			expectedDepartureDate="0"+tempDate;
		else
			expectedDepartureDate=tempDate+"";
//		System.out.println(expectedDepartureDate);
		
		Assert.assertEquals(actualDepartureDate, expectedDepartureDate);
	}

}

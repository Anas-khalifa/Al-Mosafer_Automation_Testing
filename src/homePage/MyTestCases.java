package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {

	@BeforeTest
	public void mySetUp() {
		GeneralSetUp();
	}

	@Test(priority = 1)
	public void defaultLanguageEnglishTest() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}

	@Test(priority = 2)
	public void defaultCurrencyTest() {
		String actualCurrency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3)
	public void contactNumberTest() {
		String actualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(actualContactNumber, expectedContactNumber);
	}

	@Test(priority = 4)
	public void qitafLogoTest() {
		boolean actualQitafLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"))
				.isDisplayed();
		Assert.assertEquals(actualQitafLogo, expectedQitafLogo);
	}

	@Test(priority = 5)
	public void hotelSearchIsNotSelected() {
		String actualHotelButtonResult = driver.findElement(By.cssSelector("#uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		Assert.assertEquals(actualHotelButtonResult, expectedHotelButton);
	}

	@Test(priority = 6)
	public void flightDepartureDate() {
		String actualDepartureDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText();
	
		if (tommorowDate < 10)
			expectedDepartureDate = "0" + tommorowDate;
		else
			expectedDepartureDate = tommorowDate + "";
//		System.out.println(expectedDepartureDate);

		Assert.assertEquals(actualDepartureDate, expectedDepartureDate);
	}

	@Test(priority = 7)
	public void flightReturnDate() {
		String actualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText();

		if (theDayAfterTommorowDate < 10)
			expectedReturnDate = "0" + theDayAfterTommorowDate;
		else
			expectedReturnDate = theDayAfterTommorowDate + "";
		Assert.assertEquals(actualReturnDate, expectedReturnDate);
	}

	@Test(priority = 8)
	public void pageLanguageTest() {
		RandomlyChangeTheLanguage();
	}

	@Test(priority = 9)
	public void hotelsBtnAndSearch() {
		WebElement hotelBtn = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelBtn.click();

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		if (ActualLanguage.equals("en")) {
			
			WebElement searchBarInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
			searchBarInput.sendKeys(randomEnglishCountry);
		} else {
			
			WebElement searchBarInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
			searchBarInput.sendKeys(randomArabicCountry);
		}

		WebElement firstChoice = driver.findElement(By.className("AutoComplete__ListItem"));
		firstChoice.click();

	}

	@Test(priority = 10)
	public void selectNumberOfVisitors() {
		WebElement reserveHotelBtn = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select select = new Select(reserveHotelBtn);
		select.selectByIndex(randomIndexForNumberOfVisitors);

		WebElement searchBtn = driver.findElement(By.cssSelector(
				"body > div:nth-child(1) > section:nth-child(3) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > button:nth-child(1)"));
		searchBtn.click();
	}
	
	@Test(priority=11)
	public void pageLoadedSuccessfully() throws InterruptedException {
		Thread.sleep(10000);
		String result=driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
		boolean actualResult=result.contains("found")||result.contains("وجدنا");
		
		Assert.assertEquals(actualResult, expectedResultForPageLoaded);
	}
	
	@Test(priority=12)
	public void lowestPriceSortingTest() {
		
		WebElement lowestPriceBtn=driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		lowestPriceBtn.click();
		
		WebElement hotelsSortedResultContainer=driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> sortedHotelsList=hotelsSortedResultContainer.findElements(By.className("Price__Value"));
		
		String lowestPrice=sortedHotelsList.get(0).getText();
		String highestPrice=sortedHotelsList.get(sortedHotelsList.size()-1).getText();
		
		int lowestAsInt=Integer.parseInt(lowestPrice);
		int highestAsInt=Integer.parseInt(highestPrice);

		
		boolean actualResult=lowestAsInt<highestAsInt;
		
		Assert.assertEquals(actualResult, expectedResultForLowestPrice);
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

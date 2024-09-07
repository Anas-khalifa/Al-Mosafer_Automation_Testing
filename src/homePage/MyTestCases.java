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

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String AlmosaferURL = "https://sa.almosafer.com/en";
	String ExpectedLanguage = "en";
	Random rand = new Random();

	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(AlmosaferURL);
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"))
				.click();
	}

	@Test(priority = 1)
	public void defaultLanguageEnglishTest() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}

	@Test(priority = 2)
	public void defaultCurrencyTest() {
		String actualCurrency = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		String expectedCurrency = "SAR";
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3)
	public void contactNumberTest() {
		String actualContactNumber = driver.findElement(By.tagName("strong")).getText();
		String expectedContactNumber = "+966554400000";
		Assert.assertEquals(actualContactNumber, expectedContactNumber);
	}

	@Test(priority = 4)
	public void qitafLogoTest() {
		boolean actualQitafLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"))
				.isDisplayed();
		boolean expectedQitafLogo = true;
	}

	@Test(priority = 5)
	public void hotelSearchIsNotSelected() {
		String actualHotelButtonResult = driver.findElement(By.cssSelector("#uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		String expectedHotelButton = "false";
//		System.out.println(actualHotelButtonResult);
		Assert.assertEquals(actualHotelButtonResult, expectedHotelButton);
	}

	@Test(priority = 6)
	public void flightDepartureDate() {
		LocalDate todaysDate = LocalDate.now();
		String actualDepartureDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText();
		int tempDate = todaysDate.plusDays(1).getDayOfMonth();
		String expectedDepartureDate = "";
		if (tempDate < 10)
			expectedDepartureDate = "0" + tempDate;
		else
			expectedDepartureDate = tempDate + "";
//		System.out.println(expectedDepartureDate);

		Assert.assertEquals(actualDepartureDate, expectedDepartureDate);
	}

	@Test(priority = 7)
	public void flightReturnDate() {
		LocalDate todaysDate = LocalDate.now();
		String actualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText();
		int tempDate = todaysDate.plusDays(2).getDayOfMonth();
		String expectedReturnDate = "";
		if (tempDate < 10)
			expectedReturnDate = "0" + tempDate;
		else
			expectedReturnDate = tempDate + "";
//		System.out.println(expectedDepartureDate);

		Assert.assertEquals(actualReturnDate, expectedReturnDate);
	}

	@Test(priority = 8)
	public void pageLanguageTest() {
		String URLs[] = { "https://sa.almosafer.com/en", "https://sa.almosafer.com/ar" };
		int randomUrl = rand.nextInt(URLs.length);

		driver.get(URLs[randomUrl]);

	}

	@Test(priority = 9)
	public void hotelsBtnAndSearch() {
		WebElement hotelBtn = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelBtn.click();

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		if (ActualLanguage.equals("en")) {
			String CountriesInEnglish[] = { "Dubai", "Jeddah", "Riyadh" };
			int randomNumber = rand.nextInt(CountriesInEnglish.length);
			String randomCountry = CountriesInEnglish[randomNumber];
			WebElement searchBarInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
			searchBarInput.sendKeys(randomCountry);
		} else {
			String CountriesInArabic[] = { "جدة", "دبي" };
			int randomNumber = rand.nextInt(CountriesInArabic.length);
			String randomCountry = CountriesInArabic[randomNumber];
			WebElement searchBarInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
			searchBarInput.sendKeys(randomCountry);
		}

		WebElement firstChoice = driver.findElement(By.className("AutoComplete__ListItem"));
		firstChoice.click();

	}

	@Test(priority = 10)
	public void selectNumberOfVisitors() {
		WebElement reserveHotelBtn = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		int randomIndex = rand.nextInt(2);
		Select select = new Select(reserveHotelBtn);
		select.selectByIndex(randomIndex);

		WebElement searchBtn = driver.findElement(By.cssSelector(
				"body > div:nth-child(1) > section:nth-child(3) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > button:nth-child(1)"));
		searchBtn.click();
	}
	
	@Test(priority=11)
	public void pageLoadedSuccessfully() throws InterruptedException {
		Thread.sleep(10000);
		String result=driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
		boolean actualResult=result.contains("found")||result.contains("وجدنا");
		boolean expectedResult=true;
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	@Test(priority=12)
	public void lowestPriceSortingTest() {
		
		boolean expectedResult=true;
		WebElement lowestPriceBtn=driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		lowestPriceBtn.click();
		
		WebElement hotelsSortedResultContainer=driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> sortedHotelsList=hotelsSortedResultContainer.findElements(By.className("Price__Value"));
		
		String lowestPrice=sortedHotelsList.get(0).getText();
		String highestPrice=sortedHotelsList.get(sortedHotelsList.size()-1).getText();
		
		int lowestAsInt=Integer.parseInt(lowestPrice);
		int highestAsInt=Integer.parseInt(highestPrice);

		
		boolean actualResult=lowestAsInt<highestAsInt;
		
		Assert.assertEquals(actualResult, expectedResult);
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

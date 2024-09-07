package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	String AlmosaferURL = "https://sa.almosafer.com/en";
	String ExpectedLanguage = "en";
	Random rand = new Random();
	String expectedCurrency = "SAR";		
	String expectedContactNumber = "+966554400000";
	boolean expectedQitafLogo = true;
	String expectedHotelButton = "false";
	LocalDate todaysDate = LocalDate.now();
	int tommorowDate = todaysDate.plusDays(1).getDayOfMonth();
	int theDayAfterTommorowDate = todaysDate.plusDays(2).getDayOfMonth();
	String expectedDepartureDate = "";
	String expectedReturnDate = "";
	String CountriesInArabic[] = { "جدة", "دبي" };
	String randomArabicCountry = CountriesInArabic[rand.nextInt(CountriesInArabic.length)];
	String CountriesInEnglish[] = { "Dubai", "Jeddah", "Riyadh" };
	String randomEnglishCountry = CountriesInEnglish[rand.nextInt(CountriesInEnglish.length)];
	int randomIndexForNumberOfVisitors = rand.nextInt(2);
	boolean expectedResultForPageLoaded=true;
	boolean expectedResultForLowestPrice=true;

	
	public void GeneralSetUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(AlmosaferURL);
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"))
				.click();
	}
	
	public void RandomlyChangeTheLanguage() {
		String URLsForLanguageTesting[] = { "https://sa.almosafer.com/en", "https://sa.almosafer.com/ar" };
		int randomURLsForLanguageTesting = rand.nextInt(URLsForLanguageTesting.length);

		driver.get(URLsForLanguageTesting[randomURLsForLanguageTesting]);
	}

}

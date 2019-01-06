package stepdefs;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.Utility;

public class StepDefinitions {
	static Logger log = Logger.getLogger(StepDefinitions.class);
	WebDriver driver = null;
	Actions actions = null;
	LocalDate deptdate = null;

	@Given("^I am on the travelpage$")
	public void openBrowser() {
		BasicConfigurator.configure();
		System.setProperty("webdriver.chrome.driver", "C:\\workspace\\driver\\chromedriver.exe");// for
																									// Chrome
		System.setProperty("webdriver.gecko.driver", "C:\\workspace\\driver\\geckodriver.exe"); // for
		// Firefox
		if (driver == null) {
			driver = new ChromeDriver();
			actions = new Actions(driver);
		}
		driver.get("https://www.phptravels.net");
		driver.manage().window().maximize();
	}

	@Then("^I should see \"([^\"]*)\"$")
	public void i_should_see(String expectedTitle) throws Throwable {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);

	}

	@When("^I click FLIGHTS$")
	public void i_click_FLIGHTS() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"body-section\"]/section/div[2]/div/div/div[2]/ul/li[2]/a/span")).click();

	}

	@When("^I select from source with \"([^\"]*)\"$")
	public void i_select_from_with(String sourceName) throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"s2id_location_from\"]/a/span[1]")).click();
		WebElement locationFrom = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
		locationFrom.sendKeys(sourceName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElements(By.xpath("//*[@id=\"select2-drop\"]/ul"));
		List<WebElement> liElements = driver.findElements(By.xpath("//*[@id=\"select2-drop\"]/ul/li[1]/div"));

		for (WebElement element : liElements) {
			element.click();
		}
	}

	@When("^I select to destination with \"([^\"]*)\"$")
	public void i_select_to_with(String destinationName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		driver.findElement(By.xpath("//*[@id=\"s2id_location_to\"]/a/span[1]")).click();
		WebElement locationFrom = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
		locationFrom.sendKeys(destinationName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElements(By.xpath("//*[@id=\"select2-drop\"]/ul"));
		List<WebElement> liElements = driver.findElements(By.xpath("//*[@id=\"select2-drop\"]/ul/li[1]/div"));

		for (WebElement element : liElements) {
			element.click();
		}
	}

	@When("^I select to Round Trip$")
	public void i_select_to_Round_Trip() throws Throwable {
		driver.findElement(By.cssSelector("#flights > form > div.trip-check > div:nth-child(2) > div > div > ins"))
				.click();

	}

	@When("^I select departure date (\\d+) weeks from today's date$")
	public void i_select_departure_date_weeks_from_today_s_date(int arg1) throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"flights\"]/form/div[3]/div/input")).click();
		deptdate = Utility.getDeptDay(arg1); // this will return arg1 week
												// departure date from todays
												// date

		// to select the datepicker if arg1 date falls in next month
		if (Math.abs(deptdate.getMonthValue() - LocalDate.now().getMonthValue()) > 0) {
			WebElement dateWidget = driver.findElement(By.xpath("/html/body/div[14]/div[1]/table/thead/tr[1]/th[3]"));
			System.out.println("inside if condition");
			dateWidget.click();
		}

		// selecting the datepicker from the days of month
		int count = 0;
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 8; j++) {
				String dateval = driver
						.findElement(By.xpath("/html/body/div[14]/div[1]/table/tbody/tr[" + i + "]/td[" + j + "]"))
						.getAttribute("innerHTML");
				if (Integer.parseInt(dateval) == deptdate.getDayOfMonth() && count < 1) {
					count++;
					driver.findElement(By.xpath("/html/body/div[14]/div[1]/table/tbody/tr[" + i + "]/td[" + j + "]"))
							.click();
				}
			}
		}

	}

	@When("^I select return date (\\d+) weeks from departure date$")
	public void i_select_return_date_weeks_from_departure_date(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		LocalDate date = Utility.getReturnDay(arg1, deptdate); // this will
																// return arg1
																// week return
																// date from
																// departure
																// date

		// to select the datepicker if arg1 date falls in next month
		if (Math.abs(date.getMonthValue() - LocalDate.now().getMonthValue()) > 0) {
			WebElement dateWidget = driver.findElement(By.xpath("/html/body/div[15]/div[1]/table/thead/tr[1]/th[3]"));
			dateWidget.click();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// selecting the datepicker from the days of month
		int count = 0;
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 8; j++) {
				String dateval = driver
						.findElement(By.xpath("/html/body/div[15]/div[1]/table/tbody/tr[" + i + "]/td[" + j + "]"))
						.getAttribute("innerHTML");
				if ((Integer.parseInt(dateval) == date.getDayOfMonth()) && count < 1) {
					count++;
					driver.findElement(By.xpath("/html/body/div[15]/div[1]/table/tbody/tr[" + i + "]/td[" + j + "]"))
							.click();
				}
			}
		}
	}

	@When("^I select (\\d+) Adult$")
	public void i_select_Adult(int arg1) throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"flights\"]/form/div[5]/div/input")).click();
		Select adult = new Select(driver.findElement(
				By.xpath("//*[@id=\"manual_flightTravelers\"]/div/div/div[2]/section/div/div[1]/div[1]/select")));
		adult.selectByValue("2");
	}

	@When("^I select (\\d+) Child$")
	public void i_select_Child(int arg1) throws Throwable {
		Select child = new Select(driver.findElement(
				By.xpath("//*[@id=\"manual_flightTravelers\"]/div/div/div[2]/section/div/div[2]/div[1]/select")));
		child.selectByValue("2");
		driver.findElement(By.xpath("//*[@id=\"sumManualPassenger\"]")).click();
	}

	@When("^I click SEARCH button$")
	public void i_click_SEARCH_button() throws Throwable {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"flights\"]/form/div[6]/button")).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String actualTitle = driver.getTitle();
		String expectedTitle = "Flights List";
		assertEquals(expectedTitle, actualTitle);

	}

	@When("^I filter the first four Airlines$")
	public void i_filter_the_with() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[4]/div/div[2]/div/div[2]/div[5]/div/div[1]/ins"))
				.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[4]/div/div[2]/div/div[2]/div[7]/div/div[1]/ins"))
				.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[4]/div/div[2]/div/div[2]/div[9]/div/div[1]/ins"))
				.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[4]/div/div[2]/div/div[2]/div[11]/div/div[1]/ins"))
				.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@When("^I click on BOOK NOW with the cheapest price$")
	public void i_click_on_BOOK_NOW_with_the_cheapest_price() throws Throwable {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int tablerows = driver.findElements(By.xpath("//*[@id=\"load_data\"]/tbody/tr")).size();
		// Creating map to retrieve pricelist with rowid
		Map<Integer, Integer> pricelist = new HashMap<Integer, Integer>();

		for (int i = 1; i <= tablerows; i++) {
			String pricedata = driver
					.findElement(By.xpath("//*[@id=\"load_data\"]/tbody/tr[" + i + "]/td/div[2]/p/span"))
					.getAttribute("innerHTML");
			pricedata = pricedata.substring(0, pricedata.length() - 1);
			pricelist.put(i, Integer.parseInt(pricedata));
		}

		// sort the map with values in ascending order and getting the first
		// element of sorted map
		Map.Entry<Integer, Integer> entry = Utility.sortByValue(pricelist).entrySet().iterator().next();

		Integer lowestpricekey = entry.getKey();
		driver.findElement(By.xpath("//*[@id=\"load_data\"]/tbody/tr[" + lowestpricekey + "]/td/div[2]/p/button"))
				.sendKeys(Keys.ENTER);
		log.info("price list retrieved:: " + lowestpricekey + "  lowest value: " + entry.getValue());
	}

	@Then("^I am taken to booking page$")
	public void i_am_taken_to_booking_page() throws Throwable {
		// check the text Booking summary should be displayed on the booking
		// page
		String BookingPageText = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div/div/div[1]/div/div[2]/h4"))
				.getText();
		String ExpectedResult = "BOOKING SUMMARY";
		assertEquals(ExpectedResult, BookingPageText);
		log.info("feature file completed successfully");
		driver.quit();

	}

}
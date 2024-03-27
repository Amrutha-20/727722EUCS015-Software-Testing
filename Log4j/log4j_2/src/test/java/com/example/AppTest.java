package com.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class AppTest 
{
    private static final Logger logger = LogManager.getLogger(AppTest.class);

    @Test
    public static void main(String[] args) throws ElementNotInteractableException, NoSuchElementException {
        // Configure Log4j
        System.setProperty("log4j.configurationFile", "log4j2.xml");

        // Set up Selenium WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        try {
            // Step 1: Open browser and navigate to OpenTable website
            driver.get("https://www.opentable.com/");
            logger.info("Opened browser and navigated to OpenTable website.");

            // Step 2: Read data from Excel sheet and enter city into search box
            String city = "Bangalore"; // Hardcoded for simplicity
            WebElement searchBox = driver.findElement(By.id("searchbar"));
            searchBox.sendKeys(city);
            logger.info("Entered city '{}' into the search box.", city);

            // Step 3: Click the "Let's Go" button
            WebElement letsGoButton = driver.findElement(By.id("submitButton"));
            letsGoButton.click();
            logger.info("Clicked the 'Let's Go' button.");

            // Step 4: Click on the "Asian" checkbox under "Cuisine" filter section
            WebElement asianCheckbox = driver.findElement(By.xpath("//label[text()='Asian']"));
            asianCheckbox.click();
            logger.info("Clicked on the 'Asian' checkbox under 'Cuisine' filter section.");

            // Step 5: Click on the first hotel result
            WebElement firstHotelResult = driver.findElement(By.xpath("//div[@class='result content-section-list-row cf with-times']/a"));
            firstHotelResult.click();
            logger.info("Clicked on the first hotel result.");

            // Step 6: Capture screenshot of the hotel details page
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
            // Save screenshot logic

            // Step 7: Scroll down to "Make a Reservation" section
            WebElement makeReservationSection = driver.findElement(By.xpath("//section[@id='reservation']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", makeReservationSection);
            logger.info("Scrolled down to 'Make a Reservation' section.");

            // Step 8: Choose "4 people" from party size dropdown
            WebElement partySizeDropdown = driver.findElement(By.xpath("//select[@id='partySize']"));
            partySizeDropdown.sendKeys("4");
            logger.info("Selected '4 people' from party size dropdown.");

            // Step 9: Select future date in November month
            WebElement dateDropdown = driver.findElement(By.xpath("//input[@id='datepicker']"));
            dateDropdown.sendKeys("11/11/2023");
            logger.info("Selected future date 'Nov 11, 2023'.");

            // Step 10: Select time "06:30 PM" and click "Find a time" button
            WebElement timeDropdown = driver.findElement(By.xpath("//select[@id='timeslot']"));
            timeDropdown.sendKeys("06:30 PM");
            WebElement findTimeButton = driver.findElement(By.xpath("//button[text()='Find a time']"));
            findTimeButton.click();
            logger.info("Selected time '06:30 PM' and clicked 'Find a time' button.");

            // Step 11: Click the "Sign In" button
            WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
            signInButton.click();
            logger.info("Clicked the 'Sign In' button.");
        } catch (WebDriverException e) {
            logger.error("Exception occurred: {}", e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
            logger.info("Browser closed.");
        }
    }
}



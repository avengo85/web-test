package com.hellofresh.challenge;

import com.automationpractice.Pages.AccountCreationPage;
import com.automationpractice.Pages.HomePage;
import com.automationpractice.Pages.LoginPage;
import com.automationpractice.Pages.MyAccountPage;
import com.automationpractice.framework.BasePage;
import com.automationpractice.framework.BaseTest;
import org.testng.annotations.*;
import org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class WebTest extends BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    private HomePage homePage;
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;
    private AccountCreationPage accountCreationPage;
    private final String existingUserEmail = "hf_challenge_123456@hf123456.com";
    private final String existingUserPassword = "12345678";
    private final String EXISTING_FULL_NAME = "Joe Black";
    private static final String[] DATE_OF_BIRTH = {"1", "1", "2000"};
    private static final String FIRST_NAME = "Firstname";
    private static final String LAST_NAME = "Lastname";
    private static final String PASSWORD = "passwd";
    private static final String COMPANY = "Company";
    private static final String[] ADDRESS_LINES = {"Qwerty, 123", "zxcvb"};
    private static final String CITY = "Qwerty";
    private static final String STATE = "Colorado";
    private static final String POSTCODE = "12345";
    private static final String OTHER = "Qwerty";
    private static final String HOME_PHONE = "12345123123";
    private static final String MOBILE_PHONE = "12345123123";
    private static final String ALIAS = "hf";
    private static final String WELCOME_MESSAGE = "Welcome to your account.";
    private static final String URL_TEXT = "controller=my-account";
    private static final String MYACC_HEADER_TEXT = "MY ACCOUNT";

    @BeforeMethod
    public void setUp() {

        homePage = BasePage.initPage(HomePage.class);

    }

    @Test

    public void signInTest() {
        loginPage = homePage.clickSignInButton();
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        accountCreationPage = loginPage.signIn(email);
        accountCreationPage.selectGender2();
        accountCreationPage.fillFirstName(FIRST_NAME);
        accountCreationPage.fillLastName(LAST_NAME);
        accountCreationPage.fillPassword(PASSWORD);
        accountCreationPage.fillDateOfBirth(DATE_OF_BIRTH);
        myAccountPage = accountCreationPage.fillYourAddress(COMPANY, ADDRESS_LINES, CITY, STATE, POSTCODE, OTHER, HOME_PHONE, MOBILE_PHONE, ALIAS);
        assertEquals(myAccountPage.getHeaderText(), MYACC_HEADER_TEXT, "The header should be " + MYACC_HEADER_TEXT);
        assertEquals(myAccountPage.getNameText(), FIRST_NAME + " " + LAST_NAME);
        assertTrue(myAccountPage.getWelcome().contains(WELCOME_MESSAGE), "Welcome message are not displayed");
        assertTrue(myAccountPage.isLogoutDisplayed(), "Logout link is not displayed");
        assertTrue(myAccountPage.getURL().contains(URL_TEXT));
    }

    @Test
    public void logInTest() {
        loginPage = homePage.clickSignInButton();
        myAccountPage = loginPage.login(existingUserEmail, existingUserPassword);
        assertEquals(myAccountPage.getHeaderText(), MYACC_HEADER_TEXT, "The header should be " + MYACC_HEADER_TEXT);
        assertEquals(myAccountPage.getNameText(), EXISTING_FULL_NAME);
        assertTrue(myAccountPage.getWelcome().contains(WELCOME_MESSAGE), "Welcome message are not displayed");
        assertTrue(myAccountPage.isLogoutDisplayed(), "Logout link is not displayed");
        assertTrue(myAccountPage.getURL().contains(URL_TEXT));
    }

    // Did not have time to re-write the last test
    //@Test
    public void checkoutTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
        driver.findElement(By.id("email")).sendKeys(existingUserEmail);
        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
        driver.findElement(By.id("SubmitLogin")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
        driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
        driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();
        driver.findElement(By.name("processCarrier")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button"))).click();
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        assertEquals("ORDER CONFIRMATION", heading.getText());
        assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
        assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
    }

    //    @AfterMethod
    public void TearDowm() {
        driver.close();
    }


}


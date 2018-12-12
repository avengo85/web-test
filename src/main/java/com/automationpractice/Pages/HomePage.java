package com.automationpractice.Pages;

import com.automationpractice.framework.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static final Logger log = Logger.getLogger(HomePage.class);
    private static final String SIGNIN = "login";


    @FindBy(className = SIGNIN)
    WebElement signInButton;


    public LoginPage clickSignInButton() {
        log.info("Clicking 'Sign In'...");
        signInButton.click();
        return initPage(LoginPage.class);
    }
}

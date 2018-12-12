package com.automationpractice.Pages;

import com.automationpractice.framework.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    private static final Logger log = Logger.getLogger(MyAccountPage.class);
    private static final String HEADER_CSS = "h1";
    private static final String ACC_NAME_CLASS = "account";
    private static final String WELCOME_CLASS = "info-account";
    private static final String LOGOUT_LINK_CLASS = "logout";


    @FindBy(css = HEADER_CSS)
    WebElement header;

    @FindBy(className = ACC_NAME_CLASS)
    WebElement accName;

    @FindBy(className = WELCOME_CLASS)
    WebElement welcome;

    @FindBy(className = LOGOUT_LINK_CLASS)
    WebElement logoutLink;

    public String getHeaderText() {
        String text = header.getText();
        log.info("Header is " + text);
        return text;
    }

    public String getNameText() {
        String text = accName.getText();
        log.info("Account name is " + text);
        return text;
    }

    public String getURL() {
        String text = driver.getCurrentUrl();
        log.info("URL is " + text);
        return text;

    }

    public boolean isLogoutDisplayed() {
        log.info("Checking if Logout displaying...");
        return logoutLink.isDisplayed();
    }

    public String getWelcome() {
        String text = welcome.getText();
        log.info("Welcome message = " + text);
        return text;

    }
}

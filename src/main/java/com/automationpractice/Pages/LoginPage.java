package com.automationpractice.Pages;

import com.automationpractice.framework.BasePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private static final Logger log = Logger.getLogger(LoginPage.class);

    private static final String EMAIL_CREATE_ID = "email_create";
    private static final String SUBMIT_CREATE_ID = "SubmitCreate";

    private static final String EMAIL_EXISTED_ID = "email";
    private static final String PASSWORD_ID = "passwd";
    private static final String SUBMIT_LOGIN_ID = "SubmitLogin";

    @FindBy(id = EMAIL_CREATE_ID)
    WebElement emailCreateTextField;

    @FindBy(id = SUBMIT_CREATE_ID)
    WebElement submitCreateButton;

    @FindBy(id = EMAIL_EXISTED_ID)
    WebElement emailExistedTextField;

    @FindBy(id = PASSWORD_ID)
    WebElement passwordTextField;

    @FindBy(id = SUBMIT_LOGIN_ID)
    WebElement submitLoginButton;

    public AccountCreationPage signIn(String email) {
        log.info("Filling a new email...");
        emailCreateTextField.sendKeys(email);
        log.info("Submiting...");
        submitCreateButton.click();
        return initPage(AccountCreationPage.class);
    }

    public MyAccountPage login(String email, String password) {
        log.info("Logging in with existing email...");
        emailExistedTextField.sendKeys(email);
        passwordTextField.sendKeys(password);
        submitLoginButton.click();
        return initPage(MyAccountPage.class);
    }
}

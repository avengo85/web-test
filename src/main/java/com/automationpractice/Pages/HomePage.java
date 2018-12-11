package com.automationpractice.Pages;

import com.automationpractice.framework.BasePage;
import com.automationpractice.framework.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public static final String SIGNIN = "login";

    public static final String UPASSWORD_TEXT_FIELD_XPATH = "//input[@id='j_password']";
    public static final String ULOGIN_BUTTON_XPATH = "//div[@class='form']/form/input";




    @FindBy(className = SIGNIN)
    WebElement signInButton;





    public LoginPage clickSignInButton() {
        signInButton.click();
        return initPage(LoginPage.class);
            }
}

package com.automationpractice.Pages;

import com.automationpractice.framework.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.swing.undo.CannotUndoException;

public class AccountCreationPage extends BasePage {
    public static final String GENDER2_ID = "id_gender2";
    public static final String FIRSTNAME_TEXTFIELD_ID = "customer_firstname";
    public static final String LASTNAME_TEXTFIELD_ID = "customer_lastname";
    public static final String PASSWORD_TEXTFIELD_ID = "passwd";
    public static final String DAY_BIRTH_SELECT_ID = "days";
    public static final String MONTH_BIRTH_SELECT_ID = "months";
    public static final String YEAR_BIRTH_SELECT_ID = "years";

    @FindBy(id = GENDER2_ID)
    WebElement gender2RadioButton;

    @FindBy(id = FIRSTNAME_TEXTFIELD_ID)
    WebElement firstnameTextfield;

    @FindBy(id = LASTNAME_TEXTFIELD_ID)
    WebElement lastnameTextfield;

    @FindBy(id = PASSWORD_TEXTFIELD_ID)
    WebElement passwordTextfield;


//    public HomePage loginAs(User user) {
//        userNameTextField.clear();
//        passwordTextField.clear();
//        userNameTextField.sendKeys(user.getUsername());
//        passwordTextField.sendKeys(user.getPassword());
//        loginButton.click();
//        return initPage(HomePage.class);
//    }


}


package com.automationpractice.Pages;

import com.automationpractice.framework.BasePage;
import org.apache.commons.exec.util.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import javax.swing.undo.CannotUndoException;
import java.util.List;

public class AccountCreationPage extends BasePage {

    private static final Logger log = Logger.getLogger(LoginPage.class);

    private static final String GENDER2_ID = "id_gender2";
    private static final String FIRSTNAME_TEXTFIELD_ID = "customer_firstname";
    private static final String LASTNAME_TEXTFIELD_ID = "customer_lastname";
    private static final String PASSWORD_TEXTFIELD_ID = "passwd";
    private static final String COMPANY_TEXTFIELD_ID = "company";
    private static final String ADDRESS1_TEXTFIELD_ID = "address1";
    private static final String ADDRESS2_TEXTFIELD_ID = "address2";
    private static final String CITY_TEXTFIELD_ID = "city";
    private static final String POSTCODE_TEXTFIELD_ID = "postcode";
    private static final String ADDINFO_TEXTFIELD_ID = "other";
    private static final String HOME_PHONE_TEXTFIELD_ID = "phone";
    private static final String MOBILE_PHONE_TEXTFIELD_ID = "phone_mobile";
    private static final String ALIAS_TEXTFIELD_ID = "alias";
    private static final String DAY_BIRTH_SELECT_ID = "days";
    private static final String MONTH_BIRTH_SELECT_ID = "months";
    private static final String YEAR_BIRTH_SELECT_ID = "years";
    private static final String STATE_SELECT_ID = "id_state";
    private static final String REGISTER_BUTTON_ID = "submitAccount";

    @FindBy(id = GENDER2_ID)
    private WebElement gender2RadioButton;

    @FindBy(id = REGISTER_BUTTON_ID)
    private WebElement registerButton;

    @FindBy(id = FIRSTNAME_TEXTFIELD_ID)
    private WebElement firstnameTextfield;

    @FindBy(id = LASTNAME_TEXTFIELD_ID)
    private WebElement lastnameTextfield;

    @FindBy(id = PASSWORD_TEXTFIELD_ID)
    private WebElement passwordTextfield;

    @FindBy(id = COMPANY_TEXTFIELD_ID)
    private WebElement companyTextfield;

    @FindBy(id = ADDRESS1_TEXTFIELD_ID)
    private WebElement address1Textfield;

    @FindBy(id = ADDRESS2_TEXTFIELD_ID)
    private WebElement address2Textfield;

    @FindBy(id = CITY_TEXTFIELD_ID)
    private WebElement cityTextfield;

    @FindBy(id = POSTCODE_TEXTFIELD_ID)
    private WebElement postCodeTextfield;

    @FindBy(id = ADDINFO_TEXTFIELD_ID)
    private WebElement addInfoTextfield;

    @FindBy(id = HOME_PHONE_TEXTFIELD_ID)
    private WebElement homePhoneTextfield;

    @FindBy(id = MOBILE_PHONE_TEXTFIELD_ID)
    private WebElement mobilePhoneTextfield;

    @FindBy(id = ALIAS_TEXTFIELD_ID)
    private WebElement aliasPhoneTextfield;

    public void fillDateOfBirth(String[] dateOfBirth) {
        log.info("Filling Date of birth...");
        getSelectById(DAY_BIRTH_SELECT_ID).selectByValue(dateOfBirth[0]);
        getSelectById(MONTH_BIRTH_SELECT_ID).selectByValue(dateOfBirth[1]);
        getSelectById(YEAR_BIRTH_SELECT_ID).selectByValue(dateOfBirth[2]);
    }


    public void selectGender2() {
        log.info("Selecting Gender...");
        gender2RadioButton.click();
    }

    public void fillFirstName(String firstName) {
        log.info("Filling First Name...");
        firstnameTextfield.sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        log.info("Filling Last Name...");
        lastnameTextfield.sendKeys(lastName);
    }

    public void fillPassword(String password) {
        log.info("Filling Password...");
        passwordTextfield.sendKeys(password);
    }

    public MyAccountPage fillYourAddress(String company, String[] addressLines, String city, String state, String postCode, String otherInfo,
                                         String homePhone, String mobilePhone, String alias) {
        log.info("Filling Your Address items...");
        companyTextfield.sendKeys(company);
        address1Textfield.sendKeys(addressLines[0]);
        address2Textfield.sendKeys(addressLines[1]);
        cityTextfield.sendKeys(city);
        getSelectById(STATE_SELECT_ID).selectByVisibleText(state);
        postCodeTextfield.sendKeys(postCode);
        addInfoTextfield.sendKeys(otherInfo);
        homePhoneTextfield.sendKeys(homePhone);
        mobilePhoneTextfield.sendKeys(mobilePhone);
        aliasPhoneTextfield.sendKeys(alias);
        registerButton.click();
        return initPage(MyAccountPage.class);
    }

    private Select getSelectById(String id) {
        return new Select(driver.findElement(By.id(id)));
    }


}


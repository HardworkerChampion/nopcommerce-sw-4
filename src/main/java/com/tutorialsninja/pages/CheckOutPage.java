package com.tutorialsninja.pages;

import com.tutorialsninja.utilities.Utility;
import org.openqa.selenium.By;

public class CheckOutPage extends Utility {
    By checkoutButtonLink = By.xpath("//a[@class='btn btn-primary']");
    By guestCheckoutLink = By.xpath("//input[@value='guest']");
    By continueButtonLink = By.xpath("//input[@id='button-account']");
    By firstNameFieldLink = By.xpath("//input[@id='input-payment-firstname']");
    By lastNameFieldLink = By.xpath("//input[@id='input-payment-lastname']");
    By telephoneFieldLink = By.xpath("//input[@id='input-payment-telephone']");
    By address1FieldLink = By.xpath("//input[@id='input-payment-address-1']");
    By termsAndConditionsRadioBox = By.name("agree");


public void clickOnCheckoutButton(){
    clickOnElement(checkoutButtonLink);
}
public void clickOnGuestCheckoutRadioButton(){
    clickOnElement(guestCheckoutLink);
}
   public void clickOnContinueTab(){
    clickOnElement(continueButtonLink);
   }
   public void fillTheMandatoryFields(){
    sendTextToElement(firstNameFieldLink,"John");
       sendTextToElement(lastNameFieldLink,"Cena");
       sendTextToElement(By.xpath("//input[@id='input-payment-email']"),"John@gmail.com");
       sendTextToElement(telephoneFieldLink,"07442576377");
       sendTextToElement(address1FieldLink,"PaulhanRoad");
       sendTextToElement(By.xpath("//input[@id='input-payment-city']"),"London");
       sendTextToElement(By.xpath("//input[@id='input-payment-postcode']"),"HA39AS");
       selectByValueFromDropDown(By.xpath("///select[@id='input-payment-country']"),"222");
       selectByValueFromDropDown(By.xpath("//select[@id='input-payment-zone']"),"3513");
}
    public void clickOnAgreeToTermsAndConditions() {
        clickOnElement(termsAndConditionsRadioBox);
    }

}

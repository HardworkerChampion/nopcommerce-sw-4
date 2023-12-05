package com.tutorialsninja.pages;

import com.tutorialsninja.utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends Utility {

    By desktopsLink = By.linkText("Desktops");
    By laptopsAndNotebooksLink = By.linkText("Laptops & Notebooks");
    By componentsLink = By.linkText("Components");
    By showAllDesktopLink = By.linkText("Show AllDesktops");
    By currencyDropdownLink = By.xpath("//span[text()='Currency']");
    By poundSterlingLink = By.xpath("//span[contains(text(),'Currency')]");
    By myAccounts = By.xpath("//span[contains(text(),'My Account')]");
    By registerAccountText = By.xpath("//h1[contains(text(),'Register Account')]");
    By loginAccountText = By.xpath("//h2[contains(text(),'Returning Customer')]");
    public void mouseHoverOnDesktopsTabAndClick (){
        mouseHoverToElementAndClick(desktopsLink);

    }
    public void mouseHoverOnLaptopsAndNotebooksTabAndClick(){
        mouseHoverToElementAndClick(laptopsAndNotebooksLink);
    }
    public void mouseHoverOnComponentsTabAndClick(){
        mouseHoverToElementAndClick(componentsLink);
    }
    public void clickOnShowAllDesktops(){
        clickOnElement(showAllDesktopLink);
    }
    public void mouseHoverOnCurrencyDropdownAndClick(){
        mouseHoverToElementAndClick(currencyDropdownLink);
    }
    public void mouseHoverOnPoundSterlingAndClick(){
        mouseHoverToElementAndClick(poundSterlingLink);
    }
    public void clickOnMyAccount() {
        clickOnElement(myAccounts);
    }
    public void selectMyAccountOptions(String option) {
        //This method should click on the options whatever name is passed as parameter.
        List<WebElement> registerList = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));//list with two options(do multi select)
        for (WebElement option1 : registerList) {
            if (option1.getText().equals(option)) {
                option1.click();
                break;
            }
        }
    }
    public String getRegisterAccountText() {
        return getTextFromElement(registerAccountText);
    }
    public String getLoginAccountText() {
        return getTextFromElement(loginAccountText);
    }
}

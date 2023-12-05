package com.tutorialsninja.pages;

import com.tutorialsninja.utilities.Utility;
import org.openqa.selenium.By;

public class DesktopsPage extends Utility {

    By productHPLP3065Link = By.xpath("//a[contains(text(),'HP LP3065')]");
    By addToCartLink = By.xpath("//button[@id='button-cart']");

    By shoppingCartLink = By.xpath("//a[contains(text(),'shopping cart')]");

    public void selectProductHPLP3065() {
        clickOnElement(productHPLP3065Link);
    }

    public void enterQty1UsingSelectClass() {
        sendTextToElement(By.name("quantity"), "1");

    }

    public void clickOnAddToCartButton() {
        clickOnElement(addToCartLink);
    }

    public void clickOnLinkShoppingCartDisplayIntoSuccessMessage() {
        clickOnElement(shoppingCartLink);
    }
}
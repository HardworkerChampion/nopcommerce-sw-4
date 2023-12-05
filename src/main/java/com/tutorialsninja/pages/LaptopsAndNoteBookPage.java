package com.tutorialsninja.pages;

import com.tutorialsninja.utilities.Utility;
import org.openqa.selenium.By;

public class LaptopsAndNoteBookPage extends Utility {
    By macBookLink = By.linkText("MacBook");

    public void selectProductMacBook(){
        clickOnElement(macBookLink);
    }
}

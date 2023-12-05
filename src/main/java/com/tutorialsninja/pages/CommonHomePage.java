package com.tutorialsninja.pages;

import com.tutorialsninja.utilities.Utility;
import org.openqa.selenium.By;

public class CommonHomePage extends Utility {

    By desktopsLink = By.linkText("Desktops");

    public void mouseHoverOnDesktopsTab() {
        mouseHoverToElementAndClick(desktopsLink);
    }
}
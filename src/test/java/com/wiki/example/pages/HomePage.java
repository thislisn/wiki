package com.wiki.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.wiki.example.framework.Waiter.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS;

public class HomePage extends BasePage {
    private static final String PAGE_URL = "https://www.wikipedia.org/";

    private SelenideElement searchInput = $(By.cssSelector("input#searchInput"));
    private SelenideElement submitButton = $(By.cssSelector("button.pure-button"));

    public void open() {
        Selenide.open(PAGE_URL);
        waiForUrl(PAGE_URL);
    }

    public void searchFor(String key) {
        searchInput.isDisplayed();
        searchInput.waitUntil(Condition.visible, DEFAULT_TIMEOUT_5_000_MS);
        searchInput.setValue(key);
        submitButton.click();
    }
}

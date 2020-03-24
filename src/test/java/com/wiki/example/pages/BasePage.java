package com.wiki.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.wiki.example.framework.Waiter;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.wiki.example.framework.Waiter.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS;

abstract class BasePage {

    void waiForUrl(String url) {
        Waiter.waitUntilExpected(webDriver ->
                getWebDriver().getCurrentUrl().equals(url), DEFAULT_TIMEOUT_5_000_MS);
    }

    void waiForHeader(SelenideElement header, String name) {
        Waiter.waitUntilExpected(webDriver ->
                header.getText().equals(name), DEFAULT_TIMEOUT_5_000_MS);
    }
}

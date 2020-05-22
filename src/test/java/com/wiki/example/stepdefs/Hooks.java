package com.wiki.example.stepdefs;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.Before;

import static com.codeborne.selenide.WebDriverRunner.CHROME;

public class Hooks {

    @Before(value = "@chrome", order = 1)
    public void initChrome() {
        Configuration.browser = CHROME;
    }
}

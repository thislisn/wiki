package com.wiki.example.stepdefs;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;

import static com.codeborne.selenide.WebDriverRunner.CHROME;

public class Hooks {

    @Before(value = "@chrome", order = 1)
    public void initChrome() {
        Configuration.browser = CHROME;
    }
}

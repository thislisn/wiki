package com.wiki.example.stepdefs;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.Before;


import static com.codeborne.selenide.WebDriverRunner.EDGE;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;
import static com.codeborne.selenide.WebDriverRunner.CHROME;

public class Hooks {

    @Before(value = "@chrome", order = 1)
    public void initChrome() {
        Configuration.browser = CHROME;
    }

    @Before(value = "@firefox", order = 1)
    public void initFirefox() {
        Configuration.browser = FIREFOX;
    }

    @Before(value = "@edge", order = 1)
    public void initEdge() {
        Configuration.browser = EDGE;
    }
}

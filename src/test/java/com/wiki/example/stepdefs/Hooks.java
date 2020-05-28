package com.wiki.example.stepdefs;

import com.codeborne.selenide.Configuration;
import com.wiki.example.framework.TestRailHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.WebDriverRunner.CHROME;

public class Hooks {
    private final static TestRailHelper testRailHelper = new TestRailHelper();

    @Before(value = "@chrome", order = 1)
    public void innit() {
        Configuration.browser = CHROME;
        testRailHelper.run();
    }

    @After()
    public void close() {
        testRailHelper.closeRun();
        testRailHelper.setProjectStatusCompleted();
    }
}

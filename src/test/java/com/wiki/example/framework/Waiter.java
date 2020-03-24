package com.wiki.example.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import static com.codeborne.selenide.Configuration.pollingInterval;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.wiki.example.framework.Waiter.TimeOutConstants.DEFAULT_TIMEOUT_20_000_MS;

public class Waiter {
    public static <T> T waitUntilExpected(Function<WebDriver, T> function, final long... msToWait) {
        long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : DEFAULT_TIMEOUT_20_000_MS;
        WebDriverWait wait = new WebDriverWait(getWebDriver(), msToWaitLoc / 1000);
        wait.pollingEvery(Duration.of(pollingInterval, ChronoUnit.MILLIS));
        return wait.until(function);
    }

    public class TimeOutConstants {
        public static final int DEFAULT_TIMEOUT_20_000_MS = 20_000;
        public static final int DEFAULT_TIMEOUT_5_000_MS = 5_000;
    }
}

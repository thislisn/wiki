package com.wiki.example.framework;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.WebDriverRunner.CHROME;
import static com.codeborne.selenide.WebDriverRunner.EDGE;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);

    private static ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void setup() {
        switch (Configuration.browser) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                setDriver(new ChromeDriver(options));
                break;
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                setDriver(new FirefoxDriver());
                break;
            }
            case EDGE: {
                WebDriverManager.edgedriver().setup();
                setDriver(new EdgeDriver());
                break;
            }
            default: {
                throw new RuntimeException("Browser " + Configuration.browser + " is not supported");
            }
        }
    }

    private static void setDriver(WebDriver driver) {
        logger.debug("Setting the driver");
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        logger.debug("Getting instance of remote driver");
        if (DRIVER.get() == null) {
            setup();
        }
        return DRIVER.get();
    }


    public static void closeDriver() {
        if (!(DRIVER.get() == null)) {
            getDriver().close();
        }
    }
}

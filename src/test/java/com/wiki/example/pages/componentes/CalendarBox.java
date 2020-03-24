package com.wiki.example.pages.componentes;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.wiki.example.framework.Waiter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.wiki.example.framework.Waiter.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS;

public class CalendarBox {

    private SelenideElement selectedDay = $(By.cssSelector(SELECTED_DAY));
    private static final String MAIN_XPATH = "table.toccolours.floatright";
    private static final String DAY_BUTTON = MAIN_XPATH + " a[title='%s']";
    private static final String SELECTED_DAY = MAIN_XPATH + " a.mw-selflink";

    public void clickOnDayButton(String month, int day) {
        String key = String.format("%s %s", month, day);
        SelenideElement dayButton = $(By.cssSelector(String.format(DAY_BUTTON, key)));
        dayButton.waitUntil(Condition.visible, Waiter.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS);
        dayButton.click();
        waitSelectedIs(String.valueOf(day));
    }

    private void waitSelectedIs(String day) {
        Waiter.waitUntilExpected(webDriver ->
                selectedDay.getText().equals(day), DEFAULT_TIMEOUT_5_000_MS);
    }

    public String getSelectedDay(){
        selectedDay.waitUntil(Condition.visible, Waiter.TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS);
        return selectedDay.getText();
    }
}

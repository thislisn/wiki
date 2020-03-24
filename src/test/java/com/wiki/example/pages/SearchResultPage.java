package com.wiki.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.wiki.example.framework.BaseContext;
import com.wiki.example.framework.ContextKey;
import com.wiki.example.framework.SearchKey;
import com.wiki.example.framework.dto.Cities;
import com.wiki.example.framework.dto.Continents;
import com.wiki.example.pages.componentes.CalendarBox;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.wiki.example.framework.CountriesCollector.collectCountries;
import static com.wiki.example.framework.JsonReader.readJson;

public class SearchResultPage extends BasePage {
    private static final String PAGE_URL = "https://en.wikipedia.org/wiki/%s";
    private static final String CITIES_JSON_PATH = "src/cities.json";
    private static final String CONTINENTS_JSON_PATH = "src/continents.json";

    private CalendarBox calendar = new CalendarBox();
    private SelenideElement header = $(By.cssSelector("h1#firstHeading"));
    private List<SelenideElement> links = $$(By.cssSelector("#mw-content-text a"));

    public void waiForPageLoaded(SearchKey key) {
        String uriKey = String.format("%s_%s", key.getFullMonthName(), key.getDay());
        waiForUrl(String.format(PAGE_URL, uriKey));
        String headerName = String.format("%s %s", key.getFullMonthName(), key.getDay());
        waiForHeader(header, headerName);
    }

    public List<String> getIsoCountries() {
        List<String> countries;
        if (BaseContext.getValue(ContextKey.COUNTRIES) == null) {
            countries = collectCountries();
            BaseContext.setValue(ContextKey.COUNTRIES, countries);
        } else {
            countries = (List<String>) BaseContext.getValue(ContextKey.COUNTRIES);
        }
        return countries;
    }

    public Continents getContinents() {
        Continents continents;
        if (BaseContext.getValue(ContextKey.CONTINENTS) == null) {
            continents = (Continents) readJson(CONTINENTS_JSON_PATH, Continents.class);
            BaseContext.setValue(ContextKey.CONTINENTS, continents);
        } else {
            continents = (Continents) BaseContext.getValue(ContextKey.CONTINENTS);
        }
        return continents;
    }

    public Cities getCities() {
        Cities continents;
        if (BaseContext.getValue(ContextKey.CITIES) == null) {
            continents = (Cities) readJson(CITIES_JSON_PATH, Cities.class);
            BaseContext.setValue(ContextKey.CITIES, continents);
        } else {
            continents = (Cities) BaseContext.getValue(ContextKey.CITIES);
        }
        return continents;
    }

    public Map<String, List<String>> getAllLinksMap() {
        String selectedDay = calendar.getSelectedDay();
        Map<String, List<String>> linksMap;
        List<String> linksList;
        if (BaseContext.getValue(ContextKey.LINKS_MAP) == null) {
            BaseContext.setValue(ContextKey.LINKS_MAP, new HashMap<>());
    }
            Map<String, List<String>> map = (Map<String, List<String>>) BaseContext.getValue(ContextKey.LINKS_MAP);
            if (!map.containsKey(selectedDay)) {
                linksMap = map;
                linksList = links.stream()
                        .filter(link -> !link.getText().isEmpty() && link.getText() != null)
                        .distinct()
                        .map(SelenideElement::getText)
                        .collect(Collectors.toList());
                linksMap.put(selectedDay, linksList);
                BaseContext.setValue(ContextKey.LINKS_MAP, linksMap);
        }  else {
            linksMap = (Map<String, List<String>>) BaseContext.getValue(ContextKey.LINKS_MAP);
        }
        return linksMap;
    }

    public String getSelectedDay(){
       return calendar.getSelectedDay();
    }

    public List<Integer> getFoundContinentsResults() {
        return innitList(ContextKey.FOUND_CONTINENTS);
    }

    public List<Integer> getFoundCountriesResults() {
        return innitList(ContextKey.FOUND_COUNTRIES);
    }

    public List<Integer> getFoundCitiesResults() {
        return innitList(ContextKey.FOUND_CITIES);
    }

    private List<Integer> innitList(ContextKey key) {
        return BaseContext.getValue(key) != null
                ? (List<Integer>) BaseContext.getValue(key)
                : new ArrayList<>();
    }

    public void clickDate(String monthName, int day) {
        calendar.clickOnDayButton(monthName, day);
    }
}

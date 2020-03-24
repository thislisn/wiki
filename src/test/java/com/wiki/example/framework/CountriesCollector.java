package com.wiki.example.framework;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CountriesCollector {

    public static List<String> collectCountries() {
        String[] locales = Locale.getISOCountries();
        return Arrays.stream(locales)
                .map(countryCode -> new Locale("", countryCode))
                .map(Locale::getDisplayCountry)
                .collect(Collectors.toList());
    }
}

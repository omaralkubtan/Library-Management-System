package com.legacy.lms;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {

    public static final String defaultLanguage = "en";

    public static final Set<String> supportedLanguages = new HashSet<>(Arrays.asList("ar", "en"));

    public static final Boolean allowDuplicatedCustomerNames = false;

    public static final Double defaultValue = 100.0;
}

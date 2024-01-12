package com.legacy.lms.translation;

import com.legacy.lms.Settings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class TranslationValidator implements ConstraintValidator<Translation, Map<String, String>> {
    private int max;
    private boolean required;

    @Override
    public void initialize(Translation annotation) {
        ConstraintValidator.super.initialize(annotation);
        this.max = annotation.max();
        this.required = annotation.required();
    }

    @Override
    public boolean isValid(Map<String, String> map, ConstraintValidatorContext context) {
        if (map == null) return !required;

        for (var lang : Settings.supportedLanguages) {
            if (!map.containsKey(lang)) return false;

            var value = map.get(lang);

            if (required) {
                if (value == null || value.isEmpty())
                    return false;
            }

            if (value != null && value.length() > max) return false;
        }

        return Settings.supportedLanguages.size() == map.size();
    }
}

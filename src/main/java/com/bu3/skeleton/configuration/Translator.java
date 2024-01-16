package com.bu3.skeleton.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {

    private static ResourceBundleMessageSource resourceBundleMessageSource;

    public Translator(@Qualifier("skeleton") ResourceBundleMessageSource resourceBundleMessageSource) {
        Translator.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public static String toLocale(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return resourceBundleMessageSource.getMessage(code, null, locale);
    }
}

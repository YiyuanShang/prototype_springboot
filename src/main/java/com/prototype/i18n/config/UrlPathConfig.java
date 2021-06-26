package com.prototype.i18n.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.prototype.i18n.util.LanguageCode;


/**
 * configuration for URL path based i18n support
 *
 */
@Configuration
public class UrlPathConfig implements WebMvcConfigurer{
	@Bean(name = "localeResolver")
	public LocaleResolver urlPathLocaleResolver() {
		UrlPathLocaleResolver resolver = new UrlPathLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}

//	@Bean(name = "localeResolver")
//	public LocaleResolver sessionLocaleResolver() {
//		UrlPathSessionLocaleResolver resolver = new UrlPathSessionLocaleResolver();
//		resolver.setDefaultLocale(Locale.ENGLISH);
//		return resolver;
//	}
//	
//	@Bean(name = "localeResolver")
//	public LocaleResolver cookieLocaleResolver() {
//		UrlPathSessionLocaleResolver resolver = new UrlPathSessionLocaleResolver();
//		resolver.setDefaultLocale(Locale.ENGLISH);
//		return resolver;
//	}
//	

	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		UrlLocaleInterceptor localeInterceptor = new UrlLocaleInterceptor();

		List<String> processedEnumValues = new ArrayList<>();
		List<LanguageCode> enumValues = Arrays.asList(LanguageCode.values());

		// process each enumeration value to correct path pattern
		enumValues.forEach(enumValue -> processedEnumValues.add("/" + enumValue.toString() + "/*"));

		interceptorRegistry.addInterceptor(localeInterceptor).addPathPatterns(processedEnumValues);

	}
}

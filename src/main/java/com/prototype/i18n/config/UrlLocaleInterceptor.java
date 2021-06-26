package com.prototype.i18n.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public class UrlLocaleInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UrlPathSessionLocaleResolver localeResolver = (UrlPathSessionLocaleResolver) RequestContextUtils.getLocaleResolver(request);
		
		if (localeResolver == null) {
			throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request");
		}
		System.out.println(localeResolver.getClass().getName());
		// Get locale from LocaleResolver
		Locale locale = localeResolver.resolveUrlPathLocale(request);
		localeResolver.setLocale(request, response, locale);

		return true;
	}
}

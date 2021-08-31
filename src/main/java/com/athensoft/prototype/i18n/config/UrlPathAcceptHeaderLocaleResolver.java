package com.athensoft.prototype.i18n.config;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.athensoft.prototype.i18n.util.LangUtil;

public class UrlPathAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver{
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Locale defaultLocale = getDefaultLocale();
		if (defaultLocale != null && request.getHeader("Accept-Language") == null) {
			System.out.println("return defaultLocale:" + defaultLocale);
			return defaultLocale;
		}
		Locale requestLocale = request.getLocale();
		List<Locale> supportedLocales = getSupportedLocales();
		System.out.println("supportedLocales:" + supportedLocales);
		
		if (supportedLocales.isEmpty() || supportedLocales.contains(requestLocale)) {
			System.out.println("return requestLocale:" + requestLocale);
			return requestLocale;
		}
		Locale supportedLocale = LangUtil.findLocaleFromRequest(request);
		if (supportedLocale != null) {
			System.out.println("return supportedLocale:" + supportedLocale);
			return supportedLocale;
		}
		return (defaultLocale != null ? defaultLocale : requestLocale);
	}
	
	
}

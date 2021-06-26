package com.prototype.i18n.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.prototype.i18n.util.LanguageCode;


public class UrlPathSessionLocaleResolver extends SessionLocaleResolver {
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String url = request.getRequestURI();
		System.out.println("url:" + url);

		String prefixEn = request.getServletContext().getContextPath() + "/" + LanguageCode.en + "/";
		String prefixFr = request.getServletContext().getContextPath() + "/" + LanguageCode.fr + "/";
		String prefixZh = request.getServletContext().getContextPath() + "/" + LanguageCode.zh + "/";

		Locale locale = getDefaultLocale();
        System.out.println("default locale:" + locale);
		if (url.startsWith(prefixEn)) {
			locale = Locale.ENGLISH;
		} else if (url.startsWith(prefixFr)) {
			locale = Locale.FRENCH;
		} else if (url.startsWith(prefixZh)) {
			locale = Locale.CHINESE;
		}

		return locale;
	}
}

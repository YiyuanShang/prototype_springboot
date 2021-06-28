package com.prototype.i18n.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.prototype.i18n.util.LanguageCode;


public class UrlPathCookieLocaleResolver extends CookieLocaleResolver{
	public Locale resolveUrlPathLocale(HttpServletRequest request) {
		System.out.println("entering overriden resolveLocale");
		Locale locale = super.resolveLocale(request);
		System.out.println("locale before:" + locale);
		System.out.println("processed by " + this.getClass());
		
		String url = request.getRequestURI();

		String prefixEn = request.getServletContext().getContextPath() + "/" + LanguageCode.en + "/";
		String prefixFr = request.getServletContext().getContextPath() + "/" + LanguageCode.fr + "/";
		String prefixZh = request.getServletContext().getContextPath() + "/" + LanguageCode.zh + "/";

		if (url.startsWith(prefixEn)) {
			locale = Locale.ENGLISH;
		} else if (url.startsWith(prefixFr)) {
			locale = Locale.FRENCH;
		} else if (url.startsWith(prefixZh)) {
			locale = Locale.CHINESE;
		}
		System.out.println("locale after:" + locale + "\n");
		return locale;
	}
	
}

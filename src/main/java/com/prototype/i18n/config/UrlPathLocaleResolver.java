package com.prototype.i18n.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;

import com.prototype.i18n.util.LanguageCode;


public class UrlPathLocaleResolver implements LocaleResolver {
	@Nullable
	private Locale defaultLocale;

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String url = request.getRequestURI();
		System.out.println("url:" + url);
		
		String prefixEn = request.getServletContext().getContextPath() + "/" + LanguageCode.en + "/";
        String prefixFr = request.getServletContext().getContextPath() + "/" + LanguageCode.fr + "/";
        String prefixZh = request.getServletContext().getContextPath() + "/" + LanguageCode.zh + "/";
		
        Locale locale = getDefaultLocale();
        System.out.println("default locale:" + locale);
        if(url.startsWith(prefixEn)) {
        	locale = Locale.ENGLISH;
        }else if (url.startsWith(prefixFr)) {
        	locale = Locale.FRENCH;
        }else if (url.startsWith(prefixZh)) {
        	locale = Locale.CHINESE;
        }
		
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

	}
	
	@Nullable
	public Locale getDefaultLocale() {
		return this.defaultLocale;
	}
	
	public void setDefaultLocale(@Nullable Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
}

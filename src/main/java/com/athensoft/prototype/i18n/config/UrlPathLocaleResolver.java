package com.athensoft.prototype.i18n.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;

import com.athensoft.prototype.i18n.util.LangUtil;
import com.athensoft.prototype.i18n.util.LanguageCode;


public class UrlPathLocaleResolver implements LocaleResolver {
	@Nullable
	private Locale defaultLocale;

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Locale locale = getDefaultLocale();
		locale = LangUtil.findLocaleFromRequest(request);
        System.out.println("locale after:" + locale + "\n");
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

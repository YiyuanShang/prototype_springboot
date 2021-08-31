package com.athensoft.prototype.i18n.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class LangUtil {
	public static Locale findLocaleFromRequest(HttpServletRequest request) {
		Locale locale = Locale.ENGLISH;
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
		
		return locale;
	}

}

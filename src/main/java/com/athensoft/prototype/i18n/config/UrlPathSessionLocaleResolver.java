package com.athensoft.prototype.i18n.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.athensoft.prototype.i18n.util.LanguageCode;


public class UrlPathSessionLocaleResolver extends SessionLocaleResolver {
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		// get stored locale from session
		Locale locale = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			System.out.println("Session is not null");
			locale = (Locale) session.getAttribute(LOCALE_SESSION_ATTRIBUTE_NAME);
		}else {
			System.out.println("Session is null");
			session = request.getSession();
		}
		// if no locale stored in session, get default locale
		if (locale == null) {
			System.out.println("No locale stored in session, get default locale");
			locale = determineDefaultLocale(request);
		}
		System.out.println("locale before:" + locale);
		System.out.println("processed by " + this.getClass());
		
		String url = request.getRequestURI();
		
		String prefixEn = request.getServletContext().getContextPath() + "/" + LanguageCode.en + "/";
		String prefixFr = request.getServletContext().getContextPath() + "/" + LanguageCode.fr + "/";
		String prefixZh = request.getServletContext().getContextPath() + "/" + LanguageCode.zh + "/";

		// change locale based on URL path
		if (url.startsWith(prefixEn)) {
			locale = Locale.ENGLISH;
		} else if (url.startsWith(prefixFr)) {
			locale = Locale.FRENCH;
		} else if (url.startsWith(prefixZh)) {
			locale = Locale.CHINESE;
		}
		System.out.println("locale after:" + locale + "\n");
		
		// store latest locale to session
		session.setAttribute(LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		return locale;
	}
	

//	public Locale resolveUrlPathLocale(HttpServletRequest request) {
//		Locale locale = super.resolveLocale(request);
//		System.out.println("locale before:" + locale);
//		
//		String url = request.getRequestURI();
//		System.out.println("url:" + url);
//
//		String prefixEn = request.getServletContext().getContextPath() + "/" + LanguageCode.en + "/";
//		String prefixFr = request.getServletContext().getContextPath() + "/" + LanguageCode.fr + "/";
//		String prefixZh = request.getServletContext().getContextPath() + "/" + LanguageCode.zh + "/";
//
//		// change locale based on URL path
//		if (url.startsWith(prefixEn)) {
//			locale = Locale.ENGLISH;
//		} else if (url.startsWith(prefixFr)) {
//			locale = Locale.FRENCH;
//		} else if (url.startsWith(prefixZh)) {
//			locale = Locale.CHINESE;
//		}
//		System.out.println("locale after:" + locale + "\n");
//		
//		// store latest locale to session
//		HttpSession session = request.getSession();
//		session.setAttribute(LOCALE_SESSION_ATTRIBUTE_NAME, locale);
//		return locale;
//	}
}

package com.athensoft.prototype.i18n.config;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.athensoft.prototype.i18n.util.LangUtil;

public class UrlPathAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver{
	private final Logger LOGGER = LoggerFactory.getLogger(UrlPathAcceptHeaderLocaleResolver.class);
	
	/**
	* get locale object by locale info:
	* of default locale set by program,
	* from request header in http request object from client
	* from request URL with specified language info
	*
	* priority order of locale to apply from highest to lowest:
	* default locale
	* locale by request header
	* locale by request URL
	*
	* @param request
	* @return
	*/
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Locale defaultLocale = getDefaultLocale();
		if (defaultLocale != null && request.getHeader("Accept-Language") == null) {
			LOGGER.debug("return defaultLocale:" + defaultLocale);
			return defaultLocale;
		}
		Locale requestLocale = request.getLocale();
		List<Locale> supportedLocales = getSupportedLocales();
		LOGGER.debug("supportedLocales:" + supportedLocales);
		
		if (supportedLocales.isEmpty() || supportedLocales.contains(requestLocale)) {
			LOGGER.debug("return requestLocale:" + requestLocale);
			return requestLocale;
		}
		Locale supportedLocale = LangUtil.findLocaleFromRequest(request);
		if (supportedLocale != null) {
			LOGGER.debug("return supportedLocale:" + supportedLocale);
			return supportedLocale;
		}
		return (defaultLocale != null ? defaultLocale : requestLocale);
	}
	
	
}

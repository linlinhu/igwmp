package com.emin.igwmp.skm.util;

import java.util.Locale;

/**
 * Created by Administrator on 2017/3/24.
 */
public class SocketLocalUtils {
        private static final ThreadLocal<Locale> localeLocal = new ThreadLocal();

        public SocketLocalUtils() {
        }

        public static void setLocale(Locale locale) {
            localeLocal.set(locale);
        }

        public static Locale getLocale() {
            return (Locale)localeLocal.get();
        }
}

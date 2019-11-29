package cn.myblog.utils;

import org.springframework.lang.NonNull;

import javax.servlet.http.Cookie;

public class CookieUtils {

    private static final int EXPIRED_SECONDS = 7200;

    private CookieUtils() {

    }

    @NonNull
    public static Cookie of(@NonNull String name, @NonNull String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(EXPIRED_SECONDS);
        return cookie;
    }
}

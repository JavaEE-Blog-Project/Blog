package cn.myblog.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class SecurityUtils {

    private SecurityUtils() {

    }

    public static String randomUUIDWithoutDash() {
        return StringUtils.remove(UUID.randomUUID().toString(),'-');
    }
}

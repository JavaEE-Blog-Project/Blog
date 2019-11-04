package cn.myblog.utils;

import cn.myblog.exception.BeanUtilsException;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.util.Assert;

public class BeanUtils {

    private BeanUtils() {

    }

    public static void copyProperties(@NonNull Object source, @NonNull Object target) {
        Assert.notNull(source,"source object must not be null");
        Assert.notNull(target,"target object must not be null");

        try {
            org.springframework.beans.BeanUtils.copyProperties(source,target);
        } catch (BeansException e) {
            throw new BeanUtilsException("Failed to copy properties", e);
        }
    }
}

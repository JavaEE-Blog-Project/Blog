package cn.myblog.model.dto.base;

import java.lang.reflect.ParameterizedType;

public interface InputConverter<DOMAIN> {


    /**
     * Convert this to domain
     *
     * @return new domain with same value
     */
    default DOMAIN convertTo() {
        return null;
    }

    default ParameterizedType parameterizedType() {
        return null;
    }
}

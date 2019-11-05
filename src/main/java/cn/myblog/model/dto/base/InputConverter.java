package cn.myblog.model.dto.base;

import cn.myblog.utils.BeanUtils;
import org.springframework.lang.NonNull;


public interface InputConverter<DOMAIN> {

    /**
     * Convert this to domain
     *
     * @return new domain with same value
     */
    default DOMAIN convertTo(@NonNull DOMAIN domain) {
        BeanUtils.copyProperties(this, domain);
        return domain;
    }
}

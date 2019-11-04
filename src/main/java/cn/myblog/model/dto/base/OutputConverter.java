package cn.myblog.model.dto.base;

import lombok.NonNull;

import static cn.myblog.utils.BeanUtils.copyProperties;

public interface OutputConverter<DTO extends OutputConverter<DTO,DOMAIN>, DOMAIN> {

    @NonNull
    @SuppressWarnings("unchecked")
    default <T extends DTO> T convertFrom(@NonNull DOMAIN domain) {
        copyProperties(domain,this);

        return (T) this;
    }
}

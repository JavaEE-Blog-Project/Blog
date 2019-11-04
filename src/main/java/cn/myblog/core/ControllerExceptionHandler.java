package cn.myblog.core;

import cn.myblog.exception.BaseException;
import cn.myblog.model.support.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice({"cn.myblog.controller.admin.api"})
public class ControllerExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<?> handlerBaseException(BaseException e) {
        BaseResponse<Object> baseResponse = handlerException(e);
        baseResponse.setStatus(e.getStatus().value());
        baseResponse.setData(e.getErrorData());
        return baseResponse;
    }

    private <T> BaseResponse<T> handlerException(Throwable t) {
        Assert.notNull(t,"Throwable must not be null");

        log.error("Captured an exception", t);

        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(t.getMessage());

        if (log.isDebugEnabled()) {
            baseResponse.setDevMessage(t.getMessage());
        }

        return baseResponse;
    }
}

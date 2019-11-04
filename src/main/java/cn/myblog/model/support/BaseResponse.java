package cn.myblog.model.support;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    /**
     *  Response status.
     */
    private Integer status;

    /**
     *  Response message.
     */
    private String message;

    /**
     *  Response development data
     */
    private String devMessage;

    /**
     *  Response data
     */
    private T data;

    public BaseResponse(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @NonNull
    public static <T> BaseResponse<T> ok(@Nullable String message, @Nullable T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), message, data);
    }

    public static <T> BaseResponse<T> ok(@Nullable T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }
}

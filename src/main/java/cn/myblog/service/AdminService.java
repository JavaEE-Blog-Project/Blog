package cn.myblog.service;

import cn.myblog.model.param.LoginParam;
import cn.myblog.security.token.AuthToken;
import org.springframework.lang.NonNull;

public interface AdminService {

    int ACCESS_TOKEN_EXPIRED_SECONDS = 24 * 3600;

    int REFRESH_TOKEN_EXPIRED_DAYS = 30;

    /**
     * Authenticates
     *
     * @param loginParam Login param must not be null
     * @return authentication token
     */
    @NonNull
    AuthToken authenticate(@NonNull LoginParam loginParam);

    /**
     *  Clears authentication.
     */
    void clearToken();
}

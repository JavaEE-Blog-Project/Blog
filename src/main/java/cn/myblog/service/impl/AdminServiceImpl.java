package cn.myblog.service.impl;

import cn.hutool.core.lang.Validator;
import cn.myblog.exception.BadRequestException;
import cn.myblog.exception.NotFoundException;
import cn.myblog.model.entity.User;
import cn.myblog.model.param.LoginParam;
import cn.myblog.security.token.AuthToken;
import cn.myblog.service.AdminService;
import cn.myblog.service.UserService;
import cn.myblog.utils.SecurityUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    public AdminServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthToken authenticate(LoginParam loginParam) {
        Assert.notNull(loginParam,"Login param must not be null");

        String username = loginParam.getUsername();
        String plainPassword = loginParam.getPassword();
        String mismatchTip = "账号或密码不正确";

        final User user;

        try {
            user = Validator.isEmail(username) ?
                    userService.getByEmailOfNonNull(username) : userService.getByUsernameOfNonNull(username);
        } catch (NotFoundException e) {
            log.error("Failed to find user by name: " + username, e);
            throw new BadRequestException(mismatchTip);
        }

        userService.mustNotExpire(user);

        if (!userService.passwordMatch(user,plainPassword)) {
            log.error("Password doesn't match by name: " +username);
            throw new BadRequestException(mismatchTip);
        }

        return buildAuthToken(user);
    }

    @Override
    public void clearToken() {

    }

    /**
     * Build Authentication token
     *
     * @param user user of non null
     * @return Auth token
     */
    @NonNull
    private AuthToken buildAuthToken(@NonNull User user) {
        Assert.notNull(user,"User must not be null");

        AuthToken token = new AuthToken();

        token.setAccessToken(SecurityUtils.randomUUIDWithoutDash());
        token.setRefreshToken(SecurityUtils.randomUUIDWithoutDash());
        token.setExpiredIn(ACCESS_TOKEN_EXPIRED_SECONDS);

        return token;
    }
}

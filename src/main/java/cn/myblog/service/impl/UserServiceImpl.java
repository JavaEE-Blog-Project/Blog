package cn.myblog.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import cn.myblog.exception.ForbiddenException;
import cn.myblog.exception.NotFoundException;
import cn.myblog.model.entity.User;
import cn.myblog.repository.UserRepository;
import cn.myblog.service.UserService;
import cn.myblog.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentUser() {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByUsernameOfNonNull(String username){
        return getByUsername(username).orElseThrow(() -> new NotFoundException("The username does not exist"));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByEmailOfNonNull(String email){
        return getByEmail(email).orElseThrow(() -> new NotFoundException("The email address does not exist"));
    }

    @Override
    public boolean passwordMatch(User user, String plainPassword) {
        Assert.notNull(user, "User must not be null");
        return user.getPassword().equals(plainPassword);
    }

    @Override
    public void mustNotExpire(User user) {
        Assert.notNull(user,"User must not be null");

        Date now = DateUtils.now();
        if (user.getExpireTime() != null && user.getExpireTime().after(now)) {
            long seconds = TimeUnit.MILLISECONDS.toSeconds(user.getExpireTime().getTime() - now.getTime());
            throw new ForbiddenException("账号已被停用，请稍后重试").setErrorData(seconds);
        }
    }
}

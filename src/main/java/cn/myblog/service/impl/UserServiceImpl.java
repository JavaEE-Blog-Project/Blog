package cn.myblog.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import cn.myblog.exception.BadRequestException;
import cn.myblog.exception.ForbiddenException;
import cn.myblog.exception.NotFoundException;
import cn.myblog.model.dto.UserDTO;
import cn.myblog.model.entity.User;
import cn.myblog.model.param.RegistryParam;
import cn.myblog.repository.UserRepository;
import cn.myblog.service.UserService;
import cn.myblog.utils.DateUtils;
import org.springframework.cache.annotation.Cacheable;
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
    public Boolean hasUser() {
        return userRepository.count() != 0L;
    }

    @Override
    @Cacheable(cacheNames = "user")
    public User getCurrentUser() {
        return userRepository.findById(1).orElseThrow(() -> new BadRequestException("请先初始化博客!"));
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByUsernameOfNonNull(String username) {
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
        return BCrypt.checkpw(plainPassword, user.getPassword());
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

    @Override
    public UserDTO register(RegistryParam registryParam) {
        registryParam.setPassword(BCrypt.hashpw(registryParam.getPassword()));
        User user = registryParam.convertTo(new User());
        userRepository.save(user);
        return new UserDTO().convertFrom(user);
    }
}

package cn.myblog.service;

import cn.myblog.model.dto.UserDTO;
import cn.myblog.model.entity.User;
import cn.myblog.model.param.RegistryParam;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserService {

    /**
     * Max login try count
     */
    int MAX_LOGIN_TRY = 5;

    /**
     * Lock minutes
     */
    int LOCK_MINUTES = 10;

    /**
     * Check whether there's a user
     */
    @NonNull
    Boolean hasUser();

    /**
     * Get information of host user
     *
     * @return User
     */
    @NonNull
    User getCurrentUser();

    /**
     * Get user by username
     *
     * @param username username must not be blank
     * @return an optional user
     */
    @NonNull
    Optional<User> getByUsername(@NonNull String username);

    /**
     * Get non null user by username
     *
     * @param username username
     * @return user info
     * @throws javassist.NotFoundException
     */
    @NonNull
    User getByUsernameOfNonNull(@NonNull String username);

    /**
     * Get user by email
     *
     * @param email email must not be blank
     * @return an optional user
     */
    @NonNull
    Optional<User> getByEmail(@NonNull String email);

    /**
     * Get user by email of non null
     *
     * @param email email must not be blank
     * @return an optional user
     * @throws javassist.NotFoundException
     */
    @NonNull
    User getByEmailOfNonNull(@NonNull String email);

    /**
     * Check if password matches
     *
     * @param user user of non null
     * @param plainPassword plain password
     * @return boolean
     */
    boolean passwordMatch(@NonNull User user, @Nullable String plainPassword);

    /**
     * Check if it's expired
     *
     * @param user user of non null
     */
    void mustNotExpire(@NonNull User user);

    /**
     * Register a new user
     *
     * @param registryParam registry parameter
     * @return UserDTO of non null
     */
    @NonNull
    UserDTO register(@NonNull RegistryParam registryParam);
}

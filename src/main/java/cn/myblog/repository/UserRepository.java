package cn.myblog.repository;

import cn.myblog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @NonNull
    Optional<User> findByUsername(@NonNull String username);

    @NonNull
    Optional<User> findByEmail(@NonNull String email);
}

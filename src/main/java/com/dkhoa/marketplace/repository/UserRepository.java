package com.dkhoa.marketplace.repository;

import com.dkhoa.marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String userName);

    boolean existsByUsername(String username);
}

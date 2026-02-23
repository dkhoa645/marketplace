package com.dkhoa.marketplace.repository;

import com.dkhoa.marketplace.entity.User;
import com.dkhoa.marketplace.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

    UserProfile findByUser(User user);

    boolean existByEmailAndIdNot(String email,UUID id);

    boolean existByPhoneNumberAndIdNot(String number,UUID id);
}

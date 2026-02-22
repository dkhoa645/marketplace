package com.dkhoa.marketplace.repository;

import com.dkhoa.marketplace.entity.Province;
import com.dkhoa.marketplace.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    boolean existsByName(String name);
}

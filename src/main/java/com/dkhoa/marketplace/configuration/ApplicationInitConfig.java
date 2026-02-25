package com.dkhoa.marketplace.configuration;

import com.dkhoa.marketplace.entity.Enum.RoleEnum;
import com.dkhoa.marketplace.entity.Role;
import com.dkhoa.marketplace.entity.User;
import com.dkhoa.marketplace.exception.AppException;
import com.dkhoa.marketplace.exception.ErrorCode;
import com.dkhoa.marketplace.repository.RoleRepository;
import com.dkhoa.marketplace.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {




    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner  initApplicationRunner(
            RoleRepository roleRepository, UserRepository userRepository
    ) {
        return args -> {


            Arrays.stream(RoleEnum.values()).forEach(roleEnum -> {
                roleRepository.findByName(roleEnum)
                        .orElseGet(()->{
                            Role role = new Role();
                            role.setName(roleEnum);
                            return roleRepository.save(role);
                        });
            });

            Role admin = roleRepository.findByName(RoleEnum.ADMIN)
                    .orElseThrow(()-> new AppException(ErrorCode.RESOURCE_NOT_EXIST));
            if(userRepository.findByUsername("admin").isEmpty()){
                Set<Role> roles = new HashSet<>();
                roles.add(admin);
                User adminUser = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                userRepository.save(adminUser);
            }
            log.warn("admin user has been created with default password: admin, please change it");
        };
    }
}

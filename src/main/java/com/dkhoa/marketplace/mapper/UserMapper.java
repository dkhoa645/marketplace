package com.dkhoa.marketplace.mapper;

import com.dkhoa.marketplace.dto.response.UserResponse;
import com.dkhoa.marketplace.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}

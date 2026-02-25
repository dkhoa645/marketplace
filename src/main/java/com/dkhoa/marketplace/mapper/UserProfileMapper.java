package com.dkhoa.marketplace.mapper;

import com.dkhoa.marketplace.dto.request.ProfileCreationRequest;
import com.dkhoa.marketplace.dto.request.ProfileUpdateRequest;
import com.dkhoa.marketplace.dto.response.ProfileResponse;
import com.dkhoa.marketplace.entity.UserProfile;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "avatar", ignore = true)
    UserProfile toUserProfile(ProfileCreationRequest profileCreationRequest);
    ProfileResponse toProfileResponse(UserProfile userProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfile(ProfileUpdateRequest profileUpdateRequest, @MappingTarget UserProfile userProfile);

}

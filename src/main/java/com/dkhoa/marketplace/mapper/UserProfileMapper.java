package com.dkhoa.marketplace.mapper;

import com.dkhoa.marketplace.dto.request.ProfileCreationRequest;
import com.dkhoa.marketplace.dto.response.ProfileResponse;
import com.dkhoa.marketplace.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest profileCreationRequest);
    ProfileResponse toProfileResponse(UserProfile userProfile);

}

package com.dkhoa.marketplace.service;

import com.dkhoa.marketplace.dto.request.ProfileCreationRequest;
import com.dkhoa.marketplace.dto.request.ProfileUpdateRequest;
import com.dkhoa.marketplace.dto.response.ProfileResponse;
import com.dkhoa.marketplace.entity.Province;
import com.dkhoa.marketplace.entity.User;
import com.dkhoa.marketplace.entity.UserProfile;
import com.dkhoa.marketplace.exception.AppException;
import com.dkhoa.marketplace.exception.ErrorCode;
import com.dkhoa.marketplace.mapper.UserProfileMapper;
import com.dkhoa.marketplace.repository.ProvinceRepository;
import com.dkhoa.marketplace.repository.UserProfileRepository;
import com.dkhoa.marketplace.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;
    UserRepository userRepository;
    ProvinceRepository provinceRepository;

    public List<ProfileResponse> getAllProfiles() {
        return userProfileRepository.findAll().stream()
                .map(userProfileMapper::toProfileResponse)
                .toList();
    }

    public ProfileResponse getMyProfile() {
        var context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        UserProfile userProfile = userProfileRepository.findByUser(user);
        return userProfileMapper.toProfileResponse(userProfile);
    }

    public ProfileResponse createProfile(ProfileCreationRequest profileCreationRequest) {
        UserProfile userProfile = userProfileMapper.toUserProfile(profileCreationRequest);
        if (userProfileRepository.existsByPhoneNumber(userProfile.getPhoneNumber()))
            throw new AppException(ErrorCode.PHONE_EXISTED);
        if (userProfileRepository.existsByEmail(userProfile.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        Province province = provinceRepository.findById(profileCreationRequest.getProvinceId())
                .orElseThrow(() -> new AppException(ErrorCode.PROVINCE_NOT_FOUND));
        userProfile.setProvince(province);

        var context = SecurityContextHolder.getContext();
        User user = userRepository.findByUsername(context.getAuthentication().getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userProfile.setUser(user);

        try {
            return userProfileMapper.toProfileResponse(userProfileRepository.save(userProfile));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.PROFILE_EXISTED);
        }
    }

    public void deleteProfile(UUID id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND));
        userProfileRepository.delete(userProfile);
    }

    public ProfileResponse updateProfile(UUID id, @Valid ProfileUpdateRequest profileUpdateRequest) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND));

        if(profileUpdateRequest.getEmail()!=null &&
                userProfileRepository.existByEmailAndIdNot(profile.getEmail(),id)){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        if(profileUpdateRequest.getPhoneNumber()!=null &&
                userProfileRepository.existByPhoneNumberAndIdNot(profile.getEmail(),id)){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        if (profileUpdateRequest.getProvinceId() != null) {

            Province province = provinceRepository
                    .findById(profileUpdateRequest.getProvinceId())
                    .orElseThrow(() ->
                            new AppException(ErrorCode.PROVINCE_NOT_FOUND));

            profile.setProvince(province);
        }

        userProfileMapper.updateProfile(profileUpdateRequest, profile);
        return userProfileMapper.toProfileResponse(userProfileRepository.save(profile));
    }
}

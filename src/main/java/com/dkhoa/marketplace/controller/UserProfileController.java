package com.dkhoa.marketplace.controller;

import com.dkhoa.marketplace.dto.request.ProfileCreationRequest;
import com.dkhoa.marketplace.dto.request.ProfileUpdateRequest;
import com.dkhoa.marketplace.dto.response.ApiResponse;
import com.dkhoa.marketplace.dto.response.ProfileResponse;
import com.dkhoa.marketplace.mapper.UserProfileMapper;
import com.dkhoa.marketplace.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping
    ApiResponse<List<ProfileResponse>> getAllProfile(){
        return ApiResponse.<List<ProfileResponse>>builder()
                .result(userProfileService.getAllProfiles())
                .build();
    }

    @GetMapping("/my-profile")
    ApiResponse<ProfileResponse> getMyProfile(){
        return ApiResponse.<ProfileResponse>builder()
                .result(userProfileService.getMyProfile())
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteProfile(@PathVariable UUID id){
        userProfileService.deleteProfile(id);
        return ApiResponse.<Void>builder()
                .message("Profile has been deleted")
                .build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<ProfileResponse> createProfile(
            @ModelAttribute @Valid ProfileCreationRequest  profileCreationRequest){
        return ApiResponse.<ProfileResponse>builder()
                .result(userProfileService.createProfile(profileCreationRequest))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ProfileResponse> updateProfile(
            @PathVariable UUID id,
            @RequestBody @Valid ProfileUpdateRequest profileUpdateRequest){
        return ApiResponse.<ProfileResponse>builder()
                .result(userProfileService.updateProfile(id,profileUpdateRequest))
                .build();
    }
}

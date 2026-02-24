package com.dkhoa.marketplace.dto.response;

import com.dkhoa.marketplace.entity.UserProfile;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationResponse {
    UUID id;

    String participantHash;
    List<ProfileResponse> userProfiles;

    String conversationName;
    String conversationAvatar;

    Instant createdAt;
    Instant updatedAt;
}

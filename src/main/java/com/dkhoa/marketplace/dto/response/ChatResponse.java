package com.dkhoa.marketplace.dto.response;

import com.dkhoa.marketplace.entity.Conversation;
import com.dkhoa.marketplace.entity.UserProfile;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatResponse {
    UUID id;
    UserProfile sender;
    ConversationResponse conversation;
    String message;
    Instant createdDate;
    boolean me;

}

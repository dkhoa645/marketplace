package com.dkhoa.marketplace.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "chats")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    UserProfile sender;
    @ManyToOne(fetch = FetchType.LAZY)
    Conversation conversation;
    String message;
    Instant createdDate;

}

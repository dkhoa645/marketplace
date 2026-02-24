package com.dkhoa.marketplace.service;

import com.dkhoa.marketplace.dto.request.ConversationCreationRequest;
import com.dkhoa.marketplace.dto.response.ConversationResponse;
import com.dkhoa.marketplace.entity.Conversation;
import com.dkhoa.marketplace.entity.User;
import com.dkhoa.marketplace.entity.UserProfile;
import com.dkhoa.marketplace.exception.AppException;
import com.dkhoa.marketplace.exception.ErrorCode;
import com.dkhoa.marketplace.mapper.ConversationMapper;
import com.dkhoa.marketplace.repository.ConversationRepository;
import com.dkhoa.marketplace.repository.UserProfileRepository;
import com.dkhoa.marketplace.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversationService {
    ConversationRepository conversationRepository;
    UserRepository userRepository;
    ConversationMapper  conversationMapper;

    public ConversationResponse createConversation(@Valid ConversationCreationRequest conversationCreationRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        User participant = userRepository.findById(conversationCreationRequest.getParticipantId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        List<String> ids = new ArrayList<>();
        ids.add(user.getId().toString());
        ids.add(participant.getId().toString());

        var sortIds = ids.stream().sorted().toList();
        String hashParticipant = hashParticipants(sortIds);

        List<UserProfile> listProfiles = List.of(
                user.getUserProfile(), participant.getUserProfile()
        );



        Conversation conversation = Conversation.builder()
                .userProfiles(listProfiles)
                .participantHash(hashParticipant)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        conversation = conversationRepository.save(conversation);

        return toConversationResponse(conversation);

    }

    private ConversationResponse toConversationResponse(Conversation conversation) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ConversationResponse conversationResponse = conversationMapper.toConversationResponse(conversation);
        conversation.getUserProfiles().stream()
                .filter(userProfile -> !userProfile.getUser().equals(user))
                .findFirst().ifPresent(userProfile -> {
                    conversationResponse.setConversationAvatar(userProfile.getAvatar());
                    conversationResponse.setConversationName(userProfile.getFullName());
        });
        return conversationResponse;
    }

    private String hashParticipants(List<String> ids) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        ids.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    public List<ConversationResponse> getMyConservation() {
        return conversationRepository.findAll().stream()
                .map(conversationMapper::toConversationResponse)
                .toList();
    }
}

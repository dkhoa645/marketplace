package com.dkhoa.marketplace.service;

import com.dkhoa.marketplace.dto.request.ChatCreationRequest;
import com.dkhoa.marketplace.dto.response.ChatResponse;
import com.dkhoa.marketplace.entity.Chat;
import com.dkhoa.marketplace.entity.Conversation;
import com.dkhoa.marketplace.entity.User;
import com.dkhoa.marketplace.entity.UserProfile;
import com.dkhoa.marketplace.exception.AppException;
import com.dkhoa.marketplace.exception.ErrorCode;
import com.dkhoa.marketplace.mapper.ChatMapper;
import com.dkhoa.marketplace.repository.ChatRepository;
import com.dkhoa.marketplace.repository.ConversationRepository;
import com.dkhoa.marketplace.repository.UserProfileRepository;
import com.dkhoa.marketplace.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatService {
    ChatRepository chatRepository;
    ChatMapper chatMapper;
    UserRepository userRepository;
    UserProfileRepository userProfileRepository;
    ConversationRepository conversationRepository;

    public ChatResponse createChat(ChatCreationRequest chatCreationRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));

        UserProfile userProfile = userProfileRepository.findByUser(user);

        Conversation conversation = conversationRepository.findById(chatCreationRequest.getConversationId())
                .orElseThrow(()-> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));

        conversation
                .getUserProfiles()
                .stream()
                .filter(userProfile::equals)
                .findAny()
                .orElseThrow(()-> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));

        Chat chat = Chat.builder()
                .conversation(conversation)
                .message(chatCreationRequest.getMessage())
                .sender(userProfile)
                .createdDate(Instant.now())
                .message(chatCreationRequest.getMessage())
                .build();
        chat = chatRepository.save(chat);

        return toChatResponse(chat);
    }

    private ChatResponse toChatResponse(Chat chat) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));

        ChatResponse chatResponse = chatMapper.toChatResponse(chat);

        chatResponse.setMe(user.getUserProfile().equals(chat.getSender()));

        return chatResponse;
    }

    public List<ChatResponse> getChat(UUID conversationId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));

        Conversation conversation =  conversationRepository.findById(conversationId)
                .orElseThrow(()-> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));

        conversation
                .getUserProfiles().stream()
                .filter(userProfile -> userProfile.getConversation().equals(conversationId))
                .findAny()
                .orElseThrow(()-> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));

        List<Chat> messages = chatRepository.findAllByConversationOrderByCreatedDate(conversation);

        return messages.stream()
                .map(this::toChatResponse)
                .toList();
    }
}

package com.dkhoa.marketplace.controller;

import com.dkhoa.marketplace.dto.request.ChatCreationRequest;
import com.dkhoa.marketplace.dto.response.ApiResponse;
import com.dkhoa.marketplace.dto.response.ChatResponse;
import com.dkhoa.marketplace.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
    ChatService chatService;

    @PostMapping
    ApiResponse<ChatResponse> createChat(@RequestBody ChatCreationRequest chatCreationRequest){
        return ApiResponse.<ChatResponse>builder()
                .result(chatService.createChat(chatCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<ChatResponse>> getChat(@RequestParam UUID conversationId){
        return ApiResponse.<List<ChatResponse>>builder()
                .result(chatService.getChat(conversationId))
                .build();
    }
}

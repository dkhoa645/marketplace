package com.dkhoa.marketplace.controller;

import com.dkhoa.marketplace.dto.request.ConversationCreationRequest;
import com.dkhoa.marketplace.dto.response.ApiResponse;
import com.dkhoa.marketplace.dto.response.ConversationResponse;
import com.dkhoa.marketplace.service.ConversationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversationController {
    ConversationService conversationService;

    @PostMapping
    ApiResponse<ConversationResponse> createConversation(
            @Valid @RequestBody ConversationCreationRequest conversationCreationRequest
            ){
        return ApiResponse.<ConversationResponse>builder()
                .result(conversationService.createConversation(conversationCreationRequest))
                .build();
    }

    @GetMapping()
    ApiResponse<List<ConversationResponse>> getMyConversation(){
        return ApiResponse.<List<ConversationResponse>>builder()
                .result(conversationService.getMyConservation())
                .build();
    }
}

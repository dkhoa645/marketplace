package com.dkhoa.marketplace.mapper;

import com.dkhoa.marketplace.dto.response.ConversationResponse;
import com.dkhoa.marketplace.entity.Conversation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    ConversationResponse toConversationResponse(Conversation conversation);
}

package com.dkhoa.marketplace.mapper;

import com.dkhoa.marketplace.dto.response.ChatResponse;
import com.dkhoa.marketplace.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatResponse toChatResponse(Chat chat);
}

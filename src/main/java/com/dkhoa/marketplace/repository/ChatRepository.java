package com.dkhoa.marketplace.repository;

import com.dkhoa.marketplace.entity.Chat;
import com.dkhoa.marketplace.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
    List<Chat> findAllByConversationOrderByCreatedDate(Conversation conversation);
}

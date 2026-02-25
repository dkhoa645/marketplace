package com.dkhoa.marketplace.repository;

import com.dkhoa.marketplace.entity.Conversation;
import com.dkhoa.marketplace.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

}

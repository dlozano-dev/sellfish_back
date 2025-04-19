package com.dlozano.app.rest.Repositories;

import com.dlozano.app.rest.Models.Clothes;
import com.dlozano.app.rest.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.product = :product AND (m.sender = :user OR m.receiver = :user) ORDER BY m.time ASC")
    List<Message> getMessages(@Param("user") int user, @Param("product") int product);

    @Query("SELECT DISTINCT c FROM Message m JOIN Clothes c ON c.id = m.product WHERE m.sender = :user OR m.receiver = :user")
    List<Clothes> getChats(@Param("user") int user);
}
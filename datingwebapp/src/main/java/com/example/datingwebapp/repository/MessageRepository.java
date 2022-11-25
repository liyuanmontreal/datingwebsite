package com.example.datingwebapp.repository;

import com.example.datingwebapp.entity.Client;
import com.example.datingwebapp.entity.Message;
import com.example.datingwebapp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
       Optional<Message> findFirstByContentIsNotNull();

       //@Query(value = "select count(m)  from Message m where m.status=:status AND m.recipient=:recipient")
       //Integer findNewMsgNumberUsingJPA(@Param("status") String status, @Param("recipient") Client recipient);
       @Query(value = "select count(m)  from Message m where m.status=:status AND m.recipient=:recipient")
       Integer findNewMsgNumberUsingJPA(@Param("status") String status, @Param("recipient") Client recipient);

       // Optional<Long> countByStatusAndRecipientID();

       List<Message> findAllByStatusAndRecipient (String status, Client recipient);


  /*
    List<Message> findByStatusOrderByCreatedTSDesc(String status);
    Optional <Message> findFirstByContentIsNotNull();

    @Query(value = "select m  from Message m where m.status=:status AND m.recipientID=:recipientID")
        //List <Message> findAllNewMessageUsingJPA(@Param("status") String status, @Param("recipientID") Client recipient);
    List <Message> findAllNewMessageUsingJPA(@Param("status") String status, @Param("recipientID") long recipientID);
*/

}

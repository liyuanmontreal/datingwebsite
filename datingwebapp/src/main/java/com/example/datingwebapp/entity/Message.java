package com.example.datingwebapp.entity;

import lombok.*;

import javax.validation.constraints.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="tbl_message")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    //relation to entity Client, fk = clientId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="clientId", nullable = false)
    private Client sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="recipientId", nullable = false)
    private Client recipient;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTS;

    @Column(nullable = true, length = 150)
    private String content;

    private String type = "msg";

    private String status = "delieved";

    public Message(Client sender, Client recipient,String content, LocalDateTime createdTS,String type,String status) {
        this.sender= sender;
        this.recipient= recipient;
        this.content= content;
        this.createdTS = createdTS;
        this.type = type;
        this.status = "delieved";
    }

    public Message(Client sender, Client recipient,String content,String type,String status) {
        this.sender= sender;
        this.recipient= recipient;
        this.content= content;
        this.type = type;
        this.status = "delieved";
    }



}


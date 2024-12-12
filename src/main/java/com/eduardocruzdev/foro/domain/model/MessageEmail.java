package com.eduardocruzdev.foro.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled_emails")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String recipient;
    private String subject;
    private String content;
    private boolean sent;
    private LocalDateTime scheduledSentDate;
    private LocalDateTime sentDate;
    private EmailMessageType type;

    public enum EmailMessageType {
        CONFIRMATION,
        PASSWORD_RESET,
    }


}


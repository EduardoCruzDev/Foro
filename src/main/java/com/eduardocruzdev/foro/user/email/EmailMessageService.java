package com.eduardocruzdev.foro.user.email;

import com.eduardocruzdev.foro.domain.model.MessageEmail;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailMessageService {
    private final EmailMessageRepository repository;

    public EmailMessageService(EmailMessageRepository repository) {
        this.repository = repository;
    }

    public void scheduleMessage(MessageEmail message) {
        message.setScheduledSentDate(LocalDateTime.now());
        message.setSent(false);
        repository.save(message);
    }
}

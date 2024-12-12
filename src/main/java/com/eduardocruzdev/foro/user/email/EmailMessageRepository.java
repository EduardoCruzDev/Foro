package com.eduardocruzdev.foro.user.email;

import com.eduardocruzdev.foro.domain.model.MessageEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageRepository extends JpaRepository<MessageEmail, Long> {
}

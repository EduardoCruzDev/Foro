package com.eduardocruzdev.foro.topic;

import com.eduardocruzdev.foro.domain.model.Section;
import com.eduardocruzdev.foro.domain.model.Topic;
import com.eduardocruzdev.foro.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Set<Topic> findBySection(Section section);

    Set<Topic> findByUser(User user);

    Set<Topic> findAllByOrderByFechaCreacionDesc();

    Set<Topic> findTop5ByOrderByFechaCreacionDesc();
}

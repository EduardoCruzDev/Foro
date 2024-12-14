package com.eduardocruzdev.foro.post;

import com.eduardocruzdev.foro.domain.model.Post;
import com.eduardocruzdev.foro.domain.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByTopicOrderByCreationDate(Topic topic);

    Set<Post> findTop5ByOrderByCreationDateDesc();
}

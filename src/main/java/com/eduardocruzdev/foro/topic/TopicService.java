package com.eduardocruzdev.foro.topic;


import com.eduardocruzdev.foro.domain.dto.TopicForm;
import com.eduardocruzdev.foro.domain.model.Post;
import com.eduardocruzdev.foro.domain.model.Section;
import com.eduardocruzdev.foro.domain.model.Topic;
import com.eduardocruzdev.foro.domain.model.User;
import com.eduardocruzdev.foro.domain.utiles.NotFoundException;
import com.eduardocruzdev.foro.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TopicService {


    private final TopicRepository topicRepository;

    private final PostRepository postRepository;

    public TopicService(TopicRepository topicRepository, PostRepository postRepository) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Topic findOne(int id) {
        Optional<Topic> topicOptional = topicRepository.findById((long) id);
        if (topicOptional.isEmpty()) {
            throw new NotFoundException();
        }
        return topicOptional.get();
    }

    public Set<Topic> findRecent() {
        return topicRepository.findTop5ByOrderByFechaCreacionDesc();
    }

    public Set<Topic> findBySection(Section section) {
        return topicRepository.findBySection(section);
    }

    public Topic createNewTopic(TopicForm topicForm, User author, Section section) {
        Topic topic = Topic.builder()
                .section(section)
                .user(author)
                .titulo(topicForm.getTitle()).build();
        topic = topicRepository.save(topic);
        Post post = Post.builder()
                .topic(topic)
                .content(topicForm.getContent())
                .user(author)
                .build();
        postRepository.save(post);
        return topic;
    }

    public void delete(Topic topic) {
        topicRepository.delete(topic);
    }
}

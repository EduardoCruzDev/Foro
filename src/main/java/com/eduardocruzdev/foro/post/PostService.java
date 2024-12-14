package com.eduardocruzdev.foro.post;


import com.eduardocruzdev.foro.domain.model.Post;
import com.eduardocruzdev.foro.domain.model.Topic;
import com.eduardocruzdev.foro.domain.utiles.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findOneOrExit(int id) {
        Optional<Post> postOptional = postRepository.findById((long) id);
        if (!postOptional.isPresent()) {
            throw new NotFoundException();
        }
        return postOptional.get();
    }

    public Set<Post> findRecent() {
        return postRepository.findTop5ByOrderByCreationDateDesc();
    }

    public List<Post> findByTopic(Topic topic) {
        return postRepository.findByTopicOrderByCreationDate(topic);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

}

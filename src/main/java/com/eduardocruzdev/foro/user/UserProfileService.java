package com.eduardocruzdev.foro.user;

import com.eduardocruzdev.foro.domain.model.User;
import com.eduardocruzdev.foro.domain.model.UserProfile;
import com.eduardocruzdev.foro.post.PostService;
import com.eduardocruzdev.foro.topic.TopicService;
import org.springframework.stereotype.Service;


@Service
public class UserProfileService {

    private final UserService userService;

    private final PostService postService;

    private final TopicService topicService;

    public UserProfileService(UserService userService, PostService postService, TopicService topicService) {
        this.userService = userService;
        this.postService = postService;
        this.topicService = topicService;
    }

    public UserProfile findOne(int userId) {
        UserProfile userProfile = new UserProfile();
        User user = userService.findOne(userId);
        userProfile.setUser(user);
//        userProfile.setPosts(postService.findByUser(user));
//        userProfile.setTopics(topicService.findByUser(user));
        return userProfile;
    }

    public UserProfile findOne(String username) {
        return findOne(String.valueOf(userService.findByUsername(username).getId()));
    }
}

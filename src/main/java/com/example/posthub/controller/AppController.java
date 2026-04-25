package com.example.posthub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.posthub.model.Comment;
import com.example.posthub.model.Post;
import com.example.posthub.model.PostRegistration;
import com.example.posthub.service.PosthubService;
import com.example.posthub.service.RedisService;

@RestController
@RequestMapping("/posthub/api")
public class AppController {

    private final PosthubService posthubService;
    private final RedisService redisService;

    public AppController(PosthubService posthubService, RedisService redisService) {
        this.posthubService = posthubService;
        this.redisService = redisService;
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRegistration post) {
        return posthubService.createPost(post);
    }

    @PostMapping("/posts/{postId}/comments")
    public String addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return posthubService.addComment(postId, comment);
    }

    @PostMapping("/posts/{postId}/like")
    public String likePost(@PathVariable Long postId) {
        return posthubService.likePost(postId);
    }

    @GetMapping("/posts/{postId}/score")
    public String getScore(@PathVariable Long postId) {
        return redisService.getScore(postId);
    }

}

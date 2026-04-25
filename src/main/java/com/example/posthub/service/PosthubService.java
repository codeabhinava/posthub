package com.example.posthub.service;

import org.springframework.stereotype.Service;

import com.example.posthub.model.AppUser;
import com.example.posthub.model.BotUser;
import com.example.posthub.model.Comment;
import com.example.posthub.model.Post;
import com.example.posthub.model.PostRegistration;
import com.example.posthub.repository.AppUserRepository;
import com.example.posthub.repository.BotuserRepository;
import com.example.posthub.repository.CommentRepository;
import com.example.posthub.repository.PostRepository;

@Service
public class PosthubService {

    private final PostRepository postRepository;
    private final AppUserRepository appUserRepository;
    private final RedisService redisService;
    private final BotuserRepository botuserRepository;
    private final CommentRepository commentRepository;

    public PosthubService(PostRepository postRepository, AppUserRepository appUserRepository, RedisService redisService, BotuserRepository botuserRepository, CommentRepository commentRepository) {

        this.postRepository = postRepository;
        this.appUserRepository = appUserRepository;
        this.redisService = redisService;
        this.botuserRepository = botuserRepository;
        this.commentRepository = commentRepository;
    }

    public Post createPost(PostRegistration post) {
        AppUser user = appUserRepository.findByUsername(post.getAuthorUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Post newPost = new Post();
        newPost.setContentType(post.getContentType());
        newPost.setContent(post.getContent());
        newPost.setAuthor(user);

        return postRepository.save(newPost);

    }

    public String addComment(Long postId, Comment comment) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        BotUser user = botuserRepository.findByName(comment.getAuthor().getName());

        int depth = 1;
        if (comment.getParentComment() != null) {
            depth = comment.getParentComment().getDepth_level() + 1;
        }

        if (depth > 20) {
            throw new RuntimeException("Max depth exceeded");
        }

        comment.setPost(post);
        comment.setAuthor(user);
        comment.setDepth_level(depth);

        commentRepository.save(comment);

        redisService.incrementVirality(postId, 50);

        return "Comment added!";
    }

    public String likePost(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        redisService.incrementVirality(postId, 20);

        return "Post liked!";
    }
}

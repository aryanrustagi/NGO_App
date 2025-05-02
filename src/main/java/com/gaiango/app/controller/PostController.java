package com.gaiango.app.controller;

import com.gaiango.app.model.Post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/community")
public class PostController {

    private final List<Post> posts = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> uploadPost(@RequestBody Post post) {
        posts.add(post);

        System.out.println("Received new post:");
        System.out.println("Title: " + post.getTitle());
        System.out.println("Username: " + post.getUsername());
        System.out.println("Content: " + post.getContent());
        System.out.println("Image: " + post.getImage());
        System.out.println("People No.: " + post.getPeopleNo());
        System.out.println("Tags: " + post.getTags());
        System.out.println("Category: " + post.getCategory());

        return new ResponseEntity<>("Post uploaded successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return posts;
    }
}

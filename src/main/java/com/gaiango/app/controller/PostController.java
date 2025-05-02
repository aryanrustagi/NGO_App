package com.gaiango.app.controller;

import com.gaiango.app.model.Post;
import com.gaiango.app.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Post> uploadPost(@RequestBody Post post) {
        Post savedPost = postService.savePost(post);
        if (savedPost == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}

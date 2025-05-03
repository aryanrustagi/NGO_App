package com.gaiango.app.controller;

import com.gaiango.app.model.Post;
import com.gaiango.app.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPost(
            @RequestPart("post") Post post,
            @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            if (post == null || imageFile.isEmpty()) {
                return new ResponseEntity<>("Post details or image file is missing.", HttpStatus.BAD_REQUEST);
            }

            Post savedPost = postService.savePost(post, imageFile);
            return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

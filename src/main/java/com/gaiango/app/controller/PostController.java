package com.gaiango.app.controller;

import com.gaiango.app.model.Post;
import com.gaiango.app.service.PostService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPost(
            @RequestPart("post") String postJson,
            @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            // Check if the image is being received correctly
            if (imageFile.isEmpty()) {
                return new ResponseEntity<>("Image file is missing.", HttpStatus.BAD_REQUEST);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Post post = objectMapper.readValue(postJson, Post.class);

            // Check that the image is being processed correctly
            post.setImageDate(imageFile.getBytes()); // Save the actual image data
            post.setImageName(imageFile.getOriginalFilename());
            post.setImageType(imageFile.getContentType());

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

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Post post = postService.getPostById(id); // implement this method
        if (post == null || post.getImageDate() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(post.getImageType()))
                .body(post.getImageDate());
    }

}

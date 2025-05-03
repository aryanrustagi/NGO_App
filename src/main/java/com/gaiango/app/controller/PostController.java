package com.gaiango.app.controller;

import com.gaiango.app.dto.PostRequestDTO;
import com.gaiango.app.model.Post;
import com.gaiango.app.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Base64;

@RestController
@RequestMapping("/api/community")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPost(@RequestPart("dto") PostRequestDTO dto, @RequestPart("file") MultipartFile file) {
        try {
            Post post = new Post();
            post.setTitle(dto.getTitle());
            post.setUsername(dto.getUsername());
            post.setContent(dto.getContent());
            post.setPeopleNo(dto.getPeopleNo());
            post.setTags(dto.getTags());
            post.setCategory(dto.getCategory());
            post.setImageName(dto.getImageName());
            post.setImageType(dto.getImageType());

            if (dto.getImageDate() != null && dto.getImageDate().length > 0) {
                byte[] decodedImage = Base64.getDecoder().decode(dto.getImageDate());
                post.setImageDate(decodedImage);
            }

            Post savedPost = postService.savePost(post, file);
            return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
        Post post = postService.getPostById(id);
        if (post == null || post.getImageDate() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(post.getImageType()))
                .body(post.getImageDate());
    }

}

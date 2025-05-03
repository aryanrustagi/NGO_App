package com.gaiango.app.service;

import com.gaiango.app.model.Post;
import com.gaiango.app.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Save post without MultipartFile, works with Base64 image data
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // Get all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get post by ID
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}

package com.gaiango.app.service;

import com.gaiango.app.model.Post;
import com.gaiango.app.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(Post post, MultipartFile imageFile) throws IOException {
        if (post == null) {
            throw new IllegalArgumentException("Post data is required.");
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            post.setImageUrl(java.util.Base64.getEncoder().encodeToString(imageFile.getBytes()));
            post.setImageName(imageFile.getOriginalFilename());
            post.setImageType(imageFile.getContentType());
        }

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}

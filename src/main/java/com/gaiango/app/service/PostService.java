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
    private final CloudStorageService cloudStorageService;

    public PostService(PostRepository postRepository, CloudStorageService cloudStorageService) {
        this.postRepository = postRepository;
        this.cloudStorageService = cloudStorageService;
    }

    public Post savePost(Post post, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            // Upload image to cloud and get the public URL
            String imageUrl = cloudStorageService.uploadFile(imageFile);

            post.setImageName(imageFile.getOriginalFilename());
            post.setImageType(imageFile.getContentType());
            post.setImageUrl(imageUrl);
        }

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}

package com.gaiango.app.repository;

import com.gaiango.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // You can define custom query methods here if needed
}

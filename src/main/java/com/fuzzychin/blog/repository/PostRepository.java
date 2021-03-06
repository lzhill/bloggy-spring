package com.fuzzychin.blog.repository;


import com.fuzzychin.blog.bean.Comment;
import com.fuzzychin.blog.bean.Post;
import com.fuzzychin.blog.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTags(List<Tag> tags);

    Post findByComments(List<Comment> comment);

    List<Post> findByUserId(long userId);

}

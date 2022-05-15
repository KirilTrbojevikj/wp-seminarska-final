package com.review.seminarska.service;

import com.review.seminarska.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public List<Post> getAllPosts();

    public void addPost(Post post);

    public void removePostById(Long id);

    public Optional<Post> getPostById(Long id);

    public List<Post> getAllPostsByCategoryId(Long id);

}

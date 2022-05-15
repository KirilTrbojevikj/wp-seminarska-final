package com.review.seminarska.service.Impl;

import com.review.seminarska.model.Post;
import com.review.seminarska.repository.PostRepository;
import com.review.seminarska.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postsRepository;

    public PostServiceImpl(PostRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public void addPost(Post post) {
        postsRepository.save(post);
    }

    @Override
    public void removePostById(Long id) {
        postsRepository.deleteById(id);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postsRepository.findById(id);
    }

    @Override
    public List<Post> getAllPostsByCategoryId(Long id) {
        return postsRepository.findAllByCategoryId(id);
    }

}

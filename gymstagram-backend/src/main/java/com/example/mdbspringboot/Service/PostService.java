package com.example.mdbspringboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mdbspringboot.model.Post;
import com.example.mdbspringboot.model.Comment;
import com.example.mdbspringboot.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getAllPostsbyUserId(String userId) {
        return postRepository.findByUserId(userId);
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        return postRepository.save(post);
    }

    public Post updatePost(String id, Post post) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setDescription(post.getDescription());
            //updatedPost.setMediaUrl(post.getMediaUrl());
            return postRepository.save(updatedPost);
        }
        return null;
    }

    public void deletePost(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete post");
        }
    }

    public Comment addCommentToPost(String id, Comment comment) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            List<Comment> comments = updatedPost.getComments();
            comments.add(comment);
            updatedPost.setComments(comments);
            postRepository.save(updatedPost);
            return comment;
        }
        return null;
    }

    public void deleteCommentFromPost(String postId, String commentId) {
        Optional<Post> existingPost = postRepository.findById(postId);
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            List<Comment> comments = updatedPost.getComments();
            for (int i = 0 ; i < comments.size(); i++) {
                if (comments.get(i).getId().equals(commentId)) {
                    comments.remove(i);
                    updatedPost.setComments(comments);
                    postRepository.save(updatedPost);
                }
            }
        }
    }

    public void incrementPostLike(String postId) {
        Optional<Post> existingPost = postRepository.findById(postId);
        if(existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            int currentLikes = updatedPost.getLikes();
            updatedPost.setLikes(currentLikes + 1);
            postRepository.save(updatedPost);
        }
    }

    public void decrementPostLike(String postId) {
        Optional<Post> existingPost = postRepository.findById(postId);
        if(existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            int currentLikes = updatedPost.getLikes();
            if (currentLikes > 0 ) {
                updatedPost.setLikes(currentLikes - 1);
            } 
            postRepository.save(updatedPost);
        }
    }
}
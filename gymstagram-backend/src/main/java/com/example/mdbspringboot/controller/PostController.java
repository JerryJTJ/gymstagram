/**

This class represents the controller for Post entity
*/
package com.example.mdbspringboot.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mdbspringboot.Service.PostService;
import com.example.mdbspringboot.model.Post;
import com.example.mdbspringboot.model.Comment;


@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Post> getAllPostsbyUserId(@PathVariable String userId) {
        return postService.getAllPostsbyUserId(userId);
    }

    @PostMapping("")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PostMapping("/{id}/comment")
    public Comment addCommentToPost(@PathVariable String id, @RequestBody Comment comment) {
        return postService.addCommentToPost(id, comment);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @PutMapping("/{id}/like")
    public void likePost(@PathVariable String id) {
        postService.incrementPostLike(id);
    }

    @PutMapping("/{id}/unlike")
    public void unlikePost(@PathVariable String id) {
        postService.decrementPostLike(id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        postService.deletePost(id);
    }

    @DeleteMapping("/{id}/comment/{commentId}")
    public void deleteCommentFromPost(@PathVariable String id, @PathVariable String commentId) {
        postService.deleteCommentFromPost(id, commentId);
    }
}
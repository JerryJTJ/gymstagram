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

    /**
     * Returns a list of all posts.
     * @return List of all posts
     */
    @GetMapping("")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * Returns the post with the given ID.
     * @param id The ID of the post to retrieve
     * @return The post with the given ID
     */
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    /**
     * Returns a list of all posts by the given user.
     * @param userId The ID of the user whose posts to retrieve
     * @return List of all posts by the given user
     */
    @GetMapping("/user/{userId}")
    public List<Post> getAllPostsbyUserId(@PathVariable String userId) {
        return postService.getAllPostsbyUserId(userId);
    }

    /**
     * Creates a new post.
     * @param post The post to create
     * @return The created post
     */
    @PostMapping("")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    /**
     * Adds a comment to the post with the given ID.
     * @param id The ID of the post to add the comment to
     * @param comment The comment to add to the post
     * @return The added comment
     */
    @PostMapping("/{id}/comment")
    public Comment addCommentToPost(@PathVariable String id, @RequestBody Comment comment) {
        return postService.addCommentToPost(id, comment);
    }

    /**
     * Updates the post with the given ID.
     * @param id The ID of the post to update
     * @param post The updated post object
     * @return The updated post object
     */
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    /**
     * Likes the post with the given ID.
     * @param id The ID of the post to like
     */
    @PutMapping("/{id}/like/{userId}")
    public void likePost(@PathVariable String id, @PathVariable String userId) {
        postService.addLike(id, userId);
    }

    /**
     * Unlikes the post with the given ID.
     * @param id The ID of the post to unlike
     */
    @PutMapping("/{id}/unlike/{userId}")
    public void unlikePost(@PathVariable String id, @PathVariable String userId) {
        postService.removeLike(id, userId);
    }

    /**
     * Deletes the post with the given ID.
     * @param id The ID of the post to delete
     */
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        postService.deletePost(id);
    }

    /**
     * Deletes the comment with the given ID from the post with the given ID.
     * @param id The ID of the post to delete the comment from
     * @param commentId The ID of the comment to delete
     */
    @DeleteMapping("/{id}/comment/{commentId}")
    public void deleteCommentFromPost(@PathVariable String id, @PathVariable String commentId) {
        postService.deleteCommentFromPost(id, commentId);
    }
}
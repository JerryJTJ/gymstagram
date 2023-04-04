package com.example.gymstagram;

import com.example.gymstagram.model.Post;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PostsViewModel extends ViewModel {
    private MutableLiveData<List<Post>> posts = new MutableLiveData<List<Post>>();

    public List<Post> getPosts() {
        return posts.getValue();
    }
    public void addPost(Post post){
        if (posts.getValue() == null){
            List<Post> myList = new ArrayList<>();
            posts = new MutableLiveData<List<Post>>(myList);
        }
        List<Post> postsList = posts.getValue();
        postsList.add(post);
        posts.postValue(postsList);
    }
}

package com.example.gymstagram;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.gson.JsonObject;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import utils.FileUtil;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymstagram.databinding.FragmentNewPostBinding;
import com.example.gymstagram.model.Post;
import com.example.gymstagram.model.User;
import com.example.gymstagram.retrofit.ApiClient;
import com.example.gymstagram.retrofit.RetrofitService;
import com.example.gymstagram.retrofit.UserAPI;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewPost extends Fragment {

    private FragmentNewPostBinding binding;
    private PostsViewModel viewModel;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    public class MyResponse {
        private String photoId;

        public String getphoto() {
            return photoId;
        }
    }
    private String photoID;
    private String userName;


        @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);
        binding = FragmentNewPostBinding.inflate(inflater, container, false);

        pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        File file = new File(FileUtil.getPath(uri, getContext()));
//                        Log.i("uhuhuhuhhuh", "photoid" + uri + getRealPathFromURI);
                        RequestBody requestFile = RequestBody.create(file,MediaType.parse("multipart/form-data"));
                        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

                        Call<MyResponse> addphoto = ApiClient.getPostService().addPhoto(filePart);
                        Log.i("UUUUUUU", addphoto.toString());
                        addphoto.enqueue(new Callback<MyResponse>() {
                            @Override
                            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                if(response.isSuccessful()){
                                    response.body();
                                    photoID = response.body().getphoto();
                                    Log.i("hhhh", "photoid" + response.body().photoId);
                                } else{
//                                    photoID = response.body();
                                    //hhhh: fix this - it returns a 400 bad request
                                    Log.i("hhhh", "boo" + photoID + response.errorBody());
                                }
                            }
                            @Override
                            public void onFailure(Call<MyResponse> call, Throwable t) {
                                Log.e("hhhh", "onFailure: Could not add post" + t);
//                                Log.i("uuuuuuuuuuuu", "boo" + photoID + response.errorBody());
                            }
                        });
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });
            String userID = MainActivity.userId;
            RetrofitService retrofitService = new RetrofitService();
            UserAPI userAPI = retrofitService.getRetrofit().create(UserAPI.class);
            Call<User> currUser = userAPI.getUserById(userID);
            currUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        userName = response.body().getUsername();
                        binding.username.setText(userName);
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("hhhh", "could not get username");
                }
            });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(PostsViewModel.class);

        binding.buttonUploadMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handler here
                //Android studio complains about the following line but it compiles fine
                ActivityResultContracts.PickVisualMedia.VisualMediaType mediaType = (ActivityResultContracts.PickVisualMedia.VisualMediaType) ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE;

                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(mediaType)
                        .build());
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewPost.this)
                        .navigate(R.id.action_newPost_pop);
            }
        });

        binding.buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handler here
                String description = binding.description.getText().toString();
                // TODO:get actual username


                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
                String id = ft.format(dNow);
                List<String> photos = new ArrayList<>();
                photos.add(photoID);
                Post post = new Post(id, MainActivity.userId, description, photos);

                Call<Post> newPost = ApiClient.getPostService().createPost(post);
                newPost.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()){
                            Log.i("Add Post", "Created post successfully");
                        }
                    }
                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e("Add Post", "onFailure: Could not add post");
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
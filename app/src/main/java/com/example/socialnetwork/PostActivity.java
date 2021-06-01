package com.example.socialnetwork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PostActivity extends AppCompatActivity {
//    private ImageButton SelectPostImage;
//    private Button UpdatePostButton;
//    private EditText PostDescription;
//
//    private static final int Gallery_Pick =1;
//
//    private Uri ImageUri;
//    private String Description;
//    private StorageReference PostsImagesrefrence;
//    private String saveCurrentDate, saveCurrentTime, postRandomName;
//    private String downloadUri;
//    private DatabaseReference UserRef, PostRef;
//    private FirebaseAuth mAuth;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_post);
//
//        SelectPostImage = (ImageButton) findViewById(R.id.select_post_image);
//        UpdatePostButton = (Button) findViewById(R.id.update_post_button);
//        PostDescription = (EditText) findViewById(R.id.post_description);
//        PostsImagesrefrence = FirebaseStorage.getInstance().getReference();
//        UserRef = FirebaseDatabase.getInstance().getReference().child("User");
//        PostRef = FirebaseDatabase.getInstance().getReference().child("Posts");
//
//        SelectPostImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OpenGallery();
//            }
//        });
//
//        UpdatePostButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ValidatePostInfo();
//            }
//        });
//    }
//
//    private void ValidatePostInfo() {
//        Description = PostDescription.getText().toString();
//
//        if(ImageUri == null){
//            Toast.makeText(this, "please select Image..", Toast.LENGTH_SHORT).show();
//        }
//        if(TextUtils.isEmpty(Description)){
//            Toast.makeText(this, "please describe post..", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            StoringImageToFireBaseStorage();
//        }
//    }
//
//    private void StoringImageToFireBaseStorage() {
//        Calendar calForDate = Calendar.getInstance();
//        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
//        saveCurrentDate = currentDate.format(calForDate.getTime());
//
//        Calendar calForTime = Calendar.getInstance();
//        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
//        saveCurrentTime = currentTime.format(calForTime.getTime());
//
//        postRandomName = saveCurrentDate + saveCurrentTime;
//
//        StorageReference filePath = PostsImagesrefrence.child("Post Images").child(ImageUri.getLastPathSegment() + postRandomName+ ".jpg");
//        filePath.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                if(task.isSuccessful()){
//                    downloadUri = task.getResult().getStorage().getDownloadUrl().toString();
//
//                    Toast.makeText(PostActivity.this, downloadUri, Toast.LENGTH_SHORT).show();
//                    SavingPostInformationToDatabase();
//                }
//                else{
//                    String message = task.getException().getMessage();
//                    Toast.makeText(PostActivity.this, message, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void SavingPostInformationToDatabase() {
//        UserRef.child("7sRDmhfN7rTjZ2oidCLwCYFpxqV2").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    String fullName = snapshot.child("name").getValue().toString();
//                    String email = snapshot.child("email").getValue().toString();
//                    String company = snapshot.child("company").getValue().toString();
//                    HashMap postsMap = new HashMap();
//                        postsMap.put("uid", "7sRDmhfN7rTjZ2oidCLwCYFpxqV2");
//                        postsMap.put("date", saveCurrentDate);
//                        postsMap.put("time", saveCurrentTime);
//                        postsMap.put("description", Description);
//                        postsMap.put("postImage", downloadUri);
//                        postsMap.put("name", fullName);
//                        postsMap.put("email", email);
//                        postsMap.put("company", company);
//                    PostRef.child("7sRDmhfN7rTjZ2oidCLwCYFpxqV2" + postRandomName).updateChildren(postsMap)
//                        .addOnCompleteListener(new OnCompleteListener() {
//                            @Override
//                            public void onComplete(@NonNull Task task) {
//                                if(task.isSuccessful()){
//
//                                    Toast.makeText(PostActivity.this, "posted successfully", Toast.LENGTH_SHORT).show();
//                                }
//                                else
//                                {
//                                    String Error = task.getException().toString();
//                                    Toast.makeText(PostActivity.this, Error, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    private void OpenGallery() {
//        Intent galleryIntent = new Intent();
//        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//        galleryIntent.setType("image/*");
//        startActivityForResult(galleryIntent, Gallery_Pick);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==Gallery_Pick && resultCode==RESULT_OK && data!=null){
//            ImageUri = data.getData();
//            SelectPostImage.setImageURI(ImageUri);
//        }
//    }
//}
private Toolbar mToolbar;
    private ProgressDialog loadingBar;

    private ImageButton SelectPostImage;
    private Button UpdatePostButton;
    private EditText PostDescription;

    private static final int Gallery_Pick = 1;
    private Uri ImageUri;
    private String Description;

    private StorageReference PostsImagesRefrence;
    private DatabaseReference UsersRef, PostsRef;
    private FirebaseAuth mAuth;

    private String saveCurrentDate, saveCurrentTime, postRandomName, downloadUrl, current_user_id;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();

        PostsImagesRefrence = FirebaseStorage.getInstance().getReference();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("User");
        PostsRef = FirebaseDatabase.getInstance().getReference().child("Posts");


        SelectPostImage = (ImageButton) findViewById(R.id.select_post_image);
        UpdatePostButton = (Button) findViewById(R.id.update_post_button);
        PostDescription =(EditText) findViewById(R.id.post_description);
        loadingBar = new ProgressDialog(this);


//        mToolbar = (Toolbar) findViewById(R.id.update_post_page_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Update Post");


        SelectPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                OpenGallery();
            }
        });


        UpdatePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidatePostInfo();
            }
        });
    }



    private void ValidatePostInfo()
    {
        Description = PostDescription.getText().toString();

        if(ImageUri == null)
        {
            Toast.makeText(this, "Please select post image...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Please say something about your image...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Add New Post");
            loadingBar.setMessage("Please wait, while we are updating your new post...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
//            SavingPostInformationToDatabase();
            StoringImageToFirebaseStorage();
        }
    }



    private void StoringImageToFirebaseStorage()
    {
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());

        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calFordDate.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;


        StorageReference filePath = PostsImagesRefrence.child("Post Images").child(ImageUri.getLastPathSegment() + postRandomName + ".jpg");

//        filePath.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task)
//            {
//                if(task.isSuccessful())
//                {
//                    downloadUrl = task.getResult().getStorage().getDownloadUrl().toString();
//                    Toast.makeText(PostActivity.this, "image uploaded successfully to Storage...", Toast.LENGTH_SHORT).show();
//
//                    SavingPostInformationToDatabase();
//
//                }
//                else
//                {
//                    String message = task.getException().getMessage();
//                    Toast.makeText(PostActivity.this, "Error occured: " + message, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        filePath.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri downloadUrl = uri;
                        Toast.makeText(PostActivity.this, "image uploaded successfully to Storage...", Toast.LENGTH_SHORT).show();
//
                        SavingPostInformationToDatabase(downloadUrl.toString());
                    }

                });
            }
            });

    }




    private void SavingPostInformationToDatabase(String url)
    {
        UsersRef.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String userFullName = dataSnapshot.child("name").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();

                    HashMap postsMap = new HashMap();
                    postsMap.put("uid", current_user_id);
                    postsMap.put("date", saveCurrentDate);
                    postsMap.put("time", saveCurrentTime);
                    postsMap.put("description", Description);
                    postsMap.put("postimage", url);
                    postsMap.put("email", email);
                    postsMap.put("fullname", userFullName);
                    PostsRef.child(current_user_id + postRandomName).updateChildren(postsMap)
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        SendUserToMainActivity();
                                        Toast.makeText(PostActivity.this, "New Post is updated successfully.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                    else
                                    {
                                        Toast.makeText(PostActivity.this, "Error Occured while updating your post.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Gallery_Pick);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Pick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri = data.getData();
            SelectPostImage.setImageURI(ImageUri);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            SendUserToMainActivity();
        }

        return super.onOptionsItemSelected(item);
    }



    private void SendUserToMainActivity()
    {
        Intent mainIntent = new Intent(PostActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
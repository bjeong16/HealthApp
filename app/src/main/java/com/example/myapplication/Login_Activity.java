package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;



public class Login_Activity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "Exception";
    public static final int Static_INT = 2016;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        setContentView(R.layout.activity_login_);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.sign_in_button:
                        signIn(mGoogleSignInClient);
                        break;
                }
            }
        });

    }

    private void signIn(GoogleSignInClient mGoogleSignInClient){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account == null){
            // first sign in
        }
        else{
            // user has already signed in
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        if(requestCode == Static_INT){
            if(resultCode == Activity.RESULT_OK){
                String login_2_result = data.getStringExtra("QuestionResult");
                String[] split_login_2_result = login_2_result.split(" ");
                Log.d("Firebase", login_2_result);
                write_to_firebase(split_login_2_result);
            }
        }
    }

    private void checkFirestore(GoogleSignInAccount account){       //  Check if the User is already in the Database

        final String userName = account.getId();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        System.out.println(myRef.child("Users").orderByChild("Username").toString());
        myRef.child("Users").orderByChild("Username").equalTo(userName).addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.d("Firebase", "dataSnapshot value = " + dataSnapshot.getValue());
                        dataSnapshot.child(account.getId()).getValue();
                        if (dataSnapshot.exists()) {

                            Log.d("Firebase", "User Already Exists");
                            String Username = myRef.child("Users/Username").toString();
                            String username = dataSnapshot.child(account.getId()).child("Username").getValue().toString();
                            String sex = dataSnapshot.child(account.getId()).child("Sex").getValue().toString();
                            String last_workout = dataSnapshot.child(account.getId()).child("Last_Workout").getValue().toString();
                            String age = dataSnapshot.child(account.getId()).child("age").getValue().toString();
                            String weight = dataSnapshot.child(account.getId()).child("weight").getValue().toString();
                            String height = dataSnapshot.child(account.getId()).child("height").getValue().toString();

                            User user1 = new User(username, last_workout, age, height, weight, sex);
                            Log.d("Firebase", user1.Username + user1.getLast_Workout());


                        }
                        else {
                            String username = dataSnapshot.child(account.getId()).getKey().toString();
                            String last_workout = "Chest";
                            switch_login_2(username + " " + last_workout); // 추가정보창으로 이동
                        }
                    }
                    @Override
                    public void onCancelled (DatabaseError databaseError){

                    }
                }

        );
    }
    private void write_to_firebase(String[] information){
        User user1 = new User(information[0], "chest", "21", information[3], information[2], information[4]);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child("Username").setValue(user1.Username);
        ref.child("Users").child("Last_Workout").setValue(user1.Last_Workout);
        ref.child("Users").child("age").setValue(user1.age);
        ref.child("Users").child("height").setValue(user1.height);
        ref.child("Users").child("weight").setValue(user1.weight);
        ref.child("Users").child("Sex").setValue(user1.getSex());
    }
    private void switch_login_2(String info){
        Intent switch_login_2_intent = new Intent(this, Login_Activity_2.class);
        switch_login_2_intent.putExtra("ExtraInfo", info);
        startActivityForResult(switch_login_2_intent, Static_INT);
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){       // Get Google Account Associated with User
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            TextView id_display = findViewById(R.id.textView);
            id_display.setText(account.getGivenName());
            checkFirestore(account);
        }
        catch(ApiException e){
            Log.w(TAG, "signInResult:failed code= " + e.getStatusCode());
            //update
        }
    }
}
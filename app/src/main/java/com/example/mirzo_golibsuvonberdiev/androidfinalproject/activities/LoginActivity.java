package com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.admin.AdminMainActivity;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.common.Common;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username_edit_text)
    EditText username;
    @BindView(R.id.password_edit_text)
    EditText password;
    DatabaseReference myRef;
    final String TAG = "CHECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

    }

    @OnClick(R.id.login_button)
    public void loginMehtod(View view) {

        if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Please, Wait...");
            progressDialog.show();
            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(username.getText().toString()).exists()) {
                        progressDialog.dismiss();
                        User user = dataSnapshot.child(username.getText().toString()).getValue(User.class);
                        if (user.getPassword().equals(password.getText().toString())) {
                            if (user.getType().equals("1")) {
                                Log.d("ADMIN", "WELCOME TO ADMIN PAGE");
                                Toast.makeText(LoginActivity.this, "Welcome to admin page", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                                Common.currentUser = user;
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(LoginActivity.this, BaseActivity.class);
                                Common.currentUser = user;
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        } else {
            Toast.makeText(LoginActivity.this, "Enter all information", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.sign_up_btn)
    public void signUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }


}

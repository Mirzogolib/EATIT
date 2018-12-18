package com.example.mirzo_golibsuvonberdiev.androidfinalproject.activities;

import android.app.ProgressDialog;
import android.os.WorkSource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mirzo_golibsuvonberdiev.androidfinalproject.R;
import com.example.mirzo_golibsuvonberdiev.androidfinalproject.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.username_edit_text)
    EditText username;
    @BindView(R.id.password_edit_text)
    EditText password;
    @BindView(R.id.full_name_edit_text)
    EditText fullName;
    @BindView(R.id.phone_edit_text)
    EditText phone;
    DatabaseReference myRef;
    final String TAG = "CHECK";
    boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

    }

    @OnClick(R.id.sign_up_btn)
    public void signUp(View view) {
        if (!username.getText().toString().isEmpty() && !fullName.getText().toString().isEmpty() && !phone.getText().toString().isEmpty()
                && !password.getText().toString().isEmpty()) {
            final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
            progressDialog.setMessage("Please, Wait...");
            progressDialog.show();
            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(username.getText().toString()).exists() && aBoolean) {
                        Log.d(TAG, "It is registered");
                        progressDialog.dismiss();

                        Toast.makeText(SignUpActivity.this, "This username already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "It is not registered");
                        progressDialog.dismiss();
                        User user = new User(fullName.getText().toString(), password.getText().toString(), phone.getText().toString());
                        myRef.child(username.getText().toString()).setValue(user);
                        aBoolean = false;
                        Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        } else {
            Toast.makeText(SignUpActivity.this, "Enter all specific information", Toast.LENGTH_SHORT).show();
        }
    }
}

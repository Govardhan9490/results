package com.ukv.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    EditText mEmail, mPass, mId;
    Button mButton;
    FirebaseAuth auth;
    DatabaseReference reference;
    private ProgressDialog mLoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mEmail = findViewById(R.id.email);
        mPass = findViewById(R.id.password);
        mId = findViewById(R.id.reg);
        mButton = findViewById(R.id.reg_but);
        auth = FirebaseAuth.getInstance();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String pass= mPass.getText().toString();
                String id = mId.getText().toString();

                mLoginProgress = new ProgressDialog(RegistrationActivity.this);
                //        mLoginProgress.setTitle("Logging In");
                mLoginProgress.setMessage("Logging in...");
                mLoginProgress.setCanceledOnTouchOutside(false);

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(id)){
                    Toast.makeText(RegistrationActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else {
                    register(email, pass, id);
                }

            }
        });

    }

    private void register(String email, String pass, String id) {

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    String userid = firebaseUser.getUid();
                    mLoginProgress.show();

                    reference = FirebaseDatabase.getInstance().getReference("users").child(userid);

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", userid);
                    hashMap.put("reg", id);
                    hashMap.put("email", email);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(RegistrationActivity.this, OptionActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("stid", id);
                                startActivity(intent);
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                mLoginProgress.hide();
                                finish();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistrationActivity.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                    mLoginProgress.hide();
                }
            }
        });
    }
}
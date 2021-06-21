package com.ukv.attendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class OptionActivity<Private> extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    DatabaseReference reference2;

    AdapterData messageAdapter;
    List<User> mUser;

    String stid;
    String stid2;

    TextView one, two, three, four, five, six, seven, eight;
    TextView semester;

    private ProgressDialog mLoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);


        // For sem
        one = findViewById(R.id.oneone);
        two = findViewById(R.id.onetwo);
        three = findViewById(R.id.twoone);
        four = findViewById(R.id.twotwo);
        five = findViewById(R.id.threeone);
        six = findViewById(R.id.threetwo);
        seven = findViewById(R.id.fourone);
        eight = findViewById(R.id.fourtwo);

        semester = findViewById(R.id.semester);

        // From main
        Intent intent = getIntent();
        stid = intent.getStringExtra("stid");
        // modified
        Log.i("stuid", String.valueOf(stid));

        //From Reg
        Intent intent1 = getIntent();
        stid2 = intent.getStringExtra("stid");

        mUser = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        //recyclerView.setAdapter(adapter);
        /*DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);*/
//        recyclerView.notify();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        reference2 = FirebaseDatabase.getInstance().
                getReference("details" + "/" + stid + "/" + "/one_two");

        Log.i("path", String.valueOf(reference));

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(one.getText().toString());
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(two.getText().toString());
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(three.getText().toString());
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(four.getText().toString());
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(five.getText().toString());
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(six.getText().toString());
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(seven.getText().toString());
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(eight.getText().toString());
            }
        });

        /*reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //mLoginProgress.show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User l = snapshot.getValue(User.class);
                        mUser.add(l);
                    }
                    messageAdapter = new AdapterData(getApplicationContext(), mUser);
                    recyclerView.setAdapter(messageAdapter);
                    //mLoginProgress.hide();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        /*reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //mLoginProgress.show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User l = snapshot.getValue(User.class);
                        mUser.add(l);
                    }
                    messageAdapter = new AdapterData(getApplicationContext(), mUser);
                    recyclerView.setAdapter(messageAdapter);
                    //mLoginProgress.hide();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }

    private void readMessage(String uid, String userid) {

        mUser = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("subject");
    }


    public void getData(String text) {
        mLoginProgress = new ProgressDialog(OptionActivity.this);
        //        mLoginProgress.setTitle("Logging In");
        mLoginProgress.setMessage("Getting Data...");
        mLoginProgress.setCanceledOnTouchOutside(true);
        mLoginProgress.show();

        mUser.clear();
        semester.setText(text);
        reference = FirebaseDatabase.getInstance().
                getReference("details" + "/" + stid + "/" + text);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //mLoginProgress.show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User l = snapshot.getValue(User.class);
                        mUser.add(l);
                    }
                    messageAdapter = new AdapterData(getApplicationContext(), mUser);
                    recyclerView.setAdapter(messageAdapter);
                    mLoginProgress.hide();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
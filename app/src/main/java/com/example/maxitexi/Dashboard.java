package com.example.maxitexi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class Dashboard extends AppCompatActivity {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    ImageView imgdown,imgup,imgdown1,imgup1;
    LinearLayout datalinear,datalinear1,linear1,linear2;
    RelativeLayout relative1,relative2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.LogoColor, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.LogoColor));
        }

        imgdown = findViewById(R.id.imgdown);
        imgdown1 = findViewById(R.id.imgdown1);
        imgup = findViewById(R.id.imgup);
        imgup1 = findViewById(R.id.imgup1);
        datalinear = findViewById(R.id.datalinear);
        datalinear1 = findViewById(R.id.datalinear1);
        relative1 = findViewById(R.id.relative1);
        relative2 = findViewById(R.id.relative2);
        linear2 = findViewById(R.id.linear2);
        linear1 = findViewById(R.id.linear1);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (!task.isSuccessful()) {
                    Log.e(TAG, "Failed to get the token.");
                    return;
                }

                String token = task.getResult();

                Log.e(TAG, "Token : " + token);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failed to get the token : " + e.getLocalizedMessage());
            }
        });

        imgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datalinear.setVisibility(View.GONE);
                imgup.setVisibility(View.GONE);
                relative1.setVisibility(View.GONE);
                linear1.setVisibility(View.GONE);
                imgdown.setVisibility(View.VISIBLE);
            }
        });

        imgdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datalinear.setVisibility(View.VISIBLE);
                imgup.setVisibility(View.VISIBLE);
                relative1.setVisibility(View.VISIBLE);
                linear1.setVisibility(View.VISIBLE);
                imgdown.setVisibility(View.GONE);
            }
        });

        imgup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datalinear1.setVisibility(View.GONE);
                imgup1.setVisibility(View.GONE);
                relative2.setVisibility(View.GONE);
                linear2.setVisibility(View.GONE);
                imgdown1.setVisibility(View.VISIBLE);
            }
        });

        imgdown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datalinear1.setVisibility(View.VISIBLE);
                imgup1.setVisibility(View.VISIBLE);
                relative2.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.VISIBLE);
                imgdown1.setVisibility(View.GONE);
            }
        });

    }
}
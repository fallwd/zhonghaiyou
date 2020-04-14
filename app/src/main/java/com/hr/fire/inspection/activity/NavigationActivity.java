package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;


public class NavigationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        LinearLayout btn1 = (LinearLayout) findViewById(R.id.Navigation_Btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
<<<<<<< HEAD
=======
//                Intent intent = new Intent(NavigationActivity.this, PhotoUploadActivity.class);
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b
                Intent intent = new Intent(NavigationActivity.this, FireActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout btn2 = (LinearLayout) findViewById(R.id.Navigation_Btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(NavigationActivity.this, InspectionActivity.class);
                Intent intent = new Intent(NavigationActivity.this, RoutingInspectionActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout btn3 = (LinearLayout) findViewById(R.id.Navigation_Btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout btn4 = (LinearLayout) findViewById(R.id.Navigation_Btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });
    }
}

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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                Intent intent = new Intent(NavigationActivity.this, FireActivity.class);
=======
                Intent intent = new Intent(NavigationActivity.this, CarbonDioxideAcitivty.class);
>>>>>>> cy_dev
=======
//                Intent intent = new Intent(NavigationActivity.this, PhotoUploadActivity.class);
                Intent intent = new Intent(NavigationActivity.this, ChooseCompanyActivity.class);
//                Intent intent = new Intent(NavigationActivity.this, FireActivity.class);
>>>>>>> c4246f918c6ccddb17c0082b7587b3782066eeac
=======

=======
>>>>>>> cy_dev
                Intent intent = new Intent(NavigationActivity.this, CarbonDioxideAcitivty.class);
>>>>>>> cy_dev
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

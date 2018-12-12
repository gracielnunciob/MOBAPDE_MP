package com.example.graci.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Story extends AppCompatActivity{

    private Button nextBtn;
    private LinearLayout storySV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story);

        nextBtn = findViewById(R.id.nextBtn);
    //    storySV = findViewById(R.id.storySV);

     //   storySV.setLayoutParams(new LinearLayout.LayoutParams(99, 99));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Levels.class);
                startActivity(intent);
            }
        });


    }
}

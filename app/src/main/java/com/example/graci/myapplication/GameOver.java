package com.example.graci.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    private Button playBtn;
    private String status;
    private String post;
    private TextView statusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        playBtn = findViewById(R.id.playBtn);
        statusTV = findViewById(R.id.statusTV);

        status = getIntent().getStringExtra("STATUS");

        post = getResourceFromString(this, status);

        statusTV.setText(post);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private String getResourceFromString(GameOver activity, String id){
        String packageName = activity.getPackageName();
        int resId = activity.getResources().getIdentifier(id, "string", packageName);
        return activity.getString(resId);
    }

}

package com.example.graci.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    
    private Button startBtn;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startBtn = findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Story.class);
                startActivity(intent);
            }
        });

        gestureDetector = new GestureDetector(this, new GestureListener());
        
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e2.getX() < e1.getX()){

                // swipe left

                Intent intent = new Intent(getApplicationContext(), Story.class);
                startActivity(intent);
            }

            else if (e2.getX() > e1.getX()){
                //swipe right

                Intent intent = new Intent(getApplicationContext(), Story.class);
                startActivity(intent);
            }

            return true;

        }
    }


}

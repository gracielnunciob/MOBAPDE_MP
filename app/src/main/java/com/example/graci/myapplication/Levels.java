package com.example.graci.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Levels extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    //stats to scale for enemy
    int baseHp = 60;
    int baseStrength = 6;
    int baseDefense = 2;
    private ImageView enemyIV;
    private TextView chatTV;
    private Button nextBtn;
    private int part;
    private int imageSet[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monsters);

        //connecting with the UI and initializing
        CustomView customView = new CustomView(this);
        enemyIV = findViewById(R.id.enemyIV);
        chatTV = findViewById(R.id.chatTV);
        nextBtn = findViewById(R.id.nextBtn);
        part = 1;
        imageSet = new int[]{R.drawable.student, R.drawable.scpres, R.drawable.janitor,
                            R.drawable.teacher, R.drawable.principal};

        //initializing player
        Player_model player = new Player_model("Bart", 100, 100, 10, 10);
        player.setCurrHp(50);

        //loops till you fight 5 enemies or lose 1 fight
        for (int matchCount = 0; matchCount < 5; matchCount++) {

            //new enemy
            Player_model enemy = new Player_model("Enemy" + (matchCount + 1), baseHp, baseHp, baseStrength, baseDefense);
            
            //Think(enemy,Difficulty)
            Think brain = new Think(enemy, matchCount + 1);
            //make new battle (includes counters and such)
            Battle functions = new Battle(player, enemy);


            //Part 1
            // just prints
            System.out.println("Enemy" + enemy.getName() + "Has appeared!");
            System.out.println("Enemy HP " + enemy.getHp());
            System.out.println("Enemy Strength " + enemy.getStrength());
            System.out.println("Enemy Defense " + enemy.getDefense());

            //Part 2 --> string na what to do
            //part 3 what they do

            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



        }


    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {


        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
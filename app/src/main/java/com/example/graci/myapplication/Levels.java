package com.example.graci.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
        GestureDetector.OnDoubleTapListener {

    //stats to scale for enemy
    int baseHp = 60;
    int baseStrength = 6;
    int baseDefense = 2;
    private ImageView enemyIV;
    private TextView chatTV;
    private Button nextBtn;
    private int part;
    private int imageSet[];
    int actionReg = 0;
    private GestureDetectorCompat gestureDetector;
    private Player_model player;
    private Player_model enemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monsters);

        //connecting with the UI and initializing
        CustomView customView = new CustomView(this);
        gestureDetector = new GestureDetectorCompat(this,this);
        enemyIV = findViewById(R.id.enemyIV);
        chatTV = findViewById(R.id.chatTV);
        nextBtn = findViewById(R.id.nextBtn);
        imageSet = new int[]{R.drawable.student, R.drawable.scpres, R.drawable.janitor,
                R.drawable.teacher, R.drawable.principal};
        gestureDetector.setOnDoubleTapListener(this);


        //initializing player
        player = new Player_model("Bart", 100, 100, 10, 10);


        //loops till you fight 5 enemies or lose 1 fight
        for (int matchCount = 0; matchCount < 5; matchCount++) {

            part = 1;
            String enemyAppear;
            String enemyStatus;
            String whatdo;
            final String phrase;

            //new enemy
            enemy = new Player_model("Enemy" + (matchCount + 1), baseHp, baseHp, baseStrength, baseDefense);

            //Think(enemy,Difficulty)
            final Think brain = new Think(enemy, matchCount + 1);

            //make new battle (includes counters and such)
            final Battle functions = new Battle(player, enemy);

            //picture of enemy
            enemyIV.setImageResource(imageSet[matchCount]);


            //phrase of the ENEMY
            phrase = getResourceFromString(this, "enemyPhrase_" + 1);
            chatTV.setText(phrase);

            while (player.getCurrHp() > 0 && enemy.getCurrHp() > 0) {

                //enemy Strings
                enemyStatus = "Enemy HP: " + enemy.getHp() + "\nEnemy Strength: " + enemy.getStrength() + "\nEnemy Defense: " + enemy.getDefense();
                enemyAppear = "Enemy " + getResourceFromString(this, "enemy_" + (matchCount + 1)) + " Has Appeared!\n" + enemyStatus;

                whatdo = getResourceFromString(this, "whatdo");

                final String finalEnemyAppear = enemyAppear;
                final String finalWhatdo = whatdo;

                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (part == 1) {
                            chatTV.setText(finalEnemyAppear);
                            part = 2;

                        } else if (part == 2)
                            chatTV.setText(finalWhatdo);

                        //part 3 what they do
                        else if (part == 3) {
                            switch (actionReg) {
                                case 1:
                                    functions.defend(player);
                                    chatTV.setText("Player guards!");
                                    functions.defendCount++;
                                    break;
                                case 2:
                                    functions.attack(player, enemy);
                                    chatTV.setText("Player attacks!");
                                    functions.attackCount++;
                                    break;
                                case 3:
                                    if (player.getCurrHp() + (player.getHp() / 2) > player.getHp()) {
                                        player.setCurrHp(player.getHp());
                                    } else
                                        player.setCurrHp(player.getCurrHp() + (player.getHp() / 2));
                                    chatTV.setText("Player heals!");
                                    break;

                            }
                            part = 4;
                        }


                        else if (part == 4) {
                            //enemy turn
                            //copy paste lang this but you can change the system.out and link it to android studio
                            switch (brain.Choice(brain.getDifficulty())) {
                                case 1:
                                    functions.defend(enemy);
                                    chatTV.setText("Enemy guards!");
                                    break;
                                case 2:
                                    functions.attack(enemy, player);
                                    chatTV.setText("Enemy attacks!");
                                    break;
                                case 3:
                                    enemy.setCurrHp(enemy.getCurrHp() + (enemy.getHp() / 2));
                                    chatTV.setText("Enemy heals!");
                                    break;
                            }

                            part = 5;

                        }

                        else if (part == 5){
                            chatTV.setText("You have "+ functions.getPlayer().getCurrHp() + "hp");
                            part = 6;
                        }

                        else if (part == 6){
                            //prints
                            String currStatus = "Max HP: "+player.getHp() + "\n" + "Current HP: "+
                                    functions.getPlayer().getCurrHp() + "\n" + "Strength: "+ player.getStrength() + "\n" +
                                    "Defense: "+player.getDefense() + "\n";
                            chatTV.setText(currStatus);
                            part = 1;
                        }

                    }
                });

                //escapes if enemy is dead
                if(enemy.getCurrHp()<=0)
                    break;

                //you died
                if(player.getCurrHp()<=0) {
                    System.out.println("You died");
                    Intent intent = new Intent(getApplicationContext(), GameOver.class);
                    intent.putExtra("STATUS", "lose");
                    startActivity(intent);
                    break;
                }

                //scaling after the fight (copy paste this after a fight)
                baseHp = baseHp+(matchCount+1)*5+40;
                baseStrength = baseStrength+(matchCount+1)*4+8;
                baseDefense = baseDefense+(matchCount+1)*4+3;

                //player scaling feature
                functions.postBattle();
            }

        }
    }

    private String getResourceFromString(Levels activity, String id){
        String packageName = activity.getPackageName();
        int resId = activity.getResources().getIdentifier(id, "string", packageName);
        return activity.getString(resId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
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
        if (part == 2){
            actionReg = 3;
            chatTV.setText("Action Registered");
            part = 3;
            return true;
        }

        else
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

        if (e2.getY() > e1.getY()){
            if (part == 2){
                actionReg = 1;
                chatTV.setText("Action Registered");
                part = 3;
            }
            // swipe up

            return true;
        }

        else if (e2.getX() > e1.getX()){
            //swipe right

            if (part == 2){
                actionReg = 2;
                chatTV.setText("Action Registered");
                part = 3;
            }
            return true;
        }

        else
            return false;

    }




}
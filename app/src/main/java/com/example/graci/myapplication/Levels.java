package com.example.graci.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

public class Levels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monsters);

        CustomView customView = new CustomView(this);



    }

    public void enterAnswer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("hi");

        LayoutInflater inflater = this.getLayoutInflater();
        final EditText editText = findViewById(R.id.editText);
        builder.setView(inflater.inflate(R.layout.answer_layout, null));

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Log.d("ANSWER","Cancel");

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Log.d("ANSWER","Cancel");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
package com.example.david.flow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton shootButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        shootButton = (ImageButton) findViewById(R.id.shootButton);


        shootButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Shoot.class);
                startActivityForResult(intent, 1);
            }
        });


        /*shootButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ReplayVideo.class);
                startActivityForResult(intent, 1);
            }
        });*/
    }


}

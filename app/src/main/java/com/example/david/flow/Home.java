package com.example.david.flow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton shootButton;
    ImageButton launchFlowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        shootButton = (ImageButton) findViewById(R.id.shootButton);
        launchFlowButton = (ImageButton) findViewById(R.id.launchFlowButton);

        shootButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Shoot.class);
                startActivityForResult(intent, 1);
            }
        });


        launchFlowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PlayFlow.class);
                startActivityForResult(intent, 1);
            }
        });
    }


}

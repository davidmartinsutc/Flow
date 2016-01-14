package com.example.david.flow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button takevideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        takevideo = (Button) findViewById(R.id.takevideo);


    }
}

package com.example.david.flow;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton shootButton, launchFlowButton, twitterButton, fbButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        shootButton = (ImageButton) findViewById(R.id.shootButton);
        launchFlowButton = (ImageButton) findViewById(R.id.launchFlowButton);
        twitterButton = (ImageButton) findViewById(R.id.twitterButton);
        fbButton = (ImageButton) findViewById(R.id.fbButton);


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

        twitterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToUrl(getResources().getString(R.string.twitter_app_url));
            }
        });

        fbButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToUrl(getResources().getString(R.string.fb_app_url));
            }
        });
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}

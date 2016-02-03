package com.example.david.flow;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.david.flow.CustomViews.GifView;

public class Home extends AppCompatActivity {

    ImageButton shootButton, launchFlowButton, twitterButton, fbButton;
    GifView gifView;
    VideoView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        shootButton = (ImageButton) findViewById(R.id.shootButton);
        launchFlowButton = (ImageButton) findViewById(R.id.launchFlowButton);
        twitterButton = (ImageButton) findViewById(R.id.twitterButton);
        fbButton = (ImageButton) findViewById(R.id.fbButton);
        background = (VideoView) findViewById(R.id.background_video);


        background.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mosaique));
        background.start();
        background.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                background.start();
            }
        });

        shootButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Shoot.class);
                startActivity(intent);
                background.stopPlayback();
            }
        });


        launchFlowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PlayFlow.class);
                startActivity(intent);
                background.stopPlayback();
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToUrl(getResources().getString(R.string.twitter_app_url));
                background.stopPlayback();
            }
        });

        fbButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToUrl(getResources().getString(R.string.fb_app_url));
                background.stopPlayback();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        background.start();
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }



}

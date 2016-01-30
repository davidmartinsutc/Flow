package com.example.david.flow;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.david.flow.Services.FlowManager;
import com.example.david.flow.Services.ObjectVideo;
import com.example.david.flow.Services.ServerSimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by David on 24/01/2016.
 */
public class PlayFlow extends Activity {

    //VideoView mVideoView;
    MediaPlayer mp;
    private ImageButton buttonLike, buttonReport;
    private SurfaceView mVideoPlayerView;
    private SurfaceHolder holder;
    private MediaPlayerView mPlayerView;
    private RelativeLayout playFlowLayout;
    private UUID currentUUID;
    private boolean currentliked;
    private boolean currentrepported;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_playflow2);

        playFlowLayout = (RelativeLayout)findViewById(R.id.layout);
        mPlayerView = new MediaPlayerView(this);
        mp = mPlayerView.getMp();
        playFlowLayout.addView(mPlayerView);




        buttonLike = (ImageButton) findViewById(R.id.button_like);
        buttonLike.bringToFront();
        buttonReport = (ImageButton) findViewById(R.id.button_report);
        buttonReport.bringToFront();

        goVideo();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                // restart on completion
                mp.reset();
                goVideo();
            }
        });


        buttonLike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                likeVideo(currentUUID);
            }
        });

        mPlayerView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.reset();
                goVideo();
            }
        });

        buttonReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reportVideo(currentUUID);
            }
        });

    }

    protected void onPause(){
        super.onPause();
        mp.release();
    }


    /*private void play() {

        try {
            mp.setDataSource(PlayFlow.this, Uri.parse("android.resource://com.example.david.flow/raw/testvid"));

            mp.prepare();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



    private void setVideo(ObjectVideo video) {
        currentUUID=video.getIdVideo();
        currentliked=false;
        currentrepported=false;

        try {
            mp.setDataSource(video.getMyVideo().getFD());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goVideo() {
        FlowManager flowmanager = FlowManager.getInstance();
        flowmanager.fillVideoListFlow();

        //setVideo(nextVideo());

        setVideo(flowmanager.getVideoFlow());
        mp.start();
    }

    private void likeVideo(UUID idVideo) {

        if (currentliked==false) {
            try {
                FlowManager flowmanager = FlowManager.getInstance();
                flowmanager.likeVideo(idVideo);
                Toast.makeText(PlayFlow.this, "Liked!", Toast.LENGTH_LONG).show();
                currentliked=true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void reportVideo(UUID idVideo) {
        if (currentrepported==false) {
            try {
                FlowManager flowmanager = FlowManager.getInstance();
                flowmanager.reportVideo(idVideo);
                Toast.makeText(PlayFlow.this, "Reported! Thanks for helping !", Toast.LENGTH_LONG).show();
                currentrepported=true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

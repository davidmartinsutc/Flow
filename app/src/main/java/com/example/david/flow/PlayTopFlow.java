package com.example.david.flow;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.david.flow.CustomViews.MediaPlayerView;
import com.example.david.flow.Services.FlowManager;
import common.api12.flow.com.ObjectVideo;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by David on 24/01/2016.
 */
public class PlayTopFlow extends Activity {

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
        setContentView(R.layout.activity_playflow);

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
            mp.setDataSource(video.getFilePath());
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
                Toast.makeText(PlayTopFlow.this, "Liked!", Toast.LENGTH_LONG).show();
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
                Toast.makeText(PlayTopFlow.this, "Reported! Thanks for helping !", Toast.LENGTH_LONG).show();
                currentrepported=true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //kill activity on leave :
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}

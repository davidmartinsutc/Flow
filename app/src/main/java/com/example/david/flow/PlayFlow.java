package com.example.david.flow;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.david.flow.Services.FlowManager;

import java.util.UUID;

/**
 * Created by David on 24/01/2016.
 */
public class PlayFlow extends Activity {

    VideoView mVideoView;
    private ImageButton buttonLike, buttonReport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_playflow);


        mVideoView = (VideoView) findViewById(R.id.video_view);

        goVideo();

        buttonLike = (ImageButton) findViewById(R.id.button_like);
        buttonLike.bringToFront();
        buttonReport = (ImageButton) findViewById(R.id.button_report);
        buttonReport.bringToFront();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                goVideo();
            }
        });

        buttonLike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                likeVideo();
            }
        });

    }

    private void setVideo(String video) {
        mVideoView.setVideoURI(Uri.parse(video));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.setMediaController(null);
    }

    private void goVideo() {
        String video = nextVideo();
        setVideo(video);
        mVideoView.start();
    }

    private String nextVideo() {
        String video = "android.resource://" + getPackageName() + "/" + R.raw.testvid;
        return video;
    }

    private void likeVideo() {

        try {
            FlowManager flowmanager = FlowManager.getInstance();
            flowmanager.likeVideo(new UUID(1, 1));
            Toast.makeText(PlayFlow.this, "Liked!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.david.flow;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class ReplayVideo extends Activity {

    private Context myContext;
    private ImageButton buttonCancel, buttonSave, buttonSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replayvid);

        myContext = this;


        final VideoView mVideoView = (VideoView) findViewById(R.id.video_view);
        //mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.small_video));
        mVideoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myvideo4.mp4"));

        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.setMediaController(null);

        mVideoView.start();

        buttonCancel = (ImageButton) findViewById(R.id.buttonCancel);
        buttonCancel.bringToFront();
        buttonSave = (ImageButton) findViewById(R.id.buttonSave);
        buttonSave.bringToFront();
        buttonSend = (ImageButton) findViewById(R.id.buttonSend);
        buttonSend.bringToFront();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                // restart on completion
                mVideoView.start();
            }
        });

        //initialize();
    }


}
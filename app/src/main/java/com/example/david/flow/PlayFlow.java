package com.example.david.flow;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by David on 24/01/2016.
 */
public class PlayFlow extends Activity {

    VideoView mVideoView;
    private ImageButton buttonLike, buttonReport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playflow);


        mVideoView = (VideoView) findViewById(R.id.video_view);

        goVideo();

        buttonLike = (ImageButton) findViewById(R.id.buttonSend);
        buttonLike.bringToFront();
        buttonReport = (ImageButton) findViewById(R.id.buttonSend);
        buttonReport.bringToFront();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                goVideo();
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
        String video = "android.resource://" + getPackageName() + "/" + R.raw.small_video;
        return video;
    }
}

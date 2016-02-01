package com.example.david.flow;


import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.david.flow.Services.FlowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReplayVideo extends Activity {

    private ImageButton buttonCancel, buttonSave, buttonSend;
    private VideoView mVideoView;
    private String videoURI;
    private File videoFile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replayvid);

        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))
            videoFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/tmpvideo.mp4");
        else
            videoFile = new File(getFilesDir().getAbsolutePath() + "/tmpvideo.mp4");


        mVideoView = (VideoView) findViewById(R.id.video_view);

        mVideoView.setVideoURI(Uri.parse(videoFile.getAbsolutePath()));

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

        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveVideo();
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendVideo();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
                //Shoot.finish();
            }
        });
    }


    public void saveVideo() {

        //Define video name when saved
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String videoname= "Flow_"+ timeStamp;
        File output=null;

        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            //output = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/" + videoname);
            output = new File(getFilesDir().getAbsolutePath() + "/" + videoname);
            System.out.println("saved as1 : " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/" + videoname);
            System.out.println("saved as2 : " + getFilesDir().getAbsolutePath() + "/" + videoname);
        }
        else {
            output = new File(getFilesDir().getAbsolutePath() + "/" + videoname);
            System.out.println("saved as2 : " + getFilesDir().getAbsolutePath() + "/" + videoname);
        }



        try {
            copyFileUsingFileChannels(videoFile, output);
            //copyFileUsingFileStreams(videoFile, output);
            Toast.makeText(ReplayVideo.this, "Video saved!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ReplayVideo.this, "Failed to save!", Toast.LENGTH_LONG).show();
        }
    }

    private static void copyFileUsingFileChannels(File source, File dest)
            throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }


    /*private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }*/

    private void sendVideo() {
       FlowManager flowmanager = FlowManager.getInstance();
       flowmanager.sendVideo(videoFile);
       Toast.makeText(ReplayVideo.this, "Thank you ;)", Toast.LENGTH_LONG).show();

    }

}
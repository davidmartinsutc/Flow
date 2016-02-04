package com.example.david.flow;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.david.flow.CustomViews.MediaPlayerView;
import com.example.david.flow.Services.FlowManager;
import common.api12.flow.com.ObjectVideo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
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
    private boolean changeVideo=true, needTransition=false;
    private OrientationEventListener orientationListener;
    private boolean inTransition = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_playflow);

        playFlowLayout = (RelativeLayout)findViewById(R.id.layout);
        mPlayerView = new MediaPlayerView(this);
        mp = mPlayerView.getMp();
        playFlowLayout.addView(mPlayerView);


        //lancement du singleton dans un thread à part pour que le OnCreate finisse et que l'activité démarre :
        new Thread(new Runnable() {
            public void run() {
                FlowManager flowmanager = FlowManager.getInstance();
            }
        }).start();


        buttonLike = (ImageButton) findViewById(R.id.button_like);
        buttonLike.bringToFront();
        buttonReport = (ImageButton) findViewById(R.id.button_report);
        buttonReport.bringToFront();

        transition();
        //en gros on balance que des transitions tant qu'on a pas le serveur
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                // restart on completion
                if(FlowManager.exist()){
                    if(FlowManager.getInstance().getServerStatus()!=null){
                    if(FlowManager.getInstance().getServerStatus()) {
                        if (needTransition) {
                            changeVideo = false;
                            needTransition = false;
                            transition();
                        } else {
                            changeVideo = true;
                            needTransition = true;
                            goVideo();
                        }
                    }
                    else{
                        Toast.makeText(PlayFlow.this,"Server off",Toast.LENGTH_SHORT).show();
                        finish();
                    }}else{transition();}
                }
                else{
                    transition();
                }
            }
        });


        buttonLike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                likeVideo(currentUUID);
                buttonLike.setVisibility(View.INVISIBLE);
            }
        });

        mPlayerView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (changeVideo) {
                    changeVideo=false;
                    needTransition=false;
                    transition();
                }
            }
        });

        buttonReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reportVideo(currentUUID);
            }
        });

//        orientationListener = new OrientationEventListener(this,
//                SensorManager.SENSOR_DELAY_NORMAL) {
//
//            @Override
//            public void onOrientationChanged(int orientation) {
//                switch(orientation){
//                    case 0:
//                        buttonLike.setImageDrawable(getRotatedImage(R.drawable.like, 0));
//                        buttonReport.setImageDrawable(getRotatedImage(R.drawable.alert, 0));
//                        break;
//                    case 90:
//                        buttonLike.setImageDrawable(getRotatedImage(R.drawable.like, 90));
//                        buttonReport.setImageDrawable(getRotatedImage(R.drawable.alert, 90));
//                        break;
//                    case 180:
//                        buttonLike.setImageDrawable(getRotatedImage(R.drawable.like, 180));
//                        buttonReport.setImageDrawable(getRotatedImage(R.drawable.alert, 180));
//                        break;
//                    case 270:
//                        buttonLike.setImageDrawable(getRotatedImage(R.drawable.like, 270));
//                        buttonReport.setImageDrawable(getRotatedImage(R.drawable.alert, 270));
//                        break;
//                }
//            }
//        };

//        SensorManager sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
//        sensorManager.registerListener(new SensorEventListener() {
//            int orientation = -1;
//
//            @Override
//            public void onSensorChanged(SensorEvent event) {
//                if (event.values[1] < 6.5 && event.values[1] > -6.5) {
//                    if (orientation != 1) {
//                        Log.d("Sensor", "Landscape");
//                        buttonLike.setImageDrawable(getRotatedImage(R.drawable.like, 90));
//                        buttonReport.setImageDrawable(getRotatedImage(R.drawable.alert, 90));
//                    }
//                    orientation = 1;
//                } else {
//                    if (orientation != 0) {
//                        Log.d("Sensor", "Portrait");
//                        buttonLike.setImageDrawable(getRotatedImage(R.drawable.like, 0));
//                        buttonReport.setImageDrawable(getRotatedImage(R.drawable.alert, 0));
//                    }
//                    orientation = 0;
//                }
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int accuracy) {
//                // TODO Auto-generated method stub
//
//            }
//        }, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

    }


    protected void onPause(){
        super.onPause();
        mp.release();
    }


    private void setVideo(ObjectVideo video) {
        currentUUID=video.getIdVideo();
        currentliked=false;
        currentrepported=false;

        try{
            Log.d("setVideo","video id:" + video.getIdVideo());
            Log.d("setVideo","file path:" + video.getFilePath());
            mp.setDataSource(video.getFilePath());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        video = null;
        buttonLike.setVisibility(View.VISIBLE);
    }

    private void goVideo() {
            FlowManager flowmanager = FlowManager.getInstance();
            mp.reset();
            flowmanager.fillVideoListFlow();

            //TODO: Callback !
            while(flowmanager.getVideoListFlow().isEmpty()){
                //Toast.makeText(PlayFlow.this, "Fetching new videos", Toast.LENGTH_SHORT).show();
                //transition();
            }
            mp.reset();
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

    private void transition() {

        mp.reset();
        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.transition);

        try {
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());

            mp.prepare();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }


    private Drawable getRotatedImage(int drawableId, int degrees) {
        Bitmap original = BitmapFactory.decodeResource(getResources(), drawableId);
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);

        Bitmap rotated = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true);
        return new BitmapDrawable(rotated);
    }

    //kill activity on leave :
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            mp.release();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
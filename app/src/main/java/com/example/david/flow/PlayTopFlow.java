package com.example.david.flow;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
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
import android.os.Bundle;
import android.util.Log;
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
    private ImageButton buttonReport;
    private SurfaceView mVideoPlayerView;
    private SurfaceHolder holder;
    private MediaPlayerView mPlayerView;
    private RelativeLayout topLayout;
    private UUID currentUUID;
    private boolean currentrepported;
    private boolean changeVideo=true, needTransition=false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_top);

        topLayout = (RelativeLayout)findViewById(R.id.layout);
        mPlayerView = new MediaPlayerView(this);
        mp = mPlayerView.getMp();
        topLayout.addView(mPlayerView);


        //lancement du singleton dans un thread à part pour que le OnCreate finisse et que l'activité démarre :
        new Thread(new Runnable() {
            public void run() {
                FlowManager flowmanager = FlowManager.getInstance();
            }
        }).start();


        buttonReport = (ImageButton) findViewById(R.id.button_report);
        buttonReport.bringToFront();

        transition();
        //en gros on balance que des transitions tant qu'on a pas le serveur
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                // restart on completion
                if (FlowManager.exist()) {
                    if (FlowManager.getInstance().getServerStatus()) {
                        if (needTransition) {
                            changeVideo = false;
                            needTransition = false;
                            transition();
                        } else {
                            changeVideo = true;
                            needTransition = true;
                            goVideo();
                        }
                    } else {
                        Toast.makeText(PlayTopFlow.this, "Server off", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    transition();
                }
            }
        });

        mPlayerView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (changeVideo) {
                    changeVideo = false;
                    needTransition = false;
                    transition();
                }
            }
        });

        buttonReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reportVideo(currentUUID);
            }
        });


        SensorManager sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(new SensorEventListener() {
            int orientation = -1;

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1] < 6.5 && event.values[1] > -6.5) {
                    if (orientation != 1) {
                        Log.d("Sensor", "Landscape");
                        buttonReport.setImageDrawable(getRotatedImage(R.drawable.alert, 90));
                    }
                    orientation = 1;
                } else {
                    if (orientation != 0) {
                        Log.d("Sensor", "Portrait");
                        buttonReport.setImageDrawable(getRotatedImage(R.drawable.alert, 0));
                    }
                    orientation = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub

            }
        }, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);

    }

    protected void onPause(){
        super.onPause();
        mp.release();
    }


    private void setVideo(ObjectVideo video) {
        currentUUID=video.getIdVideo();
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
    }

    private void goVideo() {
        FlowManager flowmanager = FlowManager.getInstance();
        mp.reset();
        flowmanager.fillVideoListFlow();

        //TODO: Callback !
        while(flowmanager.getVideoListFlow().isEmpty()){
            Toast.makeText(PlayTopFlow.this, "Fetching new videos", Toast.LENGTH_SHORT).show();
            transition();
        }
        mp.reset();
        setVideo(flowmanager.getVideoFlow());
        mp.start();

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
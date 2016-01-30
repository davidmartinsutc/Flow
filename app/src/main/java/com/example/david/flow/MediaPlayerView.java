package com.example.david.flow;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by David on 29/01/2016.
 */
public class MediaPlayerView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private MediaPlayer mp;


    public MediaPlayerView(Context context) {

        super(context);
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mp = new MediaPlayer();

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mp.setDisplay(mHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public SurfaceHolder getmHolder() {
        return mHolder;
    }

    public MediaPlayer getMp() {
        return mp;
    }
}

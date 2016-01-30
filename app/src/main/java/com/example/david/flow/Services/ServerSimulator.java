package com.example.david.flow.Services;

import android.os.Environment;

import com.example.david.flow.R;

import java.io.File;
import java.util.UUID;

/**
 * Created by David on 29/01/2016.
 */
public class ServerSimulator {
    private File video1;
    private File video2;
    private File video3;
    private ObjectVideo Ovideo1;
    private ObjectVideo Ovideo2;
    private ObjectVideo Ovideo3;
    private static int currentvideo;

    private static ServerSimulator INSTANCE = null;

    public static ServerSimulator getInstance() {
        if (INSTANCE== null) {
            INSTANCE = new ServerSimulator();
        }
        return INSTANCE;
    }

    public ServerSimulator() {
        currentvideo=1;

        ////Les vidéos sont a mettre dans le dossier download du telephone, pour la simulation du serveur
        this.video1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/testvid.3gp");
        this.video2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/testvid2.3gp");
        this.video3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/testvid3.3gp");

        Ovideo1= new ObjectVideo(UUID.randomUUID(),this.video1);
        Ovideo2= new ObjectVideo(UUID.randomUUID(),this.video2);
        Ovideo3= new ObjectVideo(UUID.randomUUID(),this.video3);
    }


    public ObjectVideo getNextVideo() {
        ObjectVideo toReturn=null;
        switch (currentvideo) {
            case 1:
                toReturn= this.Ovideo1;
                currentvideo = 2;
                break;
            case 2:
                toReturn= this.Ovideo2;
                currentvideo = 3;
                break;
            case 3:
                toReturn= this.Ovideo3;
                currentvideo = 1;
                break;
        }
        return toReturn;
    }

}

package com.example.david.flow.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

/**
 * Created by David on 06/01/2016.
 */
public class ObjectVideo {
    private UUID idVideo;
    private FileInputStream myVideo;

    public ObjectVideo(UUID idVideo, File video) {
        if (video.exists()) {
            System.out.println("File1 exist ici");
        }
        else {
            System.out.println("File1 exist pas ici");
        }

        this.idVideo = idVideo;
        try {
            this.myVideo = new FileInputStream(video);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileInputStream getMyVideo() {
        return myVideo;
    }

    public void setMyVideo(File myVideo) {
        try {
            this.myVideo = new FileInputStream(myVideo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UUID getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(UUID idVideo) {
        this.idVideo = idVideo;
    }
}

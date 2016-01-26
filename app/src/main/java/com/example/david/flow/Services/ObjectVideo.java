package com.example.david.flow.Services;

import java.io.File;
import java.util.UUID;

/**
 * Created by David on 26/01/2016.
 */
public class ObjectVideo {
    private UUID idVideo;
    private File myVideo;


    public File getMyVideo() {
        return myVideo;
    }

    public void setMyVideo(File myVideo) {
        this.myVideo = myVideo;
    }

    public UUID getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(UUID idVideo) {
        this.idVideo = idVideo;
    }
}

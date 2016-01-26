package com.example.david.flow.Services;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by David on 26/01/2016.
 */
public class FlowManager {

    private List <ObjectVideo> videoListFlow;
    private List <ObjectVideo> videoListTop;
    private UUID firstVideoFlow;
    private UUID currentVideoFlow;
    private UUID currentVideoTop;

    private FlowManager() {
    }

    private static FlowManager INSTANCE = null;

    public static FlowManager getInstance() {
        if (INSTANCE== null) {
            INSTANCE = new FlowManager();
        }
        return INSTANCE;
    }


    public void sendVideo(File video){

    }

    public void fillVideoListFlow() {
        //Remplit ta videoListFlow
    }

    public void fillVideoListTop() {
        //Remplit ta videoListTop
    }

    public void likeVideo(UUID idVideo){
        //Ajoute un like sur la video
    }

    public void reportVideo(UUID idVideo){
        //Ajoute un signalement sur la video
    }

    public File getVideoFlow(){

        return null;
    }

    public File getVideoTop(){

        return null;
    }

    public File getVideo(int whichVideo){
        //Renvoie la vidéo en fonction du type de liste
        return null;
    }
}

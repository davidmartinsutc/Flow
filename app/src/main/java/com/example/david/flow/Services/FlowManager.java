package com.example.david.flow.Services;

import java.io.File;
import java.util.ArrayList;
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
        videoListFlow=new ArrayList<ObjectVideo>();
        videoListTop=new ArrayList<ObjectVideo>();
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
        int tofill = 5 - videoListFlow.size();

        System.out.println("Nombre elements à remplir : "+tofill);
        //Remplit la videoListFlow

        for (int i=1 ; i<=tofill;i++) {
            ////////Code a changer avec le vrai serveur
            ServerSimulator fakeserver = ServerSimulator.getInstance();
            videoListFlow.add(fakeserver.getNextVideo());
        }
        System.out.println("Nombre elements dans la liste : "+videoListFlow.size());
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

    public ObjectVideo getVideoFlow(){
        ObjectVideo currentVideo = videoListFlow.get(1);
        videoListFlow.remove(1);
        return currentVideo;
    }

    public File getVideoTop(){

        return null;
    }

    public File getVideo(int whichVideo){
        //Renvoie la vidéo en fonction du type de liste
        return null;
    }
}

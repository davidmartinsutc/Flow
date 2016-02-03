package com.example.david.flow.Services;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.david.flow.Services.Com.FlowClient;

import common.api12.flow.com.ObjectVideo;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import message.common.api12.flow.com.MessageNewVideo;
import message.common.api12.flow.com.MessageNextVideo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
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
    private FlowClient client;
    int nbVideoStockees=5;
    Boolean serverStatus = Boolean.TRUE;


    private FlowManager() {
        videoListFlow=new ArrayList<ObjectVideo>();
        videoListTop=new ArrayList<ObjectVideo>();
        client = new FlowClient(serverStatus);

        //just to test :
        //ServerSimulator fakeserver = ServerSimulator.getInstance();
        //videoListFlow.add(fakeserver.getNextVideo());
//        videoListFlow.add(fakeserver.getNextVideo());
    }

    private static FlowManager INSTANCE = null;

    public static FlowManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FlowManager();
        }
        return INSTANCE;
    }

    public static boolean exist(){
        if(INSTANCE == null){return Boolean.FALSE;}
        return Boolean.TRUE;
    }


    public void sendVideo(File video){
        ObjectVideo tmp = new ObjectVideo(null, video);
        MessageNewVideo msgToSend = new MessageNewVideo(new Date(),tmp.getMyVideo() );
        Log.d("sendVideo","msgToSend:size:"+msgToSend.getVideoContent().length);
        client.sendMessage(msgToSend);
    }

    public void fillVideoListFlow() {
        int tofill = nbVideoStockees - videoListFlow.size();

        //Remplit la videoListFlow

//        for (int i=1 ; i<=tofill;i++) {
        for (int i=1 ; i<=tofill;i++) {
            ////////Code a changer avec le vrai serveur
            //ServerSimulator fakeserver = ServerSimulator.getInstance();
            //videoListFlow.add(fakeserver.getNextVideo());
            //test ume :
            MessageNextVideo msg = new MessageNextVideo(currentVideoFlow,null);
//
            client.sendMessage(msg);
            Log.d("FlowManager:fillVide...", "sendMessage " + msg.toString());

        }
        Log.d("FlowManager:fillVide...","Nombre elements dans la liste : "+videoListFlow.size());
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
        ObjectVideo currentVideo = videoListFlow.get(0);
        currentVideoFlow = currentVideo.getIdVideo();
        videoListFlow.remove(0);
        return currentVideo;
    }

    public File getVideoTop(){

        return null;
    }

    public File getVideo(int whichVideo){
        //Renvoie la vidéo en fonction du type de liste
        return null;
    }


    //***************
    //getter + setter

    public synchronized List<ObjectVideo> getVideoListFlow() {
        return videoListFlow;
    }

    public void setVideoListFlow(List<ObjectVideo> videoListFlow) {
        this.videoListFlow = videoListFlow;
    }

    public List<ObjectVideo> getVideoListTop() {
        return videoListTop;
    }

    public void setVideoListTop(List<ObjectVideo> videoListTop) {
        this.videoListTop = videoListTop;
    }

    public UUID getFirstVideoFlow() {
        return firstVideoFlow;
    }

    public void setFirstVideoFlow(UUID firstVideoFlow) {
        this.firstVideoFlow = firstVideoFlow;
    }

    public UUID getCurrentVideoFlow() {
        return currentVideoFlow;
    }

    public void setCurrentVideoFlow(UUID currentVideoFlow) {
        this.currentVideoFlow = currentVideoFlow;
    }

    public UUID getCurrentVideoTop() {
        return currentVideoTop;
    }

    public void setCurrentVideoTop(UUID currentVideoTop) {
        this.currentVideoTop = currentVideoTop;
    }

    public int getNbVideoStockees() {
        return nbVideoStockees;
    }

    public void setNbVideoStockees(int nbVideoStockees) {
        this.nbVideoStockees = nbVideoStockees;
    }

    public Boolean getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(Boolean serverStatus) {
        this.serverStatus = serverStatus;
    }
}

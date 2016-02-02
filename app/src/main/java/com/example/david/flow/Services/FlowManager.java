package com.example.david.flow.Services;

import android.util.Log;

import com.example.david.flow.Services.Com.FlowClient;
import com.example.david.flow.Services.Com.FlowClientInitializer;
import message.common.api12.flow.com.Message;
import message.common.api12.flow.com.MessageNextVideo;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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


    private FlowManager() {
        videoListFlow=new ArrayList<ObjectVideo>();
        videoListTop=new ArrayList<ObjectVideo>();
//        client = new FlowClient();
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
        int tofill = nbVideoStockees - videoListFlow.size();

        //Remplit la videoListFlow

        for (int i=1 ; i<=2;i++) {
        //for (int i=1 ; i<=tofill;i++) {
            ////////Code a changer avec le vrai serveur
            ServerSimulator fakeserver = ServerSimulator.getInstance();
            videoListFlow.add(fakeserver.getNextVideo());
            //test ume :
//            MessageNextVideo msg = new MessageNextVideo(null,null);
//            client.sendMessage(msg);
//            Log.d("FlowManager:fillVide...", "sendMessage " + msg.toString());

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


    //***************
    //getter + setter

    public List<ObjectVideo> getVideoListFlow() {
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
}

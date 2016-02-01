package com.example.david.flow.Services;

import android.util.Log;

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
    int nbVideoStockees=5;


    private FlowManager() {
        videoListFlow=new ArrayList<ObjectVideo>();
        videoListTop=new ArrayList<ObjectVideo>();
//        try {
//            launchAppCom("192.168.0.17",8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
            //MessageNextVideo msg = new MessageNextVideo(null,null);
            //sendMessage(msg);
            //Log.d("FlowManager:fillVide...", "sendMessage " + msg.toString());

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

    //*********************
    //Communication methods

    private Channel channel;
    private EventLoopGroup group;

    public void launchAppCom(String host, int port) throws InterruptedException {

        group = new NioEventLoopGroup();



        Bootstrap boostrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
                .handler(new FlowClientInitializer());

        try {
            this.channel = boostrap.connect(host, port).sync().channel();
        } catch (InterruptedException e) {
            Log.d("FlowManager:launchApp:","Lost connection, check your network connection");
            throw (e);
        } catch (Throwable e) {
            Log.d("FlowManager:launchApp:","Can't connect to server, please check your connection and server statuts");
//            throw (e);
        }

        Log.d("FlowManager:launchApp:","Message Manager is initialized for : " + host + ":" + port);
    }

    public void sendMessage(Message msg) throws ExceptionInInitializerError {

        if (channel != null) {
            channel.writeAndFlush(msg);
            Log.d("sendMessage", msg.getClass().getSimpleName() + " has been sent to : " + channel.remoteAddress());
        } else
            throw new ExceptionInInitializerError();
    }

    public void close() {
        group.shutdownGracefully();
    }
}

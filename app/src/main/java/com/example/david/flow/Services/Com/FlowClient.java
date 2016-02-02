package com.example.david.flow.Services.Com;

import android.os.Environment;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import message.common.api12.flow.com.Message;

/**
 * Created by ulyss_000 on 02/02/2016.
 */
public class FlowClient {

    //*********************
    //Communication methods

    private Channel channel;
    private EventLoopGroup group;

    public FlowClient() {
        try {
            launchAppCom("192.168.0.17",8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void launchAppCom(String host, int port) throws InterruptedException {

        group = new NioEventLoopGroup();



        Bootstrap boostrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
                .handler(new FlowClientInitializer());

        try {
            this.channel = boostrap.connect(host, port).sync().channel();
        } catch (InterruptedException e) {
            Log.d("FlowManager:launchApp:", "Lost connection, check your network connection");
            throw (e);
        } catch (Throwable e) {
            Log.d("FlowManager:launchApp:","Can't connect to server, please check your connection and server statuts");
//            throw (e);
        }

        Log.d("FlowManager:launchApp:","Message Manager is initialized for : " + host + ":" + port);
    }

    public void sendMessage(Message msg) throws ExceptionInInitializerError {

//        ObjectDecoderInputStream tmp = null;
//        try {
//            tmp = new ObjectDecoderInputStream(new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/testvid3.3gp"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        if (channel != null) {
//            channel.writeAndFlush(tmp);
            channel.writeAndFlush(msg);
            //channel.writeAndFlush(msg);
            Log.d("sendMessage", msg.getClass().getSimpleName() + " has been sent to : " + channel.remoteAddress());
        } else
            throw new ExceptionInInitializerError();
    }

    public void close() {
        group.shutdownGracefully();
    }
}

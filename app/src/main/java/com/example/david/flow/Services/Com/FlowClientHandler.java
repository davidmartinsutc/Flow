package com.example.david.flow.Services.Com;

import android.util.Log;

import com.example.david.flow.Services.FlowManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import adapter.message.common.api12.flow.com.MessageNewVideoAdapter;
import adapter.message.common.api12.flow.com.MessageNextVideoAdapter;
import adapter.message.common.api12.flow.com.MessageNoteAdapter;
import adapter.message.common.api12.flow.com.MessageTopVideoAdapter;
import message.common.api12.flow.com.Message;
import message.common.api12.flow.com.MessageNewVideo;
import message.common.api12.flow.com.MessageNextVideo;
import message.common.api12.flow.com.MessageNote;
import message.common.api12.flow.com.MessageTopVideo;

/**
 * Created by ulyss_000 on 01/02/2016.
 */
public class FlowClientHandler extends SimpleChannelInboundHandler<Message> {

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Message msg) throws Exception {
        Log.d("FlowClientHandler", "receiving from serveur...");
        FlowManager manager = FlowManager.getInstance();

        Message msgReceived = (Message) msg;
        Log.d("FlowClientHandler", "type:" + msg.toString());
        msgReceived.getAdapter().ProceedClient(ctx,manager);
    }
}

package com.example.david.flow.Services.Com;

import java.io.Serializable;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

/**
 * Created by ulyss_000 on 01/02/2016.
 */
public class FlowClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

//        pipeline.addLast("decoder", new ObjectDecoder(1024*1024*100, ClassResolvers.cacheDisabled(null)));
        pipeline.addLast("decoder", new ObjectDecoder(1024 * 1024 * 100, ClassResolvers.softCachingConcurrentResolver(null)));
        pipeline.addLast("encoder", new ObjectEncoder());

//        pipeline.addLast("encode-fileoutputstream", new ObjectEncoderOutputStream());
        pipeline.addLast("handler", new FlowClientHandler());

    }
}

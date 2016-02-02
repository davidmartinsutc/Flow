package message.common.api12.flow.com.Adapter;

import com.example.david.flow.Services.FlowManager;

import io.netty.channel.ChannelHandlerContext;
import message.common.api12.flow.com.MessageNewVideo;

/**
 * Created by ulyss_000 on 02/02/2016.
 */
public class MessageNewVideoAdapter implements MessageAdapter {
    private MessageNewVideo newVideo;

    public MessageNewVideoAdapter(MessageNewVideo newVideo) {
        this.newVideo = newVideo;
    }

    @Override
    public void ProceedClient(ChannelHandlerContext ctx, FlowManager manager) {

    }
}

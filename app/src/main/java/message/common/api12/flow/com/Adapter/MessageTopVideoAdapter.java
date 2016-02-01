package message.common.api12.flow.com.Adapter;

import message.common.api12.flow.com.MessageTopVideo;
import com.example.david.flow.Services.FlowManager;

import io.netty.channel.ChannelHandlerContext;

public class MessageTopVideoAdapter implements MessageAdapter{
    
    public MessageTopVideo topVideo;

    public MessageTopVideoAdapter(MessageTopVideo topVideo) {
        super();
        this.topVideo = topVideo;
    }

    public void ProceedClient(ChannelHandlerContext ctx, FlowManager manager) {
        // TODO Auto-generated method stub
        
    }

}

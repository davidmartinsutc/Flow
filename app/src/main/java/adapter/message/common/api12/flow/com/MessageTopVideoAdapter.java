package adapter.message.common.api12.flow.com;

import message.common.api12.flow.com.MessageTopVideo;
import com.example.david.flow.Services.FlowManager;

import io.netty.channel.ChannelHandlerContext;

public class MessageTopVideoAdapter implements MessageAdapter{
    
    private MessageTopVideo topVideo;

    public MessageTopVideoAdapter(MessageTopVideo topVideo) {
        super();
        this.topVideo = topVideo;
    }

    public void ProceedClient(ChannelHandlerContext ctx, FlowManager manager) {
        // TODO Auto-generated method stub
        
    }

}

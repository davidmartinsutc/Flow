package adapter.message.common.api12.flow.com;

import com.example.david.flow.Services.FlowManager;

import io.netty.channel.ChannelHandlerContext;

public abstract interface MessageAdapter {
    
    //will be used by the serveur to proceed client request :
    public void ProceedClient(ChannelHandlerContext ctx, FlowManager manager);

}

package message.common.api12.flow.com.Adapter;
import message.common.api12.flow.com.MessageNote;
import com.example.david.flow.Services.FlowManager;

import io.netty.channel.ChannelHandlerContext;

public class MessageNoteAdapter implements MessageAdapter{
    MessageNote note;

    public MessageNoteAdapter(MessageNote note) {
        super();
        this.note = note;
    }

    public void ProceedClient(ChannelHandlerContext ctx, FlowManager manager) {
        // TODO Auto-generated method stub
        
    }

}

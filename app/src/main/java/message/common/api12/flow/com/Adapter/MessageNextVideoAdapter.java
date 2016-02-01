package message.common.api12.flow.com.Adapter;

import android.util.Log;

import message.common.api12.flow.com.MessageNextVideo;
import com.example.david.flow.Services.FlowManager;

import io.netty.channel.ChannelHandlerContext;


public class MessageNextVideoAdapter implements MessageAdapter {
    MessageNextVideo nextVideo;

    public MessageNextVideoAdapter(MessageNextVideo nextVideo) {
        super();
        this.nextVideo = nextVideo;
    }

    public void ProceedClient(ChannelHandlerContext ctx, FlowManager manager) {
        // TODO Auto-generated method stub
//      FlowServer.log.debug("receiving...");
//
//      ObjectVideo test = FlowDao.getRandomVideo(24);
//
//      nextVideo.setCurrentVideo(test.getIdVideo());
        Log.d("MessageNextVideoAd...", "Id received video : " + nextVideo.getCurrentVideo());


    }

}

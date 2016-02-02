package adapter.message.common.api12.flow.com;

import android.os.Environment;
import android.util.Log;

import common.api12.flow.com.ObjectVideo;
import message.common.api12.flow.com.MessageNextVideo;
import com.example.david.flow.Services.FlowManager;

import io.netty.channel.ChannelHandlerContext;


public class MessageNextVideoAdapter implements MessageAdapter {
    private MessageNextVideo nextVideo;

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
        Log.d("MessageNextVideoAd...", "Size received video : " + nextVideo.getVideoContent().length);


        //create new ObjectVideo
        ObjectVideo tmp = new ObjectVideo(nextVideo.getCurrentVideo(), nextVideo.getVideoContent());
        //set android tmp folder file Path + create the file

        tmp.writeFileToPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + nextVideo.getCurrentVideo() + ".3gp");

        Log.d("MessageNextVideoAd...", "temp filePath : " + tmp.getFilePath());
        //TODO Callback
        while(tmp.getFilePath()==null){
            try {
                Log.d("goVideo:", "videoListFlow est encore vide !");
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        manager.getVideoListFlow().add(tmp);


        Log.d("MessageNextVideoAd...", "video added to the flow list");


    }

}

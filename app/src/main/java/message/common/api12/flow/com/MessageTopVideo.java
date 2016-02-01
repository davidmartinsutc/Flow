package message.common.api12.flow.com;


import java.io.FileInputStream;
import java.util.UUID;


public class MessageTopVideo extends Message {
    UUID currentVideo;
    FileInputStream videoContent;

    public MessageTopVideo(UUID currentVideo) {
        super();
        this.currentVideo = currentVideo;
    }

    /**
     * @return the currentVideo
     */
    public UUID getCurrentVideo() {
        return currentVideo;
    }

    /**
     * @param currentVideo the currentVideo to set
     */
    public void setCurrentVideo(UUID currentVideo) {
        this.currentVideo = currentVideo;
    }


}

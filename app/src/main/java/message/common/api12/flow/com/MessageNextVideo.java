package message.common.api12.flow.com;

import java.io.FileInputStream;
import java.util.UUID;

public class MessageNextVideo extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 4793337315566988416L;
    UUID currentVideo;
    UUID firstVideo;
    FileInputStream videoContent;

    public MessageNextVideo(UUID currentVideo, UUID firstVideo) {
        super();
        this.currentVideo = currentVideo;
        this.firstVideo = firstVideo;
    }

    /**
     * @return the currentVideo
     */
    public UUID getCurrentVideo() {
        return currentVideo;
    }

    /**
     * @param currentVideo
     *            the currentVideo to set
     */
    public void setCurrentVideo(UUID currentVideo) {
        this.currentVideo = currentVideo;
    }

    /**
     * @return the firstVideo
     */
    public UUID getFirstVideo() {
        return firstVideo;
    }

    /**
     * @param firstVideo
     *            the firstVideo to set
     */
    public void setFirstVideo(UUID firstVideo) {
        this.firstVideo = firstVideo;
    }

    /**
     * @return the videoContent
     */
    public FileInputStream getVideoContent() {
        return videoContent;
    }

    /**
     * @param videoContent
     *            the videoContent to set
     */
    public void setVideoContent(FileInputStream videoContent) {
        this.videoContent = videoContent;
    }

}

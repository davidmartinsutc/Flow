package message.common.api12.flow.com;

import java.util.UUID;

import adapter.message.common.api12.flow.com.MessageNextVideoAdapter;

public class MessageNextVideo extends Message {
    /**
     *
     */
    private static final long serialVersionUID = 4793337315566988416L;
    private UUID currentVideo;
    private UUID firstVideo;
    private byte[] videoContent;

    public MessageNextVideo(UUID currentVideo, UUID firstVideo) {
        super();
        this.currentVideo = currentVideo;
        this.firstVideo = firstVideo;
    }

    @Override
    public MessageNextVideoAdapter getAdapter() {
        return new MessageNextVideoAdapter(this);
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
    public byte[] getVideoContent() {
        return videoContent;
    }

    /**
     * @param videoContent
     *            the videoContent to set
     */
    public void setVideoContent(byte[] videoContent) {
        this.videoContent = videoContent;
    }

}

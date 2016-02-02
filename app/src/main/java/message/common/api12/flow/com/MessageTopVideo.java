package message.common.api12.flow.com;

import java.util.UUID;

import adapter.message.common.api12.flow.com.MessageTopVideoAdapter;

public class MessageTopVideo extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = 4518401289273646520L;
    private UUID currentVideo;
    private byte[] videoContent;

    public MessageTopVideo(UUID currentVideo) {
        super();
        this.currentVideo = currentVideo;
    }

    @Override
    public MessageTopVideoAdapter getAdapter() {
        return new MessageTopVideoAdapter(this);
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

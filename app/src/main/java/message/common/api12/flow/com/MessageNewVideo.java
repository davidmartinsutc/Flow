package message.common.api12.flow.com;

import java.util.Date;

import adapter.message.common.api12.flow.com.MessageNewVideoAdapter;

public class MessageNewVideo extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -6254183161771687242L;
    private Date creationDate;
    private byte[] videoContent;

    public MessageNewVideo(Date creationDate, byte[] videoContent) {
        super();
        this.creationDate = creationDate;
        this.videoContent = videoContent;
    }

    @Override
    public MessageNewVideoAdapter getAdapter() {
        return new MessageNewVideoAdapter(this);
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

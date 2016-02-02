package message.common.api12.flow.com;

import java.io.FileInputStream;
import java.util.Date;

public class MessageNewVideo extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -6254183161771687242L;
    private Date creationDate;
    private FileInputStream videoContent;

    public MessageNewVideo(Date creationDate, FileInputStream videoContent) {
        super();
        this.creationDate = creationDate;
        this.videoContent = videoContent;
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

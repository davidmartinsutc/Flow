package message.common.api12.flow.com;

import java.util.UUID;

public class MessageNote extends Message {
    UUID idVideo;
    int note;// if -1 -> signal if 1 -> note+1

    public MessageNote(UUID idVideo, int note) {
        super();
        this.idVideo = idVideo;
        this.note = note;
    }

    /**
     * @return the idVideo
     */
    public UUID getIdVideo() {
        return idVideo;
    }

    /**
     * @param idVideo the idVideo to set
     */
    public void setIdVideo(UUID idVideo) {
        this.idVideo = idVideo;
    }

    /**
     * @return the note
     */
    public int getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(int note) {
        this.note = note;
    }

}

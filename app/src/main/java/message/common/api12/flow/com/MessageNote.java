package message.common.api12.flow.com;

import java.util.UUID;

import adapter.message.common.api12.flow.com.MessageNoteAdapter;

public class MessageNote extends Message {
    /**
     * 
     */
    private static final long serialVersionUID = -4852638559401057469L;
    private UUID idVideo;
    private int note;// if -1 -> signal if 1 -> note+1

    public MessageNote(UUID idVideo, int note) {
        super();
        this.idVideo = idVideo;
        this.note = note;
    }

    @Override
    public MessageNoteAdapter getAdapter(){
        return new MessageNoteAdapter(this);
    }

    /**
     * @return the idVideo
     */
    public UUID getIdVideo() {
        return idVideo;
    }

    /**
     * @param idVideo
     *            the idVideo to set
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
     * @param note
     *            the note to set
     */
    public void setNote(int note) {
        this.note = note;
    }

}

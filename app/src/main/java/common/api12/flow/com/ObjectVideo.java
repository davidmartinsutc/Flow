package common.api12.flow.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.UUID;

/**
 * Created by David on 06/01/2016.
 */
public class ObjectVideo {
    private UUID idVideo;
    private byte[] myVideo;
    private String filePath;



    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void writeFileToPath(String filePath) {
        try {
            File fileDest = new File(filePath);
            FileWriter writer = new FileWriter(fileDest);
            FileOutputStream tmp = new FileOutputStream(fileDest);
            tmp.write(myVideo);
            tmp.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        finally {
            this.filePath = filePath;
        }
    }

    public ObjectVideo(UUID idVideo, File video) {
        this.idVideo = idVideo;
        this.setMyVideo(video);
    }

    public ObjectVideo(UUID idVideo, byte[] myVideo) {
        super();
        this.idVideo = idVideo;
        this.myVideo = myVideo;
    }


    public byte[] getMyVideo() {
        return myVideo;
    }

    public void setMyVideo(File myVideoFile) {
        try {
            FileInputStream tmp = new FileInputStream(myVideoFile);
            myVideo = new byte[(int) myVideoFile.length()];
            tmp.read(myVideo);
            tmp.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void setMyVideo(byte[] myVideo) {
        this.myVideo = myVideo;

    }

    public UUID getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(UUID idVideo) {
        this.idVideo = idVideo;
    }
}

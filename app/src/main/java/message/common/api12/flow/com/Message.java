package message.common.api12.flow.com;

import java.io.Serializable;


public abstract class Message implements Serializable {
    //This is used to get the correct adapter that will contain the proceed used by netty
    @Override
    public String toString() {
        return this.getClass().getName();
    }
}

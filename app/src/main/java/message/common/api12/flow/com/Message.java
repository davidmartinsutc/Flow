package message.common.api12.flow.com;

import java.io.Serializable;

import adapter.message.common.api12.flow.com.MessageAdapter;

public abstract class Message implements Serializable {
     /**
     * 
     */
    private static final long serialVersionUID = 4964410512962719680L;

    // This is used to get the correct adapter that will contain the proceed
    // used by netty
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getClass().getName();
    }
    
    public abstract MessageAdapter getAdapter();
}

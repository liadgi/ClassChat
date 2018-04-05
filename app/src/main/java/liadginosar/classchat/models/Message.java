package liadginosar.classchat.models;

import java.util.Date;

/**
 * Created by liadginosar on 05/04/2018.
 */

public class Message {
    private String sender;
    private String text;
    private Date time;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

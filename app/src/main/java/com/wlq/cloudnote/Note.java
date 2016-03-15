package com.wlq.cloudnote;

import java.util.Date;
import java.util.UUID;

/**
 * Created by aristark on 3/3/16.
 */
public class Note {
    private UUID uuid;
    private String title;
    private String content;
    private Date date;
    private String tag;

    public Note(){
        uuid = UUID.randomUUID();
        date = new Date();
    }

    public Note(UUID id){
        this.uuid = id;

    }

    public UUID getUuid() {
        return uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}

package com.example.saroshmadara.chatterpatter.models;

import android.app.AlarmManager;
import android.media.Image;

import java.util.Date;

/**
 * Created by Sarosh Madara on 29-09-2015.
 */
public class Todo {
    private String title;
//    private Date deadline;
//    private AlarmManager.AlarmClockInfo deadlineAlarmClockInfo;
//    private boolean isDone;
    private String tag;
    private String desc;
//    private String privacy_id;

//    public Date getMakeDate() {
//        return makeDate;
//    }
//
//    public void setMakeDate(Date makeDate) {
//        this.makeDate = makeDate;
//    }

    private Date makeDate;

//    public boolean isDone() {
//        return isDone;
//    }

//    public Date getDeadline() {
//
//        return deadline;
//    }
//
//    public void setDeadline(Date deadline) {
//        this.deadline = deadline;
//    }
//
//    public String getPrivacy_id() {
//        return privacy_id;
//    }
//
//    public void setPrivacy_id(String privacy_id) {
//        this.privacy_id = privacy_id;
//    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.example.cmpsc487wproject1;

import java.sql.Date;
import java.sql.Time;

public class userrecord {
    int id;
    Date date;
    Time time;

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public userrecord(int id, Date date, Time time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }
}

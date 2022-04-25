package com.example.fitlife;

public class Activities {

    String date,name,timer,userid;
    long timestamp_milli;

    public Activities(String date, String name, String timer, String userid, long timestamp_milli) {
        this.date = date;
        this.name = name;
        this.timer = timer;
        this.userid = userid;
        this.timestamp_milli = timestamp_milli;
    }

    //hello

    public Activities() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getTimestamp_milli() {
        return timestamp_milli;
    }

    public void setTimestamp_milli(long timestamp_milli) {
        this.timestamp_milli = timestamp_milli;
    }
}

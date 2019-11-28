package com.example.smartclock.pojo;

import java.util.Date;

public class RecordDate {
    private String Date;
    private int hour;
    private int minute;
    private int days;
    public RecordDate(String Date, int hour, int minute, int days){
        this.Date = Date;
        this.hour = hour;
        this.minute=minute;
        this.days = days;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getHour() { return hour; }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

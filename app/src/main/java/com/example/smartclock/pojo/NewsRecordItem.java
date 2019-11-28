package com.example.smartclock.pojo;

import java.util.Date;

public class NewsRecordItem {
    private Date date;
    private int hour;
    private int minute;
    private int percent;
    private int days;

    public NewsRecordItem(Date date, int hour, int minute, int percent, int days) {
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.percent = percent;
        this.days = days;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

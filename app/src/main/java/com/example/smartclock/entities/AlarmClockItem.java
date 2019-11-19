package com.example.smartclock.entities;

public class AlarmClockItem {
    private int hour;
    private int minute;
    private boolean enable;
    private boolean autoRepeat;
    private boolean shakeWhileRinging;
    private String[] repeatDay;
    private String song;
    private String description;

    public AlarmClockItem(int hour, int minute, boolean enable, boolean autoRepeat,
                          boolean shakeWhileRinging, String[] repeatDay, String song, String description) {
        this.hour = hour;
        this.minute = minute;
        this.enable = enable;
        this.autoRepeat = autoRepeat;
        this.shakeWhileRinging = shakeWhileRinging;
        this.repeatDay = repeatDay;
        this.song = song;
        this.description = description;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isAutoRepeat() {
        return autoRepeat;
    }

    public void setAutoRepeat(boolean autoRepeat) {
        this.autoRepeat = autoRepeat;
    }

    public boolean isShakeWhileRinging() {
        return shakeWhileRinging;
    }

    public void setShakeWhileRinging(boolean shakeWhileRinging) {
        this.shakeWhileRinging = shakeWhileRinging;
    }

    public String[] getRepeatDay() {
        return repeatDay;
    }

    public void setRepeatDay(String[] repeatDay) {
        this.repeatDay = repeatDay;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

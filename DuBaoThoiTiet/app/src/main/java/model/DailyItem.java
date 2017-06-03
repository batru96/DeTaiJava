package model;

/**
 * Created by hoangkhoa on 6/3/17.
 */

public class DailyItem {
    private String dayOfWeek;
    private long time;
    private int imageId;
    private int humidity;
    private float speed;
    private int minDeg;
    private int maxDeg;
    private String detail;

    public DailyItem(String dayOfWeek, long time, int imageId, int humidity, float speed, int minDeg, int maxDeg, String detail) {
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.imageId = imageId;
        this.humidity = humidity;
        this.speed = speed;
        this.minDeg = minDeg;
        this.maxDeg = maxDeg;
        this.detail = detail;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getMinDeg() {
        return minDeg;
    }

    public void setMinDeg(int minDeg) {
        this.minDeg = minDeg;
    }

    public int getMaxDeg() {
        return maxDeg;
    }

    public void setMaxDeg(int maxDeg) {
        this.maxDeg = maxDeg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

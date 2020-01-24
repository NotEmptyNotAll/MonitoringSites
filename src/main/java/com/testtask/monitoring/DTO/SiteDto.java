package com.testtask.monitoring.DTO;

public class SiteDto {

    private Long id;

    private String url;

    private boolean monitoringActive;

    private String substringInResponse;

    private int hours;

    private int minutes;

    private int seconds;

    private int min;

    private int max;

    public SiteDto() {
    }

    public SiteDto(Long id, String url, boolean monitoringActive,
                   String substringInResponse,
                   int seconds, int min, int max) {
        this.id = id;
        this.url = url;
        this.monitoringActive = monitoringActive;
        this.substringInResponse = substringInResponse;
        this.seconds = seconds;
        this.min = min;
        this.max = max;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMonitoringActive() {
        return monitoringActive;
    }

    public void setMonitoringActive(boolean monitoringActive) {
        this.monitoringActive = monitoringActive;
    }

    public String getSubstringInResponse() {
        return substringInResponse;
    }

    public void setSubstringInResponse(String substringInResponse) {
        this.substringInResponse = substringInResponse;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "SiteDto{" +
                "url='" + url + '\'' +
                ", monitoringActive=" + monitoringActive +
                ", substringInResponse='" + substringInResponse + '\'' +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}

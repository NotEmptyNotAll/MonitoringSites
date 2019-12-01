package com.testtask.monitoring.Entity;

import javax.persistence.*;

@Entity
@Table(name = "SiteInfo")
public class SiteInfo implements  Comparable<SiteInfo> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private boolean monitoringActive;

    private String substringInResponse;

    private  String status;

    private int seconds;

    private int min;

    private int max;

    public SiteInfo(String url, boolean monitoringActive,
                    String substringInResponse,String status,
                    int seconds, int min, int max) {
        this.status =status;
        this.url = url;
        this.monitoringActive = monitoringActive;
        this.substringInResponse = substringInResponse;
        this.seconds = seconds;
        this.min = min;
        this.max = max;
    }

    public SiteInfo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public int compareTo(SiteInfo o) {
        return (int) (id-o.id);
    }

    @Override
    public String toString() {
        return "SiteInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", monitoringActive=" + monitoringActive +
                ", substringInResponse='" + substringInResponse + '\'' +
                ", status='" + status + '\'' +
                ", seconds=" + seconds +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}

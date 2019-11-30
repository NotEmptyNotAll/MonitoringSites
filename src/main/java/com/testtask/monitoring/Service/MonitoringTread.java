package com.testtask.monitoring.Service;


import com.testtask.monitoring.model.SiteModel;

import java.io.IOException;

public class MonitoringTread implements Runnable {

    private HTTPValidation validation;

    private SiteService siteService;

    private Thread monitoringThread;

    private int mSeconds;

    private SiteModel siteModel;

    private boolean isActive;

    public MonitoringTread(SiteService siteService, SiteModel siteModel) {
        this.siteModel = siteModel;
        this.siteService = siteService;
        this.mSeconds = (siteModel.getSeconds() + siteModel.getMinutes() * 60 + siteModel.getHours() * 60 * 60) * 1000;
        this.isActive = true;
    }

    public void disable() {
        isActive = false;
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                Thread.sleep(mSeconds);
                validation = new HTTPStatService(siteModel);
                validation.checkTimeResponse();
                validation.checkSize();
                validation.checkSubstingInHeader();
                validation.chcekStatus();
                siteService.updateSiteInfo(validation.getStatus(), siteModel.getId());
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        System.out.println("Thread started");
        if (monitoringThread == null) {
            monitoringThread = new Thread(this, "Tread-" + siteModel.getId());
            monitoringThread.start();
        }
    }
}

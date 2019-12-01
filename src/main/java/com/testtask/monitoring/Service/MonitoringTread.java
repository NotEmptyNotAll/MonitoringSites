package com.testtask.monitoring.Service;


import com.testtask.monitoring.Controller.MainController;
import com.testtask.monitoring.DTO.SiteDto;

import java.io.IOException;

public class MonitoringTread implements Runnable {

    private HTTPValidation validation;

    private SiteService siteService;

    private Thread monitoringThread;

    private int mSeconds;

    private SiteDto siteDto;

    private boolean isActive;

    public MonitoringTread(SiteService siteService, SiteDto siteDto) {
        this.siteDto = siteDto;
        this.siteService = siteService;
        this.mSeconds = (siteDto.getSeconds() + siteDto.getMinutes() * 60 + siteDto.getHours() * 60 * 60) * 1000;
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
                validation = new HTTPStatService(siteDto);
                validation.checkTimeResponse();
                validation.checkSize();
                validation.checkSubstingInHeader();
                validation.chcekStatus();
                MainController mainController = new MainController();
                mainController.update();
                siteService.updateSiteInfo(validation.getStatus(), siteDto.getId());
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        System.out.println("Thread started");
        if (monitoringThread == null) {
            monitoringThread = new Thread(this, "Tread-" + siteDto.getId());
            monitoringThread.start();
        }
    }
}

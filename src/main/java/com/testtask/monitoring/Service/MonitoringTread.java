package com.testtask.monitoring.Service;


import com.testtask.monitoring.model.SiteModel;

public class MonitoringTread implements Runnable {

    private  HTTPStatService httpStatService;

    private SiteService siteService;

    private Thread monitoringThread;

    private int mSeconds;

    private SiteModel siteModel;

    private boolean isActive;

    public MonitoringTread(SiteService siteService, SiteModel siteModel) {
        this.siteModel = siteModel;
        this.siteService = siteService;
        this.mSeconds = (siteModel.getSeconds()+siteModel.getMinutes()*60+siteModel.getHours()*60*60 )*1000;
        this.isActive=true;

    }

    public void disable(){
        isActive=false;
    }


    @Override
    public void run() {
        while (isActive) {
            try {
                Thread.sleep(mSeconds);
                httpStatService=new HTTPStatService(siteModel);
                siteService.updateSiteInfo(httpStatService.getStatus(), siteModel.getId());
            } catch (InterruptedException e) {
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

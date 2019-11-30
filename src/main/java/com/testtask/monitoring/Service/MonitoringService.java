package com.testtask.monitoring.Service;

import com.testtask.monitoring.model.SiteModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MonitoringService {

    private Map<Long, MonitoringTread> treadMap = new HashMap<>();

    public void launchNewMonitoring(SiteService siteService, SiteModel siteModel) {
        MonitoringTread monitoringTread = new MonitoringTread(siteService, siteModel);
        monitoringTread.start();
        treadMap.put(siteModel.getId(), monitoringTread);
    }

    public void stopMonitoring(Long id) {
        try {
            treadMap.get(id).disable();
            treadMap.remove(id);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}

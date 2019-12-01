package com.testtask.monitoring.Service;

import com.testtask.monitoring.DTO.SiteDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MonitoringService {

    private Map<Long, MonitoringTread> treadMap = new HashMap<>();

    public void launchNewMonitoring(SiteService siteService, SiteDto siteDto) {
        MonitoringTread monitoringTread = new MonitoringTread(siteService, siteDto);
        monitoringTread.start();
        treadMap.put(siteDto.getId(), monitoringTread);
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

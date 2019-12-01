package com.testtask.monitoring;

import com.testtask.monitoring.Service.MonitoringService;
import com.testtask.monitoring.Service.SiteService;
import com.testtask.monitoring.DTO.SiteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MonitoringServiceTest {

    @Autowired
    private SiteService siteService;

    @Autowired
    private MonitoringService monitoringService;

    @Test
    void launchNewMonitoringTest(){
        SiteDto site1 =new SiteDto(1L,"testurl1",true,
                "testsubstring1",
                3,0,10000);
        SiteDto site2 =new SiteDto(2L,"testurl2",true,
                "testsubstring2",
                3,0,10000);
        SiteDto site3 =new SiteDto(3L,"testurl3",true,
                "testsubstring3",
                3,0,10000);
        SiteDto site4 =new SiteDto(4L,"testurl4",true,
                "testsubstring4",
                3,0,10000);
        SiteDto site5 =new SiteDto(5L,"testurl5",true,
                "testsubstring5",
                3,0,10000);
        monitoringService.launchNewMonitoring(siteService,site1);
        monitoringService.launchNewMonitoring(siteService,site2);
        monitoringService.launchNewMonitoring(siteService,site3);
        monitoringService.launchNewMonitoring(siteService,site4);
        monitoringService.launchNewMonitoring(siteService,site5);
        stopMonitoring();
    }

    void stopMonitoring(){
            monitoringService.stopMonitoring(1L);
            monitoringService.stopMonitoring(2L);
            monitoringService.stopMonitoring(3L);
            monitoringService.stopMonitoring(4L);
            monitoringService.stopMonitoring(5L);
    }


}

package com.testtask.monitoring;

import com.testtask.monitoring.DTO.SiteDto;
import com.testtask.monitoring.Entity.SiteInfo;
import com.testtask.monitoring.Service.SiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class SiteServiceTests {

    @Autowired
    private SiteService siteService;

    @Test
    void getAllSiteFromDataBase() {
        List<SiteInfo> expected = siteService.getAllSite();
        for (SiteInfo s :
                expected) {
            System.out.println(s.toString());
        }
        Assert.assertNotNull(expected);
    }

    @Test
    void saveSite() throws IOException {
        SiteDto site1 =new SiteDto(1L,"testurl1",true,
                "testsubstring1",
                3,0,10000);
        SiteDto site2 =new SiteDto(2L,"testurl2",true,
                "testsubstring2",
                3,0,10000);
        SiteDto site3 =new SiteDto(3L,"testurl3",true,
                "testsubstring3",
                3,0,10000);
        siteService.saveSite(site1);
        siteService.saveSite(site2);
        siteService.saveSite(site3);
        List<SiteInfo> expected = siteService.getAllSite();
        for (SiteInfo s :
                expected) {
            System.out.println(s.toString());
        }
        Assert.assertNotNull(expected);
    }


    @Test
    void deleteSite()  {
        siteService.deleteSite(1L);
        siteService.deleteSite(2L);
        siteService.deleteSite(3L);
        List<SiteInfo> expected = siteService.getAllSite();
        for (SiteInfo s :
                expected) {
            System.out.println(s.toString());
        }
        Assert.assertNull(expected);
    }


    @Test
    void updateSite()  {
        SiteDto site1 =new SiteDto(1L,"edittesturl1",true,
                "edittestsubstring1",
                5,3000,60000);
        SiteDto site2 =new SiteDto(2L,"edittesturl2",true,
                "edittestsubstring2",
                8,100,98000);
        siteService.updateSite(site1);
        siteService.updateSite(site2);
        List<SiteInfo> expected = siteService.getAllSite();
        for (SiteInfo s :
                expected) {
            System.out.println(s.toString());
        }
        Assert.assertNotNull(expected);
    }

    @Test
    void updateSiteMonitoringActive()  {

        siteService.updateSiteMonitoringActive(1L,false);
        siteService.updateSiteMonitoringActive(2L,false);
        siteService.updateSiteMonitoringActive(3L,false);
        List<SiteInfo> expected = siteService.getAllSite();
        for (SiteInfo s :
                expected) {
            System.out.println(s.toString());
        }
        Assert.assertNotNull(expected);
    }

    @Test
    void updateSiteInfo()  {
        siteService.updateSiteInfo("UpStatus1",1L);
        siteService.updateSiteInfo("UpStatus2",2L);
        List<SiteInfo> expected = siteService.getAllSite();
        for (SiteInfo s :
                expected) {
            System.out.println(s.toString());
        }
        Assert.assertNotNull(expected);
    }

    @Test
    void getSiteById()  {
        SiteDto expected = siteService.getSiteById(1L);
        SiteDto actual =new SiteDto(1L,"testurl1",true,
                "testsubstring1",
                3,0,10000);
        Assert.assertEquals(expected,actual);
    }


}

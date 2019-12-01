package com.testtask.monitoring;

import com.testtask.monitoring.DTO.SiteDto;
import com.testtask.monitoring.Service.HTTPStatService;
import com.testtask.monitoring.Service.HTTPValidation;
import com.testtask.monitoring.Service.SiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class HTTPVolidationTest {

    private HTTPValidation httpValidation;

    @Autowired
    private SiteService siteService;


    @Test
    void checkStat()  {
        SiteDto site1 = new SiteDto(1L, "testurl1", true,
                "testsubstring1",
                3, 0, 10000);
        httpValidation = new HTTPStatService(site1);
        httpValidation.chcekStatus();
    }



    @Test
    void checkSize() throws IOException {
        SiteDto site1 = new SiteDto(1L, "testurl1", true,
                "testsubstring1",
                3, 0, 10000);
        httpValidation = new HTTPStatService(site1);
        httpValidation.checkSize();
    }


    @Test
    void checkSubstingInHeaderTest()  {
        SiteDto site1 = new SiteDto(1L, "testurl1", true,
                "testsubstring1",
                3, 0, 10000);
        httpValidation = new HTTPStatService(site1);
        httpValidation.checkSubstingInHeader();
    }


    @Test
    void checkTimeResponse()  {
        SiteDto site1 = new SiteDto(1L, "testurl1", true,
                "testsubstring1",
                3, 0, 10000);
        httpValidation = new HTTPStatService(site1);
        httpValidation.checkTimeResponse();
    }


}

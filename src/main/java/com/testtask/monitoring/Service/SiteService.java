package com.testtask.monitoring.Service;

import com.testtask.monitoring.model.SiteModel;
import com.testtask.monitoring.Entity.SiteInfo;
import com.testtask.monitoring.Repository.SiteInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteInfoRepository siteInfoRepository;

    private HTTPStatService httpStatService;

    private int convertToSeconds(float hours, float minuts, float seconds) {
        return (int) ((hours * 60 + minuts) * 60 + seconds);
    }

    public void saveSite(SiteModel siteModel) throws IOException {
       httpStatService = new HTTPStatService(siteModel);
        int seconds = convertToSeconds(siteModel.getHours(),
                siteModel.getMinutes(), siteModel.getSeconds());
        SiteInfo siteInfo = new SiteInfo(siteModel.getUrl(), siteModel.isMonitoringActive(),
                siteModel.getSubstringInResponse(), httpStatService.getStatus(),
                seconds, siteModel.getMin(), siteModel.getMax());
        System.out.println(siteInfo.getId());
        //HTTP  logic of  check, which return boolean value
        siteInfoRepository.save(siteInfo);
    }


    public  void  updateSiteInfo(String status, Long id){
       // httpStatService= new HTTPStatService(url);
        siteInfoRepository.update(id,status);
    }

    public void updateSite(SiteModel siteModel){
            siteInfoRepository.updateSiteInfo(siteModel.getId(),
                    siteModel.getMax(),siteModel.getMin(),
                    siteModel.isMonitoringActive(),siteModel.getSeconds());
    }

    public  void  updateSiteMonitoringActive(Long id, boolean monitoringActive){
        siteInfoRepository.updateMonitoringActive(id,monitoringActive);
    }

    public void deleteSite(Long id) {
        siteInfoRepository.deleteById(id);
    }

    public SiteModel getSiteById(Long id){
        SiteInfo siteInfo =siteInfoRepository.findById(id).get();
        return new SiteModel(siteInfo.getId(),siteInfo.getUrl(),siteInfo.isMonitoringActive(),
                siteInfo.getSubstringInResponse(),siteInfo.getSeconds(),
                siteInfo.getMin(),siteInfo.getMax());
    }

    public Iterable<SiteInfo> getAllSite() {
        List<SiteInfo> sortedList = new ArrayList<>();
        for (SiteInfo siteInfo : siteInfoRepository.findAll()) {
            sortedList.add(siteInfo);
        }
        Collections.sort(sortedList);
        return siteInfoRepository.findAll();
    }
}

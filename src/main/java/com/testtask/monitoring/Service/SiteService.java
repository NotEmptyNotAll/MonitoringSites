package com.testtask.monitoring.Service;

import com.testtask.monitoring.DTO.SiteDto;
import com.testtask.monitoring.Entity.SiteInfo;
import com.testtask.monitoring.DAO.SiteInfoRepository;
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

    public void saveSite(SiteDto siteDto) throws IOException {
       httpStatService = new HTTPStatService(siteDto);
        int seconds = convertToSeconds(siteDto.getHours(),
                siteDto.getMinutes(), siteDto.getSeconds());
        SiteInfo siteInfo = new SiteInfo(siteDto.getUrl(), siteDto.isMonitoringActive(),
                siteDto.getSubstringInResponse(), httpStatService.getStatus(),
                seconds, siteDto.getMin(), siteDto.getMax());
        System.out.println(siteInfo.getId());
        //HTTP  logic of  check, which return boolean value
        siteInfoRepository.save(siteInfo);
    }


    public  void  updateSiteInfo(String status, Long id){
       // httpStatService= new HTTPStatService(url);
        siteInfoRepository.update(id,status);
    }

    public void updateSite(SiteDto siteDto){
            siteInfoRepository.updateSiteInfo(siteDto.getId(),
                    siteDto.getMax(), siteDto.getMin(),
                    siteDto.isMonitoringActive(), siteDto.getSeconds());
    }

    public  void  updateSiteMonitoringActive(Long id, boolean monitoringActive){
        siteInfoRepository.updateMonitoringActive(id,monitoringActive);
    }

    public void deleteSite(Long id) {
        siteInfoRepository.deleteById(id);
    }

    public SiteDto getSiteById(Long id){
        SiteInfo siteInfo =siteInfoRepository.findById(id).get();
        return new SiteDto(siteInfo.getId(),siteInfo.getUrl(),siteInfo.isMonitoringActive(),
                siteInfo.getSubstringInResponse(),siteInfo.getSeconds(),
                siteInfo.getMin(),siteInfo.getMax());
    }

    public List<SiteInfo> getAllSite() {
        List<SiteInfo> sortedList = new ArrayList<>();
        for (SiteInfo siteInfo : siteInfoRepository.findAll()) {
            sortedList.add(siteInfo);
        }
        Collections.sort(sortedList);
        return sortedList;
    }
}

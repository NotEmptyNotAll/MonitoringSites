package com.testtask.monitoring.Controller;

import com.testtask.monitoring.DTO.SiteDto;
import com.testtask.monitoring.Service.MonitoringService;
import com.testtask.monitoring.Entity.SiteInfo;
import com.testtask.monitoring.Service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private MonitoringService monitoringService;

    @ModelAttribute("siteModel")
    public SiteDto orderDto() {
        return new SiteDto();
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        Iterable<SiteInfo> listSite = siteService.getAllSite();

        model.addAttribute("listSite", listSite);
        return "index";
    }


        public String update() {
            return "redirect:/index";
        }


    @PostMapping("/editSite")
    public String editSiteInfo(@ModelAttribute("siteModel") SiteDto siteDto,
                               BindingResult bindingResult, @RequestParam Long action,
                               HttpServletRequest request) {
        siteDto.setMonitoringActive(false);
        siteDto.setId(action);
        siteService.updateSite(siteDto);
        return "redirect:/index";
    }


    @PostMapping("/action")
    public String action(@RequestParam Long action) {
        siteService.deleteSite(action);
        monitoringService.stopMonitoring(action);
        return "redirect:/index";
    }

    @PostMapping("/activate")
    public String activate(@RequestParam String action) {
        SiteDto siteDto = siteService.getSiteById(Long.parseLong(action));

        if (siteDto.isMonitoringActive()) {
            monitoringService.stopMonitoring(siteDto.getId());
            siteDto.setMonitoringActive(false);
            siteService.updateSiteMonitoringActive(siteDto.getId(), siteDto.isMonitoringActive());
        } else {
            monitoringService.launchNewMonitoring(siteService, siteDto);
            siteDto.setMonitoringActive(true);
            siteService.updateSiteMonitoringActive(siteDto.getId(), siteDto.isMonitoringActive());
        }
        return "redirect:/index";
    }

    @PostMapping("/addSite")
    public String addSiteInfo(@ModelAttribute("siteModel") SiteDto siteDto,
                              BindingResult bindingResult,
                              HttpServletRequest request) throws IOException {
        siteDto.setMonitoringActive(false);
        siteService.saveSite(siteDto);
        return "redirect:/index";
    }

}

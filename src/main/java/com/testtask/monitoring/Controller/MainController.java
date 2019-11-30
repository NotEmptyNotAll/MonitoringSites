package com.testtask.monitoring.Controller;

import com.testtask.monitoring.Service.MonitoringService;
import com.testtask.monitoring.model.SiteModel;
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

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        Iterable<SiteInfo> listSite = siteService.getAllSite();

        model.addAttribute("listSite", listSite);
        return "index";
    }

    @PostMapping("/editSite")
    public String editSiteInfo(@ModelAttribute("siteModel") SiteModel siteModel,
                               BindingResult bindingResult, @RequestParam Long action,
                               HttpServletRequest request) {
        siteModel.setMonitoringActive(false);
        siteModel.setId(action);
        siteService.updateSite(siteModel);
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
        SiteModel siteModel = siteService.getSiteById(Long.parseLong(action));

        if (siteModel.isMonitoringActive()) {
            monitoringService.stopMonitoring(siteModel.getId());
            siteModel.setMonitoringActive(false);
            siteService.updateSiteMonitoringActive(siteModel.getId(), siteModel.isMonitoringActive());
        } else {
            monitoringService.launchNewMonitoring(siteService, siteModel);
            siteModel.setMonitoringActive(true);
            siteService.updateSiteMonitoringActive(siteModel.getId(), siteModel.isMonitoringActive());
        }
        return "redirect:/index";
    }

    @PostMapping("/addSite")
    public String addSiteInfo(@ModelAttribute("siteModel") SiteModel siteModel,
                              BindingResult bindingResult,
                              HttpServletRequest request) throws IOException {
        siteModel.setMonitoringActive(false);
        siteService.saveSite(siteModel);
        return "redirect:/index";
    }

}

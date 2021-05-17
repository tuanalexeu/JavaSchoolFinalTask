package com.alekseytyan.logiweb.controller.role.driver;

import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.dto.LoadDTO;
import com.alekseytyan.logiweb.entity.enums.DriverState;
import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import com.alekseytyan.logiweb.service.api.DriverService;
import com.alekseytyan.logiweb.service.api.LoadService;
import com.alekseytyan.logiweb.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    private final DriverService driverService;
    private final OrderService orderService;
    private final LoadService loadService;

    @Autowired
    public DriverController(DriverService driverService,
                            OrderService orderService,
                            LoadService loadService) {
        this.driverService = driverService;
        this.orderService = orderService;
        this.loadService = loadService;
    }

    @ModelAttribute("driver")
    public DriverDTO getDriver() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DRIVER"))) {
            return driverService.findDriverByUser(auth.getName());
        }
        throw new RuntimeException("NoSuchDriverException");
    }

    @GetMapping(value = "/info")
    public String getInfo(Model model,
                          @RequestParam(required = false) Integer size,
                          @RequestParam(required = false) Integer page) {

        // Find driver's id
        DriverDTO driverDTO = (DriverDTO) model.getAttribute("driver");

        if(driverDTO == null) {
            return "role/driver/no-driver";
        }

        model.addAttribute("size", size == null ? 10 : size);
        model.addAttribute("page", page == null ? 1 : page);

        if(driverDTO.getOrder() != null) {
            Long orderId = driverDTO.getOrder().getId();

            // find list of co-drivers
            model.addAttribute("coDrivers", driverService.findCoDrivers(orderId));

            // find list of route cities
            model.addAttribute("route", orderService.calculateRoute(orderService.findById(orderId)));

            model.addAttribute("loads", driverDTO.getOrder().getLoads());
        }

        return "role/driver/driverInfo";
    }

    @PostMapping(value = "/save")
    public RedirectView saveStatus(Model model, @RequestParam String status) {
        DriverDTO driverDTO = (DriverDTO) model.getAttribute("driver");
        driverDTO.setState(DriverState.valueOf(status));
        driverService.update(driverDTO);

        return new RedirectView("/driver/info");
    }

    @GetMapping(value = "/save-load")
    public RedirectView saveLoads(@RequestParam Long loadId,
                                  @RequestParam String status) {

        LoadDTO loadDTO = loadService.findById(loadId);
        loadDTO.setStatus(LoadStatus.valueOf(status));

        loadService.update(loadDTO);

        return new RedirectView("/driver/info");
    }
}

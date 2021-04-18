package com.alekseytyan.controller.role;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.entity.Order;
import com.alekseytyan.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping(value = "/info/{id}")
    public String getInfo(@PathVariable Long id, Model model) {

        Driver driver = driverService.findById(id);
        DriverDTO dto = driverService.convertToDTO(driver);
        model.addAttribute("driver", dto);

        return "role/driver/driverInfo";
    }

    @RequestMapping(value = "/driver-order/{id}")
    public String getDutyOrders(@PathVariable Long id, Model model) {

        Driver driver = driverService.findById(id);
        Order order = driver.getOrder();
        model.addAttribute("order", order);

        return "role/driver/driverOrder";
    }
}

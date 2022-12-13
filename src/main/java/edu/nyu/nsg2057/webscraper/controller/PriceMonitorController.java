package edu.nyu.nsg2057.webscraper.controller;

import edu.nyu.nsg2057.webscraper.model.Monitor;
import edu.nyu.nsg2057.webscraper.model.Product;
import edu.nyu.nsg2057.webscraper.service.db.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/monitor")
public class PriceMonitorController {
    @Autowired
    MonitorService monitorService;
    @PostMapping
    public String addMonitor(@RequestBody Monitor pm) {

        monitorService.saveMonitor(pm);
        return "Added Monitor id " + pm.getId();
    }

    @GetMapping("/{id}")

    public Optional<Monitor> getProduct(@PathVariable Integer id) {

        return  monitorService.getMonitorById(id);
    }

    @GetMapping
    public List<Monitor> getAllProduct() {

        return monitorService.getAllMonitors();
    }

    @DeleteMapping

    public String deleteProduct(@PathVariable Integer id) {

        monitorService.delete(id);

        return "Delete pro for id " + id;
    }

    @PutMapping

    public String updateProduct(@RequestBody Monitor m) {

        monitorService.updateMonitor(m);

        return "Updated Product for id " + m.getId();
    }
}

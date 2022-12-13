package edu.nyu.nsg2057.webscraper.service.db;

import edu.nyu.nsg2057.webscraper.model.Monitor;
import edu.nyu.nsg2057.webscraper.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitorService {
    @Autowired
    MonitorRepository monitorRepository;

    public List<Monitor> getAllMonitors() {
        List<Monitor> allMonitors = monitorRepository.findAll();
        return allMonitors;
    }

    public Optional<Monitor> getMonitorById(int id) {
        Optional<Monitor> monitor = monitorRepository.findById(id);

        return monitor;
    }

    public void updateMonitor(Monitor monitor) {
        monitorRepository.save(monitor);
        System.out.println("Monitor Updated");
    }
    public String saveMonitor(Monitor monitor) {

        monitorRepository.insert(monitor);

        return "Added Monitor id " + monitor.getId();
    }

    public void delete(Integer id) {
        monitorRepository.deleteById(id);
    }
}

package edu.nyu.nsg2057.webscraper.repository;

import edu.nyu.nsg2057.webscraper.model.Monitor;
import edu.nyu.nsg2057.webscraper.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends MongoRepository<Monitor, Long> {

}
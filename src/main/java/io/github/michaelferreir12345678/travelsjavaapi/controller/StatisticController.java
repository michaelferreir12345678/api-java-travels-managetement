package io.github.michaelferreir12345678.travelsjavaapi.controller;

import java.util.List;

import io.github.michaelferreir12345678.travelsjavaapi.model.Statistic;
import io.github.michaelferreir12345678.travelsjavaapi.model.Travel;
import io.github.michaelferreir12345678.travelsjavaapi.service.StatisticService;
import io.github.michaelferreir12345678.travelsjavaapi.service.TravelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api-travels/statistics")
public class StatisticController {

    private static final Logger logger = Logger.getLogger(StatisticController.class);

    @Autowired
    private TravelService tripsService;

    @Autowired
    private StatisticService statisticsService;

    @GetMapping(produces = { "application/json" })
    public ResponseEntity<Statistic> getStatistics() {

        List<Travel> trips = tripsService.find();
        Statistic statistics = statisticsService.create(trips);

        logger.info(statistics);

        return ResponseEntity.ok(statistics);
    }

}

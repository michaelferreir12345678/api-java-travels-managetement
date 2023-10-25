package io.github.michaelferreir12345678.travelsjavaapi.controller;


import org.apache.log4j.Logger;

import io.github.michaelferreir12345678.travelsjavaapi.model.Travel;
import io.github.michaelferreir12345678.travelsjavaapi.service.TravelService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/api-travels/travels")
public class TravelController {

    private static final Logger logger = Logger.getLogger(TravelController.class);

    @Autowired
    private TravelService tripService;

    @GetMapping
    public ResponseEntity<List<Travel>> find() {
        if(tripService.find().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info(tripService.find());
        return ResponseEntity.ok(tripService.find());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {
        try {
            tripService.delete();
            return ResponseEntity.noContent().build();
        }catch(Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<Travel> create(@RequestBody JSONObject trip) {
        try {
            if(tripService.isJSONValid(trip.toString())) {
                Travel tripCreated = tripService.create(trip);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path(tripCreated.getOrderNumber()).build().toUri();

                if(tripService.isStartDateGreaterThanEndDate(tripCreated)){
                    logger.error("The start date is greater than end date.");
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
                }else {
                    tripService.add(tripCreated);
                    return ResponseEntity.created(uri).body(null);
                }
            }else {
                return ResponseEntity.badRequest().body(null);
            }
        }catch(Exception e) {
            logger.error("JSON fields are not parsable. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
    @PutMapping(path = "/{id}", produces = { "application/json" })
    public ResponseEntity<Travel> update(@PathVariable("id") long id, @RequestBody JSONObject travel) {
        try {
            if(tripService.isJSONValid(travel.toString())) {
                Travel tripToUpdate = tripService.findById(id);
                if(tripToUpdate == null){
                    logger.error("Travel not found.");
                    return ResponseEntity.notFound().build();
                }else {
                    Travel tripUpdated = tripService.update(tripToUpdate, travel);
                    return ResponseEntity.ok(tripUpdated);
                }
            }else {
                return ResponseEntity.badRequest().body(null);
            }
        }catch(Exception e) {
            logger.error("JSON fields are not parsable." + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
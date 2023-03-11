package com.example.housing.controller;

import com.example.housing.model.train.CurrentTrains;
import com.example.housing.model.train.IrishRailStation;
import com.example.housing.service.IrishRailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/trains")
public class IrishRailController {

    @Autowired
    IrishRailService irishRailService;

    @GetMapping(path = "/stations")
    public ResponseEntity<List<IrishRailStation>> getTrains() throws Exception {
        return ResponseEntity.ok().body(irishRailService.getStations());
    }

    @GetMapping(path = "/current-trains")
    public ResponseEntity<List<CurrentTrains>> getCurrentTrains() throws Exception {
        return ResponseEntity.ok().body(irishRailService.getCurrentTrains());
    }
}

package com.example.housing.service;

import com.example.housing.api.IrishRailDataRetriever;
import com.example.housing.model.train.CurrentTrains;
import com.example.housing.model.train.IrishRailStation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrishRailService {

    public List<IrishRailStation> getStations() throws Exception {
        return IrishRailDataRetriever.getStations();
    }

    public List<CurrentTrains> getCurrentTrains() throws Exception {
        return IrishRailDataRetriever.getTrainMovements();
    }

}

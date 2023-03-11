package com.example.housing.api;

import com.example.housing.model.train.CurrentTrains;
import com.example.housing.model.train.IrishRailStation;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class IrishRailDataRetriever {

    private static final String GET_ALL_STATIONS_URL = "http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML";
    private static final String GET_ALL_TRAIN_MOVEMENTS_URL = "http://api.irishrail.ie/realtime/realtime.asmx/getCurrentTrainsXML";
    private static final RestTemplate restTemplate = new RestTemplate();


    public static List<IrishRailStation> getStations() {
        return IrishRailStation.parseResponse(restTemplate.getForObject(GET_ALL_STATIONS_URL, String.class));
    }

    public static List<CurrentTrains> getTrainMovements() {
        return CurrentTrains.parseResponse(restTemplate.getForObject(GET_ALL_TRAIN_MOVEMENTS_URL, String.class));
    }
}

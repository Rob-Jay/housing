package com.example.housing.model.train;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class CurrentTrains {

    public String TrainStatus;
    public double TrainLatitude;
    public double TrainLongitude;
    public String TrainCode;
    public Date TrainDate;
    public String PublicMessage;
    public String Direction;

    public CurrentTrains(String trainStatus, double trainLatitude, double trainLongitude, String trainCode, Date trainDate, String publicMessage, String direction) {
        TrainStatus = trainStatus;
        TrainLatitude = trainLatitude;
        TrainLongitude = trainLongitude;
        TrainCode = trainCode;
        TrainDate = trainDate;
        PublicMessage = publicMessage;
        Direction = direction;
    }

    public static List<CurrentTrains> parseResponse(String xml) {
        List<CurrentTrains> trainList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);
            NodeList nodeList = doc.getElementsByTagName("objTrainPositions");
            IntStream.range(0, nodeList.getLength())
                    .mapToObj(nodeList::item)
                    .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                    .map(node -> (Element) node)
                    .forEach(element -> {
                        String trainStatus = element.getElementsByTagName("TrainStatus").item(0).getTextContent();
                        double trainLatitude = Double.parseDouble(element.getElementsByTagName("TrainLatitude").item(0).getTextContent());
                        double trainLongitude = Double.parseDouble(element.getElementsByTagName("TrainLongitude").item(0).getTextContent());
                        String trainCode = element.getElementsByTagName("TrainCode").item(0).getTextContent();
                        String trainDateString = element.getElementsByTagName("TrainDate").item(0).getTextContent();
                        Date trainDate = null;
                        try {
                            trainDate = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).parse(trainDateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String publicMessage = element.getElementsByTagName("PublicMessage").item(0).getTextContent();
                        String direction = element.getElementsByTagName("Direction").item(0).getTextContent();
                        CurrentTrains train = new CurrentTrains(trainStatus, trainLatitude, trainLongitude, trainCode, trainDate, publicMessage, direction);
                        trainList.add(train);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trainList;
    }


}


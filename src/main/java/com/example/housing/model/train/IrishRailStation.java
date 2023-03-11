package com.example.housing.model.train;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class IrishRailStation {

    public String StationCode;
    public String StationDesc;
    public String StationLatitude;
    public String StationLongitude;
    public String StationId;


    public IrishRailStation(String stationCode, String stationDesc, String stationLatitude, String stationLongitude, String stationId) {
        StationCode = stationCode;
        StationDesc = stationDesc;
        StationLatitude = stationLatitude;
        StationLongitude = stationLongitude;
        StationId = stationId;
    }

    public static List<IrishRailStation> parseResponse(String xml) {
        List<IrishRailStation> stationList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);
            NodeList nodeList = doc.getElementsByTagName("objStation");
            IntStream.range(0, nodeList.getLength())
                    .mapToObj(nodeList::item)
                    .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                    .map(node -> (Element) node)
                    .forEach(element -> {
                        String stationCode = element.getElementsByTagName("StationCode").item(0).getTextContent();
                        String stationDesc = element.getElementsByTagName("StationDesc").item(0).getTextContent();
                        String stationLatitude = element.getElementsByTagName("StationLatitude").item(0).getTextContent();
                        String stationLongitude = element.getElementsByTagName("StationLongitude").item(0).getTextContent();
                        String stationId = element.getElementsByTagName("StationId").item(0).getTextContent();
                        IrishRailStation station = new IrishRailStation(stationCode, stationDesc, stationLatitude, stationLongitude, stationId);
                        stationList.add(station);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stationList;
    }

}
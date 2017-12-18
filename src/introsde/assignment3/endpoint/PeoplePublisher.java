package introsde.assignment3.endpoint;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.ws.Endpoint;

import introsde.assignment3.soap.PeopleImpl;

public class PeoplePublisher {

    public static void main(String[] args) throws IllegalArgumentException, UnknownHostException{
        String PROTOCOL = "http://";
        String HOSTNAME = "localhost";
        String PORT = "6902";
        String BASE_URL = "/people";
        
        String endpointUrl = PROTOCOL+HOSTNAME+":"+PORT+BASE_URL;
        System.out.println("Starting People Service...");
        System.out.println("--> Published. Check out "+endpointUrl+"?wsdl");
        Endpoint.publish(endpointUrl, new PeopleImpl());
}
}
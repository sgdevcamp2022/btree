package com.btree.post.service;


import com.btree.post.dto.locationresponsedto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class distancecheck {
    private static final double EARTH_RADIUS = 6371;

    public boolean comparelocation (locationresponsedto georesponse, locationresponsedto realresponse){
        if(georesponse.documents[0].address_name==realresponse.documents[0].address_name){
            return true;
        }
        else if(getDistance(georesponse.documents[0].x,georesponse.documents[0].y,realresponse.documents[0].x,realresponse.documents[0].y)<=5000.0){
            return true;
        }
        else
            return false;
    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(lat1))* Math.cos(Math.toRadians(lat2))* Math.sin(dLon/2)* Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = EARTH_RADIUS * c * 1000;    // Distance in m
        return d;
    }
}

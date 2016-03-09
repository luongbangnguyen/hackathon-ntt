package com.vn.ntt.until;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;

/**
 * Created by bangnl on 3/9/16.
 */
public class MeasureUntil {
    static public Distance getDistanceByMet(double radius){
        double km = exchangeKilometToMet(radius);
        return new Distance(km, Metrics.KILOMETERS);
    }

    static public double exchangeKilometToMet(double km){
        if(km == 0){
            return km;
        }
        return km / 1000;
    }
}

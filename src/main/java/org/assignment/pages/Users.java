package org.assignment.pages;

import org.assignment.request.GetRequest;
import org.assignment.response.Response;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Users extends BasePage {
    final static String endpoint = "/users";

    public Set<Integer> getUsersBasedOnLocation(String city, String latitude, String longitude) {
        try {
            double startLatitude = Double.parseDouble(latitude.split("To")[0].trim());
            double endLatitude = Double.parseDouble(latitude.split("To")[1].trim());
            double startLongitude = Double.parseDouble(longitude.split("To")[0].trim());
            double endLongitude = Double.parseDouble(longitude.split("To")[1].trim());

            Response response = new GetRequest(environment, endpoint).getResponse();
            List listOfAllUsers = response.extractDataAsList("id");
            List listOfGeoLocation = response.extractDataAsList("address.geo");
            Set<Integer> setOfUsersBelongsToTheCity = new HashSet<>();
            for (int i = 0; i < listOfAllUsers.size(); i++) {
                Map<String, String> geoLocation = (Map<String, String>) listOfGeoLocation.get(i);
                double mapLatitude = Double.parseDouble(geoLocation.get("lat"));
                double mapLongitude = Double.parseDouble(geoLocation.get("lng"));
                boolean isLatInRange = this.isNumberRage(mapLatitude, startLatitude, endLatitude);
                boolean isLngInRange = this.isNumberRage(mapLongitude, startLongitude, endLongitude);
                if (isLatInRange && isLngInRange) {
                    setOfUsersBelongsToTheCity.add((int) listOfAllUsers.get(i));
                }
            }
            return setOfUsersBelongsToTheCity;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean isNumberRage(double value, double lowerBound, double upperBound) {
        try {
            if (value >= lowerBound && lowerBound <= upperBound)
                return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}

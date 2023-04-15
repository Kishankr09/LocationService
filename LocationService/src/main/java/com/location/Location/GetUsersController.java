package com.location.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUsersController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping("/get_users/{n}")
  public List<User> getUserLocations(@PathVariable int n) {
    String sql = "SELECT name, latitude, longitude FROM user_location";
    List<java.util.Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

    //Get all the users from the database
    List<User> userLocations = new ArrayList<>();
    for (java.util.Map<String, Object> row : rows) {
        User userLocation = new User();
        userLocation.setName((String) row.get("name"));
        userLocation.setLatitude((Double) row.get("latitude"));
        userLocation.setLongitude((Double) row.get("longitude"));
        userLocations.add(userLocation);
    }

  //Calculate the distance from the origin for each user
    userLocations.forEach(user -> {
      double distance = Math.sqrt(Math.pow(0 - user.getLatitude(), 2) +
                                  Math.pow(0 - user.getLongitude(), 2));
      user.setDistance(distance);
  });

  //Sort the users by distance
  Collections.sort(userLocations, new Comparator<User>() {
    public int compare(User u1, User u2) {
        return Double.compare(u1.getDistance(), u2.getDistance());
    }
});

  //Return the first n users in the list
  return userLocations.stream().limit(n).collect(Collectors.toList());
  }
	
}
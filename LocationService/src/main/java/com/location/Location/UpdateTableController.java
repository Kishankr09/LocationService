package com.location.Location;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

  @RestController
  public class UpdateTableController {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  public void insertData(String name, double latitude, double longitude ) 
  {
  String sql = "INSERT INTO user_location (name,latitude,longitude) VALUES (:name,:latitude,:longitude)";
  Map<String, Object> params = new HashMap<>();
  params.put("name",name);  
  params.put("latitude",latitude);
  params.put("longitude",longitude);
  int rowsAffected = jdbcTemplate.update(sql, params);
  System.out.println(rowsAffected + " rows inserted.");
  }
  
	@PostMapping("/update_data")
	public ResponseEntity<Void> updatetable(@RequestParam("name") String name,
  @RequestParam("latitude") double latitude,
  @RequestParam("longitude") double longitude) {

    // UserLocation location = new UserLocation(name,latitude,longitude);

    insertData(name, latitude, longitude);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
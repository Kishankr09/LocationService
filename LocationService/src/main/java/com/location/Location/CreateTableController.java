package com.location.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTableController {
  @Autowired
  private JdbcTemplate jdbcTemplate;

	@PostMapping("/create_data")
	public ResponseEntity<String> createtable(@RequestParam(value = "isAdmin", defaultValue = "false") String isAdmin) {
	if(isAdmin.equals("true"))
	{
		jdbcTemplate.execute("CREATE TABLE user_location (name VARCHAR(255), latitude DOUBLE, longitude DOUBLE)");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	else
	{
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("If you are an admin please pass 'isAdmin' parameter true");

	}

}
}
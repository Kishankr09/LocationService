package com.location.Location;

public class User {


  
  private String name;
  private double latitude;
  private double longitude;
  private double distance;

  public void setDistance(double distance) {
    this.distance = distance;
  }
  public double getDistance() {
    return this.distance;
  }

  
  public String getName() {
    return this.name;
  }


  public double getLatitude() {
    return this.latitude;
  }


  public double getLongitude() {
    return this.longitude;
  }

  public void setName(String string) 
  {
  name = string;
  }
  public void setLatitude(Double double1) 
  {
  latitude = double1;
  }
  public void setLongitude(Double double1) 
  {
  longitude = double1;
  }


 

}
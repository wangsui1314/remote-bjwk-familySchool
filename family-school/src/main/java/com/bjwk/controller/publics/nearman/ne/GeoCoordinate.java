package com.bjwk.controller.publics.nearman.ne;

public class GeoCoordinate {
	 private double longitude;  
	  private double latitude;  
	  
	  private String key;
	  
	
	  public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	  
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {  
	    return longitude;  
	  }  
	  
	  public double getLatitude() {  
	    return latitude;  
	  }  
	  
	  @Override  
	  public boolean equals(Object o) {  
	    if (this == o) return true;  
	    if (!(o instanceof GeoCoordinate)) return false;  
	  
	    GeoCoordinate that = (GeoCoordinate) o;  
	  
	    if (Double.compare(that.longitude, longitude) != 0) return false;  
	    return Double.compare(that.latitude, latitude) == 0;  
	  }  
	  
	  @Override  
	  public int hashCode() {  
	    // follows IntelliJ default hashCode implementation  
	    int result;  
	    long temp;  
	    temp = Double.doubleToLongBits(longitude);  
	    result = (int) (temp ^ (temp >>> 32));  
	    temp = Double.doubleToLongBits(latitude);  
	    result = 31 * result + (int) (temp ^ (temp >>> 32));  
	    return result;  
	  }  
	  
	  @Override  
	  public String toString() {  
	    return "(" + longitude + "," + latitude + ")";  
	  }  
}

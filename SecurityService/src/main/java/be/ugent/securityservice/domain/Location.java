package be.ugent.securityservice.domain;

public class Location {
	private String name;
	private float xlong;
	private float xlat;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getXlong() {
		return xlong;
	}
	public void setXlong(float xlong) {
		this.xlong = xlong;
	}
	public float getXlat() {
		return xlat;
	}
	public void setXlat(float xlat) {
		this.xlat = xlat;
	}
	
}

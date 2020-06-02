package it.univpm.SpringBootApp.model;

public class Location{
	protected String city_location;
	protected String country_location;
	protected double latitude_location;
	protected double longitude_location;
	protected String zip_location;
	
	public Location (String city_location, String country_location, double latitude_location, double longitude_location, String zip_location) {
		this.city_location=city_location;
		this.country_location=country_location;
		this.latitude_location=latitude_location;
		this.longitude_location=longitude_location;
		this.zip_location=zip_location;
	}
	
	public Location() {
		
	}
	
	/**
	 * Metodo che restituisce city_location
	 * @return city_location
	 */
	public String getcity_location() {
		return city_location;
	}
	
	/**
	 * Metodo che restituisce country_location
	 * @return country_location
	 */
	public String getcountry_location() {
		return country_location;
	}
	
	/**
	 * Metodo che restituisce latitude_location
	 * @return latitude_location
	 */
	public double getlatitude_location() {
		return latitude_location;
	}
	
	/**
	 * Metodo che restituisce longitude_location
	 * @return longitude_location
	 */
	public double getlongitude_location() {
		return longitude_location;
	}
	
	/**
	 * Metodo che restituisce zip_location
	 * @return zip_location
	 */
	public String getzip_location() {
		return zip_location;
	}
	
	/**
	 * Metodo che imposta il valore di city_location
	 * @param city_location
	 */

	public void setcity_location(String city_location) {
		this.city_location = city_location;
	}
	
	/**
	 * Metodo che imposta il valore di country_location
	 * @param country_location
	 */
	
	public void setcountry_location(String country_location) {
		this.country_location = country_location;
	}
	
	/**
	 * Metodo che imposta il valore di latitude_location
	 * @param latitude_location
	 */
	
	public void setlatitude_location(double latitude_location) {
		this.latitude_location = latitude_location;
	}
	
	/**
	 * Metodo che imposta il valore di longitude_location
	 * @param longitude_location
	 */
	
	public void setlongitude_location(double longitude_location) {
		this.longitude_location = longitude_location;
	}
	
	/**
	 * Metodo che imposta il valore di zip_location
	 * @param zip_location
	 */
	
	public void setzip_location(String zip_location) {
		this.zip_location = zip_location;
	}
	
	@Override
	public String toString(){
		return " CITY: " + getcity_location() + " COUNTRY: "+getcountry_location()+" LATITUDE: "+getlatitude_location() + " LONGITUDE: " +getlongitude_location()+ " ZIPCODE: " +getzip_location();
	}
}
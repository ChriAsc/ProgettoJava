package it.univpm.SpringBootApp.model;

import it.univpm.SpringBootApp.model.Location;

public class Place{
	protected String name_place;
	protected Location location_place;
	protected String id_place;	
	
	public Place(String name_place, Location location_place, String id_place) {
				this.name_place=name_place;
				this.location_place=location_place;
				this.id_place=id_place;
	}
	
	public Place() {
		
	}
	
	/**
	 * Metodo che restituisce name_place
	 * @return name_place
	 */
	public String getname_place() {
		return name_place;
	}
	
	/**
	 * Metodo che restituisce location_place
	 * @return location_place
	 */
	public Location getlocation_place() {
		return location_place;
	}
	
	/**
	 * Metodo che restituisce id_place
	 * @return id_place
	 */
	public String getid_place() {
		return id_place;
	}
	
	/**
	 * Metodo che imposta il valore di name_place
	 * @param name_place
	 */

	public void setname_place(String name_place) {
		this.name_place = name_place;
	}
	
	/**
	 * Metodo che imposta il valore di location_place
	 * @param location_place
	 */

	public void setlocation_place(Location location_place) {
		this.location_place = location_place;
	}
	
	/**
	 * Metodo che imposta il valore di id_place
	 * @param id_place
	 */

	public void setid_place(String id_place) {
		this.id_place = id_place;
	}
	
}
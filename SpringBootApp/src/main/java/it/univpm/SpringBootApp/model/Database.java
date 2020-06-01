package it.univpm.SpringBootApp.model;

import java.util.ArrayList;

import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.model.Metadata;;

/**
 * Classe che forma Arraylist di Dati e Metadati
 * @author Cristian Cingolani & Christian Ascani
 *
 */

public class Database {
	private ArrayList<Data> arrData = new ArrayList<Data>();
	private ArrayList<Metadata> arrMetadata = new ArrayList<Metadata>();
	
	
	/**
	* Costruttore della classe Database
	 */
	
	public Database()
	{
		arrMetadata.add(new Metadata("id", "String"));
		arrMetadata.add(new Metadata("can_upload", "boolean"));
		arrMetadata.add(new Metadata("count", "int"));
		arrMetadata.add(new Metadata("created_time", "String"));
		arrMetadata.add(new Metadata("description", "String"));
		arrMetadata.add(new Metadata("event", "String"));
		arrMetadata.add(new Metadata("link", "String"));
		arrMetadata.add(new Metadata("location", "String"));
		arrMetadata.add(new Metadata("name", "String"));
		
		arrMetadata.add(new Metadata("place", "Place"));
		
		arrMetadata.add(new Metadata("name_place", "String"));
		arrMetadata.add(new Metadata("location_place", "Location"));
		arrMetadata.add(new Metadata("id_place", "string"));
		
		arrMetadata.add(new Metadata("city_location", "String"));
		arrMetadata.add(new Metadata("country_location", "String"));
		arrMetadata.add(new Metadata("latitude_location", "double"));
		arrMetadata.add(new Metadata("longitude_location", "double"));
		arrMetadata.add(new Metadata("zip_location", "String"));
		
		arrMetadata.add(new Metadata("privacy", "String"));
		arrMetadata.add(new Metadata("type", "String"));
		arrMetadata.add(new Metadata("updated_time", "float"));
		
		
	}
	
	/**
	 * Metodo che restituisce arrData
	 * @return arrData
	 */
	
	public ArrayList<Data> getarrData() {
		return arrData;
	}
	
	/**
	 * Metodo che restituisce arrMetadata
	 * @return arrMetadata
	 */
	
	public ArrayList<Metadata> getarrMetadata() {
		return arrMetadata;
	}
	
	/**
	 * Metodo che inserisce in arrData un elemento di tipo Data
	 * @param Data
	 */
	public void setArrData(Data d) {
		arrData.add(d);
	}

}

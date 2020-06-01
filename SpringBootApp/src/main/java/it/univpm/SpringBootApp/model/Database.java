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
		arrMetadata.add(new Metadata("id", "string"));
		arrMetadata.add(new Metadata("can_upload", "boolean"));
		arrMetadata.add(new Metadata("count", "int"));
		arrMetadata.add(new Metadata("created_time", "string"));
		arrMetadata.add(new Metadata("description", "string"));
		arrMetadata.add(new Metadata("event", "string"));
		arrMetadata.add(new Metadata("link", "string"));
		arrMetadata.add(new Metadata("location", "string"));
		arrMetadata.add(new Metadata("name", "string"));
		arrMetadata.add(new Metadata("place", "Place"));
		arrMetadata.add(new Metadata("privacy", "string"));
		arrMetadata.add(new Metadata("type", "string"));
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

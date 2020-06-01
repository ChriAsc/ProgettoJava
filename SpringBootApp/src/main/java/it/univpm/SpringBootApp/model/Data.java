package it.univpm.SpringBootApp.model;

import java.util.ArrayList;

import it.univpm.SpringBootApp.model.Place;

/**
 * Classe che descrive Dati
 * @author Cristian Cingolani & Christian Ascani
 *
 */

public class Data {
	protected String id;
	protected boolean can_upload;
	protected long count;
	protected String created_time;
	protected String description;
	protected String event;
	protected String link;
	protected String location;
	protected String name;
	protected ArrayList<Place> place;
	protected String privacy;
	protected String type;
	protected String updated_time;


/**
* Costruttore della classe Data
* @param id;
* @param can_upload;
* @param count;
* @param created_time;
* @param description;
* @param event;
* @param link;
* @param location;
* @param name;
* @param place;
* @param privacy;
* @param type;
* @param updated_time;
 */

public Data(String id, boolean can_upload, int count, String created_time,
String description, String event, String link, String location,
String name, ArrayList<Place> place, String privacy, String type, String updated_time) {
	this.id=id;
	this.can_upload=can_upload;
	this.count=count;
	this.created_time=created_time;
	this.description=description;
	this.event=event;
	this.link=link;
	this.location=location;
	this.name=name;
	this.place=place;
	this.privacy=privacy;
	this.type=type;
	this.updated_time=updated_time;
	}



/**
 * costruttore vuoto per la classe Data
 */
public Data() {
	
}

/**
 * Metodo che restituisce id
 * @return id
 */

public String getid() {
	return id;
}

/**
 * Metodo che restituisce can_upload
 * @return can_upload
 */

public boolean getcan_upload() {
	return can_upload;
}

/**
 * Metodo che restituisce count
 * @return count
 */

public long getcount() {
	return count;
}

/**
 * Metodo che restituisce created_time
 * @return created_time
 */

public String getcreated_time() {
	return created_time;
}

/**
 * Metodo che restituisce description
 * @return description
 */

public String getdescription() {
	return description;
}

/**
 * Metodo che restituisce event
 * @return event
 */

public String getevent() {
	return event;
}

/**
 * Metodo che restituisce link
 * @return link
 */

public String getlink() {
	return link;
}

/**
 * Metodo che restituisce location
 * @return location
 */

public String getlocation() {
	return location;
}

/**
 * Metodo che restituisce name
 * @return name
 */

public String getname() {
	return name;
}


/**
 * Metodo che restituisce privacy
 * @return privacy
 */

public String getprivacy() {
	return privacy;
}

/**
 * Metodo che restituisce type
 * @return type
 */

public String gettype() {
	return type;
}

/**
 * Metodo che restituisce updated_time
 * @return updated_time
 */

public String getupdated_time() {
	return updated_time;
}

/**
 * Metodo che restituisce place
 * @return place
 */

public ArrayList<Place> getplace() {
	return place;
}

/**
 * Metodo che imposta il valore di id
 * @param id
 */

public void setid(String id) {
	this.id = id;
}

/**
 * Metodo che imposta il valore di can_upload
 * @param can_upload
 */

public void setcan_upload(boolean can_upload) {
	this.can_upload = can_upload;
}

/**
 * Metodo che imposta il valore di count
 * @param count
 */

public void setcount(long count) {
	this.count = count;
}

/**
 * Metodo che imposta il valore di created_time
 * @param created_time
 */

public void setcreated_time(String created_time) {
	this.created_time = created_time;
}

/**
 * Metodo che imposta il valore di description
 * @param description
 */

public void setdescription(String description) {
	this.description = description;
}

/**
 * Metodo che imposta il valore di event
 * @param event
 */

public void setevent(String event) {
	this.event = event;
}

/**
 * Metodo che imposta il valore di link
 * @param link
 */

public void setlink(String link) {
	this.link = link;
}

/**
 * Metodo che imposta il valore di location
 * @param location
 */

public void setlocation(String location) {
	this.location = location;
}

/**
 * Metodo che imposta il valore di name
 * @param name
 */

public void setname(String name) {
	this.name = name;
}

/**
 * Metodo che imposta il valore di privacy
 * @param privacy
 */

public void setprivacy(String privacy) {
	this.privacy = privacy;
}

/**
 * Metodo che imposta il valore di type
 * @param type
 */

public void settype(String type) {
	this.type = type;
}

/**
 * Metodo che imposta il valore di updated_time
 * @param updated_time
 */

public void setupdated_time(String updated_time) {
	this.updated_time = updated_time;
}

/**
 * Metodo che imposta il valore di place
 * @param place
 */

public void setplace(ArrayList<Place> place) {
	this.place = place;
}
}
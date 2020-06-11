package it.univpm.SpringBootApp.service;

import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.model.Location;
import it.univpm.SpringBootApp.model.Place;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe utilizzata come servizio per la lettura di un json. Questo viene
 * gestito mediante una lista di ArrayList
 * @author Cristian Cingolani & Christian Ascani
 */
public class ParserJSON {

	/**
	 * Metodo per parsare il file json in classi Data, collezionati come Arraylist
	 *
	 * @param fileName nome del json da parsare
	 * @return ritorna l'ArrayList
	 * @throws java.text.ParseException 
	 *
	**/
	public ArrayList<Data> parserJson(String fileName) throws java.text.ParseException
    {
		ArrayList<Data> Albums = new ArrayList<>();
    	JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(new FileReader(fileName));
	        JSONArray arr = (JSONArray) object.get("data");
	        for(int i=0; i<arr.size(); i++){
	        	Data a = new Data();
	        	//filling Album a
	            JSONObject array = (JSONObject) arr.get(i);
	            if(array.get("id")!=null)
	            {
	            	a.setid((String) array.get("id"));
	            }
	            else
	            {
	            	a.setid("null");
	            }
	            
	            a.setcan_upload((boolean) array.get("can_upload"));
	            a.setcount((long) array.get("count"));
	            a.setcreated_time((String) array.get("created_time"));
	            if(array.get("description")!=null)
	            {
	            a.setdescription((String) array.get("description"));
	            }
	            else
	            {
	            	a.setdescription("null");
	            }
	            if(array.get("event")!=null)
	            {
	            a.setevent((String) array.get("event"));
	            }
	            else
	            {
	            	a.setevent("null");
	            }
	            a.setlink((String) array.get("link"));
	            if(array.get("location")!=null)
	            {
	            a.setlocation((String) array.get("location"));
	            }
	            else
	            {
	            	a.setlocation("null");
	            }
	            a.setname((String) array.get("name"));
	            a.setprivacy((String) array.get("privacy"));
	            a.settype((String) array.get("type"));
	            a.setupdated_time((String) array.get("updated_time"));
	            
	            Place p = new Place();
            	p.setname_place("null");
            	p.setid_place("null");
            	Location l = new Location();
            	l.setcity_location("null");
            	l.setcountry_location("null");
            	l.setlatitude_location(0.0);
            	l.setlongitude_location(0.0);
            	l.setzip_location("null");
            	p.setid_place("null");
	            
	            
	            if((JSONObject) array.get("place") != null) {
	            	JSONObject places = (JSONObject) array.get("place");
	            	String name = (String) places.get("name");
	            	p.setname_place(name);
	            	String id = (String) places.get("id");
	            	p.setid_place(id);
	            	
	            	JSONObject locations = (JSONObject) places.get("location");
	            	if(!locations.isEmpty()) {
	            		            		
                        String city = (String) locations.get("city");
                        l.setcity_location(city);
                        String country = (String) locations.get("country");
                        l.setcountry_location(country);
                        Object latitude = ((Object) locations.get("latitude"));
                        l.setlatitude_location(((Number) latitude).doubleValue());
                        Object longitude = ((Object) locations.get("longitude"));
                        l.setlongitude_location(((Number) longitude).doubleValue());
                        String zip = (String) locations.get("zip");
                        l.setzip_location(zip);
                        
	            	p.setlocation_place(l);
	            	}
	            	
	            	a.setplace(p);
	            }
	            	
	            
	            Albums.add(a);																//adding album to ArrayList of Album
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return Albums;
    }
}


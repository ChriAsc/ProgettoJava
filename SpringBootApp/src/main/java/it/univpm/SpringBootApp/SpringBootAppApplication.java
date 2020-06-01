package it.univpm.SpringBootApp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.model.Place;
import it.univpm.SpringBootApp.service.JSONGetAndDecode;
import it.univpm.SpringBootApp.service.ParserJSON;


/**
	 * Main del programma. Inizialmente si istanzia un oggetto della classe JsonGetDecode per scaricare
	 * il file json contenete i dati. Successivamente si avvia la spring application.
	 * @param args
	 * @throws Exception 
	 * @author Cristian Cingolani & Christian Ascani
 */
@SpringBootApplication
public class SpringBootAppApplication {
	
	public static ArrayList<Data> albumArrayList;
	public static ArrayList<Place> placeArrayList;

	public static void main(String[] args) throws IOException {
		
			File file = new File("dataFile.json");
	        if(!file.exists()){
				JSONGetAndDecode download = new JSONGetAndDecode("https://graph.facebook.com/me/albums?fields=id,can_upload,count,created_time,description,event,link,location,name,place,privacy,type,updated_time&access_token=EAAg0XZALFgWIBAPzlZCLZBmuaadhqvZAQtcTs9QoBC5mbq1fUIdAUPD4W4JDZBNzvboGDTTRmWKMwGrxzkh0RV83egmGU5gDKbcZBo9ZBPHAb46LKMKmWbZCyfZAnHgahLp0q6GGoLZA4134kdGq5TOUR3gYWmDIw6EB0V14d3XGrXZBjVuEQZBpPoeuZBE4na6oh3cbzSGLZAmS9lxSPyd1ze2ZAWYjUw79fT1NgH5Anpf41Bu0wZDZD");
				try {
					download.downloadJson(file.getName());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			ParserJSON parseFile = new ParserJSON();
	        albumArrayList = parseFile.parserJson(file.getName());
	        for (int i = 0; i<albumArrayList.size(); i++) {
	        	System.out.println(albumArrayList.get(i).getid() + " = ID " + albumArrayList.get(i).getlink() + " = LINK " + albumArrayList.get(i).getlocation() + " = LOCATION " + albumArrayList.get(i).getname() + " = NAME " + albumArrayList.get(i).getprivacy() + " = PRIVACY "
	        			+ albumArrayList.get(i).gettype() + " = TYPE " + albumArrayList.get(i).getupdated_time() + " = UPDATED TIME ");
	        	placeArrayList = albumArrayList.get(i).getplace();
	        	
	        	if(placeArrayList != null) {
	        		
	        		Iterator<Place> it = placeArrayList.iterator();
	        		while (it.hasNext()) { 
	                    System.out.print(it.next().getid_place() + " PLACEID " + it.next().getname_place()+ " PLACENAME " + it.next().getlocation_place() + " PLACELOCATION ");
	        				
        				//System.out.println(albumArrayList.get(i).getplace().get(1).getid_place() + " = PLACEID " + albumArrayList.get(i).getplace().get(1).getname_place() + " = PLACENAME " + albumArrayList.get(i).getplace().get(1).getlocation_place() + " = LOCATIONPLACE ");
	        		}
	        	}
	        }
	        //SpringApplication.run(SpringBootAppApplication.class, args);
		}
}

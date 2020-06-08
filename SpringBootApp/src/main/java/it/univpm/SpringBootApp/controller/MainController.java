package it.univpm.SpringBootApp.controller;

import java.util.ArrayList;
import java.util.Map;
import java.lang.reflect.Field; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import it.univpm.SpringBootApp.model.*;

@RestController
public class MainController {
	
	/**
     * Collegamento con Database
     */
	@Autowired
	private Database AlbumS;
	/**
     * Rotta che restituisce tutti i metadati
     */
	@GetMapping("/metadata")
	public ArrayList<Metadata> getMetaData() {
		return AlbumS.getarrMetadata();
	}
	
	/**
     * Rotta che restituisce tutti i dati
     */
	@GetMapping("/data")
	public ArrayList<Data> getData() {
		return AlbumS.getarrData();
	}
	
	@GetMapping("/statistiche")
    public ArrayList<Map> getStatistiche(@RequestParam(value = "field", defaultValue = "") String fieldName) throws Exception {
    	Field[] fields = Data.class.getDeclaredFields();
    	ArrayList<Map> lista = new ArrayList<>();
    	if(fieldName.equals("")) // se non viene specificato nulla, calcolerà le statistiche di ogni attributo
    	{  
    		for(int i=0; i < fields.length; i++) {
    			lista.add(AlbumS.getStatistiche(fields[i].getName()));		
    		}
    		return lista;
    	}
    	else {  											// altrimenti calcolerà le statistiche del solo attributo richiesto
    		lista.add(AlbumS.getStatistiche(fieldName));
    		return lista;
    	}
	}

	

}
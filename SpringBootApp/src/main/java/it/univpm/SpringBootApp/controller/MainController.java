package it.univpm.SpringBootApp.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.SpringBootApp.model.*;
import it.univpm.SpringBootApp.service.CommandNotFoundException;
import it.univpm.SpringBootApp.service.ParserField;
import it.univpm.SpringBootApp.utils.StatNum;
import it.univpm.SpringBootApp.utils.Utilities;

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
	
	@RequestMapping(value = "/stats/number", method = RequestMethod.GET, produces = "application/json")
    HashMap<String, StatNum> showStatsNumber(@RequestParam String field) throws NoSuchMethodException{
        HashMap<String, StatNum> hashMap = new HashMap<>();
        hashMap.put(field, new StatNum(AlbumS.arrData,field));
        if(hashMap.get(field).getSum() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Field not correct");
        }
        return hashMap;
    }
	

}
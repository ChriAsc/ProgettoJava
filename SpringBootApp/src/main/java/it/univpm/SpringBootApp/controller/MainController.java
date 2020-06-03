package it.univpm.SpringBootApp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
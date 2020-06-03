package it.univpm.SpringBootApp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import it.univpm.SpringBootApp.model.*;

@RestController
public class MainController {
	
	@Autowired
	private Database AlbumS;

	@GetMapping("/metadata")
	public ArrayList<Metadata> getMetaData() {
		return AlbumS.getarrMetadata();
	}
	
	@GetMapping("/data")
	public ArrayList<Data> getData() {
		return AlbumS.getarrData();
	}
	
}
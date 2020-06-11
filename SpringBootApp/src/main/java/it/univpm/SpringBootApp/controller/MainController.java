package it.univpm.SpringBootApp.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.reflect.Field; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import it.univpm.SpringBootApp.model.*;
import it.univpm.SpringBootApp.utils.StatBase;
import it.univpm.SpringBootApp.utils.StatNum;

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
	
	/**
     * Rotta che restituisce le statistiche in base al campo inserito
     */
	@GetMapping("/statistiche")
    public ArrayList<Map> getStatistiche(@RequestParam(value = "field", defaultValue = "") String fieldName) throws Exception {
    	Field[] fields = Data.class.getDeclaredFields();
    	StatBase sb = new StatBase();
    	ArrayList<Map> list = new ArrayList<>();
    	if(fieldName.equals("")) 
    	{  
    		for(int i=0; i < fields.length; i++) {
    			list.add(sb.getStatistiche(fields[i].getName()));		
    		}
    		return list;
    	}
    	else {  											
    		list.add(sb.getStatistiche(fieldName));
    		return list;
    	}
	}
	
	@GetMapping("/statdate")
	public Map<String, Object> getStatDataY(){
		ArrayList<Number> listYear = new ArrayList<Number>();
		ArrayList<Number> listMonth = new ArrayList<Number>();
		ArrayList<Number> listDay = new ArrayList<Number>();
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listYear.add(AlbumS.arrData.get(i).getcreated_time().getYear()+1900);
		}
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listMonth.add(AlbumS.arrData.get(i).getcreated_time().getMonth());
		}
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listDay.add(AlbumS.arrData.get(i).getcreated_time().getDay());
		}		
		StatNum statY = new StatNum();
		StatNum statM = new StatNum();
		StatNum statD = new StatNum();
		Map<String, Object> YMD = new LinkedHashMap<>();
		YMD.put("campY", "Year");
		for (Map.Entry<String, Object> entry : statY.NumStatData(listYear).entrySet()) {
			YMD.put(entry.getKey()+"Y",entry.getValue());
		}
		YMD.put("campM", "Mouth");
		for (Map.Entry<String, Object> entry : statM.NumStatData(listMonth).entrySet()) {
			YMD.put(entry.getKey()+"M",entry.getValue());
		}
		YMD.put("campD", "Day");
		for (Map.Entry<String, Object> entry : statD.NumStatData(listDay).entrySet()) {
			YMD.put(entry.getKey()+"D",entry.getValue());
		}
		return YMD;
		}
	
	@GetMapping("/statisto")
	public Map<String, Object> getIsto(@RequestParam(value = "field", defaultValue = "") String fieldName) throws Exception {
		ArrayList<Number> listIsto = new ArrayList<Number>();
		StatNum statIsto = new StatNum();
		if(fieldName.equals("year")) {
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listIsto.add(AlbumS.arrData.get(i).getcreated_time().getYear());
		}
		return statIsto.StatIstoYear(listIsto);
		}
		else if(fieldName.equals("month")) {
			for(int i=0; i < AlbumS.arrData.size(); i++) {
				listIsto.add(AlbumS.arrData.get(i).getcreated_time().getMonth());
			}
			return statIsto.StatIstoMonth(listIsto);
		}
			else if(fieldName.equals("day")) {
				for(int i=0; i < AlbumS.arrData.size(); i++) {
					listIsto.add(AlbumS.arrData.get(i).getcreated_time().getDay());
				}
				return statIsto.StatIstoDay(listIsto);
			}
		return null;
		
	}
	
	

	

}
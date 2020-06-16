package it.univpm.SpringBootApp.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.reflect.Field;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.SpringBootApp.model.*;
import it.univpm.SpringBootApp.service.ParserOperator;
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
	 * @return ArrayList dei Metadati
	 */
	@GetMapping("/metadata")
	public ArrayList<Metadata> getMetaData() {
		return AlbumS.getarrMetadata();
	}
	
	/**
	 * Rotta che restituisce tutti i dati
	 * @return ArrayList di tutti i dati
	 */
	@GetMapping("/data")
	public ArrayList<Data> getData() {
		return AlbumS.getarrData();
	}
	
	/**
	 * Rotta che restituisce le statistiche in base al campo inserito
	 * @param fieldName Campo inserito
	 * @return ArrayList di Map con tutte le statistiche inerenti al campo inserito
	 * @throws Exception
	 */
	@GetMapping("/statistiche")
    public ArrayList<Map> getStatistics(@RequestParam(value = "field", defaultValue = "") String fieldName) throws Exception {
    	Field[] fields = Data.class.getDeclaredFields();
    	StatBase sb = new StatBase();
    	ArrayList<Map> list = new ArrayList<>();
    	if(fieldName.equals("place") || fieldName.equals("location")) 
    	{  
    		Map<String, Object> err = new LinkedHashMap<>();
    		err.put("Error", "A statistic to this field (Place or Location) cannot be requested.");
    		list.add(err);
    		return list;
    	}
    	
    	else {
    		if(fieldName.equals("created_time")|| fieldName.equals("updated_time")) {
    			Map<String, Object> err = new LinkedHashMap<>();
        		err.put("!Warning!", "Please use route '/statdate' to request statistic about created_time or updated_time!");
        		list.add(err);
    		}
    		list.add(sb.getStatistics(fieldName));
    		return list;
    	}
	}
	
	/**
	 * Rotta che restituisce le statistiche riguardanti anno, mese e giorno in base al campo inserito
	 * @return Mappa delle statistiche annuali, mensili, giornaliere
	 */
	@GetMapping("/statdate")
	public Map<String, Object> getStatDate(){
		ArrayList<Number> listYearC = new ArrayList<Number>();
		ArrayList<Number> listMonthC = new ArrayList<Number>();
		ArrayList<Number> listDayC = new ArrayList<Number>();
		ArrayList<Number> listYearU = new ArrayList<Number>();
		ArrayList<Number> listMonthU = new ArrayList<Number>();
		ArrayList<Number> listDayU = new ArrayList<Number>();
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listYearC.add(AlbumS.arrData.get(i).getcreated_time().getYear()+1900);
		}
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listMonthC.add(AlbumS.arrData.get(i).getcreated_time().getMonth()+1);
		}
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listDayC.add(AlbumS.arrData.get(i).getcreated_time().getDate());
		}
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listYearU.add(AlbumS.arrData.get(i).getupdated_time().getYear()+1900);
		}
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listMonthU.add(AlbumS.arrData.get(i).getupdated_time().getMonth()+1);
		}
		for(int i=0; i < AlbumS.arrData.size(); i++) {
			listDayU.add(AlbumS.arrData.get(i).getupdated_time().getDate());
		}	
		StatNum statYC = new StatNum();
		StatNum statMC = new StatNum();
		StatNum statDC = new StatNum();
		StatNum statYU = new StatNum();
		StatNum statMU = new StatNum();
		StatNum statDU = new StatNum();
		Map<String, Object> YMD = new LinkedHashMap<>();
		YMD.put("fieldC", "CREATED_TIME!");
		YMD.put("campYC", "Year");
		for (Map.Entry<String, Object> entry : statYC.NumStatDate(listYearC).entrySet()) {
			YMD.put(entry.getKey()+"YC",entry.getValue());
		}
		YMD.put("campMC", "Month");
		for (Map.Entry<String, Object> entry : statMC.NumStatDate(listMonthC).entrySet()) {
			YMD.put(entry.getKey()+"MC",entry.getValue());
		}
		YMD.put("campDC", "Day");
		for (Map.Entry<String, Object> entry : statDC.NumStatDate(listDayC).entrySet()) {
			YMD.put(entry.getKey()+"DC",entry.getValue());
		}
		YMD.put("fieldU", "UPDATED_TIME!");
		YMD.put("campYU", "Year");
		for (Map.Entry<String, Object> entry : statYU.NumStatDate(listYearU).entrySet()) {
			YMD.put(entry.getKey()+"YU",entry.getValue());
		}
		YMD.put("campMU", "Month");
		for (Map.Entry<String, Object> entry : statMU.NumStatDate(listMonthU).entrySet()) {
			YMD.put(entry.getKey()+"MU",entry.getValue());
		}
		YMD.put("campDU", "Day");
		for (Map.Entry<String, Object> entry : statDU.NumStatDate(listDayU).entrySet()) {
			YMD.put(entry.getKey()+"DU",entry.getValue());
		}
		return YMD;
		}
	
	/**
	 * Rotta che restituisce il numero di album pubblicati in base annuale, mensile o giornaliera a seconda del campo specificato
	 * @param fieldName Campo inserito
	 * @return Mappa con il numero di album pubblicati secondo il campo inserito
	 * @throws Exception
	 */
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
	
	/**
	 * Rotta che restituisce dati filtrati
	 * @param param Filtro passato
	 * @return ArrayList dei dati filtrati
	 */
	@PostMapping(value = "/filter")
	public ArrayList<Data> filtering (@RequestBody (required = true) String param) {
		try {
			JSONObject json = null;
			ArrayList<Data> out = new ArrayList<Data>();
			if (param != null) {
				try {
					json = new JSONObject(param);
					ParserOperator p = new ParserOperator(AlbumS, json);
					out = p.parseFilter(AlbumS, json);
				} catch(Exception e) { }
			}
			
			return out;
		} catch (Exception e) {
			e.printStackTrace();
			}
		return null;
	}
	
}
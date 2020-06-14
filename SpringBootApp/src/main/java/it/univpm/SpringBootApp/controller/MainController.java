package it.univpm.SpringBootApp.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import it.univpm.SpringBootApp.model.*;
import it.univpm.SpringBootApp.utils.FilterCheck;
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
    public ArrayList<Map> getStatistics(@RequestParam(value = "field", defaultValue = "") String fieldName) throws Exception {
    	Field[] fields = Data.class.getDeclaredFields();
    	StatBase sb = new StatBase();
    	ArrayList<Map> list = new ArrayList<>();
    	if(fieldName.equals("place") || fieldName.equals("location")) 
    	{  
    		Map<String, Object> err = new LinkedHashMap<>();
    		err.put("Error", "It's not possible to apply a statistic to this field! (Place or Location)");
    		list.add(err);
    		return list;
    	}
    	
    	else {
    		if(fieldName.equals("created_time")|| fieldName.equals("updated_time")) {
    			Map<String, Object> err = new LinkedHashMap<>();
        		err.put("!Attention!", "route '/statdate' is better for fields like created_time or updated_time!");
        		list.add(err);
    		}
    		list.add(sb.getStatistics(fieldName));
    		return list;
    	}
	}
	
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
	
	@PostMapping(value = "/filter")
	public ArrayList<Data> filtering (@RequestBody (required = true) String param) {
		try {
			JSONObject json = null;
			ArrayList<Data> out = new ArrayList<Data>();
			if (param != null) {
				try {
					json = new JSONObject(param);
					out = parseFilter(AlbumS, json);
				} catch(Exception e) { }
			}
			
			return out;
		} catch (Exception e) { }
		return null;
	}
	
	public ArrayList<Data> parseFilter(Database database, JSONObject jsonObj) {
		String op = jsonObj.keys().next();
		if (op.equalsIgnoreCase("$and")){
			FilterCheck<Data> f = new FilterCheck<Data>();
			JSONArray jsonArray = jsonObj.getJSONArray(op);
			ArrayList<ArrayList<Data>> c = new ArrayList<>();
			for (Object cc : jsonArray) {
				if (cc instanceof JSONObject) {
					c.add(parseFilter(database, (JSONObject) cc));
				}
			}
			return f.And(c);
		}
		else if (op.equalsIgnoreCase("$or")) {
			FilterCheck<Data> f = new FilterCheck<Data>();
			JSONArray jsonArray = jsonObj.getJSONArray(op);
			ArrayList<ArrayList<Data>> c = new ArrayList<>();
			for (Object cc : jsonArray) {
				if (cc instanceof JSONObject) {
					c.add(parseFilter(database, (JSONObject) cc));
				}
			}
			return f.Or(c);
		} else {
			JSONObject innerObj = jsonObj.getJSONObject(op);
        String operator = innerObj.keys().next();
        if(operator.equalsIgnoreCase("$bt")) {
        	double min = innerObj.getJSONArray(operator).getDouble(0);
            double max = innerObj.getJSONArray(operator).getDouble(1);
            try {
				return database.filterField(op, operator, min, max);
			} catch (SecurityException | IllegalArgumentException e) {
				e.printStackTrace();
			}
        } else if (operator.equalsIgnoreCase("$in") || operator.equalsIgnoreCase("$nin")) {
        	ArrayList<Object> v = new ArrayList<>();
            for(Object el : innerObj.getJSONArray(operator)) {
                v.add(el);
            }
            try {
				return database.filterField(op, operator, v.toArray());
			} catch (SecurityException | IllegalArgumentException e) {
				e.printStackTrace();
			}
        } else if (operator.equalsIgnoreCase("$gt") || operator.equalsIgnoreCase("$gte") || operator.equalsIgnoreCase("lt") || operator.equalsIgnoreCase("$lte") || operator.equalsIgnoreCase("$eq") || operator.equalsIgnoreCase("$not")) {
        	try {
        		Object v = innerObj.get(operator);
					return database.filterField(op, operator, v);
				} catch (SecurityException |  IllegalArgumentException e1) {
					e1.printStackTrace();
				}
            }
        }
		return null;
		
}

	
}
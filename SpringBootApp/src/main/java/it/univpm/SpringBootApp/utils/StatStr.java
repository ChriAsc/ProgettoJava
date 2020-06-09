package it.univpm.SpringBootApp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatStr extends StatBase{
	
	/**
	 * Costruttore base di StatStr
	 */
	public StatStr(){
		
	}
	/**
	 * Metodo che preso in ingresso l'arraylist lista ritorna le statistiche riguardanti stringhe
	 * @param list
	 * @param field
	 * @return maps
	 */
	public Map<String, Object> StrStat(String field, ArrayList<Object> list) {
		StatNum sn = new StatNum();
		Map<String, Object> maps = new HashMap<>();  
		sn.setCount(list);
		maps.put("field", field);
		maps.put("count", sn.getCount());
		maps.put("elementi unici", getUniqueElement(list));
	    return maps;
	}
	
	
    
}

package it.univpm.SpringBootApp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Sottoclasse per calcolo statistiche di tipo string
 * @author Cingolani Cristian & Ascani Christian
 */
public class StatStr extends StatBase{
	
	/**
	 * Costruttore base di StatStr
	 */
	public StatStr(){
		
	}
	/**
	 * Metodo che prende in ingresso l'arraylist list ed il campo field  
	 * restituisce una mappa con tutte le statistiche di tipo stringa
	 * @param list arraylist di oggetti
	 * @param field campo su cui eseguire statistiche riguardanti stringhe
	 * @return map mappa restituita contenente un campo dei vari album con le ricorsioni
	 */
	public Map<String, Object> StrStat(String field, ArrayList<Object> list) {
		StatNum sn = new StatNum();
		Map<String, Object> map = new HashMap<>();  
		sn.setCount(list);
		map.put("field", field);
		map.put("count", sn.getCount());
		map.put("elements", getElem(list));
	    return map;
	}
	
	
    
}

package it.univpm.SpringBootApp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatStr extends StatBase{
public StatStr(){
		
	}
	/**
	 * Metodo che restituisce una mappa nella quale vengono visualizzate tutte le statistiche non numeriche di una lista 
	 * 
	 * @param lista,  lista che fornisce i valori con i quali si possono calcolare tutte le statistiche non numeriche
	 * @return map che contiene come chiavi il nome della statistica e come valore quello calcolato tramite i metodi della classe
	 */
	public Map<String, Object> StrStat(String campo, ArrayList<Object> lista) {
		StatNum sn = new StatNum();
		Map<String, Object> maps = new HashMap<>();  //crea una mappa che contiene le chiavi e i valori delle statistiche non numeriche
		sn.setCount(lista);
		maps.put("field", campo);
		maps.put("count", sn.getCount());
		maps.put("elementi unici", getUniqueElement(lista));
	    return maps;
	}
	
	
    
}

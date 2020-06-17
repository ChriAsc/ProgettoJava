package it.univpm.SpringBootApp.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.univpm.SpringBootApp.exceptions.InvalidFieldException;
import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.model.Database;

/**
 * Classe che contiene metodi utili 
 * per il calcolo delle varie statistiche
 * @author Cingolani Cristian & Ascani Christian
 */
public class StatBase {

	/**
	 * Costruttore base di StatBase
	 */
    public StatBase(){
		
	}

	/**
	 * Questo metodo prende in ingresso il nome del campo field passato
	 * verifica se la mappa viene riempita
	 * ed in caso contrario ritorna un messaggio di errore
	 * @param field campo su cui eseguire la statistica 
	 * @return map mappa riempita con dati filtrati
	 * @throws NoSuchMethodException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws InvalidFieldException 
	 */
    
	public static Map<String, Object> getStatistics(String field) throws NoSuchMethodException, IOException, ParseException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InvalidFieldException 
	{
		Database db = new Database();
		Map<String, Object> map = new HashMap<>();
		
		Field[] fields = Data.class.getDeclaredFields();		
		for (Field f : fields) 
		{
			if(field.equalsIgnoreCase(f.getName())) 
				map = getStat(field, fieldValues(field, db.getarrData()));
		}
		if(map.isEmpty()) throw new InvalidFieldException("A statistic to this field cannot be requested.");
		else 
			return map;
	}
	
	/**
	 * Questo metodo prende in ingresso il campo fieldName passato
	 * verifica che questo campo rientri tra quelli possibili
	 * e lo aggiunge all'arraylist values
	 * @param field campo su cui eseguire la statistica 
	 * @param list arraylist di data usato per verificare che il campo field rientri tra quelli possibili 
	 * @return val ArrayList di oggetti ritornati dopo la verifica
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static ArrayList<Object> fieldValues(String field, ArrayList<Data> list) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<Object> val = new ArrayList<>();
			Field[] fields = Data.class.getDeclaredFields();
			for(Object e : list) { 
				for(int i=0; i < fields.length; i++) {
					if(field.equalsIgnoreCase(fields[i].getName())) {
						Method m = e.getClass().getMethod("get"+fields[i].getName());
						Object specificval = m.invoke(e);
						val.add(specificval); 
					}
				}
			}	
		return val;
	}
	
	/**
	 * Metodo che serve a visualizzare il tipo di statistiche in base al campo specificato
	 * @param field campo su cui eseguire la statistica 
	 * @param list arraylist di oggetti 
	 * @return map mappa con statistiche di tipo Stringa o Numerico
	 */
    public static Map<String, Object> getStat(String field, ArrayList<Object> list) {
		Map<String, Object> map = new HashMap<>();
		StatNum statN = new StatNum();
		StatStr statS = new StatStr();
		if(!list.isEmpty()) {
			if (list.get(0) instanceof Number) { 
				ArrayList<Number> numList = new ArrayList<>();
				for (Object elem : list) {
					numList.add(((Number) elem));
				}
				map = statN.NumStat(field, numList);
			} else {
				map = statS.StrStat(field, list);
			}
		}
		return map;
	}
    
    /**
	 * Questo metodo che conta le volte in cui i valori di lista si ripetono
	 * @param list arraylist di oggetti 
	 * @return map mappa contenente il campo specifico di ciascun album con numero ricorsioni
	 */
	public Map<Object, Integer> getElem(ArrayList<Object> list) {
		Map<Object,Integer> map = new HashMap<>();  
		for(Object obj : list) { 
			if(map.containsKey(obj))  
				map.replace(obj, map.get(obj) + 1); 
			else
				map.put(obj, 1);  
		}
		return map;
	}
}

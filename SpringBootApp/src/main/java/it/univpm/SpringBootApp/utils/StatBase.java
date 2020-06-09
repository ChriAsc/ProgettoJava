package it.univpm.SpringBootApp.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	 * @param nomeCampo
	 * @return mappa 
	 * @throws NoSuchMethodException 
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public Map<String, Object> getStatistiche(String nomeCampo) throws NoSuchMethodException, IOException, ParseException 
	{
		Database db = new Database();
		Map<String, Object> mappa = new HashMap<>();
		Map<String, Object> Errore = new HashMap<>();
		Errore.put("Error", "It's not possible to apply a statistic to this field!");
		Field[] fields = Data.class.getDeclaredFields();		
		for (Field f : fields) 
		{
			if(nomeCampo.equalsIgnoreCase(f.getName())) 
				mappa = getStat(nomeCampo, fieldValues(nomeCampo, db.getarrData()));
		}
		if(mappa.isEmpty())
			return Errore;
		else 
			return mappa;
	}
	
	/**
	 * Questo metodo prende in ingresso il campo fieldName passato
	 * verifica che questo campo rientri tra quelli possibili
	 * e lo aggiunge all'arraylist values
	 * @param fieldName 
	 * @param list 
	 * @return values
	 */
	public ArrayList<Object> fieldValues(String fieldName, ArrayList<Data> list) {
		ArrayList<Object> values = new ArrayList<>();
		try {
			Field[] fields = Data.class.getDeclaredFields();
			for(Object e : list) { 
				for(int i=0; i < fields.length; i++) {
					if(fieldName.equalsIgnoreCase(fields[i].getName())) {
						Method m = e.getClass().getMethod("get"+fields[i].getName());
						Object val = m.invoke(e);
						values.add(val); 
					}
				}
			}
		} catch(NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch(SecurityException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		return values;
	}
	
	/**
	 * Metodo che serve a visualizzare il tipo di statistiche in base al campo specificato
	 * 
	 * @param campo contiene il nome dell'attributo del quale si vogliono si vogliono calcolare le statistiche 
	 * @param lista contiene la lista dei valori utili per il calcolo delle statistiche
	 * @return maps, mappa delle statistiche
	 */
    public Map<String, Object> getStat(String campo, ArrayList<Object> lista) {
		Map<String, Object> maps = new HashMap<>();
		StatNum statN = new StatNum();
		StatStr statS = new StatStr();
		if(!lista.isEmpty()) {
			if (lista.get(0) instanceof Number) { 
				ArrayList<Number> numLista = new ArrayList<>();
				for (Object elem : lista) {
					numLista.add(((Number) elem));
				}
				maps = statN.NumStat(campo, numLista);
			} else {
				maps = statS.StrStat(campo, lista);
			}
		}
		return maps;
	}
    
    /**
	 * Questo metodo che conta le volte in cui i valori di lista si ripetono
	 * @param lista 
	 * @return mappa
	 */
	public static Map<Object, Integer> getUniqueElement(ArrayList<Object> lista) {
		Map<Object,Integer> mappa = new HashMap<>();  
		for(Object obj : lista) { 
			if(mappa.containsKey(obj))  
				mappa.replace(obj, mappa.get(obj) + 1); 
			else
				mappa.put(obj, 1);  
		}
		return mappa;
	}
}

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

public class StatBase {

public StatBase(){
		
	}
	/**
	 * Questo metodo gestisce le statistiche, verificando se l'attributo passato è di tipo numerico o string
	 * verifica se l'attributo è presente
	 * grazie all'uso del metodo equalsIgnoreCase è possibile inserire nella richiesta dell'attributo 
	 * sia lettere maiscole che minuscole
	 * senza creare errori riguardanti il Case
	 * @param nomeCampo
	 * @return mappa delle statistiche 
	 * @throws NoSuchMethodException 
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public Map<String, Object> getStatistiche(String nomeCampo) throws NoSuchMethodException, IOException, ParseException 
	{
		//StatNum statistica = new StatNum();
		Database db = new Database();
		Map<String, Object> mappa = new HashMap<>();
		Map<String, Object> Errore = new HashMap<>();
		Errore.put("WARNING", "NON VI SONO STATISTICHE SULL'ATTRIBUTO INSERITO");
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
	 * Questo metodo estrae i valori di un determinato campo, passato tramite fieldName 
	 * verifica se è presente nel vettore dei campi e se risulta TRUE aggiunge il valore dell'oggetto della lista
	 * @param fieldName nome del campo del file JSON
	 * @param list lista che si ottiene dopo aver effettuato il parsing, array di oggetti Data
	 * @return la lista che contiene i valori di un determinato campo
	 */
	public ArrayList<Object> fieldValues(String fieldName, ArrayList<Data> list) {
		ArrayList<Object> values = new ArrayList<>();
		try {
			Field[] fields = Data.class.getDeclaredFields();
			for(Object e : list) {
				// controlla se è presente l'attributo  
				for(int i=0; i < fields.length; i++) {
					if(fieldName.equalsIgnoreCase(fields[i].getName())) {
						Method m = e.getClass().getMethod("get"+fields[i].getName());
						Object val = m.invoke(e);
						values.add(val); //lo aggiunge alla lista
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
		StatNum statistica = new StatNum();
		StatStr statisticaS = new StatStr();
		if(!lista.isEmpty()) {
			 // se il primo valore e' un numero crea una lista di numeri e gli passa i valori della lista castati a Number
			if (lista.get(0) instanceof Number) { 
				ArrayList<Number> numLista = new ArrayList<>();
				for (Object elem : lista) {
					numLista.add(((Number) elem));
				}
				maps = statistica.NumStat(campo, numLista); // calcola le statistiche numeriche
			} else {
				maps = statisticaS.StrStat(campo, lista);
			}
		}
		return maps;
	}
    
    /**
	 * Questo metodo conta le occorrenze di un elemento all'interno di una lista
	 * 
	 * @param lista contiene i valori per i quali si vogliono calcolare le occorrenze
	 * @return restituisce una map chiave-valore dove le chiavi sono gli elementi della lista e i valori le corrispondenti occorrenze
	 */
	public static Map<Object, Integer> getUniqueElement(ArrayList<Object> lista) {
		Map<Object,Integer> mappa = new HashMap<>();  //creazione della mappa
		for(Object obj : lista) {  //scorre la lista
			if(mappa.containsKey(obj))  //controlla se la chiave esiste giï¿½
				mappa.replace(obj, mappa.get(obj) + 1);  //se esiste aumenta il suo valore di 1
			else
				mappa.put(obj, 1);  //se non esiste la crea e le assegna il valore 1
		}
		return mappa;
	}
}

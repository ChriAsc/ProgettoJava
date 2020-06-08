package it.univpm.SpringBootApp.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univpm.SpringBootApp.model.*;

/**
 * Sottoclasse per calcolo statistiche di tipo numerico
 * @author Cingolani Cristian & Ascani Christian
 */

public class StatNum{
	
	public StatNum() {
		
	}
	
    /**
     * Metodo che calcola e imposta il valore di sum
     * @param values
     */
    private static double setSum(ArrayList<Number> values) {
        double sum = 0;
    	for (Number v : values){
            sum += v.doubleValue();
        }
        return sum;
    }
    
    /**
     * Metodo che imposta il valore di count
     * @param count
     */
       
    public static int setCount(ArrayList count) {
        return count.size();
    }
    
    /**
     * Metodo che calcola e imposta il valore di avg
     * @param avg
     */
    
    private static double setAvg(ArrayList<Number> values) {
        double avg = 0;
        avg = setSum(values)/setCount(values);
    	return avg;
    }
    
    /**
     * Metodo che calcola e imposta il valore di can_upload
     * @param values
     */

    private static double setMin(ArrayList<Number> values) {
    	double min = values.get(0).doubleValue();
    	for(Number n : values) {
    		if(n.doubleValue() < min) {
                min = n.doubleValue();
            }
        }
    	return min;
    }
    
    /**
     * Metodo che calcola e imposta il valore di max
     * @param max
     */

    private static double setMax(ArrayList<Number> values) {
        double max = values.get(0).doubleValue();
        for(Number n : values) {
    		if(n.doubleValue() > max) {
                max = n.doubleValue();
            }
        }

    	return max;
    }
    
    /**
     * Metodo che calcola e imposta il valore di dev
     * @param dev
     */

    private static double setDev(ArrayList<Number> values) {
    	double s=0;
    	for(Number numero : values) {
    		s += Math.pow(numero.doubleValue() - setAvg(values), 2);
    	}
        s=((double) Math.pow(s/setCount(values), 0.5));
        return s;
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
	
	/**
	 * Metodo che restituisce una mappa nella quale vengono visualizzate tutte le statistiche non numeriche di una lista 
	 * 
	 * @param lista,  lista che fornisce i valori con i quali si possono calcolare tutte le statistiche non numeriche
	 * @return map che contiene come chiavi il nome della statistica e come valore quello calcolato tramite i metodi della classe
	 */
	public static Map<String, Object> StrStat(String campo, ArrayList<Object> lista) {
		Map<String, Object> maps = new HashMap<>();  //crea una mappa che contiene le chiavi e i valori delle statistiche non numeriche
		maps.put("field", campo);
		maps.put("count", setCount(lista));
		maps.put("elementi unici", getUniqueElement(lista));
	    return maps;
	}
    
    /**
     * Metodo che restituisce una mappa nella quale vengono visualizzate tutte le statistiche numeriche di una lista 
     * 
     * @param numLista  lista che fornisce i valori con i quali si possono calcolare tutte le statistiche
     * @return map che contiene come chiavi il nome della statistica e come valore quello calcolato tramite i metodi della classe
     */
    public static Map<String, Object> NumStat(String campo, ArrayList<Number> numLista) {
    	Map<String, Object> maps = new HashMap<>();  //crea una mappa che contiene le chiavi e i valori delle statistiche numeriche
    	maps.put("field", campo);
    	maps.put("count", setCount(numLista));
    	maps.put("sum", setSum(numLista));
    	maps.put("avg", setAvg(numLista));
        maps.put("min", setMin(numLista));
        maps.put("max", setMax(numLista));
        maps.put("dev", setDev(numLista));
        return maps;
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
		if(!lista.isEmpty()) {
			 // se il primo valore e' un numero crea una lista di numeri e gli passa i valori della lista castati a Number
			if (lista.get(0) instanceof Number) { 
				ArrayList<Number> numLista = new ArrayList<>();
				for (Object elem : lista) {
					numLista.add(((Number) elem));
				}
				maps = NumStat(campo, numLista); // calcola le statistiche numeriche
			} else {
				maps = StrStat(campo, lista);
			}
		}
		return maps;
	}

}

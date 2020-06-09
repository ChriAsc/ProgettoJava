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
	
	private double sum;
	private int count;
	private double avg;
    private double min;
    private double max;
    private double dev;
    
	
	public StatNum() {
		
	}
	
	/**
     * Metodo che restituisce sum
     * @return sum
     */
    
    public double getSum() {
        return sum;
    }

    /**
     * Metodo che restituisce count
     * @return count
     */
    
    public int getCount() {
        return count;
    }
    
    /**
     * Metodo che restituisce avg
     * @return avg
     */
    
    public double getAvg() {
        return avg;
    }
    
    /**
     * Metodo che restituisce min
     * @return min
     */
    
    public double getMin() {
        return min;
    }
    
    /**
     * Metodo che restituisce max
     * @return max
     */
    
    public double getMax() {
        return max;
    }
    
    /**
     * Metodo che restituisce dev
     * @return dev
     */
    
    public double getDev() {
        return dev;
    }    
	
    /**
     * Metodo che calcola e imposta il valore di sum
     * @param values
     */
    private void setSum(ArrayList<Number> values) {
    	for (Number v : values){
            sum += v.doubleValue();
        }
    }    
    
    /**
     * Metodo che calcola e imposta il valore di count
     * @param values
     */
    private void setCount(ArrayList values) {
    	count= values.size();
    }
      
    
    /**
     * Metodo che calcola e imposta il valore di avg
     * @param avg
     */
    
    private void setAvg(ArrayList<Number> values) {
        avg = getSum()/getCount();
    }
    
    /**
     * Metodo che calcola e imposta il valore di min
     * @param values
     */

    private void setMin(ArrayList<Number> values) {
    	min = values.get(0).doubleValue();
    	for(Number n : values) {
    		if(n.doubleValue() < min) {
                min = n.doubleValue();
            }
        }
    }
    
    /**
     * Metodo che calcola e imposta il valore di max
     * @param max
     */

    private void setMax(ArrayList<Number> values) {
        max = values.get(0).doubleValue();
        for(Number n : values) {
    		if(n.doubleValue() > max) {
                max = n.doubleValue();
            }
        }
    }
    
    /**
     * Metodo che calcola e imposta il valore di dev
     * @param dev
     */

    private void setDev(ArrayList<Number> values) {
    	dev = 0;
    	for(Number numero : values) {
    		dev += Math.pow(numero.doubleValue() - getAvg(), 2);
    	}
        dev=((double) Math.pow(dev/getCount(), 0.5));
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
	public Map<String, Object> StrStat(String campo, ArrayList<Object> lista) {
		Map<String, Object> maps = new HashMap<>();  //crea una mappa che contiene le chiavi e i valori delle statistiche non numeriche
		setCount(lista);
		maps.put("field", campo);
		maps.put("count", getCount());
		maps.put("elementi unici", getUniqueElement(lista));
	    return maps;
	}
    
    /**
     * Metodo che restituisce una mappa nella quale vengono visualizzate tutte le statistiche numeriche di una lista 
     * 
     * @param numLista  lista che fornisce i valori con i quali si possono calcolare tutte le statistiche
     * @return map che contiene come chiavi il nome della statistica e come valore quello calcolato tramite i metodi della classe
     */
    public Map<String, Object> NumStat(String campo, ArrayList<Number> numLista) {
    	Map<String, Object> maps = new HashMap<>();  //crea una mappa che contiene le chiavi e i valori delle statistiche numeriche
    	setCount(numLista);
    	setSum(numLista);
    	setAvg(numLista);
    	setMin(numLista);
    	setMax(numLista);
    	setDev(numLista);
    	maps.put("field", campo);
    	maps.put("count", getCount());
    	maps.put("sum", getSum());
    	maps.put("avg", getAvg());
        maps.put("min", getMin());
        maps.put("max", getMax());
        maps.put("dev", getDev());
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
    
    public Map<String, Object> NumStatData(ArrayList<Number> numLista) {
    	Map<String, Object> maps = new HashMap<>();  //crea una mappa che contiene le chiavi e i valori delle statistiche numeriche
    	setCount(numLista);
    	setSum(numLista);
    	setAvg(numLista);
    	setMin(numLista);
    	setMax(numLista);
    	setDev(numLista);
    	maps.put("count", getCount());
    	maps.put("sum", getSum());
    	maps.put("avg", getAvg());
        maps.put("min", getMin());
        maps.put("max", getMax());
        maps.put("dev", getDev());
        return maps;
    }

}

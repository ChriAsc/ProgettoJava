package it.univpm.SpringBootApp.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe che permette di filtrare tramite parametri specifici
 * @param <Data> Tipo specifico Data, ovvero quello dell'album
 * @author Cristian Cingolani & Christian Ascani
 * 
 */
public class Filter<Data> {
	
	/**
     * Metodo che filtra l'intera collection di Data su un campo, con una definita condizione e 
     * @param source Collection intera da filtrare
     * @param fieldName Campo su cui il filtro opera
     * @param operator Operatore
     * @param value Oggetti che definiscono propriamente il filtro
     * @return Collection filtrata
     */
	public Collection<Data> filter(Collection<Data> source, String fieldName, String operator, Object... value) {
		Collection<Data> Albums = new ArrayList<>();
		
		for(Data album : source) {
			
			try {
				Method m = album.getClass().getMethod("get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1), null);
				
				try {
					Object temp = m.invoke(album);
					if(FilterCheck.check(temp, operator, value))
						Albums.add(album);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}					
		}
		
		return Albums;
	}
	
	/**
     * Metodo utilizzato per implementare la logica AND qualora venga richiesta nel filtraggio
     * @param data Collection di Data
     * @return Restituisce la collection
     */
    public ArrayList<Data> And (ArrayList<ArrayList<Data>> data) {
    	
        Collection<Data> coll = new ArrayList<>();
        for(int i = 0; i < data.size(); i++) {
        	
            for(Data d : data.get(i)){
                boolean included = true;
                
                for(ArrayList<Data> dataToCompare : data) {
                    if(!dataToCompare.contains(d)) {
                        included = false;
                        break;
                    }
                }
                
                if(included && !coll.contains(d))
                    coll.add(d);
            }
        }
        return (ArrayList<Data>) coll;
    }
    
    /**
     * Metodo utilizzato per implementare la logica OR qualora venga richiesta nel filtraggio
     * @param data Collection di Data
     * @return Restituisce l'insieme
     */
    public ArrayList<Data> Or (ArrayList<ArrayList<Data>> data) {
        Set<Data> set = new HashSet<Data>();

        for (ArrayList<Data> d : data)
            set.addAll(d);

        return new ArrayList<Data>(set);
    }
}
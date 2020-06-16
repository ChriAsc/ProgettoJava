package it.univpm.SpringBootApp.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import it.univpm.SpringBootApp.model.Data;

/**
 * Classe che permette di filtrare tramite parametri specifici
 * @param <Data> Tipo specifico Data, ovvero quello dell'album
 * @author Cristian Cingolani & Christian Ascani
 */
public class Filter<T> {
	
	/**
     * Metodo che filtra l'intera collection di Data su un campo, con una definita condizione e operatore
     * @param source Collection intera da filtrare
     * @param fieldName Campo su cui il filtro opera
     * @param operator Operatore
     * @param value Oggetti che definiscono propriamente il filtro
     * @return Collection filtrata
     */
	public Collection<Data> filterMethod(Collection<Data> source, String fieldName, String operator, Object... value)  {
		Collection<Data> Albums = new ArrayList<>();
		
		for(Data album : source) {
			try {
                Method m = null;
                    m = album.getClass().getMethod("get"+fieldName);
                
                try {
                    Object temp = null;
                    temp = m.invoke(album);
                    if (temp instanceof Date) {
                    	int yearT, monthT;
                    	yearT = ((Date) temp).getYear() +1900;
                    	monthT = ((Date) temp).getMonth() +1;
                    		if(FilterCheck.check(yearT, operator, value) || FilterCheck.check(monthT, operator, value))
                                Albums.add(album);
                    }
                    else if(FilterCheck.check(temp, operator, value))
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
	
}
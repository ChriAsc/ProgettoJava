package it.univpm.SpringBootApp.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import it.univpm.SpringBootApp.model.Data;

/**
 * Classe che permette di filtrare tramite parametri specifici
 * @param <Data> Tipo specifico Data, ovvero quello dell'album
 * @author Cristian Cingolani & Christian Ascani
 * 
 */
public class Filter<T> {
	
	/**
     * Metodo che filtra l'intera collection di Data su un campo, con una definita condizione e 
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
                    		if(check(yearT, operator, value) || check(monthT, operator, value))
                                Albums.add(album);
                    }
                    else if(check(temp, operator, value))
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
	 * Metodo booleano che analizza operatore e oggetto passati
	 * @param value Oggetto di riferimento attraverso cui il filtro viene applicato
	 * @param operator Operatore
	 * @param obj Valore su cui il filtro viene applicato
	 * @return true se il valore deve essere inserito, false in caso contrario
	 * @author Cristian Cingolani & Christian Ascani
	 * 
	 */
	public static boolean check(Object value, String operator, Object... obj) {
		if (obj.length==1 && obj[0] instanceof Number && value instanceof Number) {
			Double objD = ((Number)obj[0]).doubleValue();
			Double valueD = ((Number)value).doubleValue();
			
			if (operator.equals("$eq"))
				return value.equals(obj[0]);
			else if (operator.equals("$not"))
				return !(value.equals(obj[0]));
			else if (operator.equals("$lt"))
				return valueD < objD;
			else if (operator.equals("$gt"))
				return valueD > objD;
			else if (operator.equals("$lte"))
				return valueD <= objD;
			else if (operator.equals("$gte"))
				return valueD >= objD;
		} else if(obj.length==1 && obj[0] instanceof String && value instanceof String) {
			if(operator.equals("$eq") || operator.equals("$in"))
				return value.equals(obj[0]);
            else if(operator.equals("$not") || operator.equals("$nin"))
            	return !value.equals(obj[0]);
		} else if(obj.length>1) {
			if(operator.equals("$bt")) {
				if(obj.length==2 && obj[0] instanceof Number && obj[1] instanceof Number && value instanceof Number) {
					Double min = ((Number)obj[0]).doubleValue();
					Double max = ((Number)obj[1]).doubleValue();
					Double valueD = ((Number)value).doubleValue();
					
					return valueD >= min && valueD <= max;
				}
			}
			else if (operator.equals("$in"))
                return Arrays.asList(obj).contains(value);
            else if (operator.equals("$nin"))
                return !Arrays.asList(obj).contains(value);
		}
		return false;		
	}
	
}
package it.univpm.SpringBootApp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import it.univpm.SpringBootApp.model.Data;

public class FilterCheck<T> {
	
	/**
	 * Metodo booleano che analizza operatore e oggetto passati
	 * @param value Oggetto di riferimento attraverso cui il filtro viene applicato
	 * @param operator Operatore
	 * @param obj Valore su cui il filtro viene applicato
	 * @return true se il valore deve essere inserito, false in caso contrario
	 * @author Cristian Cingolani & Christian Ascani
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
	
	/**
     * Metodo utilizzato per implementare la logica AND qualora venga richiesta nel filtraggio
     * @param data ArrayList di ArrayList di Data
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
     * @param data ArrayList di ArrayList di Data
     * @return Restituisce l'insieme come ArrayList
     */
    public ArrayList<Data> Or (ArrayList<ArrayList<Data>> data) {
        Set<Data> set = new HashSet<Data>();

        for (ArrayList<Data> d : data)
            set.addAll(d);

        return new ArrayList<Data>(set);
    }
}
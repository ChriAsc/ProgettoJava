package it.univpm.SpringBootApp.utils;

public class FilterCheck<T> {
	
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
		}
		return false;		
	}
}
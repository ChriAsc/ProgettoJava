package it.univpm.SpringBootApp.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;

import it.univpm.SpringBootApp.model.*;

/**
 * Superclasse per calcolo statistiche
 * @author Cingolani Cristian & Ascani Christian
 */

public class StatBase {
	protected Method m;
	
	/**
	 * Costruttore della superclasse
	 * @param arrD
	 * @param field
	 */
	
	public StatBase(ArrayList<Data> arrD, String field) throws NoSuchMethodException, SecurityException
	{
		m = arrD.get(0).getClass().getMethod("get"+field.substring(0, 1).toUpperCase()+field.substring(1));
	}

}

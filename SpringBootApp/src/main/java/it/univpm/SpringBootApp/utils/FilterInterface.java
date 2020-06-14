package it.univpm.SpringBootApp.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Intefaccia contenente il metodo da implementare per il filtraggio dei dati
 * @param <E> Tipo dell'oggetto da filtrare
 * @param <T> Tipo del valore utilizzato per filtrare l'oggetto
 * @author Cristian Cingolani & Christian Ascani
 * 
 */
public interface FilterInterface<E,T> {
	
	/**
     * Metodo astratto per il filtraggio della collection di oggetti
     * @param fieldName Nome del campo da filtrare
     * @param operator Operatore utilizzato per filtrare l'elemento
     * @param value Valori su cui deve essere eseguito tale filtro
     * @return Collection di oggetti fltrata
     */
	abstract Collection<E> filterField(String fieldName, String operator, T value);
}
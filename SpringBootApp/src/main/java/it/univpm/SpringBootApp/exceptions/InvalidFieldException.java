package it.univpm.SpringBootApp.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe che implementa un'eccezione personalizzata, lanciata nel caso in cui si inserisca un campo che non corrisponde a quelli richiesti
 * @author Cristian Cingolani & Christian Ascani
 *
 */
public class InvalidFieldException extends Exception {
	/**
	 * Costruttore
	 * @param msg Messaggio dell'eccezione
	 */
	public InvalidFieldException(String msg) {
		super(msg);
	}
}

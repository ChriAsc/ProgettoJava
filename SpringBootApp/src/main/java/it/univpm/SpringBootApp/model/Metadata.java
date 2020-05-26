package it.univpm.SpringBootApp.model;

/**
 * Classe che descrive Metadati
 * @author Cristian Cingolani & Christian Ascani
 *
 */

public class Metadata {
	private String spec, type;
	
	/**
     Costruttore della classe Metadata
	 * @param spec, Parametro che rappresenta la descrizione del campo
	 * @param type, Parametro che rappresenta il tipo del campo
     */
	
	public Metadata(String spec, String type)
	{
		this.spec = spec;
		this.type = type;
	}
	
	/**
	 * Metodo che restituisce spec
	 * @return spec
	 */
	public String getspec() {
		return spec;
	}
	/**
	 * Metodo che restituisce type
	 * @return type
	 */
	public String gettype() {
		return type;
	}

}

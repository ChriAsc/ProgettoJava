package it.univpm.SpringBootApp.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import it.univpm.SpringBootApp.exceptions.InvalidFieldException;
import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.model.Database;
import it.univpm.SpringBootApp.utils.FilterCheck;

/**
 * Classe utilizzata per filtrare i contenuti del dataset in base ai filtri specificati
 * @author Cristian Cingolani & Christian Ascani
 */
public class ParserOperator {
	protected Database database;
	protected JSONObject jsonObj;
	
	/**
	 * Costruttore
	 * @param database Dataset intero su cui poi verrà fatto il filtraggio
	 * @param jsonObj JSONObject che rappresenta il filtro passato nella chiamata
	 */
	public ParserOperator(Database database, JSONObject jsonObj) {
		this.database = database;
		this.jsonObj = jsonObj;
	}
	
	/**
	 * Metodo che esegue il filtraggio dei contenuti del dataset, basandosi sull'oggetto passato come filtro
	 * @param database Dataset che viene effettivamente filtrato
	 * @param jsonObj Oggetto che viene passato come filtro
	 * @return ArrayList con i dati filtrati
	 * @throws InvalidFieldException 
	 */
	public ArrayList<Data> parseFilter(Database database, JSONObject jsonObj) throws InvalidFieldException {
		String operator = jsonObj.keys().next();
		if (operator.equalsIgnoreCase("$and")) {
			FilterCheck<Data> check = new FilterCheck<Data>();
			return check.And(logLinkOpCase(database, jsonObj, operator));
		}
		else if (operator.equalsIgnoreCase("$or")) {
			FilterCheck<Data> check = new FilterCheck<Data>();
			return check.Or(logLinkOpCase(database, jsonObj, operator));
		} else {
			return noLogLinkOpCase(database, jsonObj, operator);
        	}
		
	}
	
	/**
	 * Metodo che permette di eseguire il filtraggio nel caso in cui vengono passati gli operatori logici "AND" "OR"
	 * @param db Dataset che viene effettivamente filtrato
	 * @param json Oggetto che viene passato come filtro
	 * @param loglinkOp Operatore logico passato
	 * @return ArrayList con dati filtrati
	 * @throws InvalidFieldException 
	 */
	public ArrayList<ArrayList<Data>> logLinkOpCase (Database db, JSONObject json, String loglinkOp) throws InvalidFieldException {
		JSONArray jsonArray = json.getJSONArray(loglinkOp);
		ArrayList<ArrayList<Data>> c = new ArrayList<>();
		for (Object cc : jsonArray) {
			if (cc instanceof JSONObject) {
				c.add(parseFilter(db, (JSONObject) cc));
			}
		}
		return c;
	}

	/**
	 * Metodo che permette di eseguire il filtraggio nel caso in cui non vengono passati operatori logici e siano presenti gli altri
	 * @param db Dataset che viene effettivamente filtrato
	 * @param json Oggetto che viene passato come filtro
	 * @param op Operatore passato (non "AND" o "OR")
	 * @return ArrayList con dati filtrati
	 * @throws InvalidFieldException 
	 */
	public ArrayList<Data> noLogLinkOpCase (Database db, JSONObject json, String op) throws InvalidFieldException {
		JSONObject innerObj = json.getJSONObject(op);
        String operator = innerObj.keys().next();
        if(operator.equalsIgnoreCase("$bt")) {
        	double min = innerObj.getJSONArray(operator).getDouble(0);
            double max = innerObj.getJSONArray(operator).getDouble(1);
            try {
				return db.filterField(op, operator, min, max);
			} catch (SecurityException | IllegalArgumentException e) {
				e.printStackTrace();
			}
        } else if (operator.equalsIgnoreCase("$in") || operator.equalsIgnoreCase("$nin")) {
        	ArrayList<Object> v = new ArrayList<>();
            for(Object obj : innerObj.getJSONArray(operator)) {
                v.add(obj);
            }
            try {
				return db.filterField(op, operator, v.toArray());
			} catch (SecurityException | IllegalArgumentException e) {
				e.printStackTrace();
			}
        } else if (operator.equalsIgnoreCase("$gt") || operator.equalsIgnoreCase("$gte") || operator.equalsIgnoreCase("lt") || operator.equalsIgnoreCase("$lte") || operator.equalsIgnoreCase("$eq") || operator.equalsIgnoreCase("$not")) {
        	try {
        		Object obj = innerObj.get(operator);
					return db.filterField(op, operator, obj);
				} catch (SecurityException |  IllegalArgumentException e1) {
					e1.printStackTrace();
				}
        	}
        throw new InvalidFieldException("A filter cannot be requested because the body is not correct.");
	}
}
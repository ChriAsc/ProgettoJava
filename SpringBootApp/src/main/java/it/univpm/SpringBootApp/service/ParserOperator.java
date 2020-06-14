package it.univpm.SpringBootApp.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.model.Database;
import it.univpm.SpringBootApp.utils.FilterCheck;

public class ParserOperator {
	protected Database db;
	protected JSONObject json;
	
	public ParserOperator(Database db, JSONObject json) {
		this.db = db;
		this.json = json;
	}
	
	public ArrayList<Data> parseFilter(Database database, JSONObject jsonObj) {
		String op = jsonObj.keys().next();
		if (op.equalsIgnoreCase("$and")) {
			FilterCheck<Data> f = new FilterCheck<Data>();
			return f.And(andCase(database, jsonObj, op));
		}
		else if (op.equalsIgnoreCase("$or")) {
			FilterCheck<Data> f = new FilterCheck<Data>();
			return f.Or(orCase(database, jsonObj, op));
		} else {
			return noLogicalLinkOperator(database, jsonObj, op);
        	}
		
	}
	
	public ArrayList<ArrayList<Data>> andCase (Database db, JSONObject json, String loglinkOp) {
		JSONArray jsonArray = json.getJSONArray(loglinkOp);
		ArrayList<ArrayList<Data>> c = new ArrayList<>();
		for (Object cc : jsonArray) {
			if (cc instanceof JSONObject) {
				c.add(parseFilter(db, (JSONObject) cc));
			}
		}
		return c;
	}
	
	public ArrayList<ArrayList<Data>> orCase (Database db, JSONObject json, String loglinkOp) {
		JSONArray jsonArray = json.getJSONArray(loglinkOp);
		ArrayList<ArrayList<Data>> c = new ArrayList<>();
		for (Object cc : jsonArray) {
			if (cc instanceof JSONObject) {
				c.add(parseFilter(db, (JSONObject) cc));
			}
		}
		return c;
	}
	
	public ArrayList<Data> noLogicalLinkOperator (Database db, JSONObject json, String op) {
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
            for(Object el : innerObj.getJSONArray(operator)) {
                v.add(el);
            }
            try {
				return db.filterField(op, operator, v.toArray());
			} catch (SecurityException | IllegalArgumentException e) {
				e.printStackTrace();
			}
        } else if (operator.equalsIgnoreCase("$gt") || operator.equalsIgnoreCase("$gte") || operator.equalsIgnoreCase("lt") || operator.equalsIgnoreCase("$lte") || operator.equalsIgnoreCase("$eq") || operator.equalsIgnoreCase("$not")) {
        	try {
        		Object v = innerObj.get(operator);
					return db.filterField(op, operator, v);
				} catch (SecurityException |  IllegalArgumentException e1) {
					e1.printStackTrace();
				}
        	}
		return null;
	}
}
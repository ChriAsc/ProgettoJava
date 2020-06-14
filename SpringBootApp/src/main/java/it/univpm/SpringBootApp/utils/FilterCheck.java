package it.univpm.SpringBootApp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import it.univpm.SpringBootApp.model.Data;

public class FilterCheck<T> {
	
	
	/**
     * Metodo utilizzato per implementare la logica AND qualora venga richiesta nel filtraggio
     * @param data Collection di Data
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
     * @param data Collection di Data
     * @return Restituisce l'insieme
     */
    public ArrayList<Data> Or (ArrayList<ArrayList<Data>> data) {
        Set<Data> set = new HashSet<Data>();

        for (ArrayList<Data> d : data)
            set.addAll(d);

        return new ArrayList<Data>(set);
    }
}
package it.univpm.SpringBootApp.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Sottoclasse per calcolo statistiche di tipo numerico
 * @author Cingolani Cristian & Ascani Christian
 */
public class StatNum{
	
	private double sum;
	private int count;
	private double avg;
    private double min;
    private double max;
    private double dev;
    
    /**
	 * Costruttore base di StatNum
	 */
	public StatNum() {
		
	}
	
	/**
     * Metodo che restituisce sum
     * @return sum
     */ 
    public double getSum() {
        return sum;
    }

    /**
     * Metodo che restituisce count
     * @return count
     */   
    public int getCount() {
        return count;
    }
    
    /**
     * Metodo che restituisce avg
     * @return avg
     */   
    public double getAvg() {
        return avg;
    }
    
    /**
     * Metodo che restituisce min
     * @return min
     */    
    public double getMin() {
        return min;
    }
    
    /**
     * Metodo che restituisce max
     * @return max
     */    
    public double getMax() {
        return max;
    }
    
    /**
     * Metodo che restituisce dev
     * @return dev
     */   
    public double getDev() {
        return dev;
    }    
	
    /**
     * Metodo che calcola e imposta il valore di sum
     * @param val
     */
    private void setSum(ArrayList<Number> val) {
    	for (Number v : val){
            sum += v.doubleValue();
        }
    }    
    
    /**
     * Metodo che calcola e imposta il valore di count
     * @param val
     */
    protected void setCount(ArrayList val) {
    	count= val.size();
    }      
    
    /**
     * Metodo che calcola e imposta il valore di avg
     * @param val
     */  
    private void setAvg(ArrayList<Number> val) {
        avg = getSum()/getCount();
    }
    
    /**
     * Metodo che calcola e imposta il valore di min
     * @param val
     */
    private void setMin(ArrayList<Number> val) {
    	min = val.get(0).doubleValue();
    	for(Number n : val) {
    		if(n.doubleValue() < min) {
                min = n.doubleValue();
            }
        }
    }
    
    /**
     * Metodo che calcola e imposta il valore di max
     * @param val
     */
    private void setMax(ArrayList<Number> val) {
        max = val.get(0).doubleValue();
        for(Number n : val) {
    		if(n.doubleValue() > max) {
                max = n.doubleValue();
            }
        }
    }
    
    /**
     * Metodo che calcola e imposta il valore di dev
     * @param val
     */
    private void setDev(ArrayList<Number> val) {
    	dev = 0;
    	for(Number numero : val) {
    		dev += Math.pow(numero.doubleValue() - getAvg(), 2);
    	}
        dev=((double) Math.pow(dev/getCount(), 0.5));
    }
		
    /**
     * Metodo che riceve in ingresso un particolare field ed un arraylist di number
     * restituisce una mappa con tutte le statistiche di tipo numerico
     * @param field campo su cui eseguire la statistica 
     * @param numList arraylist di numeri su cui eseguire le statistiche di tipo numerico
     * @return map mappa restituita contenente le statistiche di tipo numerico
     */
    public Map<String, Object> NumStat(String field, ArrayList<Number> numList) {
    	Map<String, Object> map = new LinkedHashMap<>(); 
    	setCount(numList);
    	setSum(numList);
    	setAvg(numList);
    	setMin(numList);
    	setMax(numList);
    	setDev(numList);
    	map.put("field", field);
    	map.put("count", getCount());
    	map.put("sum", getSum());
    	map.put("avg", getAvg());
        map.put("min", getMin());
        map.put("max", getMax());
        map.put("dev", getDev());
        return map;
    }
	 
    /**
	 * Metodo che riceve in ingresso un arraylist di number
	 * restituisce una mappa con tutte le statistiche di tipo numerico (per la data)
	 * @param numList arraylist di numeri su cui eseguire le statistiche di tipo numerico
	 * @return map mappa restituita contenente le statistiche di tipo numerico
	 * */
    public Map<String, Object> NumStatDate(ArrayList<Number> numList) {
    	Map<String, Object> map = new LinkedHashMap<>();  
    	setCount(numList);
    	setSum(numList);
    	setAvg(numList);
    	setMin(numList);
    	setMax(numList);
    	setDev(numList);
    	map.put("count", getCount());
    	map.put("avg", getAvg());
        map.put("min", getMin());
        map.put("max", getMax());
        map.put("dev", getDev());
        return map;
    }
    
    /**
	 * Metodo che riceve in ingresso un arraylist di number
	 * calcola il numero di album pubblicati in ciascun anno (dal 2010 al 2020)
	 * restituisce una map con tutti gli anni(chiave) e con le relative occorrenze(valore)
	 * @param numList arraylist di numeri contenente tutti gli anni di created_time o updated_time dei vari album
	 * @return map mappa restituita contenente il numero di album con created_time o updated_time aventi un certo anno
	 * */
    public Map<String, Object> StatIstoYear(ArrayList<Number> numList) {
    	Map<String, Object> map = new LinkedHashMap<>();
    	int[] years = new int[11];
    	for(int i=0; i < 11; i++) {
    		years[i]=0;
    	}   	
    	for(int i=0; i < numList.size(); i++) {
    		for(int j=2010; j < 2021; j++) {
    			if(numList.get(i).intValue()+1900==j) years[j-2010]++;
    		}
    	}
    	map.put("2010",years[0]);
    	map.put("2011",years[1]);
    	map.put("2012",years[2]);
    	map.put("2013",years[3]);
    	map.put("2014",years[4]);
    	map.put("2015",years[5]);
    	map.put("2016",years[6]);
    	map.put("2017",years[7]);
    	map.put("2018",years[8]);
    	map.put("2019",years[9]);
    	map.put("2020",years[10]);
    	return map;	
    }
    
    /**
	 * Metodo che riceve in ingresso un arraylist di number
	 * calcola il numero di album pubblicati in ciascun mese
	 * restituisce una map con tutti i mesi(chiave) e con le relative occorrenze(valore)
	 * @param numList arraylist di numeri contenente tutti i mesi di created_time o updated_time dei vari album
	 * @return map mappa restituita contenente il numero di album con created_time o updated_time aventi un certo mese
	 * */
    public Map<String, Object> StatIstoMonth(ArrayList<Number> numList) {
    	Map<String, Object> map = new LinkedHashMap<>();
    	int[] months = new int[12];
    	for(int i=0; i < 12; i++) {
    		months[i]=0;
    	}   	
    	for(int i=0; i < numList.size(); i++) {
    		for(int j=1; j < 13; j++) {
    			if(numList.get(i).intValue()==j) months[j-1]++;
    		}
    	}
    	map.put("Gennaio",months[0]);
    	map.put("Febbraio",months[1]);
    	map.put("Marzo",months[2]);
    	map.put("Aprile",months[3]);
    	map.put("Maggio",months[4]);
    	map.put("Giugno",months[5]);
    	map.put("Luglio",months[6]);
    	map.put("Agosto",months[7]);
    	map.put("Settembre",months[8]);
    	map.put("Ottobre",months[9]);
    	map.put("Novembre",months[10]);
    	map.put("Dicembre",months[11]);
    	return map;	
    }
    
    /**
	 * Metodo che riceve in ingresso un arraylist di number
	 * calcola il numero di album pubblicati in ciascun giorno
	 * restituisce una map con tutti i giorni(chiave) e con le relative occorrenze(valore)
	 * @param numList arraylist di numeri contenente tutti i giorni di created_time o updated_time dei vari album
	 * @return map mappa restituita contenente il numero di album con created_time o updated_time aventi un certo giorno
	 * */   
    public Map<String, Object> StatIstoDay(ArrayList<Number> numList) {
    	Map<String, Object> map = new LinkedHashMap<>();
    	int[] days = new int[31];
    	for(int i=0; i < 31; i++) {
    		days[i]=0;
    	}
    	
    	for(int i=0; i < numList.size(); i++) {
    		for(int j=1; j < 32; j++) {
    			if(numList.get(i).intValue()==j) days[j-1]++;
    		}
    	}
    	map.put("1",days[0]);
    	map.put("2",days[1]);
    	map.put("3",days[2]);
    	map.put("4",days[3]);
    	map.put("5",days[4]);
    	map.put("6",days[5]);
    	map.put("7",days[6]);
    	map.put("8",days[7]);
    	map.put("9",days[8]);
    	map.put("10",days[9]);
    	map.put("11",days[10]);
    	map.put("12",days[11]);
    	map.put("13",days[12]);
    	map.put("14",days[13]);
    	map.put("15",days[14]);
    	map.put("16",days[15]);
    	map.put("17",days[16]);
    	map.put("18",days[17]);
    	map.put("19",days[18]);
    	map.put("20",days[19]);
    	map.put("21",days[20]);
    	map.put("22",days[21]);
    	map.put("23",days[22]);
    	map.put("24",days[23]);
    	map.put("25",days[24]);
    	map.put("26",days[25]);
    	map.put("27",days[26]);
    	map.put("28",days[27]);
    	map.put("29",days[28]);
    	map.put("30",days[29]);
    	map.put("31",days[30]);
    	return map;	
    } 
}

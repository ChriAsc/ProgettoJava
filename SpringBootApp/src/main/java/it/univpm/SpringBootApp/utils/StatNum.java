package it.univpm.SpringBootApp.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import it.univpm.SpringBootApp.model.*;

/**
 * Sottoclasse per calcolo statistiche di tipo numerico
 * @author Cingolani Cristian & Ascani Christian
 */

public class StatNum extends StatBase{
	
	private double sum;
	private int count;
	private double avg;
    private double min;
    private double max;
    private double dev;
    
    /**
	 * Costruttore della sottoclasse
	 * @param arrD
	 * @param field
	 */
    
    public StatNum(ArrayList<Data> arrD, String field) throws NoSuchMethodException {
    	super(arrD,field);
        this.count = arrD.size();
        double[] values = new double[count];
        for (int i = 0; i < count ; i++){
            try {
                Object doubleValue = m.invoke(arrD.get(i));
                values[i] = (double) doubleValue;
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            
            //Richiamo alle funzioni che eseguono il calcolo delle statistiche
            setSum(values);
            setAvg(values);
            setMin(values);
            setMax(values);
            setDev(values);
        }     
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
     * @param values
     */

    private void setSum(double[] values) {
        for (double v : values){
            sum += v;
        }
    }
    
    /**
     * Metodo che imposta il valore di count
     * @param count
     */
       
    public void setCount(int count) {
        this.count = count;
    }
    
    /**
     * Metodo che calcola e imposta il valore di avg
     * @param avg
     */
    
    private void setAvg(double[] values) {
        this.avg=(sum/count);
    }
    
    /**
     * Metodo che calcola e imposta il valore di can_upload
     * @param values
     */

    private void setMin(double[] values) {
        double min = values[0];
        for ( int i = 1; i < values.length ; i++){
            if(values[i] < min){
                min = values[i];
            }
        }
        this.min=min;
    }
    
    /**
     * Metodo che calcola e imposta il valore di max
     * @param max
     */

    private void setMax(double[] values) {
        double max = values[0];
        for ( int i = 1; i < values.length ; i++){
            if(values[i] > max){
                max = values[i];
            }
        }
        this.max=max;
    }
    
    /**
     * Metodo che calcola e imposta il valore di dev
     * @param dev
     */

    private void setDev(double[] values) {
        double summ = 0;
        for (double v : values) {
            summ += Math.pow(v - avg, 2);
        }
        this.dev=((double) Math.pow(summ/count, 0.5));
    }
}

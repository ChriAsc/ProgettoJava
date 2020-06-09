package it.univpm.SpringBootApp.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field; 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;

import org.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.model.Metadata;
import it.univpm.SpringBootApp.service.JSONGetAndDecode;
import it.univpm.SpringBootApp.service.ParserJSON;
import it.univpm.SpringBootApp.utils.StatNum;

/**
 * Classe che forma Arraylist di Dati e Metadati
 * @author Cristian Cingolani & Christian Ascani
 *
 */
@Component
public class Database{
	public ArrayList<Data> arrData = new ArrayList<>();
	public ArrayList<Metadata> arrMetadata = new ArrayList<>();
	
	
	/**
	* Costruttore della classe Database
	* @throws IOException 
	 *@throws NoSuchMethodException 
	 * @throws ParseException 
	*/
	public Database() throws IOException, NoSuchMethodException, ParseException
	{
		arrMetadata.add(new Metadata("id", "String"));
		arrMetadata.add(new Metadata("can_upload", "boolean"));
		arrMetadata.add(new Metadata("count", "int"));
		arrMetadata.add(new Metadata("created_time", "SimpleDateFormat"));
		arrMetadata.add(new Metadata("description", "String"));
		arrMetadata.add(new Metadata("event", "String"));
		arrMetadata.add(new Metadata("link", "String"));
		arrMetadata.add(new Metadata("location", "String"));
		arrMetadata.add(new Metadata("name", "String"));
		arrMetadata.add(new Metadata("place", "Place"));
		arrMetadata.add(new Metadata("name_place", "String"));
		arrMetadata.add(new Metadata("location_place", "Location"));
		arrMetadata.add(new Metadata("id_place", "string"));
		arrMetadata.add(new Metadata("city_location", "String"));
		arrMetadata.add(new Metadata("country_location", "String"));
		arrMetadata.add(new Metadata("latitude_location", "double"));
		arrMetadata.add(new Metadata("longitude_location", "double"));
		arrMetadata.add(new Metadata("zip_location", "String"));
		arrMetadata.add(new Metadata("privacy", "String"));
		arrMetadata.add(new Metadata("type", "String"));

		arrMetadata.add(new Metadata("updated_time", "String"));

		arrMetadata.add(new Metadata("updated_time", "SimpleDateFormat"));

		fillData();
	}
	
	/**
	 * Metodo che restituisce arrData
	 * @return arrData
	 */
	
	public ArrayList<Data> getarrData() {
		return arrData;
	}
	
	/**
	 * Metodo che restituisce arrMetadata
	 * @return arrMetadata
	 */
	
	public ArrayList<Metadata> getarrMetadata() {
		return arrMetadata;
	}
	
	/**
	 * Metodo che inserisce in arrData un elemento di tipo Data
	 * @param Data
	 */
	public void setArrData(ArrayList<Data> d) {
		arrData.addAll(d);
	}

	/**
	 * Metodo che si occupa del riempimento di arrData 
	 * sulla base dei valori scaricati ed estratti dal Parser
	 * @param Data
	 * @throws ParseException 
	 */
	public void fillData() throws IOException, ParseException {
		File file = new File("dataFile.json");
        if(!file.exists()){
			JSONGetAndDecode download = new JSONGetAndDecode("https://graph.facebook.com/me/albums?fields=id,can_upload,count,created_time,description,event,link,location,name,place,privacy,type,updated_time&access_token=EAAg0XZALFgWIBADHqHKXxySRdKfEFHeMyfGPOyP7YArpZBiQwWXcGngjVoPGre5yMG2mTsfBMpFInyGhQcv0GjkIUqDLkSOe5Vid4gNvKgVshyJkoyDpy3x61gUOARYM6V7kcIax3MWzjNBFxRsQDXbmygEDZANKd7QT1jQpXEaG4pDL45nkSfPBayaHhIXs1SxSGcq9NfEYJj6Vnr7SNLCwutjvAuAKWNi0wScvgZDZD");
			try {
				download.downloadJson(file.getName());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
        ParserJSON parseFile = new ParserJSON();
        arrData = parseFile.parserJson(file.getName());
	}
	
	/**
	 * Questo metodo gestisce le statistiche, verificando se l'attributo passato è di tipo numerico o string
	 * verifica se l'attributo è presente
	 * grazie all'uso del metodo equalsIgnoreCase è possibile inserire nella richiesta dell'attributo 
	 * sia lettere maiscole che minuscole
	 * senza creare errori riguardanti il Case
	 * @param nomeCampo
	 * @return mappa delle statistiche 
	 * @throws NoSuchMethodException 
	 */
	public Map<String, Object> getStatistiche(String nomeCampo) throws NoSuchMethodException 
	{
		StatNum statistica = new StatNum();
		Map<String, Object> mappa = new HashMap<>();
		Map<String, Object> Errore = new HashMap<>();
		Errore.put("WARNING", "NON VI SONO STATISTICHE SULL'ATTRIBUTO INSERITO");
		Field[] fields = Data.class.getDeclaredFields();
		
		for (Field f : fields) 
		{
			if(nomeCampo.equalsIgnoreCase(f.getName())) 
				mappa = statistica.getStat(nomeCampo, fieldValues(nomeCampo, getarrData()));
		}
		if(mappa.isEmpty())
			return Errore;
		else 
			return mappa;
	}
	
	/**
	 * Questo metodo estrae i valori di un determinato campo, passato tramite fieldName 
	 * verifica se è presente nel vettore dei campi e se risulta TRUE aggiunge il valore dell'oggetto della lista
	 * @param fieldName nome del campo del file JSON
	 * @param list lista che si ottiene dopo aver effettuato il parsing, array di oggetti Data
	 * @return la lista che contiene i valori di un determinato campo
	 */
	public ArrayList<Object> fieldValues(String fieldName, ArrayList<Data> list) {
		ArrayList<Object> values = new ArrayList<>();
		try {
			Field[] fields = Data.class.getDeclaredFields();
			for(Object e : list) {
				// controlla se è presente l'attributo  
				for(int i=0; i < fields.length; i++) {
					if(fieldName.equalsIgnoreCase(fields[i].getName())) {
						Method m = e.getClass().getMethod("get"+fields[i].getName());
						Object val = m.invoke(e);
						values.add(val); //lo aggiunge alla lista
					}
				}
			}
		} catch(NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch(SecurityException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		return values;
	}
}

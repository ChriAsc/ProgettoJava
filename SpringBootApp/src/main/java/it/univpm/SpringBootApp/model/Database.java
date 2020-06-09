package it.univpm.SpringBootApp.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.text.ParseException;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.model.Metadata;
import it.univpm.SpringBootApp.service.JSONGetAndDecode;
import it.univpm.SpringBootApp.service.ParserJSON;

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
	
	
}

package it.univpm.SpringBootApp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.SpringBootApp.model.Data;
import it.univpm.SpringBootApp.service.JSONGetAndDecode;
import it.univpm.SpringBootApp.service.ParserJSON;


/**
	 * Main del programma. Inizialmente si istanzia un oggetto della classe JsonGetDecode per scaricare
	 * il file json contenete i dati. Successivamente si avvia la spring application.
	 * @param args
	 * @throws Exception 
	 * @author Cristian Cingolani & Christian Ascani
 */
@SpringBootApplication
public class SpringBootAppApplication {
	
	public static ArrayList<Data> albumArrayList;
	public static void main(String[] args) throws IOException {
		
			File file = new File("dataFile.json");
	        if(!file.exists()){
				JSONGetAndDecode download = new JSONGetAndDecode("https://graph.facebook.com/me/albums?fields=id,can_upload,count,created_time,description,event,link,location,name,privacy,type,updated_time&access_token=EAAg0XZALFgWIBAJ5yHAuX0RK2PZADtC03A5r1DY2jTzKB4Uh1AeLLz2mH5sG0oeLQZCc1K1CRX3oV5cXZAK26YcDxjIDkZC7EyduGbwUZAnh4p8ZC5eWS97BAAclSuGZAKJZA8JWAtKfcUTURknkj4kmErPwGOPnDD9SyJNYZCBozMkaZAkWiZATrbqbLwuC9m0rO6LTm6E60IeMbZCIyJ6zVHUzIpy5mSLUpweXu92hurMkdfwZDZD");
				try {
					download.downloadJson(file.getName());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			ParserJSON parseFile = new ParserJSON();
	        albumArrayList = parseFile.parserJson(file.getName());
	        SpringApplication.run(SpringBootAppApplication.class, args);
		}
}

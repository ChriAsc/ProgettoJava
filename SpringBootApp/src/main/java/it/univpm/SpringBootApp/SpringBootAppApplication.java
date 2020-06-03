package it.univpm.SpringBootApp;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
	 * Main del programma. Inizialmente si istanzia un oggetto della classe JsonGetDecode per scaricare
	 * il file json contenete i dati. Successivamente si avvia la spring application.
	 * @param args
	 * @throws Exception 
	 * @author Cristian Cingolani & Christian Ascani
 */
@SpringBootApplication
public class SpringBootAppApplication {
	
	public static void main(String[] args) throws IOException {
		
	        SpringApplication.run(SpringBootAppApplication.class, args);
	        
		}
}

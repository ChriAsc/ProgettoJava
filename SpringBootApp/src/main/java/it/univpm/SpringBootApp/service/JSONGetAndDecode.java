package it.univpm.SpringBootApp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class JSONGetAndDecode {

	//private URL link;
	private URL url;
	
		/**
		 * Costruttore che copia il contenuto dell'URL e lo memorizza in una variabile
		 * @param url L'indirizzo web nel quale è contenuto il link al dataset
		 * @throws IOException
		 */
		public JSONGetAndDecode(String url) throws IOException
		{
			this.url = new URL(url);
		
		}
		
		public void downloadJson (String filename) {
			
			DateFormat dateFormat = new SimpleDateFormat("E d MMMM yyyy", Locale.ITALIAN);					
			Date date = null;
						
			try {
				
				URLConnection openConnection = (url).openConnection();
				InputStream in = openConnection.getInputStream();
				
				 System.out.println("Reading data from the URL...");
				 
				 String data = "";
				 String line = "";
				 
				 try {
				   InputStreamReader inR = new InputStreamReader( in );
				   BufferedReader buf = new BufferedReader( inR );
				  
				   while ( ( line = buf.readLine() ) != null ) {
					   data+= line;
				   }
				 } finally {
				   in.close();
				 }				 

				 System.out.println("Data read correctly!");
				 System.out.println("Parsing the json...");
				
				JSONObject obj = (JSONObject) JSONValue.parseWithException(data);	 		//parse JSON Object                     		//parse JSON Array
				System.out.println("JSON parsed!");
				System.out.println("Starting download file JSON...");
								
				try (FileWriter file = new FileWriter(filename)) {					
					file.write(obj.toJSONString());
					file.flush();
					System.out.println("Download completed!");					 
				 } 
				catch (FileAlreadyExistsException e) 	         							//this exception is thrown when the file already exists
				{					
					File dataFile = new File(filename);
					String fileDataString =	dateFormat.format(dataFile.lastModified());		//get the data of the last edit of the file
					Date fileData = null;					
					try {
						fileData = dateFormat.parse(fileDataString);                        //convert the data of the file into date type
					} catch (java.text.ParseException ex) {
						ex.printStackTrace();
					}
					if(fileData.compareTo(date)<0)			//.compareTo method returns the value 0 if the argument date is equal to this Date; a value less than 0 if this Date is before the date argument; and a value greater than 0 if this Date is after the Date argument
						System.out.println("The data file already exists | Last edit: " + fileDataString);
					else
					{						
						if (dataFile.delete())	{											//the old data file is deleted and then the new data is downloaded
							try (FileWriter file = new FileWriter(filename)) {
								file.write(obj.toJSONString());
								file.flush();
								System.out.println("Download completed!");
							} catch (Exception e1) {
								e1.printStackTrace();
							}							
							} else
							{
								System.out.println("Unable to delete the older " + filename);
							}
					}					
				} catch (IOException e) {
					 e.printStackTrace();
				 }
			}
			 catch (IOException | ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
}

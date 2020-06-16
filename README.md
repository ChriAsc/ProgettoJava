# **Progetto di Programmazione ad Oggetti**
## Descrizione
L’applicazione presente nella repository è un progetto in Java che – tramite l’utilizzo di Spring Boot - consente di leggere un file di tipo JSON e scaricare i dati presenti in un altro file di tipo JSON. Questo consiste in un insieme di dati riguardo gli album di un utente Facebook (in particolare utente Ascani Christian) con il fine di manipolarli. 
Il dataset può essere consultato al seguente indirizzo: 
https://graph.facebook.com/me/albums?fields=id,can_upload,count,created_time,description,event,link,location,name,place,privacy,type,updated_time&access_token=EAAg0XZALFgWIBAHcqFzpUOF3fLBfnQ4hNl9RlZAO6879qdoZBV4pjoQzt8AmohVaXkU3mISmlRYZAWMBZAwdohQHEUd6XR7cZB5Adw1P2dyvzAOxlOCuuOZAGFxqHjCI5yWRDL943W6cftEqRjwxpinwy054qnuSJRiiCzVbz3XyQZDZD
(poichè nelle specifiche era indicata l'analisi di campi riguardanti date, la creazione di una nuova pagina non sarebbe stata efficace e coerente; per analizzare un profilo diverso da quello riportato in precedenza è sufficiente cambiare l'access token alla riga 96 di Database.java contenuto all'interno del package it.univpm.SpringBootApp.model)
### Formato dei dati
Ogni album presenta i seguenti campi con i rispettivi tipi:
1. id (String)
2. can_upload (boolean) 
3. count (long) 
4. created_time (Date) 
5. description (String) 
6. event (String) 
7. link (String) 
8. location (String)
9. name (String)
10. place (Place) 
10.1 name_place (String)
10.2 location_place (Location)
10.2.1 city_location (String)
10.2.2 country_location (String)
10.2.3 latitude_location (double)
10.2.4 longitude_location (double)
10.2.5 zip_location (String)
10.3 id_place (String)
11. privacy (String)
12. type (String)
13. updated_time (Date) 

## Funzionamento & Rotte
Tramite Spring Boot l’applicazione crea un server locale (http://localhost:8080) e attraverso **API REST GET** l'utente può eseguire le seguenti richieste:
-Restituzione dei **metadati**
-Restituzione dei **dati** 
-Restituzione delle **statistiche** 
-Restituzione **dati filtrati** 

* Rotta che restituisce la lista dei metadati:
> **GET** /metadata
Un esempio di Metadati restituito:
```java
{
        "spec": "id",
        "type": "String"
}
...
```

* Rotta che restituisce la lista dei dati:

> **GET** /data

Un esempio di Album restituito:

```java
{
        "id": "323857157709974",
        "can_upload": false,
        "count": 4,
        "created_time": "2012-08-29T08:25:29.000+0000",
        "description": "null",
        "event": "null",
        "link": "https://www.facebook.com/album.php?fbid=323857157709974&id=2868559909906340&aid=71795",
        "location": "Loreto",
        "name": "Compleanno 2012",
        "place": {
            "name_place": "Loreto, Italy",
            "location_place": {
                "city_location": "Loreto",
                "country_location": "Italy",
                "latitude_location": 43.43977,
                "longitude_location": 13.60726,
                "zip_location": null
            },
            "id_place": "108369075854001"
        },
        "privacy": "custom",
        "type": "normal",
        "updated_time": "2012-08-29T08:26:14.000+0000"
}
```

* Rotta che restituisce statistiche generiche, le quali possono essere rilevate su tutti i campi eccetto Place e Location; per campi di tipo numerico si possono calcolare *Somma, Minimo, Massimo, Media, Numero Elementi, Deviazione Standard*; per campi di tipo string si possono calcolare il *numero delle ricorrenze* di ogni singola stringa
  
> **GET** /statistiche?field={value}

Un esempio di statistiche con campo passato di tipo numerico:
```java
    {
        "field": "count",
        "count": 15,
        "sum": 333.0,
        "avg": 22.2,
        "min": 0.0,
        "max": 94.0,
        "dev": 27.587920061746836
    }
```

Un esempio di statistiche con campo passato di tipo stringa:

```java
        "field": "name",
        "elements": {
            "Instagram Photos": 1,
            "Deutschland 2013": 1,
            "Ultimo giorno di scuola :')": 1,
            "A Livigno": 1,
            "Compleanno 2012": 1,
            "Timeline Photos": 1,
            "Cover Photos": 1,
            "Gita 2-3-4 Maggio": 1,
            "Parco della Gola Della Rossa!": 1,
            "Catto!": 1,
            "Profile Pictures": 1,
            "Nasinsù": 1,
            "25 agosto 2011": 1,
            "Calcetto Villa Musone": 1,
            "Mobile Uploads": 1
        },
        "count": 15
```


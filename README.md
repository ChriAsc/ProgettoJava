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
11. privacy (String)
12. type (String)
13. updated_time (Date) 

Il campo Place è ulteriormente formato da:
1. name_place (String)
2. location_place (Location)
3. id_place (String)

Il campo Location è ulteriormente formato da:
1. city_location (String)
2. country_location (String)
3. latitude_location (double)
4. longitude_location (double)
5. zip_location (String)

## Funzionamento & Rotte

Tramite Spring Boot l’applicazione crea un server locale (http://localhost:8080) e attraverso **API REST GET** l'utente può eseguire le seguenti richieste:
-Restituzione dei **metadati**
-Restituzione dei **dati** 
-Restituzione delle **statistiche** 
-Restituzione **dati filtrati** 

### METADATI

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

### DATI
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

### STATISTICHE

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

* Rotta che restituisce statistiche riguardanti i campi created_time ed updated_time, ovvero i campi che contengono *date*; in particolare ciascun campo viene suddivisono in *Anni, Mesi, Giorni* con relative statistiche di tipo numerico (*Minimo, Massimo, Media, Numero Elementi, Deviazione Standard*)
  
> **GET** /statdate

Un esempio di statistiche riguardanti campi date:

```java
 "fieldC": "CREATED_TIME!",
    "campYC": "Year",
    "countYC": 15,
    "avgYC": 2011.9333333333334,
    "minYC": 2011.0,
    "maxYC": 2014.0,
    "devYC": 0.771722460186015,
    "campMC": "Month",
    "countMC": 15,
    "avgMC": 7.2,
    "minMC": 4.0,
    "maxMC": 11.0,
    "devMC": 1.5577761927397231,
    "campDC": "Day",
    "countDC": 15,
    "avgDC": 17.4,
    "minDC": 3.0,
    "maxDC": 30.0,
    "devDC": 9.562426470305537,
    "fieldU": "UPDATED_TIME!",
    "campYU": "Year",
    "countYU": 15,
    "avgYU": 2016.1333333333334,
    "minYU": 2012.0,
    "maxYU": 2019.0,
    "devYU": 2.093375795747678,
    "campMU": "Month",
    "countMU": 15,
    "avgMU": 6.466666666666667,
    "minMU": 1.0,
    "maxMU": 10.0,
    "devMU": 2.217105219775452,
    "campDU": "Day",
    "countDU": 15,
    "avgDU": 20.2,
    "minDU": 5.0,
    "maxDU": 29.0,
    "devDU": 7.564830908002285
```

* Rotta che restituisce il numero di riccorrenze degli album in base al campo passato (*Year, Month, Day*)
  
> **GET** /statisto?field={value}

Un esempio di statisto con field=year:

```java
{
    "2010": 0,
    "2011": 4,
    "2012": 9,
    "2013": 1,
    "2014": 1,
    "2015": 0,
    "2016": 0,
    "2017": 0,
    "2018": 0,
    "2019": 0,
    "2020": 0
}
```

### FILTRI

Il filtro presenta una richiesta di tipo POST per filtrare i dati, la richiesta deve essere effettuata seguendo lo standard JSON; in generale:

```java
{
    "operatore_logico":
    [
        {
            "campo1":{"operatore_condizionale":"value1"}
        },
        {
            "campo2":{"operatore_condizionale":"value2"}
        }
    ]
}
``` 

Nel filtro è possibile utilizzare operatori di tipo condizionale

#### Operatori Condizionali
 - **$eq:** uguaglianza
 - **$not:** non uguaglianza
 - **$lt:** minore
 - **$lte:** minore o uguale
 - **$gt:** maggiore
 - **$gte:** maggiore o uguale
 - **$bt:** compreso tra due valori
 - **$in:** uguaglianza con un qualsiasi valore di un array
 - **$nin:** non deve essere uguale a nessun valore di un arrayvalori.

#### Operatori Logici
E' possibile unire (opzionalmente) più filtri con l'utilizzo di Operatori Logici (**AND** e **OR**)

* Rotta che restituisce i dati filtrati i base il filtro passato
  
> **POST** /filter

Un esempio di filtro corrrispondente al body:

```java
{
    "$and":
    [
        {
            "name":{"$eq":"Cover Photos"}
        },
        {
            "created_time":{"$gt":2011}
        }
    ]
}
```

La relativa risposta:

```java
[
    {
        "id": "316461128449577",
        "can_upload": false,
        "count": 27,
        "created_time": "2012-08-06T05:13:00.000+0000",
        "description": "null",
        "event": "null",
        "link": "https://www.facebook.com/album.php?fbid=316461128449577&id=2868559909906340&aid=69085",
        "location": "null",
        "name": "Cover Photos",
        "place": null,
        "privacy": "everyone",
        "type": "cover",
        "updated_time": "2018-08-27T14:00:08.000+0000"
    }
]
```
## ECCEZIONI
Si sono gestite con i metodi try-catch, throw e classe InvalidFieldException

## TEST


## Librerie esterne
Si sono utilizzate le seguenti librerie:
* org.json.simple (json-simple): per la gestione dei JSONObject nel progetto

## UML
Qui vengono riportati i diagrammi UML inerenti all’applicazione
## Diagramma dei casi d'uso
![Use_Case_Diagram](https://github.com/ChriAsc/ProgettoJava/blob/master/Use%20case%20diagram.png)

## Diagramma delle sequenza
![Sequence Diagram](https://github.com/ChriAsc/ProgettoJava/blob/master/Basic%20UML%20Sequence%20Diagram.png)

## Diagramma delle classi
![Class Diagram](https://github.com/ChriAsc/ProgettoJava/blob/master/UML%20Class%20Diagram.png)

# AirlineConnector
This project allows the user to manage the database for a really important -- and fictional -- airport. There are many ways in which the user can interact with the database. After putting all the necessary credentials and successfully connected to the database, the user will be prompted to insert commands:

* For adding something to the database, the user has to enter "a" and one of the following:
  * For adding an airline, code is "a". And the following arguments are:
    * An airline ID, **USUALLY** a couple of characters (i.e. "aa")
    * An airline name which corresponds to that id (i.e. "american airlines")
  * For adding a city, code is "c". And the following arguments are:
    * A city ID, **USUALLY** a couple of characters (i.e. "stl")
    * A city name which corresponds to that id (i.e. "saint louis")
  * For adding a flight, code is "f". And the following arguments are:
    * The airline ID that would offer the flight
    * The departing city ID
    * The arrival city ID
    * The cost of the flight. **MUST BE AN INTEGER**
* For listing flights, the user has to enter "l" and one of the following:
  * For listing cities, code is "c"
  * For listing airlines, code is "a"
  * For listing flights, code is "f"
* For  finding flights, the user has to enter "f" and the following:
  * The departing city ID
  * The arrival city ID
  * The number of connections:
    * 0 for direct flights
    * 1 for flights with one connections

## Requirements
This project works with Java's mysql-connector. I have provided the file I used and it has to be added as an external library to the project.


## Additional Information
  **AUTHOR**    : Andres Enrique Rodriguez Hernandez  
  **DATE**      : 11/02/2016  
  **PLATFORM**  : Windows  
  **LANGUAGES** : JAVA/MySQL  

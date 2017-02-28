import java.sql.*;
import java.util.Scanner;

public class airlineConnector {

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "your_database_url";

	   //  Database credentials
	   static final String USER = "username";
	   static final String PASS = "password";
	   
	   public static void main(String[] args) 
	   {
		   Scanner kbd = new Scanner(System.in);
		   
		   Connection conn; // the actual mysql server connection
		   Statement stmt;
		   //Connection conn = null;
		   //Statement stmt = null;
		   
	   		// strings for mysql hostname, userid, ...
			String db_host;
			String db_user;
			String db_password;
			String db_name;
		   
			// get user credentials and mysql server info
			System.out.print("Enter MySQL database hostname (or IP adress):");
			db_host = kbd.nextLine();

			System.out.print("Enter MySQL database username:");
			db_user = kbd.nextLine();

			System.out.print("Enter MySQL database password:");
			db_password=kbd.nextLine(); 

			// could also prompt for this, if desired
			db_name=db_user;
			
			db_host = "jdbc:mysql://" + db_host+ "/" + db_name;
			
		   // most mysql calls can cause exceptions,so we'll try to catch them. 
		   try
		   {
			   // set up the client (this machine) to use mysql
			   System.out.print("Initializing client DB subsystem ...");
			   Class.forName("com.mysql.jdbc.Driver");
			   System.out.println("Done.");
			   			   
			   // go out and connect to the mysql server
			   System.out.print("Connecting to remote DB ...");
			   conn = DriverManager.getConnection(db_host,db_user, db_password);
			   System.out.println("DB connection established.");
			   
			   // now, send mysql our query
			   stmt = conn.createStatement();
			   // initialize query string
			   String myQuery;
			   
			   // create table for cities if it does not exist already
			   myQuery = "create table if not exists Cities ("
			   			+ "cID varchar(100) not null, "
			   			+ "cName varchar(100) not null,"
			   			+ "PRIMARY KEY (cID) ); ";
			   stmt.execute(myQuery);
			   
			   // create table for airlines if it does not exist already
			   myQuery = "create table if not exists Airlines ("
				   		+ "aID varchar(100) not null, "
				   		+ "aName varchar(100) not null,"
				   		+ "PRIMARY KEY (aID) ); ";
			   stmt.execute(myQuery);
			   
			   // create table for flights if it does not exist already
			   myQuery = "create table if not exists Flights ("
				   		+ "aID varchar(100) not null, "
				   		+ "depID varchar(100) not null, "
				   		+ "arrID varchar(100) not null, "
				   		+ "cost integer not null, "
				   		+ "PRIMARY KEY (aID, depID, arrID) ); ";
			   stmt.execute(myQuery);
			   
			   // create -- or replace existing -- view for departures
			   myQuery = "create or replace view DeparturesView AS "
			   		    + "select distinct Cities.cID AS depID, Cities.cName AS depName "
			   		    + "from Flights, Cities "
			   		    + "where Flights.depID = cID;";
			   stmt.execute(myQuery);
			   
			   // create -- or replace existing -- view for arrivals
			   myQuery = "create or replace view ArrivalsView AS "
			   		    + "select distinct Cities.cID AS arrID, Cities.cName AS arrName "
			   		    + "from Flights, Cities "
			   		    + "where Flights.arrID = cID;";
			   stmt.execute(myQuery);
			   
			   System.out.println("+----------------------------------+");
			   System.out.println("| Welcome to your flights manager! |");
			   System.out.println("+----------------------------------+");
			   
			   String command = null;
			   
			   // get input from user until he decides to (q)uit
			   do{
				   System.out.print(">> ");
				   // read in option
				   command = kbd.next();
				   command = command.toLowerCase();
				   // user requested to add something
				   if (command.equals("a"))
				   {
					   // read in what does the user want to add
					   String op = kbd.next();
					   
					   if (op.equals("c"))  // build query to add a city
					   {
						   // read in data
						   String code = kbd.next();
						   String name = kbd.nextLine().trim();

						   myQuery = "insert into Cities (cID, cName) "
							   		+ "values (\'"+ code + "\', \'"
							   		+ name + "\');";
						   
						   try {
							   // add entry to cities table
							   stmt.executeUpdate(myQuery);
						   }
						   catch (SQLException e){
							   // Handle duplicate entry error
							   System.out.println("Error! City Code already exists!" );
						   }

					   }
					   else if (op.equals("a"))  // build query to add an airline
					   {
						   // read in data
						   String code = kbd.next();
						   String name = kbd.nextLine().trim();

						   myQuery = "insert into Airlines (aID, aName) "
									+ "values (\'"+ code + "\', \'"
							   		+ name + "\');";
						   
						   try {
							   // add entry to airlines table
							   stmt.executeUpdate(myQuery);
						   }
						   catch (SQLException e){
							   // Handle duplicate entry error
							   System.out.println("Error! Airline Abbreviation already exists!" );
						   }

					   }
					   else if (op.equals("f"))  // build query to add a flight
					   {
						   // read in data
						   String aCode = kbd.next();
						   String depC = kbd.next();
						   String arrC = kbd.next();
						   int cost = kbd.nextInt();
						   
						   myQuery = "insert into Flights (aID, depID, arrID, cost) "
							 		+ "values (\'"+ aCode + "\', \'"
							   		+ depC + "\', \'"
							   		+ arrC + "\', \'" 
							   		+ cost + "\');";
						   
						   try {
							   // add entry to flights table
							   stmt.executeUpdate(myQuery);
						   }
						   catch (SQLException e){
							   // Handle duplicate entry error
							   System.out.println("Error! Flight already exists!" );
						   }
					   }
					   else
					   {
						   // handle unknown operation
						   System.out.println("Unknown Operation");
						   continue;
					   }
				   }
				   // user requested to list something
				   else if (command.equals("l"))
				   {
					   // initialize result set
					   ResultSet rs = null;
					   // read in what the user wants to list
					   String op = kbd.next();
					   {
						   if (op.equals("c")) // list the cities
						   {
							   myQuery = "select * from Cities order by cID;";
							   rs = stmt.executeQuery(myQuery);
							   while (rs.next())
								   System.out.println(rs.getString("cID")+ " " + rs.getString("cName"));
						   }
						   else if (op.equals("a"))  // list the airlines
						   {
							   myQuery = "select * from Airlines order by aID;";
							   rs = stmt.executeQuery(myQuery);
							   while (rs.next())
								   System.out.println(rs.getString("aID")+ " " + rs.getString("aName"));
							}
						   else if (op.equals("f"))  // list all flights
						   {
							   myQuery = "select aName, depName, arrName, cost "
									    + "from Flights, ArrivalsView, Airlines, DeparturesView "
									    + "where ("
									    + "Flights.depID = DeparturesView.depID AND "
									    + "Flights.aID = Airlines.aID AND "
									    + "Flights.arrID = ArrivalsView.arrID) "
									    + "order by depName, arrName, cost, aName;";
							   rs = stmt.executeQuery(myQuery);
							   while (rs.next())
								   System.out.println(rs.getString("aName")+ ": " 
										   + rs.getString("depName") + " -> "
										   + rs.getString("arrName") + " $"
										   + rs.getInt("cost"));
						   }
						   else
						   {
							   // handle unknown operation
							   System.out.println("Unknown Operation");
							   continue;
						   }
						   
						   // clean up results
						   rs.close();
					   }
				   }
				   // user requested to find some flights
				   else if (command.equals("f"))
				   {
					   // initialize result set
					   ResultSet rs = null;
					   // read in data
					   String departure = kbd.next();
					   String arrival = kbd.next();
					   int connections = kbd.nextInt();
					   
					   if (connections == 0)  // find direct flights
					   {
						   // fetch flights that follow this criteria
						   myQuery = "select * "
								    + "from Flights "
								    + "where ("
								    + "depID = \'" + departure + "\' AND "
								    + "arrID = \'" + arrival + "\') "
								    + "order by cost;";
						   
						   rs = stmt.executeQuery(myQuery);
						   // print matching flights
						   if (rs.next())  // this checks the first entry of the set
						   {
							   // move pointer back to the beginning of the set
							   rs.beforeFirst();
							   while (rs.next())
								   System.out.println(rs.getString("depID") + " -> "
										   + rs.getString("arrID") + " : "
										   + rs.getString("aID") + " $"
										   + rs.getInt("cost"));
					   
						   }
						   else  // there are no flights
							   System.out.println("There are no flights that match this criteria.");

					   }
					   else if (connections == 1)  // find flights with one connection
					   {
						   // create -- or replace existing -- view for all possible first trips
						   myQuery = "create or replace view First_Trip_View AS "
						   		    + "select aID, depID, arrID as connection_A, cost "
						   		    + "from Flights "
						   		    + "where ("
						   		    + "depID = \'" + departure + "\' AND "
						   		    + "arrID <> \'" + arrival + "\');";
						   stmt.execute(myQuery);
						   // create -- or replace existing -- view for all possible second trips
						   myQuery = "create or replace view Second_Trip_View AS "
						   		    + "select "
						   		    + "Flights.aID, "
						   		    + "First_Trip_View.connection_A AS connection_B, "
						   		    + "Flights.arrID, "
						   		    + "Flights.cost "
						   		    + "from Flights, First_Trip_View "
						   		    + "where ("
						   		    + "Flights.depID = First_Trip_View.connection_A AND "
						   		    + "Flights.arrID = \'" + arrival + "\');";
						   stmt.execute(myQuery);
						   
						   // fetch flights that follow this criteria
						   myQuery = "select distinct "
						   			+ "First_Trip_View.aID AS airline_A, "
						   			+ "First_Trip_View.depID AS departure, "
						   			+ "connection_A AS connection, "
						   			+ "First_Trip_View.cost AS cost_A, "
						   			+ "Second_Trip_View.aID AS airline_B, "
						   			+ "Second_Trip_View.arrID AS arrival, "
						   			+ "Second_Trip_View.cost AS cost_B, "
						   			+ "First_Trip_View.cost + Second_Trip_View.cost AS totalCost "
								    + "from Flights, First_Trip_View, Second_Trip_View "
								    + "where ("
								    + "connection_A = connection_B AND "
								    + "First_Trip_View.depID = \'" + departure + "\' AND "
								    + "Second_Trip_View.arrID = \'" + arrival + "\')"
								    + "order by totalCost;";
						   rs = stmt.executeQuery(myQuery);
						   // print matching flights
						   if (rs.next())  // this checks the first entry of the set
						   {
							   // move pointer back to the beginning of the set
							   rs.beforeFirst();
							   while (rs.next())
							   {
								   System.out.println(rs.getString("departure") + " -> "
										   + rs.getString("connection") + " : "
										   + rs.getString("airline_A") + " $"
										   + rs.getInt("cost_A") + "; "
										   + rs.getString("connection") + " -> "
										   + rs.getString("arrival") + " : "
										   + rs.getString("airline_B") + " $"
										   + rs.getInt("cost_B") + ", for a total cost of $"
										   + rs.getInt("totalCost"));
							   }
						   }
						   else  // there are no flights
							   System.out.println("There are no flights that match this criteria.");
					   }
					  					   
					   // clean up results
					   rs.close();
				   }
				   // user input command is unknown
				   else if (!command.equals("q"))
					   System.out.println("Unknown Command");
				   
			   }while(!command.equals("q"));
			   
			   System.out.print("Disconnecting from DB ...");
			   //close db connection
	     	   conn.close();
	     	   stmt.close();
	     	   System.out.println("Done.");
			   System.out.println("+----------------------------------+");
			   System.out.println("|    Bye    �\\_(*-*)_/�    Bye!    |");
			   System.out.println("+----------------------------------+");
		   }
		   catch(SQLException se)
		   {
			   //Handle errors for JDBC
			   se.printStackTrace();
		   }
		   catch(Exception e)
		   {
			   // Handle any other exceptions
			   System.out.println("Exception"+e);
		   }
	   }
}




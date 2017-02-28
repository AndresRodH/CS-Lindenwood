
<?php

// start session
session_start();

$_SESSION["host"] = $_POST["host"];
$_SESSION["user"] = $_POST["user"];
$_SESSION["passw"] = $_POST["password"];

//remember, for our purposes the DB is the same as the username ...
$dbName = $_SESSION["user"];

// build the connection ...
echo "Attempting to connect to DB server: $host ...";
$conn = mysqli_connect($_SESSION["host"], $_SESSION["user"], $_SESSION["passw"], $dbName);

if (!$conn)
	// die is a php function that terminates execution. 
	//   the . means string concatenation in php. 
	die("Could not connect:".mysqli_connect_error());
else
	echo " connected!<br>";

$_SESSION["logged"] = true;

// try and create the Recipes table (if it does not exist) ...
$queryString = "create table if not exists Recipes 
		(RecipeName char(200) not null, Ingredient char(100) not null, Quantity integer, 
		primary key (RecipeName, Ingredient))";
$status = mysqli_query($conn, $queryString);

if (!$status)
    die("Error creating table: " . mysqli_error($conn));

// try and create the Inventory table (if it does not exist) ...
$queryString = "create table if not exists Inventory".
               " (Ingredient char(100) not null, Quantity integer, primary key (Ingredient))";
$status = mysqli_query($conn, $queryString);

if (!$status)
    die("Error creating table: " . mysqli_error($conn));


// close the connection (to be safe)
mysqli_close($conn);

// redirect user to main menu
header("Location: mainMenu.php");

?>

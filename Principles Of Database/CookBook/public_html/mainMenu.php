<?php 
	// check if session is open
	session_start();
	// if the user is not logged in, redirect to login page
	if (!$_SESSION["logged"])
		header("Location: index.html");
?>

<h1> Welcome to CookBook! </h1>

What would you like to do today?

<ul>
<li><a href="creAdd.php"> Create a recipe or add ingredient to a recipe </a></li>

<li><a href="list.php"> List a recipe's ingredients </a></li>

<li><a href="buy.php"> Buy all recipe ingredients from the store </a></li>

<li><a href="addIng.php"> Add ingredients to the store </a></li>
</ul>

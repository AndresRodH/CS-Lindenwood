<?php
// check if session is open
session_start();

// if the user is not logged in, redirect to login page
if (!$_SESSION["logged"])
	header("Location: index.html");

// establish connection
$conn = mysqli_connect($_SESSION["host"], $_SESSION["user"], $_SESSION["passw"], $_SESSION["user"]);

?>

<h1> Create Recipe/Add Ingredient </h1>

Enter all the details here: <br><br>


<form action="" method="post">

    Recipe Name: <input type=text name="recipe"></input> <br>
Ingredient Name: <input type=text name="ingredient"></input> <br>
       Quantity: <input type=number name="quantity" min="1"></input> <br>

<br>
<br>
<input type=submit value="GO" name="submitted"></input>
</form>

<form action="mainMenu.php">
    <input type=submit value="Main Menu"> </input>
</form>

<?php

if (isset($_POST["submitted"]))
{
    // get the info from user
    $recName = $_POST["recipe"];
    $ingName = $_POST["ingredient"];
    $quantity = $_POST["quantity"];

    // try adding these items to the Recipes table 
    $queryString = "insert into Recipes values (\"$recName\", \"$ingName\", $quantity) ";

    $status = mysqli_query($conn, $queryString);

    // if the recipe already exists, just update the ingredients
    if (!$status)
        echo "Info of this recipe is already in our records!";
    else
        echo "Added entry of <strong>$ingName</strong> into <strong>$recName</strong> recipe!";
    
    // close the connection (to be safe)
    mysqli_close($conn);
}

?>


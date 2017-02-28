<?php
// check if session is open
session_start();

// if the user is not logged in, redirect to login page
if (!$_SESSION["logged"])
	header("Location: index.html");

// establish connection
$conn = mysqli_connect($_SESSION["host"], $_SESSION["user"], $_SESSION["passw"], $_SESSION["user"]);

?>

<h1> Add Ingredients to the store </h1>

Which ingredient is going to fill the shelves? <br><br>


<form action="" method="post">

Ingredient Name: <input type=text name="ingredient"></input> <br>
       Quantity: <input type=number name="quantity" min="1"></input> <br>

<br>
<br>
<input type=submit value="GO" name="submitted"></input>
</form>

<form action="mainMenu.php">
    <input type=submit value="Main Menu"></input>
</form>

<?php

if (isset($_POST["submitted"]))
{
    // get the info from user
    $ingName = $_POST["ingredient"];
    $quantity = $_POST["quantity"];

    // try adding these items to the Inventory table 
    $queryString = "insert into Inventory values (\"$ingName\", $quantity) ";

    $status = mysqli_query($conn, $queryString);

    // if the Ingredient already exists, just update the quantity
    if (!$status){

        $queryString = "update Inventory set Quantity = Quantity+$quantity where Ingredient = \"$ingName\"";

        mysqli_query($conn, $queryString);
    }
    
    echo "Added $quantity of $ingName to inventory!";
    
    // close the connection (to be safe)
    mysqli_close($conn);
}
?>


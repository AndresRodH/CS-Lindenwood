<?php
// check if session is open
session_start();

// if the user is not logged in, redirect to login page
if (!$_SESSION["logged"])
	header("Location: index.html");

// establish connection
$conn = mysqli_connect($_SESSION["host"], $_SESSION["user"], $_SESSION["passw"], $_SESSION["user"]);

?>

<h1> Buy all recipe ingredients from the store </h1>

What are we shopping for today?: <br><br>


<form action="" method="post">

Recipe: <input type=text name="recipe"></input> <br>
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

    // turn off autocommit to avoid multiple users from making changes
    mysqli_autocommit($conn, FALSE);

    // try to get all of the ingredients from the store
    $queryString = "update Inventory, Recipes set Inventory.Quantity = Inventory.Quantity-Recipes.Quantity where Recipes.RecipeName = \"$recName\" AND Recipes.Ingredient = Inventory.Ingredient";

    mysqli_query($conn, $queryString);

    // get all the total quantities we got
    $queryString = "select Inventory.Quantity from Inventory, Recipes where RecipeName = \"$recName\" AND Inventory.Ingredient = Recipes.Ingredient";

    $check = mysqli_query($conn, $queryString);

    // see if there are any ingredients returned
    $success = true;

    if (mysqli_num_rows($check)==0)
        $success = false;

    // check all of them to see if we got everything
    while($row = mysqli_fetch_array($check))
    {
        if ($row[0] < 0 || mysqli_num_rows($check)==0)
        {
            // there are not enough ingredients in store
            $success = false;
            break;
        }
    }

    // if there are not enough ingredients, rollback
    if (!$success)
    {
        // undo changes and print the error message
        echo "It seems that there are not enough ingredients for that <strong>$recName</strong> you want to make... Try another recipe!";
        mysqli_rollback($conn);
    }
    else
    {
        // everything went accordingly, we can commit
        echo "Bring some of that <strong>$recName</strong> next time!";
        mysqli_commit($conn);
    }
    // close the connection (to be safe)
    mysqli_close($conn);
}
?>


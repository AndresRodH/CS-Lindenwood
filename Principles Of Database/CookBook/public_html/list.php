<?php
// check if session is open
session_start();

// if the user is not logged in, redirect to login page
if (!$_SESSION["logged"])
	header("Location: index.html");

// establish connection
$conn = mysqli_connect($_SESSION["host"], $_SESSION["user"], $_SESSION["passw"], $_SESSION["user"]);

?>

<h1> List a recipe's ingredients </h1>

What do you want to list?: <br><br>


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

    // get all the ingredients that match the description
    $queryString = "select * from Recipes where RecipeName = \"$recName\"";

    $stuff = mysqli_query($conn, $queryString);


    if ($recName != "")
        echo 
        "<table border='0' width = '300px' align='center'>
        <tr> <th>Ingredient</th> <th>Quantity</th> </tr>";

    while($row = mysqli_fetch_array($stuff))
    {
        echo "<tr> 
             <td align='center'>" . $row["Ingredient"] ."</td>
             <td align='center'>" . $row["Quantity"] . "</td>
             </tr>";
    }

    echo "</table>";

    // close the connection (to be safe)
    mysqli_close($conn);
}
?>


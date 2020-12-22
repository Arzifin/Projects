<?php
    include('konfig/tk_yhteys.php');
    include('IDetsija.php');

    if (isset($_POST['lopeta'])) {
        // Suljetaan yhteys
        pg_close($yhteys);
        // Vapautetaan sessiomuuttujat
        session_unset();
        // Poistetaan sessio
        session_destroy();
    }
?>

<!DOCTYPE html>
<html>
    <head>
		<title>Etusivu</title>
		<meta charset="utf-8" >
      	<meta name="author" content="Arttu Lehtola"/>
		<style>
			body {background-color: white;}
			p    {color: black;}
		</style>
	</head>
    <body>
        <p>Etusivu</p>
        <h1>TMI Sähkötärsky</h1>
        <br> <hr>
        <table>
            <tr> 
                <td> <a href="nyktyokohteet.php">Nykyiset työkohteet</a> </td> 
            </tr>
            <!-- ehkä tulossa? -->
            <tr> 
                <td> <a href="vanhat">Vanhat työkohteet</a> </td>
            </tr>
            
            <tr> 
                <td> <a href="tarvikelista.php">Tarvikelista</a> </td> 
            </tr>
        </table>
        <form action="lopetus.php">
            <br>
            <button type='submit' class='action'>Lopetus</button>
            <input type='hidden' name='lopeta'/>
        </form>
    </body>
</html>

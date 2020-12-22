<?php
    include('konfig/tk_yhteys.php');
    include('IDetsija.php');

    if (isset($_POST['lopeta'])) {
        // Suljetaan yhteys
        pg_close($yhteys);
        // Vapautetaan sessiomuuttujat
        session_unset();
        // Poistetaan nykyinen sessio
        session_destroy();
    }
?>

<!DOCTYPE html>
<html>
    <head>
		<title>Lopetus</title>
		<meta charset="utf-8" >
      	<meta name="author" content="Arttu Lehtola"/>
		<style>
			body {background-color: white;}
			p    {color: black;}
		</style>
	</head>

    <?php include('konfig/tk_yhteys.php'); ?>
    <body>
        <p><a href="index.php">Etusivu</a> > Lopetus</p>
        <div>
            <?php 
                $haku = "SELECT *
                        FROM asiakas;";

                $hakuTulos = pg_query($haku);
    
                if(!$lisaysTulos) {
                     echo "<h2>Yhteyden katkaisu onnistui</h2>";
                     echo "<h3>Kiitos ja hei hei!</h3>";
                     echo "<h4>Voit nyt sulkea selaimen.</h4>";
                     echo "<IMG SRC='https://media0.giphy.com/media/a3IWyhkEC0p32/giphy.gif'>";
                }
            ?>
        </div>
    </body>
</html>

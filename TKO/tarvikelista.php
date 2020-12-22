<?php 
    include('konfig/tk_yhteys.php');
    
    // Poiston kyselyt yms
    if (isset($_POST['tnimi'])) {
            
        $poistettava = $_POST['tnimi'];

        $poistoTulos = pg_query("DELETE FROM tarvike 
                                WHERE tnimi='$poistettava';");

        if(!$poistoTulos) {
            echo "Poisto epäonnistui<br>";
        }
        
        //echo "Kohde: $poistettava<br>";
    }
    
    // Arkistoinnin kyselyt yms
    if (isset($_POST['tnimiArkistoon'])) {
        
        $arkistoitava = $_POST['tnimiArkistoon'];
            
        $arkistoTulos = pg_query("UPDATE tarvike 
                                SET vtilanne=false
                                WHERE tnimi='$arkistoitava';");

        if(!$arkistoTulos) {
            echo "Poisto epäonnistui<br>";
        }

        //echo "ara ara arkistoon<br>";
    }

    if (isset($_POST['tnimiTakaisin'])) {
        //echo "Takaisin!<br>";
        $palautus = $_POST['tnimiTakaisin'];
            
        $palautusTulos = pg_query("UPDATE tarvike 
                                SET vtilanne=true
                                WHERE tnimi='$palautus';");

        if(!$palautusTulos) {
            echo "Palautus epäonnistui<br>";
        }

        //echo "ara ara arkistoon<br>";
    }

    // Lisäyksen kyselyt yms
    if (isset($_POST['nimi']) && isset($_POST['ostohinta']) 
    && isset($_POST['myyntihinta']) && isset($_POST['yksikko']) 
    && isset($_POST['varastossa']) && isset($_POST['alv'])) {

        $tni = $_POST['nimi'];
        $osh = $_POST['ostohinta'];
        $myh = $_POST['myyntihinta'];
        $yks = $_POST['yksikko'];
        $var = $_POST['varastossa'];
        if ($var === 't' || $var === 'tosi' || $var === 'totta' || $var === 'kyllä'
            || $var === 'true') {
            $var = 'true';
        }
        else {
            $var = 'false';
        }
        $arv = $_POST['alv'];

        $lisays = "INSERT INTO tarvike (tnimi, ohinta, mhinta, yksikko, vtilanne, alv)
                    VALUES ('$tni', $osh, $myh, '$yks', $var, $arv);";

        $lisaysTulos = pg_query($lisays);
        
        if(!$lisaysTulos) {
            echo "Lisäys epäonnistui<br>";
        }
        
    }
    

?>

<!DOCTYPE html>
<html>
	<head>
		<title>Tarvikelista</title>
		<meta charset="utf-8" >
      	<meta name="author" content="Arttu Lehtola"/>
		<style>
			body {background-color: white;}
			p    {color: black;}
            th {
                text-align: left;
            }
		</style>
	</head>
	<body>
        
	<p><a href="index.php">Etusivu</a> > Tarvikelista</p>
	
	<h1 class="otsikko">Tarvikelista</h1>

		<div class="container">
            <h3>Lisää tuote:</h3>

            <form method='post'>
                <table class="kysely" width="600px">
                    <tr>
                        <td><label for="ni">Nimi</label></td>
                    </tr>
                    <tr>
                        <td><input id="ni" type="text" name="nimi" size="30"></td>
                    </tr>
                    <tr>
                        <td><label for="oh">Ostohinta (€)</label></td>
                    </tr>
                    <tr>
                        <td><input id="oh" type="text" name="ostohinta" size="9"></td>
                    </tr>
                    <tr>
                        <td><label for="mh">Myyntihinta (€)</label></td>
                    </tr>
                    <tr>
                        <td><input id="mh" type="text" name="myyntihinta" size="9"></td>
                    </tr>
                    <tr>
                        <td><label for="yk">Yksikkö (mitta)</label></td>
                    </tr>
                    <tr>
                        <td><input id="yk" type="text" name="yksikko" size="5"></td>
                    </tr>
                    <tr>
                        <td><label for="va">Varastossa (true/false)</label></td>
                    </tr>
                    <tr>
                        <td><input id="va" type="text" name="varastossa" size="5"></td>
                    </tr>
                    <tr>
                        <td><label for="al">ALV (%)</label></td>
                    </tr>
                    <tr>
                        <td><input id="al" type="text" name="alv" size="3"></td>
                    </tr>
                    <tr>
                        <td><button type='submit' class='action' value='Lisaa'>Lisää </button></td>
                    </tr>
                </table>
            </form>
			<h3>Katalogin tuotteet:</h3>
			<?php
				$tulos1 = pg_query("SELECT DISTINCT tarvike.tnimi, tarvike.ohinta, 
                tarvike.mhinta, tarvike.yksikko, tarvike.vtilanne, tarvike.alv
                FROM tarvike
                WHERE tarvike.vtilanne=true
                ORDER BY tarvike.tnimi
                ;
                ");
                
                $tulos2 = pg_query("SELECT DISTINCT tarvike.tnimi, tarvike.ohinta, 
                tarvike.mhinta, tarvike.yksikko, tarvike.vtilanne, tarvike.alv
                FROM tarvike
                WHERE tarvike.vtilanne=false
                ORDER BY tarvike.tnimi
                ;
				");
					
				if (!$tulos1) {
					echo "Kysely 1 epäonnistui.\n";
                }
                
                if (!$tulos2) {
					echo "Kysely 2 epäonnistui.\n";
				}

				while ($rivi1 = pg_fetch_row($tulos1)) {
                    $tarvike = $rivi1[0];
                    $ostohinta = $rivi1[1];
                    $myyntihinta = $rivi1[2];
                    $yksikko = $rivi1[3];
                    $varastossa = $rivi1[4];
                    $alv = $rivi1[5]; 
            ?>        
   
                    <table class="lista" width="700px">
                        <tr>
						    <th width="10%">Tarvikkeen nimi</th>
						    <th width="5%">Myyntihinta</th>
						    <th width="5%">Ostohinta</th>
                            <th width="5%">Yksikkö</th>
                            <th width="5%">Varastossa</th>
						    <th width="5%">ALV</th>     
					    </tr>
                        <tr>
                            <td><?php echo "$tarvike"; ?></td>
                            <td><?php echo "$ostohinta €"; ?></td>
                            <td><?php echo "$myyntihinta €"; ?></td>
                            <td><?php echo "$yksikko"; ?></td>
                            <?php if ($rivi1[4] === "t") {
                                $rivi1[4] = "kyllä";
                            }
                            else {
                                $rivi1[4] = "ei";
                            }
                            ?>
                            <td><?php echo "$rivi1[4]"; ?></td>
                            <td><?php echo "$alv %"; ?></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>
                                <form method='post'>
                                    <button type='submit' class='action' value='Poista'>Poista </button>
                                    <input type='hidden' name='tnimi' value='<?php echo "$rivi1[0]"; ?>' />
                                </form>
                            </td>
                            <td>
                                <form method='post'>
                                    <button type='submit' class='action' value='Arkistoi'>Arkistoi </button>
                                    <input type='hidden' name='tnimiArkistoon' value='<?php echo "$rivi1[0]"; ?>' />
                                </form>
                            </td>
                            <td>
                                <!--action="tarvikkeenmuokkaus.php"-->
                                <form method='post' action="tarvikkeenmuokkaus.php">
                                    <button type='submit' class='action' value='Muokkaa'>Muokkaa </button>
                                    <input type='hidden' name='muokkaus' value='<?php echo "$rivi1[0]"; ?>' />
                                </form>
                            </td>
                        </tr>
                    </table>
            <?php
                }
			?>
            <h3>Vanhat katalogin tuotteet:</h3>

            <?php 
                while ($rivi2 = pg_fetch_row($tulos2)) {
                    $tarvike2 = $rivi2[0];
                    $ostohinta2 = $rivi2[1];
                    $myyntihinta2 = $rivi2[2];
                    $yksikko2 = $rivi2[3];
                    $varastossa2 = $rivi2[4];
                    $alv2 = $rivi2[5];
            ?>
                    
                    <table class="lista" width="700px">
                        <tr>
						    <th width="10%">Tarvikkeen nimi</th>
						    <th width="5%">Myyntihinta</th>
						    <th width="5%">Ostohinta</th>
                            <th width="5%">Yksikkö</th>
                            <th width="5%">Varastossa</th>
						    <th width="5%">ALV</th>     
					    </tr>
                        <tr>
                            <td><?php echo "$tarvike2"; ?></td>
                            <td><?php echo "$ostohinta2 €"; ?></td>
                            <td><?php echo "$myyntihinta2 €"; ?></td>
                            <td><?php echo "$yksikko2"; ?></td>
                            <?php if ($rivi2[4] === "t") {
                                $rivi1[4] = "kyllä";
                            }
                            else {
                                $rivi2[4] = "ei";
                            }
                            ?>
                            <td><?php echo "$rivi2[4]"; ?></td>
                            <?php if (empty($alv2)) { $alv2 = 24; } ?>
                            <td><?php echo "$alv2 %"; ?></td>
                        </tr>
                    </table>
                    <form method='post'>
                        <button type='submit' class='action' value='Palauta'>Palauta </button>
                        <input type='hidden' name='tnimiTakaisin' value='<?php echo "$rivi2[0]"; ?>' />
                    </form>

            <?php 
                }
            ?>
		</div>
	</body>
</html>
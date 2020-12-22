<?php 
	include ('konfig/tk_yhteys.php');
	include ('IDetsija.php');

	$ktunnus = $_SESSION['ktunnus'];
	$atunnus = $_SESSION['atunnus'];
?>

<!DOCTYPE html>
<html>
	<head>
		<title>Urakkatarjous</title>
		<meta charset="utf-8" >
      	<meta name="author" content="Arttu Lehtola"/>
		<style>
			body {background-color: white;}
			p    {color: black;}
		</style>
	</head>
	<body>
	
	<h1 class="otsikko">Urakkatarjous</h1>

		<div class="container">
			
			<div class="row">
			
				<h2>Asiakas:</h2>
				<?php
					$tulos1 = pg_query("SELECT atunnus, animi, kotiosoite, kotikaupunki, postinro FROM
					asiakas
					WHERE atunnus=$atunnus;
					");
					
					if (!$tulos1) {
						echo "Kysely epäonnistui.\n";
					}

					while ($rivi1 = pg_fetch_row($tulos1)) {
						echo "Asiakastunnus: $rivi1[0]";
						echo "<br />\n";
						echo "Asiakkaan nimi: $rivi1[1]";
						echo "<br />\n";
						echo "Kotisoite: $rivi1[2]";
						echo "<br />\n";
						echo "Kotikaupunki: $rivi1[3]";
						echo "<br />\n";
						echo "Postinumero: $rivi1[4]";
						echo "<br />\n";
					}
				?>
			</div>
			<h2>Työkohde:</h2>
			<?php
					$tulos2 = pg_query("SELECT tyokohde.ktunnus, tyokohde.raktyyppi, tyokohde.osoite, 
					tyokohde.kaupunki, tyokohde.atunnus 
					FROM tyokohde
					WHERE atunnus=$atunnus
					AND ktunnus=$ktunnus;
					");

					if (!$tulos2) {
						echo "Kysely epäonnistui.\n";
					}

					while ($rivi2 = pg_fetch_row($tulos2)) {
						echo "Työtunnus: $rivi2[0]";
						echo "<br />\n";
						echo "Rakennuksen tyyppi: $rivi2[1]";
						echo "<br />\n";
						echo "Osoite: $rivi2[2]";
						echo "<br />\n";
						echo "Kaupunki: $rivi2[3]";
						echo "<br />\n";
						echo "Asiakastunnus: $rivi2[4]";
						echo "<br />\n";
					}
			?>
			<h3>Arvioitu työn osuus hinnasta: </h3>
			<?php
					$tulos3 = pg_query("SELECT ttunnus, aletyo, aputyo, tyo, suunnittelu, 
										tyosuoritus.status, tyyppi 
										FROM tyosuoritus
										WHERE tyosuoritus.ttunnus IN
											(SELECT tyokohde.ktunnus
											FROM tyokohde
											WHERE tyokohde.atunnus=$atunnus
											AND tyosuoritus.ttunnus=$ktunnus
											);");

					if (!$tulos3) {
						echo "Kysely epäonnistui.\n";
					}
					$alvpro = 0;
					$tyosum = 0;
					$alvEur = 0;
					$aleEur = 0;
					$loppuSum = 0;
					while ($rivi3 = pg_fetch_row($tulos3)) {
						echo "Aputyö: $rivi3[2] tuntia<br>";
						$tyosum = $tyosum + $rivi3[2] * 35;
						echo "Työ: $rivi3[3] tuntia<br>";
						$tyosum = $tyosum + $rivi3[3] * 45;
						echo "Suunnittelua: $rivi3[4] tuntia<br>";
						$tyosum = $tyosum + $rivi3[4] * 55;
						$tyosum = $tyosum * ((100-$rivi3[1])/100);
						$alvEur = $tyosum * (24/100);
						if (rivi3[1] > 0) {
							echo "Alennusta $rivi3[1] %<br>";
						}
						$alvpro = $tyosum * (24/100);
						$aleEur = ($tyosum - $alvpro) * ($rivi3[1]/100);
						$loppuSum = $tyosum - $aleEur;
						echo "Hinta $loppuSum €<br>";
						echo "ALV: 24 % ($alvpro €) <br>";
					}

			?>
			<h3>Tarvitut tarvikkeet:</h3>
			<?php
					$tulos4 = pg_query("SELECT tarvitaan.tnimi, tarvitaan.maara, tarvitaan.aletar, 
					tarvike.yksikko, tarvike.alv, tarvike.mhinta
					FROM tarvitaan
					INNER JOIN tarvike ON tarvitaan.tnimi = tarvike.tnimi
					WHERE tarvitaan.ttunnus IN
						(SELECT tyosuoritus.ttunnus
						FROM tyosuoritus
						WHERE tyosuoritus.ttunnus IN 
							(SELECT tyokohde.ktunnus
							FROM tyokohde
							WHERE tyokohde.atunnus IN
									(SELECT asiakas.atunnus
									FROM asiakas
									WHERE asiakas.atunnus=$atunnus
									)));");

					if (!$tulos4) {
						echo "Kysely epäonnistui.\n";
					}
					$summa2 = 0;
					$alvsumma2 = 0;
					$alennusEur = 0;
					while ($rivi4 = pg_fetch_row($tulos4)) {
						$summa2 = 0;
						$alennusEur = 0;
						// Kapitalisoidaan 1. kirjain
						// lisäksi korostamme tuotteen tekstiä 
						$rivi4[0] = ucfirst($rivi4[0]);
						echo "<b>$rivi4[0]</b><br>";
						echo "$rivi4[1] * $rivi4[5] € / $rivi4[3]<br>";
						// Jos alv puuttuu, niin oletamme sen olevan 24%
						if (empty($rivi4[4])) {
							$rivi4[4] = 24;
						}
						$summa2 = $summa2 + $rivi4[5]*$rivi4[1];
						$alvEuroina = $rivi4[5] - (((100-$rivi4[4]) / 100) * $rivi4[5]);
						// Tarkistamme, että aleprosentti on olemassa
						if (!($rivi4[2] <= 0)) {
							$alennusEur = ($summa2 - $alvEuroina) * ($rivi4[2]/100);
							echo "Alennus: $rivi4[2] %<br>";				
						}
						echo "ALV: $rivi4[4] % ($alvEuroina € /  $rivi4[3])<br>";
						
						$keruuSum = $keruuSum + $summa2;
						$keruuAle = $keruuAle + $alennusEur;
					}
					$finaaliSum = $keruuSum + $loppuSum - $keruuAle;
					pg_close($yhteys);
				?>
			<h3>Yhteensä: 
				<?php echo "$rivi4[5] $finaaliSum €"; ?>
				<?php if ($keruuAle > 0) { echo "(Säästät: $keruuAle €)"; } ?>
			</h3>
		</div>
	</body>
</html>
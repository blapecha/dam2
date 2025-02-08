<!doctype html>
<html>
	<head>
		<script defer src="jocarsa | tan.js"></script>
		<style>
			table tbody tr td{text-align:center;padding:10px;}
		</style>
		
	</head>
	<body>
		<?php
			$columnas = 8;
			$filas = 16;
		?>
		<h1>Una tabla</h1>
		<table class="jocarsa-tan" style="color:rgb(234,0,0);background:rgb(0,255,0);">
			<thead>
				<tr>
					<?php
						for($i = 0;$i<$columnas;$i++){
							echo '<th>'.$i.'</th>';
						}
					?>
				</tr>
			</thead>
			<tbody>
				<?php
						for($i = 0;$i<$filas;$i++){
							echo '<tr>';
							for($j = 0;$j<$columnas;$j++){
								echo '<td>'.rand(1,500).'</td>';
							}
							echo '</tr>';
						}
					?>
			</tbody>
		</table>
		<h1>Otra tabla</h1>
		<table>
			<thead>
				<tr>
					<?php
						for($i = 0;$i<$columnas;$i++){
							echo '<th>'.$i.'</th>';
						}
					?>
				</tr>
			</thead>
			<tbody>
				<?php
						for($i = 0;$i<$filas;$i++){
							echo '<tr>';
							for($j = 0;$j<$columnas;$j++){
								$valor = rand(1,500);
								if($valor>250){
									echo '<td style="color:rgb(234,0,0);background:rgb(0,255,0);">'.$valor.'</td>';
								}else{
									echo '<td>'.$valor.'</td>';
								}
							}
							echo '</tr>';
						}
					?>
			</tbody>
		</table>
	</body>
</html>

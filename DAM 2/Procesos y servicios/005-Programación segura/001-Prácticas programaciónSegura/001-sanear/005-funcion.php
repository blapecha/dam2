<?php
	function sanear($elemento){
		$coleccion = ['DELETE', 'DROP', 'TRUNCATE'];
		$entradas = $elemento;
		foreach ($entradas as $clave=>$valor) {
			 
			$entrada = $clave;
			if (array_filter($coleccion, fn($elemento) => strpos($entrada, $elemento) !== false)) {
				 die("No permitido");
			} 
			$entrada = $valor;
			if (array_filter($coleccion, fn($elemento) => strpos($entrada, $elemento) !== false)) {
				 die("No permitido");
			} 
			$clave = "";
			 "select * from usuarios where nombre = '$clave' and password = '$valor'";

			 "select * from usuarios where nombre = '';DROP TABLE usuarios; SELECT *FROM usuarios where nombre = '' and password = '$valor'";
		}
	}
	sanear($_REQUEST);
?>
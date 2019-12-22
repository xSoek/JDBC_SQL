<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrUsuarioEsperado = array();

$arrEsperado["peticion"] = "deleteAll";

$arrEsperado["usuarioEliminarAll"] = $arrUsuarioEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoDelete($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="deleteAll" && isset($recibido["usuarioEliminarAll"])){
		$auxUsuario = $recibido["usuarioEliminar"];
			$auxCorrecto = true;
	}

	return $auxCorrecto;

}

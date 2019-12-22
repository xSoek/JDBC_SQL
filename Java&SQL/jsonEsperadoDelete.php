<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrUsuarioEsperado = array();

$arrEsperado["peticion"] = "delete";

$arrUsuarioEsperado["id"] = "AAA_00 (Un string)";

$arrEsperado["usuarioEliminar"] = $arrUsuarioEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoDelete($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="delete" && isset($recibido["usuarioEliminar"])){

		$auxUsuario = $recibido["usuarioEliminar"];
		if(isset($auxUsuario["id"])){
			$auxCorrecto = true;
		}

	}

	return $auxCorrecto;

}

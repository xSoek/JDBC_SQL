<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrUsuarioEsperado = array();

$arrEsperado["peticion"] = "add";

$arrUsuarioEsperado["id"] = "AAA_00 (Un string)";
$arrUsuarioEsperado["nombre"] = "Lorenzo (Un string)";
$arrUsuarioEsperado["numero"] = "2 (Un int)";

$arrEsperado["usuarioAnnadir"] = $arrUsuarioEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["usuarioAnnadir"])){

		$auxUsuario = $recibido["usuarioAnnadir"];
		if(isset($auxUsuario["nombre"]) && isset($auxUsuario["id"]) && isset($auxUsuario["numero"])){
			$auxCorrecto = true;
		}

	}

	return $auxCorrecto;

}

<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrUsuarioEsperado = array();

$arrEsperado["peticion"] = "update";

$arrUsuarioEsperado["id"] = "AAA_00 (Un string)";
$arrUsuarioEsperado["nombre"] = "Lorenzo (Un string)";
$arrUsuarioEsperado["numero"] = "2 (Un int)";

$arrEsperado["usuarioUpdate"] = $arrUsuarioEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="update" && isset($recibido["usuarioUpdate"])){

		$auxUsuario = $recibido["usuarioUpdate"];
		if(isset($auxUsuario["nombre"]) && isset($auxUsuario["id"]) && isset($auxUsuario["numero"])){
			$auxCorrecto = true;
		}

	}

	return $auxCorrecto;

}

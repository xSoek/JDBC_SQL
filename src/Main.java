import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import controlador.Controller;
import modelo.AccesoaDatos;
import modelo.BDManager;
import modelo.FicheroManager;
import vista.ConsoleView;

public class Main {
	public static void main(String[] args) {

		// Creacion de los Objetos
		ConsoleView menu = new ConsoleView();
		Controller cont = new Controller();
		AccesoaDatos bd = new BDManager();
		AccesoaDatos fichero = new FicheroManager();

		// Conectar Modelo, Controlador y Vista
		menu.conectaControlador(cont);
		cont.conectaControlador(bd, fichero);
		

		// Ejecuta el programa
		menu.menu();
	}

}

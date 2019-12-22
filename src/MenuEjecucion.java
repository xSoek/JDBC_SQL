import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import controlador.Controller;
import modelo.AccesoaDatos;
import modelo.BDManager;
import modelo.FicheroManager;
import modelo.HibernateManager;
import modelo.JsonManager;
import modelo.MongoDBManager;
import vista.ConsoleView;

public class MenuEjecucion {
	public static void main(String[] args) {

		// Creacion de los Objetos
		ConsoleView menu = new ConsoleView();
		Controller cont = new Controller();
		AccesoaDatos bd = new BDManager();
		AccesoaDatos fichero = new FicheroManager();
		AccesoaDatos hibernate = new HibernateManager();
		AccesoaDatos mongo = new MongoDBManager();
		AccesoaDatos php = new JsonManager();

		// Conectar Modelo, Controlador y Vista
		menu.conectaControlador(cont);
		cont.conectaControlador(bd, fichero, hibernate, mongo, php);
		

		// Ejecuta el programa
		menu.menu();
	}

}

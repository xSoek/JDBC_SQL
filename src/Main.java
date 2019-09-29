import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import controlador.AccesoaDatos;
import controlador.BDManager;
import controlador.Controller;
import controlador.FicheroManager;
import vista.ConsoleView;

public class Main {
	public static void main(String[] args) {
		ConsoleView menu = new ConsoleView();
		Controller cont = new Controller();
		AccesoaDatos bd = new BDManager();
		AccesoaDatos fichero = new FicheroManager();
		menu.conectaControlador(cont);
		cont.conectaControlador(bd, fichero);
		
		
		
		HashMap<String, HashMap<Integer, String>> codigoUsuario = new HashMap<String, HashMap<Integer, String>>();
		HashMap<Integer, String> hola = new HashMap<Integer, String>();
		
		codigoUsuario.put("AAA_01", hola);
		hola.put(1231, "Jorge");
		codigoUsuario.put("AAA_03", hola);
		hola.put(1111, "Jorge");
		codigoUsuario.put("AAA_02", hola);
		hola.put(0000, "Jorge");
		for (Entry<String, HashMap<Integer, String>> outer: codigoUsuario.entrySet()) {
			System.out.print(outer.getKey() + ";");
			for (Entry<Integer, String> inner : codigoUsuario.get(outer.getKey()).entrySet()) {
				System.out.print(inner.getKey() + ";" + inner.getValue() + "\n;");
			}
		}
		menu.menu();
	}

}

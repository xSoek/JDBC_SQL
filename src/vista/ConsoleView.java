package vista;

import java.util.Scanner;

import controlador.AccesoaDatos;
import controlador.BDManager;
import controlador.Controller;
import controlador.FicheroManager;

public class ConsoleView {
	
	private Controller control;

	public ConsoleView() {
		
	}
	public void conectaControlador(Controller control) {
		this.control = control;
		
	}

	

	public void menu() {

		Scanner in = new Scanner(System.in);
		int menuAccion = 0;
		do {
			System.out.println("\n\nEscoge una opción: ");
			System.out.println("1- Base de Datos");
			System.out.println("2- Fichero");

			int menuOpcion = in.nextInt();

			System.out.println("Escoge una acción: ");
			System.out.println("1- Leer todos los datos");
			System.out.println("2- Ingresar Datos");
			System.out.println("3- Pasar Datos a otro formato");
			System.out.println("0- Salir \nElija una opcion...");
			menuAccion = in.nextInt();

			if (menuOpcion == 1) {

				switch (menuAccion) {
				case 1:
					System.out.println("'Lectura de Base de Datos'");
					System.out.println("ID\tNombre\tNumero");
					control.leerTodosBD("bd");
					break;
				case 2:
					System.out.print("Introduzca el Id: ");
					String codigo = in.next();
					System.out.print("\nIntroduzca el nombre: ");
					String nombre = in.next();
					System.out.print("\nIntroduzca el numero: ");
					int numero = in.nextInt();
					control.agregarBD(codigo, nombre, numero, "bd");
					break;
				case 3:
					control.cambiarBD("bd");
					System.out.println("'Se ha sobreescrito satisfactoriamente el fichero con la BD'");
					break;
				case 0:
					System.out.println("SALIR");
					break;
				}
			} else if (menuOpcion == 2) {

				switch (menuAccion) {
				case 1:
					System.out.println("'Lectura de Base de Datos'");
					control.leerTodosBD("fich");
					break;
				case 2:
					System.out.print("Introduzca el Id: ");
					String codigo = in.next();
					System.out.print("\nIntroduzca el nombre: ");
					String nombre = in.next();
					System.out.print("\nIntroduzca el numero: ");
					int numero = in.nextInt();
					try {
						control.agregarBD(codigo, nombre, numero, "fich");
						System.out.println("Se han ingresado los datos satisfactoriamente");
					} catch (Exception e) { // TODO Auto-generated catch block
						System.out.println("Se ha producido un error al intentar 'Ingresar Datos'");
					}
					break;
				case 3:
					control.cambiarBD("fich");
					System.out.println("'Se ha sobreescrito satisfactoriamente el la BD con los datos del fichero'");
					break;
				case 0:
					System.out.println("SALIR");
					break;
				}
			}
		} while (menuAccion != 0);
	}
}

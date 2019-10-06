package vista;

import java.util.Scanner;

import controlador.Controller;
import modelo.AccesoaDatos;
import modelo.BDManager;
import modelo.FicheroManager;

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
			System.out.println();
			System.out.println("     //////////////////////////");
			System.out.println("    //  Escoge una opción:  //");
			System.out.println("   //  1- Base de Datos    //");
			System.out.println("  //   2- Fichero         //");
			System.out.println(" //////////////////////////");

			int menuOpcion = in.nextInt();

			System.out.println("Escoge una acción: ");
			System.out.println("1- Leer todos los datos");
			System.out.println("2- Ingresar Datos");
			System.out.println("3- Pasar Datos a otro formato");
			System.out.println("4- Borrar Dato");
			System.out.println("5- Borrar Todo");
			System.out.println("6- Buscar Dato");
			System.out.println("7- Modificar Dato");
			System.out.println("0- Salir \nElija una opcion...");
			menuAccion = in.nextInt();

			if (menuOpcion == 1) {

				switch (menuAccion) {
				case 1:
					System.out.println("'Lectura de Base de Datos'");
					System.out.println("ID\tNombre\tNumeros");
					System.out.println(control.leerTodosBD("bd"));
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
					break;
				case 4:
					System.out.print("Introduzca el Id: ");
					String code = in.next();
					System.out.println(control.borrarDatoBD(code, "bd"));
					break;
				case 5:
					System.out.println(control.borrarTodo("bd"));
					break;
				case 6:
					System.out.print("Introduzca el Id: ");
					String code1 = in.next();
					System.out.println(control.buscarDato(code1, "bd"));
					break;
				case 7:
					System.out.print("Buscar el Id: ");
					String code2 = in.next();
					System.out.print("Modificar nombre: ");
					String name1 = in.next();
					System.out.print("Modificar numero: ");
					int number1 = in.nextInt();
					control.ModificarDato(code2, name1, number1, "bd");
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

					} catch (Exception e) { // TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Se ha producido un error al intentar 'Ingresar Datos'");
					}
					break;
				case 3:
					control.cambiarBD("fich");
					break;
				case 4:
					System.out.print("Introduzca el Id: ");
					String code = in.next();
					System.out.println(control.borrarDatoBD(code, "fich"));
					break;
				case 5:
					System.out.println(control.borrarTodo("fich"));
					break;
				case 6:
					System.out.print("Introduzca el Id: ");
					String code1 = in.next();
					System.out.println(control.buscarDato(code1, "fich"));
					break;
				case 7:
					System.out.print("Buscar el Id: ");
					String code2 = in.next();
					System.out.print("Modificar nombre: ");
					String name1 = in.next();
					System.out.print("Modificar numero: ");
					int number1 = in.nextInt();
					control.ModificarDato(code2, name1, number1, "fich");
					break;
				case 0:
					System.out.println("SALIR");
					break;
				}
			}
		} while (menuAccion != 0);
	}
}

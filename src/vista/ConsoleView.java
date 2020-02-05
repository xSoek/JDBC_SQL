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
			System.out.println("        ///////////////////////////");
			System.out.println("       //    Escoge una opción: //");
			System.out.println("      //    1- Base de Datos   //");
			System.out.println("     //    2- Fichero         //");
			System.out.println("    //    3- Hibernate       //");
			System.out.println("   //    4- MongoDB         //");
			System.out.println("  //    5- PHP & JSON      //");
			System.out.println(" //    6- NodeJS          //");
			System.out.println("///////////////////////////");

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
					System.out.println("1- Fichero");
					System.out.println("2- Hibernate");
					System.out.println("3- MongoDB");
					System.out.println("4- PHP & JSON");
					System.out.println("5- API Node");

					int opcionCambio = in.nextInt();
					if (opcionCambio == 1) {
						control.cambiarBD("bd", "fich");
					} else if (opcionCambio == 2) {
						control.cambiarBD("bd", "hbnt");
					} else if (opcionCambio == 3) {
						control.cambiarBD("bd", "mongo");
					} else if (opcionCambio == 4) {
						control.cambiarBD("bd", "php");
					} else if (opcionCambio == 5) {
						control.cambiarBD("bd", "node");
					}

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
					System.out.println("1- BD SQL");
					System.out.println("2- Hibernate");
					System.out.println("3- MongoDB");
					System.out.println("4- PHP & JSON");
					System.out.println("5- API Node");
					int opcionCambio = in.nextInt();

					if (opcionCambio == 1) {
						control.cambiarBD("fich", "bd");
					} else if (opcionCambio == 2) {
						control.cambiarBD("fich", "hbnt");
					} else if (opcionCambio == 3) {
						control.cambiarBD("fich", "mongo");
					} else if (opcionCambio == 4) {
						control.cambiarBD("fich", "php");
					} else if (opcionCambio == 5) {
						control.cambiarBD("fich", "node");
					}

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
			} else if (menuOpcion == 3) {

				// MENU HIBERNATE
				switch (menuAccion) {
				case 1:
					System.out.println("'Lectura de Base de Datos'");

					System.out.println(control.leerTodosBD("hbnt"));
					break;
				case 2:
					System.out.print("Introduzca el Id: ");
					String codigo = in.next();
					System.out.print("\nIntroduzca el nombre: ");
					String nombre = in.next();
					System.out.print("\nIntroduzca el numero: ");
					int numero = in.nextInt();
					try {
						control.agregarBD(codigo, nombre, numero, "hbnt");

					} catch (Exception e) { // TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Se ha producido un error al intentar 'Ingresar Datos'");
					}
					break;
				case 3:
					System.out.println("1- BD SQL");
					System.out.println("2- Fichero");
					System.out.println("3- MongoDB");
					System.out.println("4- PHP & JSON");
					System.out.println("5- API Node");
					int opcionCambio = in.nextInt();

					if (opcionCambio == 1) {
						control.cambiarBD("hbnt", "bd");
					} else if (opcionCambio == 2) {
						control.cambiarBD("hbnt", "fich");
					} else if (opcionCambio == 3) {
						control.cambiarBD("hbnt", "mongo");
					} else if (opcionCambio == 4) {
						control.cambiarBD("hbnt", "php");
					} else if (opcionCambio == 5) {
						control.cambiarBD("hbnt", "node");
						
					}

					break;
				case 4:
					System.out.print("Introduzca el Id: ");
					String code = in.next();
					System.out.println(control.borrarDatoBD(code, "hbnt"));
					break;
				case 5:
					System.out.println(control.borrarTodo("hbnt"));
					break;
				case 6:
					System.out.print("Introduzca el Id: ");
					String code1 = in.next();
					System.out.println(control.buscarDato(code1, "hbnt"));
					break;
				case 7:
					System.out.print("Buscar el Id: ");
					String code2 = in.next();
					System.out.print("Modificar nombre: ");
					String name1 = in.next();
					System.out.print("Modificar numero: ");
					int number1 = in.nextInt();
					control.ModificarDato(code2, name1, number1, "hbnt");
					break;
				case 0:
					System.out.println("SALIR");
					break;
				}
			} else if (menuOpcion == 4) {

				// MENU HIBERNATE
				switch (menuAccion) {
				case 1:
					System.out.println("'Lectura de Base de Datos'");

					System.out.println(control.leerTodosBD("mongo"));
					break;
				case 2:
					System.out.print("Introduzca el Id: ");
					String codigo = in.next();
					System.out.print("\nIntroduzca el nombre: ");
					String nombre = in.next();
					System.out.print("\nIntroduzca el numero: ");
					int numero = in.nextInt();
					try {
						control.agregarBD(codigo, nombre, numero, "mongo");

					} catch (Exception e) { // TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Se ha producido un error al intentar 'Ingresar Datos'");
					}
					break;
				case 3:
					System.out.println("1- BD SQL");
					System.out.println("2- Fichero");
					System.out.println("3- Hibernate");
					System.out.println("4- PHP & JSON");
					System.out.println("5- API Node");
					int opcionCambio = in.nextInt();

					if (opcionCambio == 1) {
						control.cambiarBD("mongo", "bd");
					} else if (opcionCambio == 2) {
						control.cambiarBD("mongo", "fich");
					} else if (opcionCambio == 3) {
						control.cambiarBD("mongo", "hbnt");
					} else if (opcionCambio == 4) {
						control.cambiarBD("mongo", "php");
					} else if (opcionCambio == 5) {
						control.cambiarBD("mongo", "node");
					}

					break;
				case 4:
					System.out.print("Introduzca el Id: ");
					String code = in.next();
					System.out.println(control.borrarDatoBD(code, "mongo"));
					break;
				case 5:
					System.out.println(control.borrarTodo("mongo"));
					break;
				case 6:
					System.out.print("Introduzca el Id: ");
					String code1 = in.next();
					System.out.println(control.buscarDato(code1, "mongo"));
					break;
				case 7:
					System.out.print("Buscar el Id: ");
					String code2 = in.next();
					System.out.print("Modificar nombre: ");
					String name1 = in.next();
					System.out.print("Modificar numero: ");
					int number1 = in.nextInt();
					control.ModificarDato(code2, name1, number1, "mongo");
					break;
				case 0:
					System.out.println("SALIR");
					break;
				}
			} else if (menuOpcion == 5) {

				// MENU HIBERNATE
				switch (menuAccion) {
				case 1:
					System.out.println("'Lectura de Base de Datos'");

					System.out.println(control.leerTodosBD("php"));
					break;
				case 2:
					System.out.print("Introduzca el Id: ");
					String codigo = in.next();
					System.out.print("\nIntroduzca el nombre: ");
					String nombre = in.next();
					System.out.print("\nIntroduzca el numero: ");
					int numero = in.nextInt();
					try {
						control.agregarBD(codigo, nombre, numero, "php");

					} catch (Exception e) { // TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Se ha producido un error al intentar 'Ingresar Datos'");
					}
					break;
				case 3:
					System.out.println("1- BD SQL");
					System.out.println("2- Fichero");
					System.out.println("3- Hibernate");
					System.out.println("4- Mongo");
					System.out.println("5- API Node");
					int opcionCambio = in.nextInt();

					if (opcionCambio == 1) {
						control.cambiarBD("php", "bd");
					} else if (opcionCambio == 2) {
						control.cambiarBD("php", "fich");
					} else if (opcionCambio == 3) {
						control.cambiarBD("php", "hbnt");
					} else if (opcionCambio == 4) {
						control.cambiarBD("php", "mongo");
					} else if (opcionCambio == 5) {
						control.cambiarBD("php", "node");
					}

					break;
				case 4:
					System.out.print("Introduzca el Id: ");
					String code = in.next();
					System.out.println(control.borrarDatoBD(code, "php"));
					break;
				case 5:
					System.out.println(control.borrarTodo("php"));
					break;
				case 6:
					System.out.print("Introduzca el Id: ");
					String code1 = in.next();
					System.out.println(control.buscarDato(code1, "php"));
					break;
				case 7:
					System.out.print("Buscar el Id: ");
					String code2 = in.next();
					System.out.print("Modificar nombre: ");
					String name1 = in.next();
					System.out.print("Modificar numero: ");
					int number1 = in.nextInt();
					control.ModificarDato(code2, name1, number1, "php");
					break;
				case 0:
					System.out.println("SALIR");
					break;
				}
			} else if (menuOpcion == 6) {

				// MENU HIBERNATE
				switch (menuAccion) {
				case 1:
					System.out.println("'Lectura de Base de Datos'");
					System.out.println(control.leerTodosBD("node"));
					break;
				case 2:
					System.out.print("Introduzca el Id: ");
					String codigo = in.next();
					System.out.print("\nIntroduzca el nombre: ");
					String nombre = in.next();
					System.out.print("\nIntroduzca el numero: ");
					int numero = in.nextInt();
					try {
						control.agregarBD(codigo, nombre, numero, "node");

					} catch (Exception e) { // TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Se ha producido un error al intentar 'Ingresar Datos'");
					}
					break;
				case 3:
					System.out.println("1- BD SQL");
					System.out.println("2- Fichero");
					System.out.println("3- Hibernate");
					System.out.println("4- Mongo");
					System.out.println("5- PHP & JSON");
					int opcionCambio = in.nextInt();

					if (opcionCambio == 1) {
						control.cambiarBD("node", "bd");
					} else if (opcionCambio == 2) {
						control.cambiarBD("node", "fich");
					} else if (opcionCambio == 3) {
						control.cambiarBD("node", "hbnt");
					} else if (opcionCambio == 4) {
						control.cambiarBD("node", "mongo");
					} else if (opcionCambio == 5) {
						control.cambiarBD("node", "php");
					}

					break;
				case 4:
					System.out.print("Introduzca el Id: ");
					String code = in.next();
					System.out.println(control.borrarDatoBD(code, "node"));
					break;
				case 5:
					System.out.println(control.borrarTodo("node"));
					break;
				case 6:
					System.out.print("Introduzca el Id: ");
					String code1 = in.next();
					System.out.println(control.buscarDato(code1, "node"));
					break;
				case 7:
					System.out.print("Buscar el Id: ");
					String code2 = in.next();
					System.out.print("Modificar nombre: ");
					String name1 = in.next();
					System.out.print("Modificar numero: ");
					int number1 = in.nextInt();
					control.ModificarDato(code2, name1, number1, "node");
					break;
				case 0:
					System.out.println("SALIR");
					break;
				}
			}

		} while (menuAccion != 0);
	}
}

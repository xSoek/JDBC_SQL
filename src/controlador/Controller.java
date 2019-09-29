package controlador;

import modelo.AccesoaDatos;

public class Controller {

	private AccesoaDatos bd, fichero;

	public void conectaControlador(AccesoaDatos bd, AccesoaDatos fichero) {
		this.bd = bd;
		this.fichero = fichero;
	}

	public void leerTodosBD(String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			bd.LeerTodos();
		else if (BdoFich.equalsIgnoreCase("fich"))
			fichero.LeerTodos();
	}

	public void agregarBD(String codigo, String nombre, int numero, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			bd.AgregarDato(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("fich"))
			fichero.AgregarDato(codigo, nombre, numero);
	}

	public void cambiarBD(String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			bd.PasarBDFichero();
		else if (BdoFich.equalsIgnoreCase("fich"))
			fichero.PasarBDFichero();
	}

}

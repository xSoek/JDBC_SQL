package controlador;

import java.util.HashMap;

import modelo.AccesoaDatos;
import modelo.FicheroManager;
import modelo.Persona;

public class Controller {

	public HashMap<Integer, Persona> personas = new HashMap<Integer, Persona>();
	private AccesoaDatos bd, fichero;

	public void conectaControlador(AccesoaDatos bd, AccesoaDatos fichero) {
		this.bd = bd;
		this.fichero = fichero;
	}

	public String leerTodosBD(String BdoFich) {

		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.LeerTodos();
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.LeerTodos();
		else
			return "Error, formato no encontrado";
	}

	public String agregarBD(String codigo, String nombre, int numero, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.AgregarDato(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.AgregarDato(codigo, nombre, numero);
		else
			return "Error, formato no encontrado";
	}

	public void cambiarBD(String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd")) {
			fichero.recibirHashMap(bd.escribeHashMap());
		} else if (BdoFich.equalsIgnoreCase("fich")) {
			bd.recibirHashMap(fichero.escribeHashMap());
		}
	}

	public String borrarDatoBD(String code, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.BorrarDato(code);
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.BorrarDato(code);
		else
			return "Error, formato no encontrado";
	}

	public String borrarTodo(String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd")) {
			return bd.BorrarTodo();
		} else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.BorrarTodo();
		else
			return "Error, formato no encontrado";
	}

	public String buscarDato(String code, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.Buscar(code);
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.Buscar(code);
		else
			return "Error, formato no encontrado";
	}

	public String ModificarDato(String codigo, String nombre, int numero, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.Modificar(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.Modificar(codigo, nombre, numero);
		else
			return "Error, formato no encontrado";
	}
}

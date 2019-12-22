package controlador;

import java.util.HashMap;

import modelo.AccesoaDatos;
import modelo.FicheroManager;
import modelo.MongoDBManager;
import modelo.Persona;

public class Controller {

	public HashMap<Integer, Persona> personas = new HashMap<Integer, Persona>();
	private AccesoaDatos bd, fichero, hibernate, mongo, php;

	public void conectaControlador(AccesoaDatos bd, AccesoaDatos fichero, AccesoaDatos hibernate, AccesoaDatos mongo,
			AccesoaDatos php) {
		this.bd = bd;
		this.fichero = fichero;
		this.hibernate = hibernate;
		this.mongo = mongo;
		this.php = php;
	}

	public String leerTodosBD(String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.LeerTodos();
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.LeerTodos();
		else if (BdoFich.equalsIgnoreCase("hbnt"))
			return hibernate.LeerTodos();
		else if (BdoFich.equalsIgnoreCase("mongo"))
			return mongo.LeerTodos();
		else if (BdoFich.equalsIgnoreCase("php"))
			return php.LeerTodos();
		else
			return " - Error, formato no encontrado - ";
	}

	public String agregarBD(String codigo, String nombre, int numero, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.AgregarDato(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.AgregarDato(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("hbnt"))
			return hibernate.AgregarDato(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("mongo"))
			return mongo.AgregarDato(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("php"))
			return php.AgregarDato(codigo, nombre, numero);
		else
			return " - Error, formato no encontrado - ";
	}

	public void cambiarBD(String BdoFich, String cambioA) {
		if (BdoFich.equalsIgnoreCase("bd")) {
			if (cambioA.equalsIgnoreCase("fich"))
				fichero.recibirHashMap(bd.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("hbnt"))
				hibernate.recibirHashMap(bd.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("mongo"))
				mongo.recibirHashMap(bd.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("php"))
				php.recibirHashMap(bd.escribeHashMap());

		} else if (BdoFich.equalsIgnoreCase("fich")) {
			if (cambioA.equalsIgnoreCase("bd"))
				bd.recibirHashMap(fichero.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("hbnt"))
				hibernate.recibirHashMap(fichero.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("mongo"))
				mongo.recibirHashMap(fichero.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("php"))
				php.recibirHashMap(fichero.escribeHashMap());

		} else if (BdoFich.equalsIgnoreCase("hbnt")) {
			if (cambioA.equalsIgnoreCase("bd"))
				bd.recibirHashMap(hibernate.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("fich"))
				fichero.recibirHashMap(hibernate.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("mongo"))
				mongo.recibirHashMap(hibernate.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("php"))
				php.recibirHashMap(hibernate.escribeHashMap());

		} else if (BdoFich.equalsIgnoreCase("mongo")) {
			if (cambioA.equalsIgnoreCase("bd"))
				bd.recibirHashMap(mongo.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("fich"))
				fichero.recibirHashMap(mongo.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("hbnt"))
				hibernate.recibirHashMap(mongo.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("php"))
				php.recibirHashMap(mongo.escribeHashMap());
			
		} else if (BdoFich.equalsIgnoreCase("php")) {
			if (cambioA.equalsIgnoreCase("bd"))
				bd.recibirHashMap(php.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("fich"))
				fichero.recibirHashMap(php.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("hbnt"))
				hibernate.recibirHashMap(php.escribeHashMap());
			else if (cambioA.equalsIgnoreCase("mongo"))
				mongo.recibirHashMap(php.escribeHashMap());
		}

	}

	public String borrarDatoBD(String code, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.BorrarDato(code);
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.BorrarDato(code);
		else if (BdoFich.equalsIgnoreCase("hbnt"))
			return hibernate.BorrarDato(code);
		else if (BdoFich.equalsIgnoreCase("mongo"))
			return mongo.BorrarDato(code);
		else if (BdoFich.equalsIgnoreCase("php"))
			return php.BorrarDato(code);
		else
			return " - Error, formato no encontrado - [Se aconseja: REVISA borrarDatoBD at Controller.java]";
	}

	public String borrarTodo(String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd")) {
			return bd.BorrarTodo();
		} else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.BorrarTodo();
		else if (BdoFich.equalsIgnoreCase("hbnt"))
			return hibernate.BorrarTodo();
		else if (BdoFich.equalsIgnoreCase("mongo"))
			return mongo.BorrarTodo();
		else if (BdoFich.equalsIgnoreCase("php"))
			return php.BorrarTodo();
		else
			return " - Error, formato no encontrado - ";
	}

	public String buscarDato(String code, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.Buscar(code);
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.Buscar(code);
		else if (BdoFich.equalsIgnoreCase("hbnt"))
			return hibernate.Buscar(code);
		else if (BdoFich.equalsIgnoreCase("mongo"))
			return mongo.Buscar(code);
		else if (BdoFich.equalsIgnoreCase("php"))
			return php.Buscar(code);
		else
			return " - Error, formato no encontrado - ";
	}

	public String ModificarDato(String codigo, String nombre, int numero, String BdoFich) {
		if (BdoFich.equalsIgnoreCase("bd"))
			return bd.Modificar(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("fich"))
			return fichero.Modificar(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("hbnt"))
			return hibernate.Modificar(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("mongo"))
			return mongo.Modificar(codigo, nombre, numero);
		else if (BdoFich.equalsIgnoreCase("php"))
			return php.Modificar(codigo, nombre, numero);
		else
			return " - Error, formato no encontrado - ";
	}
}

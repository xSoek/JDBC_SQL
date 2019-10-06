package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;
import controlador.Controller;
import java.util.Map.Entry;
import vista.ConsoleView;

public class BDManager implements AccesoaDatos {

	public Persona persona;
	ConexionBD conn = new ConexionBD();
	Scanner sc = new Scanner(System.in);

	private String BDFich = "";
	Controller control = new Controller();

	public void conectarControl(Controller control) {
		this.control = control;
	}

	public BDManager() {

	}

	public HashMap<Integer, Persona> escribeHashMap() {
		String query = "SELECT * FROM `inicio` ORDER BY `inicio`.`Id` DESC";
		try {

			int columna = 3;
			Statement stmt = conn.getConn().createStatement();
			ResultSet rset = stmt.executeQuery(query);
			int filas = 0;
			// Recoge las filas de la query, las imprime y las guarda para un uso posterior
			while (rset.next()) {
				filas++;
				persona = new Persona();
				for (int i = 1; i <= columna; i++) {
					if (i == 1) {
						persona.setId(rset.getString(i));
						BDFich = BDFich + rset.getString(i) + ":";
					} else if (i == 2) {
						persona.setNombre(rset.getString(i));
						BDFich = BDFich + rset.getString(i) + ":";
					} else {
						persona.setNumero(rset.getInt(i));
						BDFich = BDFich + Integer.toString(rset.getInt(i)) + "\n:";
					}
				}
				control.personas.put(filas, persona);
			}
			rset.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return control.personas;
	}

	@Override
	public String LeerTodos() {
		// TODO Auto-generated method stub
		Persona person = new Persona();
		String persona = "";
		try {
			int columna = 3;
			Statement stmt = conn.getConn().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM basedatos1.inicio");

			while (rset.next()) {
				for (int i = 1; i <= columna; i++) {
					if (i == 1) {
						person.setId(rset.getString(i));

					} else if (i == 2) {

						person.setNombre(rset.getString(i));

					} else {
						person.setNumero(rset.getInt(i));

					}
				}
				persona += person.getId() + "\t" + person.getNombre() + "\t" + person.getNumero() + "\t\n";
			}
			rset.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return persona;

	}

	@Override
	public String AgregarDato(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		Persona person = new Persona();
		try {

			// Utilizo la clase Persona para guardar los datos para luego insertarlos en la
			// BD
			person.setId(codigo);
			person.setNombre(nombre);
			person.setNumero(numero);

			int r = 0;

			String query = "INSERT INTO inicio VALUES ('" + person.getId() + "', '" + person.getNombre() + "', "
					+ person.getNumero() + ")";
			Statement stmt;
			stmt = conn.getConn().createStatement();
			r = stmt.executeUpdate(query);
			stmt.close();
			return "Dato agregado con éxito";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error al agregar el dato";
		}
	}

	@Override
	public boolean recibirHashMap(HashMap<Integer, Persona> persona) {
		// TODO Auto-generated method stub
		String query = "";
		String truncate = "TRUNCATE `basedatos1`.`inicio`";
		int r = 0;

		try {
			Statement stmt;
			stmt = conn.getConn().createStatement();
			r = stmt.executeUpdate(truncate);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Integer i : persona.keySet()) {
			query = "INSERT INTO inicio VALUES ('" + persona.get(i).getId() + "', '" + persona.get(i).getNombre()
					+ "', " + persona.get(i).getNumero() + ")";
			Statement stmt;
			try {
				stmt = conn.getConn().createStatement();
				r = stmt.executeUpdate(query);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public String BorrarDato(String codigo) {
		// TODO Auto-generated method stub

		try {

			int r = 0;

			String query = "DELETE FROM inicio WHERE Id = '" + codigo + "'";
			Statement stmt;
			stmt = conn.getConn().createStatement();
			r = stmt.executeUpdate(query);
			stmt.close();
			return "Se ha eliminado con éxito";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
			return "Ha ocurrido un error al eliminar";
			
		}

	}

	@Override
	public String BorrarTodo() {
		// TODO Auto-generated method stub
		int cont = 0;
		Statement vaciar;
		try {

			// Se vacia la BD para que no haya conflictos con la PRIMARY KEY y los numero
			// UNICOS
			vaciar = conn.getConn().createStatement();
			cont = vaciar.executeUpdate("TRUNCATE inicio");
			return "Se ha vaciado con exito el contenido de la BD";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "Se ha producido un error al eliminar";
		}

	}

	@Override
	public String Buscar(String codigo) {
		// TODO Auto-generated method stub
		Persona person = new Persona();
		String persona = "";
		try {
			int columna = 3;
			Statement stmt = conn.getConn().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM basedatos1.inicio WHERE Id = '" + codigo + "'");

			while (rset.next()) {
				for (int i = 1; i <= columna; i++) {
					if (i == 1) {
						person.setId(rset.getString(i));
					} else if (i == 2) {
						person.setNombre(rset.getString(i));
					} else {
						person.setNumero(rset.getInt(i));
					}
				}
				persona += person.getId() + "\t" + person.getNombre() + "\t" + person.getNumero() + "\t\n";
			}
			rset.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return persona;
	}

	@Override
	public String Modificar(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub

		int cont = 0;
		Statement vaciar;
		try {

			// Se vacia la BD para que no haya conflictos con la PRIMARY KEY y los numero
			// UNICOS
			vaciar = conn.getConn().createStatement();
			cont = vaciar.executeUpdate(
					"UPDATE inicio SET Nombre = '" + nombre + "', Numero = " + numero + " WHERE Id = '" + codigo + "'");
			return "Usuario modificado con éxito";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "Error al modificar usuario";
		}
	}

}

package controlador;

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
import java.util.Map.Entry;

import modelo.ConexionBD;
import modelo.Persona;
import vista.ConsoleView;

public class BDManager implements AccesoaDatos {

	ConexionBD conn = new ConexionBD();
	Scanner sc = new Scanner(System.in);
	Persona person = new Persona();
	private String BDFich = "";
	private Controller control;

	public void conectarControl (Controller control) {
		this.control= control;
	}

	public BDManager() {
	}

	public String Consulta(String query) {
		try {
			String code = null;
			String name = null;
			int number = 31101;
			int columna = 3;
			Statement stmt = conn.getConn().createStatement();
			ResultSet rset = stmt.executeQuery(query);

			while (rset.next()) {
				for (int i = 1; i <= columna; i++) {
					if (i == 1) {
						code = rset.getString(i);
						BDFich = BDFich + rset.getString(i) + ":";
						System.out.print(rset.getString(i) + "\t");
					} else if (i == 2) {
						name = rset.getString(i);
						BDFich = BDFich + rset.getString(i) + ":";
						System.out.print(rset.getString(i) + "\t");
					} else {
						number = rset.getInt(i);
						BDFich = BDFich + Integer.toString(rset.getInt(i)) + "\n:";
						System.out.print(rset.getInt(i) + "\t");
					}
				}
				System.out.println();
			}
			rset.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return BDFich;
	}

	@Override
	public void LeerTodos() {
		// TODO Auto-generated method stub
		try {
			int columna = 3;
			Statement stmt = conn.getConn().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM basedatos1.inicio");
			while (rset.next()) {
				for (int i = 1; i <= columna; i++) {
					if (i == 1) {

						System.out.print(rset.getString(i) + "\t");
					} else if (i == 2) {

						System.out.print(rset.getString(i) + "\t");
					} else {

						System.out.print(rset.getInt(i) + "\t");
					}
				}
				System.out.println();
			}
			rset.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void AgregarDato(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void PasarBDFichero() {
		// TODO Auto-generated method stub
		String uno = Consulta("SELECT * FROM `inicio` ORDER BY `inicio`.`Id` DESC");
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File("Fichero.txt");
			// Si el archivo no existe, se crea

			file.delete();
			file.createNewFile();

			// flag true, indica adjuntar información al archivo.
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(uno);
			System.out.println("información agregada!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Cierra instancias de FileWriter y BufferedWriter
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}

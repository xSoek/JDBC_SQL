package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import modelo.ConexionBD;
import modelo.Persona;

public class FicheroManager implements AccesoaDatos {
	BDManager bd = new BDManager();
	ConexionBD conn = new ConexionBD();
	Persona personFich = new Persona();
	
	
	@Override
	public void LeerTodos() {
		// TODO Auto-generated method stub
		try {
			FileReader entrada = new FileReader("Fichero.txt");
			int c = entrada.read();
			while (c != -1) {
				char letra = (char) c;
				System.out.print(letra);
				c = entrada.read();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha encontrado el archivo");
		}
	}

	@Override
	public void AgregarDato(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			personFich.setId(codigo);
			personFich.setNombre(nombre);
			personFich.setNumero(numero);

			File file = new File("Fichero.txt");
			// Si el archivo no existe, se crea!
			if (!file.exists()) {
				file.createNewFile();
			}
			// flag true, indica adjuntar información al archivo.
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(personFich.getId() + ";" + personFich.getNombre() + ";" + personFich.getNumero() + "\n;");
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

	@Override
	public void PasarBDFichero() {
		// TODO Auto-generated method stub
		try {
			FileReader entrada = new FileReader("Fichero.txt");
			int c = entrada.read();
			String todo = "";
			int cont = 0;

			Statement vaciar;
			try {
				vaciar = conn.getConn().createStatement();
				cont = vaciar.executeUpdate("TRUNCATE inicio");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			while (c != -1) {
				char letra = (char) c;
				c = entrada.read();
				todo = todo + letra;

				if (letra == ':')
					cont++;
				if (cont == 3) {

					String[] usuario = todo.split(":");
					try {
						int r = 0;
						String query = "INSERT INTO inicio VALUES ('" + usuario[0] + "', '" + usuario[1] + "', "
								+ usuario[2] + ")";
						Statement stmt;
						stmt = conn.getConn().createStatement();
						r = stmt.executeUpdate(query);
						stmt.close();
						todo = "";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					cont = 0;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha encontrado el archivo");
		}
	}
}

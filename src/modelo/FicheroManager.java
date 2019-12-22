package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import controlador.Controller;

public class FicheroManager implements AccesoaDatos {

	BDManager bd = new BDManager();
	ConexionBD conn = new ConexionBD();
	public Persona personFich;
	Controller control = new Controller();

	@Override
	public String LeerTodos() {

		// TODO Auto-generated method stub
		try {
			FileReader entrada = new FileReader("BBDD_Formatos/Fichero.txt");
			escribeHashMap();
			int c = entrada.read();

			// Se lee e imprime el fichero caracter a caracter
			for (Integer i : control.personas.keySet()) {
				System.out.println(control.personas.get(i).getId() + ":" + control.personas.get(i).getNombre() + ":"
						+ control.personas.get(i).getNumero());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha encontrado el archivo");
		}
		return null;
	}

	@Override
	public String AgregarDato(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		FileWriter fw = null;
		personFich = new Persona();
		try {
			personFich.setId(codigo);
			personFich.setNombre(nombre);
			personFich.setNumero(numero);

			File file = new File("BBDD_Formatos/Fichero.txt");
			// Si el archivo no existe, se crea!
			if (!file.exists()) {
				file.createNewFile();
			}
			// flag true, indica adjuntar información al archivo.
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			// Se ecribe en el fichero con el formato a elegir
			bw.write(personFich.getId() + "-" + personFich.getNombre() + "-" + personFich.getNumero() + "-");
			System.out.println("información agregada satisfactoriamente");
			return "Dato agregado con éxito";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error al agregar";
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
	public HashMap<Integer, Persona> escribeHashMap() {
		try {
			FileReader entrada = new FileReader("BBDD_Formatos/Fichero.txt");
			int c = entrada.read();
			String text = "";
			int cont = 0, key = 0;

			// Se lee e imprime el fichero caracter a caracter
			while (c != -1) {

				char letra = (char) c;
				c = entrada.read();
				text = text + letra;

				if (letra == '-')
					cont++;
				if (cont == 3) {
					key++;
					personFich = new Persona();
					// Guardo cada 'columna' del fichero en un array que me permite separar cada
					// dato introducido y la inserto en la BD
					String[] usuario = text.split("-");

					personFich.setId(usuario[0]);
					personFich.setNombre(usuario[1]);
					personFich.setNumero(Integer.parseInt(usuario[2]));
					control.personas.put(key, personFich);

					text = "";

					cont = 0;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return control.personas;
	}

	public boolean recibirHashMap(HashMap<Integer, Persona> persona) {
		String escritura = "";
		for (Integer i : persona.keySet()) {
			escritura += persona.get(i).getId() + "-" + persona.get(i).getNombre() + "-" + persona.get(i).getNumero()
					+ "-";
		}

		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File("BBDD_Formatos/Fichero.txt");
			// Si el archivo no existe, se crea!
			if (!file.exists()) {
				file.createNewFile();
			}
			// flag true, indica adjuntar información al archivo.
			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);

			// Se ecribe en el fichero con el formato a elegir
			bw.write(escritura);
			System.out.println("\nSe ha pasado la BD al fichero correctamente");

		} catch (IOException e) {
			System.out.println("\nHa ocurrido un error y no se pasado la información");
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
		return true;
	}

	@Override
	public String BorrarDato(String codigo) {
		int key = 0;
		int findKey = 0;
		String escritura = "";
		escribeHashMap();
		File file = new File("BBDD_Formatos/Fichero.txt");

		// Si el archivo no existe, se crea!

		for (Integer i : control.personas.keySet()) {
			key++;
			if (control.personas.get(i).getId().equalsIgnoreCase(codigo)) {
				findKey = i;

			}
		}
		control.personas.remove(findKey);
		for (Integer i : control.personas.keySet()) {
			escritura += control.personas.get(i).getId() + "-" + control.personas.get(i).getNombre() + "-"
					+ control.personas.get(i).getNumero() + "-";
		}

		try {
			BufferedWriter bw = null;
			FileWriter fw = null;

			// flag true, indica adjuntar información al archivo.
			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);

			// Se ecribe en el fichero con el formato a elegir
			bw.write(escritura);
			bw.close();
			fw.close();
			return "Se ha eliminado con éxito";
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
			return "Ha ocurrido un error al eliminar";
		}

	}

	@Override
	public String BorrarTodo() {
		// TODO Auto-generated method stub
		File file = new File("BBDD_Formatos/Fichero.txt");
		String truncate = "";
		try {
			BufferedWriter bw = null;
			FileWriter fw = null;

			// flag true, indica adjuntar información al archivo.
			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);

			// Se ecribe en el fichero con el formato a elegir
			bw.write(truncate);
			bw.close();
			fw.close();
			return "Se ha vaciado con éxito el contenido de 'Fichero.txt'";
		} catch (IOException e) {
			e.printStackTrace();
			return "Ha ocurrido un error a la hora de eliminar el fichero";
		}

	}

	@Override
	public String Buscar(String codigo) {
		// TODO Auto-generated method stub
		int key = 0, findKey = 0;
		String encontrado = "";
		escribeHashMap();

		for (Integer i : control.personas.keySet()) {
			key++;
			if (control.personas.get(i).getId().equalsIgnoreCase(codigo)) {
				findKey = i;

			}
		}
		encontrado += control.personas.get(findKey).getId() + "\t" + control.personas.get(findKey).getNombre() + "\t"
				+ control.personas.get(findKey).getNumero();
		return encontrado;
	}

	@Override
	public String Modificar(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		int key = 0, findKey = 0;
		String encontrado = "";
		String escritura = "";
		escribeHashMap();

		for (Integer i : control.personas.keySet()) {
			key++;
			if (control.personas.get(i).getId().equalsIgnoreCase(codigo)) {
				findKey = i;

			}

		}
		control.personas.get(findKey).setNombre(nombre);
		control.personas.get(findKey).setNumero(numero);
		for (Integer i : control.personas.keySet()) {
			escritura += control.personas.get(i).getId() + "-" + control.personas.get(i).getNombre() + "-"
					+ control.personas.get(i).getNumero() + "-";
		}

		File file = new File("BBDD_Formatos/Fichero.txt");
		try {
			BufferedWriter bw = null;
			FileWriter fw = null;

			// flag true, indica adjuntar información al archivo.
			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);

			// Se ecribe en el fichero con el formato a elegir
			bw.write(escritura);
			bw.close();
			fw.close();
			return "Usuario modificado con éxito";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error al modificar usuario";
		}

	}

}

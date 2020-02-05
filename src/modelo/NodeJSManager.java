package modelo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class NodeJSManager implements AccesoaDatos {

	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, SHOW_ONE;

	public NodeJSManager() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost:9000/usuario";
		SHOW_ONE = "/";
	}

	@Override
	public String LeerTodos() {
		String result = "";
		HashMap<String, Persona> auxhm = new HashMap<String, Persona>();

		try {

			System.out.println("---------- Leemos datos de JSON --------------------");
			System.out.println("Lanzamos peticion JSON para usuarios");
			String url = SERVER_PATH;
			String response = encargadoPeticiones.getRequest(url);
			JSONArray respuesta = (JSONArray) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else {

				for (int j = 0; j < respuesta.size(); j++) {
					JSONObject array = (JSONObject) respuesta.get(j);

					if (array.size() > 0) {

						// Declaramos variables

						String nombre;
						int numero;
						String id;

						Persona nuevoPersona = new Persona();
						nombre = array.get("Nombre").toString();
						numero = Integer.parseInt(array.get("Numero").toString());
						id = array.get("Id").toString();

						nuevoPersona.setId(id);
						nuevoPersona.setNombre(nombre);
						nuevoPersona.setNumero(numero);

						auxhm.put(nuevoPersona.getId(), nuevoPersona);

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la búsqueda de datos");
			e.printStackTrace();
			System.exit(-1);
		}
		for (Entry<String, Persona> elto : auxhm.entrySet()) {
			result += elto.getValue().getId() + " " + elto.getValue().getNombre() + " " + elto.getValue().getNumero()
					+ "\n";
		}
		return result;
	}

	@Override
	public String AgregarDato(String codigo, String nombre, int numero) {
		try {
			JSONObject objPersona = new JSONObject();

			objPersona.put("Id", codigo);
			objPersona.put("Nombre", nombre);
			objPersona.put("Numero", numero);

			// objPeticion.put("peticion", "add");
			// objPeticion.put("usuarioAnnadir", objPersona);

			String json = objPersona.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un jugador");

			String url = SERVER_PATH;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			System.out.println("Procesando insertado, espere un momento...");
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);

			} else {

				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {
					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else {

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));
					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Insertado con éxito");
		}
		return "Usuario Añadido con éxito";
	}

	@Override
	public boolean recibirHashMap(HashMap<Integer, Persona> persona) {
		try {
			JSONObject objPersona = new JSONObject();

			for (Entry<Integer, Persona> elto : persona.entrySet()) {
				objPersona.put("Id", elto.getValue().getId());
				objPersona.put("Nombre", elto.getValue().getNombre());
				objPersona.put("Numero", elto.getValue().getNumero());

				// objPeticion.put("peticion", "add");
				// objPeticion.put("usuarioAnnadir", objPersona);

				String json = objPersona.toJSONString();

				System.out.println("Lanzamos peticion JSON para almacenar un jugador");

				String url = SERVER_PATH + "/cambiobbdd";

				System.out.println("La url a la que lanzamos la petición es " + url);
				System.out.println("El json que enviamos es: ");
				System.out.println(json);
				// System.exit(-1);

				String response = encargadoPeticiones.postRequest(url, json);

				System.out.println("El json que recibimos es: ");

				System.out.println(response); // Traza para pruebas
				System.exit(-1);

				// Parseamos la respuesta y la convertimos en un JSONObject

				JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

				if (respuesta == null) {
					System.out.println("El json recibido no es correcto. Finaliza la ejecución");
					System.exit(-1);

				} else {

					String estado = (String) respuesta.get("estado");
					if (estado.equals("ok")) {
						System.out.println("Almacenado jugador enviado por JSON Remoto");

					} else {

						System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
						System.out.println("Error: " + (String) respuesta.get("error"));
						System.out.println("Consulta: " + (String) respuesta.get("query"));
						System.exit(-1);

					}
				}
			}
		} catch (Exception e) {

		}
		return true;
	}

	@Override
	public HashMap<Integer, Persona> escribeHashMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String BorrarDato(String codigo) {
		// TODO Auto-generated method stub
		try {
			JSONObject objPersona = new JSONObject();

			objPersona.put("Id", codigo);

			String json = objPersona.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un jugador");

			String url = SERVER_PATH + "/" + codigo;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.deleteRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response); // Traza para pruebas
			System.exit(-1);

			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);

			} else {

				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {
					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else {

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));
					System.exit(-1);

				}
			}

		} catch (Exception e) {
		}
		return "Usuario eliminado con éxito";
	}

	@Override
	public String BorrarTodo() {
		try {

			JSONObject objPeticion = new JSONObject();

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para eliminar todos los usuarios");

			String url = SERVER_PATH;
			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);

			String response = encargadoPeticiones.deleteRequest(url, json);

			System.out.println("El json que recibimos es: ");
			System.out.println(response);
			System.exit(-1);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {

				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);

			}
		} catch (Exception e) {
			System.out.println("Se han eliminado a todos los usuarios");
		}
		return "Se han eliminado a todos los usuarios";
	}

	@Override
	public String Buscar(String codigo) {
		String result = "";
		HashMap<String, Persona> auxhm = new HashMap<String, Persona>();

		try {
			String url = SERVER_PATH + "/" + codigo;
			System.out.println("---------- Leemos datos de JSON --------------------");
			System.out.println("Lanzamos peticion JSON para usuarios a la direccion " + url);

			String response = encargadoPeticiones.getRequest(url);
			JSONArray respuesta = (JSONArray) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else {

				for (int j = 0; j < respuesta.size(); j++) {
					JSONObject array = (JSONObject) respuesta.get(j);

					if (array.size() > 0) {

						// Declaramos variables

						String nombre;
						int numero;
						String id;

						Persona nuevoPersona = new Persona();
						nombre = array.get("Nombre").toString();
						numero = Integer.parseInt(array.get("Numero").toString());
						id = array.get("Id").toString();

						nuevoPersona.setId(id);
						nuevoPersona.setNombre(nombre);
						nuevoPersona.setNumero(numero);

						auxhm.put(nuevoPersona.getId(), nuevoPersona);

						System.out.println("Usuario encontrado, imprimiendo datos...");
						System.out.println();

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la búsqueda de datos");
			e.printStackTrace();
			System.exit(-1);
		}
		for (Entry<String, Persona> elto : auxhm.entrySet()) {
			if (elto.getValue().getId().equalsIgnoreCase(codigo)) {
				result += elto.getValue().getId() + " " + elto.getValue().getNombre() + " "
						+ elto.getValue().getNumero() + "\n";
			}
		}
		return result;
	}

	@Override
	public String Modificar(String codigo, String nombre, int numero) {
		try {
			JSONObject objPersona = new JSONObject();

			
			objPersona.put("Nombre", nombre);
			objPersona.put("Numero", numero);

			String json = objPersona.toJSONString();

			System.out.println("Lanzamos peticion JSON para modificar un Usuario");

			String url = SERVER_PATH + "/" + codigo;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			System.out.println("Procesando instruccion, espere un momento...");
			// System.exit(-1);

			String response = encargadoPeticiones.putRequest(url, json);


			// Parseamos la respuesta y la convertimos en un JSONObject

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {

				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);

			} else { // El JSON recibido es correcto

				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Almacenado usuario enviado por JSON Remoto");

				} else {

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println("Se ha modificado con exito al usuario");
		}
		return "Se ha modificado con exito al usuario";
	}

}

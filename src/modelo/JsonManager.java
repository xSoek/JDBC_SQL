package modelo;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonManager implements AccesoaDatos {
	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_USER, SET_USER, DELETE_USER, DELETE_ALLUSER, UPDATE_USER; // Datos de la conexion

	public JsonManager() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost/project1/Java&SQL/";
		
		GET_USER = "leeUsuarios.php";
		SET_USER = "escribirUsuario.php";
		DELETE_USER = "deleteUsuario.php";
		DELETE_ALLUSER = "deleteAllUsuario.php";
		UPDATE_USER = "updateUsuario.php";
	}

	@Override
	public String LeerTodos() {
		String result = "";
		HashMap<String, Persona> auxhm = new HashMap<String, Persona>();

		try {

			System.out.println("---------- Leemos datos de JSON --------------------");

			System.out.println("Lanzamos peticion JSON para usuarios");

			String url = SERVER_PATH + GET_USER;

			String response = encargadoPeticiones.getRequest(url);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else {
				String estado = (String) respuesta.get("estado");

				if (estado.equals("ok")) {
					JSONArray array = (JSONArray) respuesta.get("usuarios");

					if (array.size() > 0) {

						// Declaramos variables

						String nombre;
						int numero;
						String id;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);
							Persona nuevoPersona = new Persona();
							nombre = row.get("nombre").toString();
							numero = Integer.parseInt(row.get("numero").toString());
							id = row.get("id").toString();

							nuevoPersona.setId(id);
							nuevoPersona.setNombre(nombre);
							nuevoPersona.setNumero(numero);

							auxhm.put(nuevoPersona.getId(), nuevoPersona);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else {

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

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
			JSONObject objPeticion = new JSONObject();

			objPersona.put("id", codigo);
			objPersona.put("nombre", nombre);
			objPersona.put("numero", numero);

			objPeticion.put("peticion", "add");
			objPeticion.put("usuarioAnnadir", objPersona);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un jugador");

			String url = SERVER_PATH + SET_USER;

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

		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirUsuario' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}

		return "Insertado con éxito";
	}

	@Override
	public boolean recibirHashMap(HashMap<Integer, Persona> persona) {
		try {
			JSONObject objPersona = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			for (Entry<Integer, Persona> elto : persona.entrySet()) {

				objPersona.put("id", elto.getValue().getId());
				objPersona.put("nombre", elto.getValue().getNombre());
				objPersona.put("numero", elto.getValue().getNumero());

				objPeticion.put("peticion", "add");
				objPeticion.put("usuarioAnnadir", objPersona);

				String json = objPeticion.toJSONString();

				System.out.println("Lanzamos peticion JSON cambiar los datos a 'basedatos3' ");

				String url = SERVER_PATH + SET_USER;

				System.out.println("La url a la que lanzamos la petición es " + url);
				System.out.println("El json que enviamos es: ");
				System.out.println(json);
				
				String response = encargadoPeticiones.postRequest(url, json);
				System.out.println("El json que recibimos es: ");
				System.out.println(response); 
				
				JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

				if (respuesta == null) {

					System.out.println("El json recibido no es correcto. Finaliza la ejecución");
					System.exit(-1);

				} else {

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
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirUsuario' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
		return false;
	}

	@Override
	public HashMap<Integer, Persona> escribeHashMap() {
		String result = "";
		HashMap<Integer, Persona> auxhm = new HashMap<Integer, Persona>();

		try {

			System.out.println("Lanzamos peticion JSON para exportar a otra base de datos");
			String url = SERVER_PATH + GET_USER;
			String response = encargadoPeticiones.getRequest(url);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
				
			} else {
				
				String estado = (String) respuesta.get("estado");

				if (estado.equals("ok")) {
					
					JSONArray array = (JSONArray) respuesta.get("usuarios");

					if (array.size() > 0) {

						String nombre, id;
						int numero;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);
							Persona nuevoPersona = new Persona();
							nombre = row.get("nombre").toString();
							numero = Integer.parseInt(row.get("numero").toString());
							id = row.get("id").toString();

							nuevoPersona.setId(id);
							nuevoPersona.setNombre(nombre);
							nuevoPersona.setNumero(numero);

							auxhm.put(i, nuevoPersona);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { 
						
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else {
					
					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));
					System.exit(-1);

				}
			}

		} catch (Exception e) {
			
			System.out.println("Ha ocurrido un error en la busqueda de datos");
			e.printStackTrace();
			System.exit(-1);
			
		}

		return auxhm;
	}

	@Override
	public String BorrarDato(String codigo) {

		try {
			JSONObject objPersona = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objPersona.put("id", codigo);

			objPeticion.put("peticion", "delete");
			objPeticion.put("usuarioEliminar", objPersona);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para eliminar un usuario");

			String url = SERVER_PATH + DELETE_USER;
			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			// System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");

			System.out.println(response);
			System.exit(-1);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { 
				
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			
			} else { 
				
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Eliminado jugador enviado por JSON Remoto");

				} else {
					
					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));
					System.exit(-1);

				}
			}
			
		} catch (Exception e) {
			
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirUsuario' de la clase JSON REMOTO");
			System.out.println("Fin ejecución");
			System.exit(-1);
			
		}

		return "Eliminado con éxito";
	}

	@Override
	public String BorrarTodo() {
		try {
			JSONObject objPersona = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objPeticion.put("peticion", "deleteAll");
			objPeticion.put("usuarioEliminarAll", objPersona);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para eliminar todos los usuarios");

			String url = SERVER_PATH + DELETE_ALLUSER;
			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);

			String response = encargadoPeticiones.postRequest(url, json);

			System.out.println("El json que recibimos es: ");
			System.out.println(response);
			System.exit(-1);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { 
				
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			
			} else { 
				
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {

					System.out.println("Eliminados los jugadores enviados por JSON Remoto");

				} else { 
					
					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));
					System.exit(-1);

				}
			}
			
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirUsuario' de la clase JSON REMOTO");
			System.out.println("Fin ejecución");
			System.exit(-1);
		}

		return "Eliminado con éxito";

	}

	@Override
	public String Buscar(String codigo) {
		String result = "";
		HashMap<String, Persona> auxhm = new HashMap<String, Persona>();

		try {

			System.out.println("---------- Leemos datos de JSON --------------------");
			String url = SERVER_PATH + GET_USER;
			String response = encargadoPeticiones.getRequest(url);

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) {
				
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
		
			} else {
				
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {
					
					JSONArray array = (JSONArray) respuesta.get("usuarios");
					if (array.size() > 0) {

						// Declaramos variables

						String nombre;
						int numero;
						String id;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);
							Persona nuevoPersona = new Persona();
							nombre = row.get("nombre").toString();
							numero = Integer.parseInt(row.get("numero").toString());
							id = row.get("id").toString();

							nuevoPersona.setId(id);
							nuevoPersona.setNombre(nombre);
							nuevoPersona.setNumero(numero);

							auxhm.put(nuevoPersona.getId(), nuevoPersona);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { // El array de jugadores está vacío
						
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					
					}

				} else { 
					
					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}

		result += auxhm.get(codigo).getId() + " " + auxhm.get(codigo).getNombre() + " " + auxhm.get(codigo).getNumero()
				+ "\n";

		return result;
	}

	@Override
	public String Modificar(String codigo, String nombre, int numero) {
		try {
			JSONObject objPersona = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objPersona.put("id", codigo);
			objPersona.put("nombre", nombre);
			objPersona.put("numero", numero);

			objPeticion.put("peticion", "update");
			objPeticion.put("usuarioUpdate", objPersona);

			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para modificar un Usuario");

			String url = SERVER_PATH + UPDATE_USER;

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
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirJugador' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}
		return null;
	}

}

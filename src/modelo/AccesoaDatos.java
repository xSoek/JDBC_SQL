package modelo;

import java.util.HashMap;

public interface AccesoaDatos {

	public String LeerTodos();

	public String AgregarDato(String codigo, String nombre, int numero);
	
	public boolean recibirHashMap(HashMap<Integer, Persona> persona);

	public HashMap<Integer, Persona> escribeHashMap();
	
	public String BorrarDato(String codigo);
	
	public String BorrarTodo();
	
	public String Buscar (String codigo);
	
	public String Modificar(String codigo, String nombre, int numero);
}

package modelo;

public interface AccesoaDatos {

	public void LeerTodos();

	public void AgregarDato(String codigo, String nombre, int numero);

	public void PasarBDFichero();
}

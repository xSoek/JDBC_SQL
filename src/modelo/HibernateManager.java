package modelo;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import controlador.Controller;

public class HibernateManager implements AccesoaDatos {

	Persona persona;
	Controller control = new Controller();

	SessionFactory sf = new Configuration().configure().buildSessionFactory();
	Session s = sf.openSession();

	public void conectarControl(Controller control) {
		this.control = control;
	}

	@Override
	public String LeerTodos() {
		String persona = "";
		Query q = s.createQuery("from Persona");
		List<Persona> usuarios = q.list();
		for (Persona person : usuarios) {
			persona += person.getId() + "\t" + person.getNombre() + "\t" + person.getNumero() + "\n";
		}
		s.beginTransaction().commit();
		// TODO Auto-generated method stub
		System.out.println("\n\nId\tNombre\tNumero");
		
		return persona;
	}

	@Override
	public String AgregarDato(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		persona = new Persona();
		persona.setId(codigo);
		persona.setNombre(nombre);
		persona.setNumero(numero);
		s.save(persona);
		s.beginTransaction().commit();
		
		return " - Insertado con éxito - ";
	}

	@Override
	public boolean recibirHashMap(HashMap<Integer, Persona> personas) {
		// TODO Auto-generated method stub
		BorrarTodo();	
		s.clear();
		s.beginTransaction();
		for (Entry<Integer, Persona> i : personas.entrySet()) {
			s.save(i.getValue());
		}
		s.getTransaction().commit();
		return true;
	}

	@Override
	public HashMap<Integer, Persona> escribeHashMap() {
		// TODO Auto-generated method stub
		String persona = "";
		Query q = s.createQuery("from Persona");
		int i = 1;
		List<Persona> usuarios = q.list();
		for (Persona person : usuarios) {
			control.personas.put(i, person);
			i++;
		}
		// TODO Auto-generated method stub
		return control.personas;
	}

	@Override
	public String BorrarDato(String codigo) {
		s.beginTransaction();
		Query q = s.createQuery("delete Persona where Id = :id");
		q.setParameter("id", codigo);
		q.executeUpdate();
		s.getTransaction().commit();
		
		return " - Borrado del usuario finalizado con éxito - ";
	}

	@Override
	public String BorrarTodo() {
		// TODO Auto-generated method stub
		s.beginTransaction();
		Query q = s.createQuery("delete from Persona");
		q.executeUpdate();
		s.getTransaction().commit();
		
		return " - Borrado de la BD finalizado con éxito - ";
	}

	@Override
	public String Buscar(String codigo) {
		// TODO Auto-generated method stub
		String persona = "";
		s.beginTransaction();
		Query q = s.createQuery("from Persona where Id = :id");
		q.setParameter("id", codigo);
		List<Persona> usuarios = q.list();
		for (Persona person : usuarios) {
			persona += person.getId() + "\t" + person.getNombre() + "\t" + person.getNumero();
		}
		s.getTransaction().commit();
		return persona;
	}

	@Override
	public String Modificar(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		s.beginTransaction();
		Query q = s.createQuery("update Persona set Nombre = :name, Numero = :numero where Id = :id");
		q.setParameter("id", codigo);
		q.setParameter("name", nombre);
		q.setParameter("numero", numero);
		
		q.executeUpdate();
		s.getTransaction().commit();
		
		return " - Se ha Modificado con exito al usuario - ";
	}

}

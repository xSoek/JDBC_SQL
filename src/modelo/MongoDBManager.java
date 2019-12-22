package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import controlador.Controller;

public class MongoDBManager implements AccesoaDatos {

	Controller control = new Controller();
	Persona persona;


	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	MongoDatabase database = mongoClient.getDatabase("basedatos1");
	MongoCollection<Document> collection = database.getCollection("basedatos1");

	public void conectarControl(Controller control) {
		this.control = control;
	}

	@Override
	public String LeerTodos() {
		
		String datos = "";
		FindIterable<Document> docs = collection.find(); // SELECT * FROM sample;
		for (Document doc : docs) {
			datos += (doc.toString() + "\n");
		}
		return datos;
	}

	@Override
	public String AgregarDato(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		Document document = new Document().append("_id", codigo).append("Nombre", nombre).append("Numero", numero);
		collection.insertOne(document);
		return "Dato insertado con exito en MongoDB";
	}

	@Override
	public boolean recibirHashMap(HashMap<Integer, Persona> persona) {
		// TODO Auto-generated method stub
		BorrarTodo();
		for (Integer i : persona.keySet()) {
			Document document = new Document().append("_id", persona.get(i).getId())
					.append("Nombre", persona.get(i).getNombre()).append("Numero", persona.get(i).getNumero());
			collection.insertOne(document);
		}
		return true;
	}

	@Override
	public HashMap<Integer, Persona> escribeHashMap() {
		// TODO Auto-generated method stub
		try (MongoCursor<Document> cur = collection.find().iterator()) {
			int key = 0;
			while (cur.hasNext()) {
				key++;
				persona = new Persona();
				Document doc = cur.next();
				ArrayList persons = new ArrayList<>(doc.values());
				persona.setId(persons.get(0).toString());
				persona.setNombre(persons.get(1).toString());
				persona.setNumero(Integer.parseInt(persons.get(2).toString()));
				control.personas.put(key, persona);
			}
		}

		return control.personas;
	}

	@Override
	public String BorrarDato(String codigo) {
		// TODO Auto-generated method stub
		collection.deleteOne(Filters.eq("_id", codigo));
		return "Usuario " + codigo + " eliminado correctamente";
	}

	@Override
	public String BorrarTodo() {
		// TODO Auto-generated method stub
		collection.drop();
		return "Se ha eliminado con éxito toda la colección";
	}

	@Override
	public String Buscar(String codigo) {
		// TODO Auto-generated method stub
		Object myDoc = collection.find(Filters.eq("_id", codigo)).first();
		return myDoc.toString();
	}

	@Override
	public String Modificar(String codigo, String nombre, int numero) {
		// TODO Auto-generated method stub
		collection.updateOne(Filters.eq("_id", codigo), Updates.set("Nombre", nombre));
		collection.updateOne(Filters.eq("_id", codigo), Updates.set("Numero", numero));
		return "Se ha modificado con exito al usuario " + codigo;
	}

}

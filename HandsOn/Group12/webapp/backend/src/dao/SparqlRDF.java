package dao;

import java.io.IOException;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.json.simple.JSONObject;

public class SparqlRDF {

	public static List<JSONObject> getBarrios(){
		String filename = "/home/neoh/Escritorio/Jena/avisa-2016-with-links.ttl";
		String filename2 = "/home/neoh/Escritorio/Jena/encuestas-2014.ttl";
		
		// Create an empty model
		Model model = ModelFactory.createDefaultModel();
		Model model2 = ModelFactory.createDefaultModel();
		Utils utils = new Utils();
		
		// Read the Turtle file
		model.read(filename, "TURTLE");
		model2.read(filename2, "TURTLE");
		
		return utils.getBarrios(model, model2);
	}
	
	public static JSONObject getBarrio (int idBarrio){
		String filename = "/home/neoh/Escritorio/Jena/avisa-2016-with-links.ttl";
		String filename2 = "/home/neoh/Escritorio/Jena/encuestas-2014.ttl";
		
		// Create an empty model
		Model model = ModelFactory.createDefaultModel();
		Model model2 = ModelFactory.createDefaultModel();
		Utils utils = new Utils();
		
		// Read the Turtle file
		model.read(filename, "TURTLE");
		model2.read(filename2, "TURTLE");
		
		return utils.makeJSON(model, model2, idBarrio);
	}	
	
	public static String printJSON (JSONObject json) throws IOException{
		Utils utils = new Utils();
		return utils.printJSON(json);
	}
	
//	public static void main(String[] args) throws IOException {
//		
//		String filename = "/home/neoh/Escritorio/Jena/avisa-2016-with-links.ttl";
//		String filename2 = "/home/neoh/Escritorio/Jena/encuestas-2014.ttl";
//		
//		// Create an empty model
//		Model model = ModelFactory.createDefaultModel();
//		Model model2 = ModelFactory.createDefaultModel();
//		Utils utils = new Utils();
//	
//		// Read the Turtle file
//		model.read(filename, "TURTLE");
//		model2.read(filename2, "TURTLE");
//		
//		// GET barrios/
//		// Obtener JSONS de todos los barrios
////		List<JSONObject> barrios = utils.getBarrios(model, model2);
//		
//		// GET barrio/{id}
//		// Obtener JSON de un barrio
//		int numBarrio = 154;
//		
//		JSONObject json = utils.makeJSON(model, model2, numBarrio);
//		System.out.println(json);
//		
//	}
	

}

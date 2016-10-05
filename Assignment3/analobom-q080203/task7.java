package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * 
 * @author Ana Lobo Muñoz q080203
 * 
 */
public class Task07 {
	public static String ns = "http://somewhere#";

	public static void main(String args[]) {
		String filename = "example6.rdf";

		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);

		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);

		if (in == null)
			throw new IllegalArgumentException("File: " + filename + " not found");

		// Read the RDF/XML file
		model.read(in, null);

		// ** TASK 7.1: List all individuals of "Person" **
		System.out.println("First of all I will list all individuals of Person:");
				ExtendedIterator<Individual> eIterInd = model.listIndividuals(model.getOntClass(ns + "Person"));
		while (eIterInd.hasNext()) {
			Individual persona = eIterInd.next();
			System.out.println("\t" + persona.getLocalName().toString() + " is a person.");
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\nNow I will list every subclass of Person: ");
		System.out.print(new Task07().listEverySubClass(model, "Person"));

		// ** TASK 7.3: Make the necessary changes to get as well indirect
		// instances and subclases. TIP: you need some inference... **
		System.out.println("\nNow I'd try to show every indirect instance/subclass");
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		ExtendedIterator<Individual> eIterInd2 = model2.listIndividuals(model.getOntClass(ns + "Person"));
		while (eIterInd2.hasNext()) {
			Individual persona = eIterInd2.next();
			System.out.println("\t" + persona.getLocalName().toString() + " is an instance of Person.");
		}
		ExtendedIterator<OntClass> eIterPers = model2.getOntClass(ns + "Person").listSubClasses();
		while (eIterPers.hasNext()) {
			OntClass clase = eIterPers.next();
			System.out.println("\t" + clase.getLocalName() + " is a subclass of Person.");
		}

		System.out.print(new Task07().listEverySubClass(model2, "Person"));

	}

	private String listEverySubClass(OntModel model, String thing) {

		String res = "";
		String aux = "";
		ExtendedIterator<OntClass> eIterPers = model.getOntClass(ns + thing).listSubClasses();
		while (eIterPers.hasNext()) {
			OntClass clase = eIterPers.next();
			if (clase.hasSubClass()) {
				aux += listEverySubClass(model, clase.getLocalName());
			}
			res += "\t" + clase.getLocalName() + "\n";

		}
		res += aux;
		return res;
	}
}

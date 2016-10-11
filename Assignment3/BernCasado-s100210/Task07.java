package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.VCARD;

/**
 * Task 07: Querying ontologies (RDFs)
 * 
 * @author elozano
 * @author isantana
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
		System.out.println("************1************\n");
		OntClass Person = model.getOntClass(ns + "Person");
		ExtendedIterator it = Person.listInstances();
		while (it.hasNext()) {
			Individual next = (Individual) it.next();
			System.out.println(next.getURI() + " is an Individual of Person");
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n\n************2************\n");
		ExtendedIterator <OntClass> it2 = Person.listSubClasses();
		while (it2.hasNext()) {
			OntClass next = it2.next();
			System.out.println(next.getURI() + " is a subclass of Person");
		}

		// ** TASK 7.3: Make the necessary changes to get as well indirect
		// instances and subclasses. TIP: you need some inference... **
		System.out.println("\n\n************3************\n");
		OntModel model2 =ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
		OntClass Person_inf = model2.getOntClass(ns+"Person");
		ExtendedIterator it_inf = Person_inf.listInstances();
		while (it_inf.hasNext()) {
			Individual next_inf = (Individual) it_inf.next();
			System.out.println(next_inf.getURI() + " is an inferred individual of Person"); 
			// Aqui deberia salir tmabien JaneSmith que es una researcher y por tanto
			// instancia indirecta de Persona
		}	
		
		System.out.println("\n");
		
		ExtendedIterator <OntClass> it2_inf = Person_inf.listSubClasses();
		while (it2_inf.hasNext()) {
			OntClass next = it2_inf.next();
			System.out.println(next.getURI() + " is an inferred subclass of Person");
			// Aqui deberia salir tambien PHDStudent que es una subclase de researcher y portanto
			//Subclase indirecta de Person 
		}
	}
}

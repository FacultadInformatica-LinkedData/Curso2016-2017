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
 * @author elozano
 * @author isantana
 *
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person = model.getOntClass(ns + "Person");
		ExtendedIterator iterador = person.listInstances();
		while(iterador.hasNext()){
			Individual next = (Individual) iterador.next();
			System.out.println("Person: " + next.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		iterador = person.listSubClasses();
		while(iterador.hasNext()){
			OntClass next = (OntClass) iterador.next();
			System.out.println("Subclass Person: " + next.getURI());
		} 
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel inf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass p = inf.getOntClass(ns+"Person");
		ExtendedIterator itInf = p.listInstances();
		while(itInf.hasNext()){
			Individual next = (Individual) itInf.next();
			System.out.println("Inference: " + next.getURI());
		}
		itInf = p.listSubClasses();
		while(itInf.hasNext()){
			OntClass next = (OntClass) itInf.next();
			System.out.println("Subclass Inference: " + next.getURI());
		}
	
	}
}

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
		System.out.println("7.1 List all individuals:");
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator<Individual> it = model.listIndividuals(person);
		while(it.hasNext()){
			Individual ind = it.next();
			System.out.println(ind.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n7.1 List all subclasses:");
		ExtendedIterator<OntClass> it2 = person.listSubClasses();
		while(it2.hasNext()){
			OntClass ont = it2.next();
			System.out.println(ont.getURI());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelInference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
		// Use the FileManager to find the input file
		InputStream in2 = FileManager.get().open(filename);
			
		if (in2 == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
			
		// Read the RDF/XML file
		model.read(in2, null);
		OntClass personInf = modelInference.getOntClass(ns+"Person");
		
		System.out.println("\n7.3.1 List all individuals:");
		ExtendedIterator<Individual> it3 = modelInference.listIndividuals(personInf);
		while(it3.hasNext()){
			Individual ind2 = it3.next();
			System.out.println(ind2.getURI());
		}
		
		System.out.println("\n7.3.2 List all subclasses:");
		ExtendedIterator<OntClass> it4 = personInf.listSubClasses();
		while(it4.hasNext()){
			OntClass ont2 = it4.next();
			System.out.println(ont2.getURI());
		}
	}
}

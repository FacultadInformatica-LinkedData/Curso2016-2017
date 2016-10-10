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
 * @author Gabriel Llera 
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
		System.out.println("List all individuals of Person");
		
		OntClass person = model.getOntClass(ns+"Person");
		
		for (ExtendedIterator<Individual> i = (ExtendedIterator<Individual>) person.listInstances(); i.hasNext(); System.out.println(i.next().getURI()));

		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("");
		System.out.println("Subclasses of Person");
		
		for (ExtendedIterator<OntClass> i = person.listSubClasses(); i.hasNext(); System.out.println(i.next().getURI()));

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("");
		System.out.println("Individuals of Person (Indirect)");
		
		OntClass persons = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model).getOntClass(ns+"Person");
		
		for (ExtendedIterator<Individual> i = (ExtendedIterator<Individual>) persons.listInstances(); i.hasNext(); System.out.println(i.next().getURI()));
		
		System.out.println("");
		System.out.println("Subclasses of Person (Indirect)");
		
		for (ExtendedIterator<OntClass> i = persons.listSubClasses(); i.hasNext(); System.out.println(i.next().getURI()));

	}
}

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
		System.out.println("\n7.1");
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator<Individual> iterInd = model.listIndividuals(person);
		while(iterInd.hasNext()) {
			System.out.println(iterInd.next().getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n7.2");
		ExtendedIterator<OntClass> iterSubC = person.listSubClasses();
		while(iterSubC.hasNext()) {
			System.out.println(iterSubC.next().getURI());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("\n7.3");
		
		// Create an empty model with inference support
		OntModel infModel = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		
		// Use the FileManager to find the input file
		InputStream in2 = FileManager.get().open(filename);
	
		if (in2 == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
				
		// Read the RDF/XML file
		infModel.read(in2, null);
		
		OntClass infPerson = infModel.getOntClass(ns+"Person");
		
		System.out.println("\nPersons");
		iterInd = infModel.listIndividuals(infPerson);
		while(iterInd.hasNext()) {
			System.out.println(iterInd.next().getURI());
		}
		
		System.out.println("\nSubclasses");
		iterSubC = infPerson.listSubClasses();
		while(iterSubC.hasNext()) {
			System.out.println(iterSubC.next().getURI());
		}
		
	}
}

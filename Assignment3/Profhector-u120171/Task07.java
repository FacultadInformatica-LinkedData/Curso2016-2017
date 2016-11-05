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
		System.out.println("Task 7.1. Listing all individuals of \"Person\".");
		
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator<Individual> iterator1 = model.listIndividuals(person);
		
		while(iterator1.hasNext()){
			Individual ind = iterator1.next();
			System.out.println(ind.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Task 7.2. Listing all subclasses of \"Person\".");
		ExtendedIterator<OntClass> iterator2 = person.listSubClasses();
		
		while(iterator2.hasNext()){
			System.out.println(iterator2.next());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("Task 7.3. Listing with inference.");
		
		OntModel inferredModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,model);
		OntClass inferredPerson = inferredModel.getOntClass(ns+"Person");
		
		ExtendedIterator<Individual> infIndIterator = inferredModel.listIndividuals(inferredPerson);
		ExtendedIterator<OntClass> infClassIterator = inferredPerson.listSubClasses();
		
		while(infIndIterator.hasNext()){
			Individual inferred = infIndIterator.next();
			System.out.println(inferred.getURI());
		}
		while(infClassIterator.hasNext()){
			System.out.println(infClassIterator.next());
		}
		
	}
}

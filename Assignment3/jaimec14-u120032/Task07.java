
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
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator<Individual> iter1 = model.listIndividuals(person);
		
		System.out.println("All individuals of Person:");
		while (iter1.hasNext())
		{
			Individual i = iter1.next();
			System.out.println(i.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> iter2 = person.listSubClasses();
		
		System.out.println("All subclasses of Person:");
		while(iter2.hasNext())
		{
			OntClass i = iter2.next();
			System.out.println(i.getURI());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		// We create a new model with inference
		OntModel modelWithInference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		InputStream in2 = FileManager.get().open(filename); //No need to check existence again.
		modelWithInference.read(in2, null);
		
		OntClass personWI = modelWithInference.getOntClass(ns+"Person");
		
		// We list all indirect individuals of Person
		ExtendedIterator<Individual> iter31 = modelWithInference.listIndividuals(personWI);
		
		System.out.println("All individuals of Person (with inference):");
		while (iter31.hasNext())
		{
			Individual i = iter31.next();
			System.out.println(i.getURI());
		}
		
		// We list all indirect subclasses of Person
		ExtendedIterator<OntClass> iter32 = personWI.listSubClasses();
		
		System.out.println("All subclasses of Person (with inference):");
		while(iter32.hasNext())
		{
			OntClass i = iter32.next();
			System.out.println(i.getURI());
		}	
	}
}
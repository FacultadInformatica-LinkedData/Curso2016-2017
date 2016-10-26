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
 * @author RafaelTruji
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
		System.out.println("-Individuals of Person:");
		OntClass person = model.createClass(ns+"Person");
		ExtendedIterator<Individual>itInd = model.listIndividuals();
		
		while(itInd.hasNext()){
			System.out.println(itInd.next().getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n-Subclasses of Person:");
		ExtendedIterator<OntClass>itSub = person.listSubClasses();
		
		while(itSub.hasNext()){
			System.out.println(itSub.next().getURI());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);//inference
		InputStream in2 = FileManager.get().open(filename);
		
		if (in2 == null)
			throw new IllegalArgumentException("File: " + filename + " not found");
		
		// Read the RDF/XML file
		model2.read(in2, null);
		
		OntClass person2 = model2.getOntClass(ns+"Person");
		
		//Indirect instances
		ExtendedIterator<Individual> itInd2 = model2.listIndividuals(person2);
		
		System.out.println("\n-Individuals of Person(inference):");
		while (itInd2.hasNext())
		{
			System.out.println(itInd2.next().getURI());
		}
	
		//Subclasses of Person
		ExtendedIterator<OntClass> itSub2 = person2.listSubClasses();
		
		System.out.println("\n-Subclasses of Person(inference):");
		while(itSub2.hasNext())
		{
			System.out.println(itSub2.next().getURI());
		}
	
	}
}

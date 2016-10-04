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
 * @author albervado
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
		ExtendedIterator<Individual> iter71 = model.listIndividuals();

		System.out.println("7.1:Person:");
		while (iter71.hasNext())
		{
			Individual i = iter71.next();
			System.out.println(i.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass>iter72 = person.listSubClasses();

		System.out.println("7.2:Subclasses of Person:");
		while(iter72.hasNext()){
			System.out.println(iter72.next().getURI());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelInf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		InputStream in2 = FileManager.get().open(filename);
		if (in2 == null)
			throw new IllegalArgumentException("File: " + filename + " not found");

		modelInference.read(in2, null);
		OntClass person2 = modelInf.getOntClass(ns+"Person");
		System.out.println("7.3:Individuals with inference:");

		ExtendedIterator<Individual> iter73 = modelInf.listIndividuals(person2);
		while (iter73.hasNext())
		{
			System.out.println(iter73.next().getURI());
		}
		ExtendedIterator<OntClass> iter73sub = person2.listSubClasses();
		
		System.out.println("7.3:Subclasses of Person(inference):");
		while(iter73sub.hasNext())
		{
			System.out.println(iter73sub.next().getURI());
		}

	}
		
}

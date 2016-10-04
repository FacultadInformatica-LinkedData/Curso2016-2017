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

/**
 * Task 07: Querying ontologies (RDFs)
 * @author 		JesusPmelero
 * @version		1.0
 * @category	Assignment 3
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

		System.out.println("Listing all individuals of Person \n");
		ExtendedIterator<Individual> it = model.listIndividuals();
		int counter_Ind = 0;
		
		OntClass person = model.getOntClass(ns + "Person");

		while (it.hasNext()) 
		{
			Individual object = it.next();
			System.out.println(object.hasOntClass(person) ? "\tIndividual nº " + counter_Ind++ + ": " + object.getURI() + "\n": "");
		}
		System.out.println(counter_Ind>1 || counter_Ind == 0 ? counter_Ind + " results were found": counter_Ind + " result was found");

		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("Listing all subclasses of Person \n");
		ExtendedIterator<OntClass> it2 = person.listSubClasses();
		int counter_subc = 0;
		
		while (it2.hasNext()) 
		{
			OntClass object = it2.next();
			System.out.println("\tSubclass nº " + counter_subc++ + ": " + object.getURI());
		}
		System.out.println(counter_subc>1 || counter_subc == 0 ? counter_subc + " results were found": counter_subc + " result was found");


		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **

	}
}
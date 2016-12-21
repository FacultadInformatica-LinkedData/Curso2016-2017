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
		System.out.println("TASK 7.1");
		
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator personIndividualList = person.listInstances();
		//ExtendedIterator personIndividualList = model.listIndividuals(person);
		while(personIndividualList.hasNext())
		{
			Individual individual = (Individual)personIndividualList.next();
			System.out.println("Individual: " + individual.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("TASK 7.2");
		
		ExtendedIterator personSubClassesList = person.listSubClasses();
		while(personSubClassesList.hasNext())
		{
			OntClass subClass = (OntClass)personSubClassesList.next();
			System.out.println("SubClass: " + subClass.getURI());
		}	
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("TASK 7.3");
		
		personSubClassesList = person.listSubClasses();
		while(personSubClassesList.hasNext())
		{
			OntClass subClass = (OntClass)personSubClassesList.next();
			ExtendedIterator subClassInstances = subClass.listInstances();
			
			System.out.println("SubClass: "+ subClass.getURI());
			
			while(subClassInstances.hasNext())
			{
				Individual instance = (Individual)subClassInstances.next();
				System.out.println("- Instance: " + instance.getURI());
			}
		}
	}
}

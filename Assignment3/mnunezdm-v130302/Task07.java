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
		
		System.out.println("** TASK 7.1: List all individuals of \"Person\" **\n");
		ExtendedIterator<Individual> r = model.listIndividuals();
		OntClass person = model.getOntClass(ns + "Person");
		
		while (r.hasNext()) {
			Individual ind = r.next();
			if (ind.hasOntClass(person))
				System.out.println("|-- Individual: " + ind.getURI());
			
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n** TASK 7.2: List all subclasses of \"Person\"\n");
		ExtendedIterator<OntClass> i = person.listSubClasses();
		while (i.hasNext()) {
			OntClass ind2 = i.next();
			System.out.println("|-- Subclass: " + ind2.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		System.out.println("\n** TASK 7.3: Make the necessary changes to get as well "
				+ "indirect instances and subclasses. TIP: you need some inference... **");
		
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		ExtendedIterator<OntClass> inf_classes = person.listSubClasses();
		ExtendedIterator<Individual> inf_instances = (ExtendedIterator<Individual>) person.listInstances();
		while (inf_classes.hasNext()) {
			OntClass ind3 = inf_classes.next();
			System.out.println("|-- All subclasses: " + ind3.getURI());
		}
		
		while (inf_instances.hasNext()) {
			Individual indiv_instance = (Individual) inf_instances.next();
			System.out.println("|-- Instances: " + indiv_instance.getURI());
		}
		
	
	}
}

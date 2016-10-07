package ontologyapi;

import java.io.InputStream;

import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
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
		ExtendedIterator<Individual> it = model.listIndividuals(person);
		while(it.hasNext()){
			System.out.println("Person Individual: "+it.next());
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> it2 = person.listSubClasses();
		while(it2.hasNext()){
			System.out.println("Person SubClass: "+it2.next());
		}

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		it = model.listIndividuals(person);
		while(it.hasNext()){
			System.out.println("Direct Instance: "+it.next());
		}
		ExtendedIterator<OntClass> it3 = person.listSubClasses();
		while(it3.hasNext()){
			OntClass ontClass = it3.next();
			System.out.println("Direct SubClass: " + ontClass);
			ExtendedIterator<Individual> it4 = model.listIndividuals(ontClass);
			while(it4.hasNext())
				System.out.println("Indirect Instance: "+it4.next());
			ExtendedIterator<OntClass> it5 = ontClass.listSubClasses();
			while(it5.hasNext())
				System.out.println("Indirect SubClass: "+it5.next());
		}
	}
}
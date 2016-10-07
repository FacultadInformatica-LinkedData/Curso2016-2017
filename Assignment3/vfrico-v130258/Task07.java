package ontologyapi;

import java.io.InputStream;
import java.util.Iterator;

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
		model.write(System.out, "TURTLE");
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass people = model.getOntClass(ns+"Person");
		
		System.out.println("Individuals of 'Person'");
		ExtendedIterator<? extends OntResource> lista = people.listInstances();
		while(lista.hasNext()){
			System.out.println(lista.next());
		}
		
		System.out.println("Subclasses of 'Person'");
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<? extends OntResource> subclases = people.listSubClasses();
		while(subclases.hasNext()){
			System.out.println(subclases.next());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelInference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
	
		OntClass peopleInf = modelInference.getOntClass(ns+"Person");
		
		System.out.println("Individuals of 'Person' (indirect)");
		ExtendedIterator<? extends OntResource> peopleListInf = peopleInf.listInstances();
		while(peopleListInf.hasNext()){
			System.out.println(peopleListInf.next());
		}
		
		System.out.println("Subclasses of 'Person' (indirect)");
		ExtendedIterator<? extends OntResource> subclasesInf = peopleInf.listSubClasses();
		while(subclasesInf.hasNext()){
			System.out.println(subclasesInf.next());
		}
	}
}


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
		ExtendedIterator persons = person.listInstances();
		while (persons.hasNext())
	      {
	        Individual thisInstance = (Individual) persons.next();
	        System.out.println("Found instance: "+thisInstance.toString());
	      }
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator classes = person.listSubClasses();
		while(classes.hasNext()){
			OntClass subcl = (OntClass)classes.next();
			System.out.println("subclass_:" + subcl.getLocalName());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelWithInf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		InputStream in2 = FileManager.get().open(filename);
		modelWithInf.read(in2,null);
		OntClass personInf = modelWithInf.getOntClass(ns+"Person");
		ExtendedIterator persons2 = personInf.listInstances();
		while (persons2.hasNext())
	      {
	        Individual thisInstance = (Individual) persons2.next();
	        System.out.println("Found instance with inference: "+thisInstance.toString());
	      }
		ExtendedIterator classes2 = personInf.listSubClasses();
		while(classes2.hasNext()){
			OntClass subcl = (OntClass)classes2.next();
			System.out.println("subclass with inference:" + subcl.getLocalName());
		}
	}
}

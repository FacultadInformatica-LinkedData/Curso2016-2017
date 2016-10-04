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
	//Recursive function to get all subclasses and instances
	public static void getSubclInst(OntClass cl ) {
		System.out.println("Class " + cl + " instances: ---");
		for (ExtendedIterator<? extends OntResource> i =  cl.listInstances(); i.hasNext(); ) {
			  Individual c = (Individual)i.next();
			  System.out.println("instances"+ c.getURI());
			}
		System.out.println();
		System.out.println("Class " + cl + " subclasses: ---");
		for (ExtendedIterator<OntClass> j = cl.listSubClasses(); j.hasNext(); ) {
			  OntClass c = j.next();
			  System.out.println("subclases"+ c.getURI() );
			  getSubclInst(c);
			}
		System.out.println();
		}
	
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
		
		ExtendedIterator<Individual> indiv = (ExtendedIterator<Individual>) person.listInstances();
		while (indiv.hasNext()) {
			System.out.println(indiv.next());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> subClas = (ExtendedIterator<OntClass>) person.listSubClasses();
		while (subClas.hasNext()) {
			System.out.println(subClas.next());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("ALL instances and subclasses");
		getSubclInst(person);
	
	}
}

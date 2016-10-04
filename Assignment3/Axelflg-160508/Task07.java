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
 * @author Axel Flores
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
		Resource person = model.getResource(ns+"Person");
		ExtendedIterator<Individual> iter = model.listIndividuals(person);
		while(iter.hasNext())
		{
			System.out.println("Personas: " + iter.next().getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		OntClass person2 = model.getOntClass(ns + "Person");
		ExtendedIterator<? extends OntResource> iter2 = person.listInstances();
		iter2 = person2.listSubClasses();
		while(iter2.hasNext()) 
		{
			OntClass subclass = (OntClass)iter2.next();
			System.out.println("Subclases: " + subclass);
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
		OntClass person3 = model2.getOntClass( ns + "Person" );
		for (ExtendedIterator<? extends OntResource> i =  person3.listInstances(); i.hasNext(); ) {
		  Individual c = (Individual)i.next();
		  System.out.println("instances"+ c.getURI() );
		}
		
		for (ExtendedIterator<OntClass> j = person3.listSubClasses(); j.hasNext(); ) {
		  OntClass c = j.next();
		  System.out.println("Subclases"+ c.getURI() );
		}
	
	}
}
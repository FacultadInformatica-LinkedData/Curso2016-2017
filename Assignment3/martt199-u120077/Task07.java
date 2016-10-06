package ontologyapi;

import java.io.InputStream;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.VCARD;
/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task07 extends Task06
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
		 Model lista =model.read(in, null);
		 
		
	
		
		// ** TASK 7.1: List all individuals of "Person" **
	ExtendedIterator<Individual> it= (ExtendedIterator<Individual>) model.getOntClass(ns + "Person");

		while (it.hasNext()){
			Resource result = it.next();
			System.out.println(result.getLocalName().toString());
			
		}
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> it2= model.getOntClass(ns + "Person").listSubClasses();
		
		while(it2.hasNext()){
			System.out.println(it2.next());
		}
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
	
	}
}

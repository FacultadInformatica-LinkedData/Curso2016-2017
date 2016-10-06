package ontologyapi;

import java.io.InputStream;
//import java.util.Iterator;

//import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
//import org.apache.jena.rdf.model.RDFList;
//import org.apache.jena.rdf.model.RDFNode;
//import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
//import org.apache.jena.vocabulary.VCARD;

/**
 * Task 07: Querying ontologies (RDFs)
 * 
 * @author Marta Villar u120320
 * 
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
			throw new IllegalArgumentException("File: "+ filename +" not found");
	
		// Read the RDF/XML file
		
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		
		System.out.println("List all individuals of Person " + "members: ");
		ExtendedIterator<OntClass> rIter =  model.getOntClass(ns + "Person").listSubClasses();
		while (rIter.hasNext()) {
		  System.out.println(rIter.next());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println(" ");
		System.out.println("List all individuals of Person " + "members: ");
		ExtendedIterator<OntClass> rIter2 =  model.getOntClass(ns + "Person").listSubClasses();
		while (rIter2.hasNext()) {
		  System.out.println(rIter2.next());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		System.out.println(" ");
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, model);

		ExtendedIterator<OntClass> rIter3 =  model2.getOntClass(ns + "Person").listSubClasses();
		while (rIter3.hasNext()) {
		  System.out.println(rIter3.next());
		}
		
		
	
	}
}

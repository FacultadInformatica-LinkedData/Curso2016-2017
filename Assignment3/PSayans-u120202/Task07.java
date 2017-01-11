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
public class Task07 {
	public static String ns = "http://somewhere#";
	
	public static void main(String args[]) {

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
		OntClass class = model.getOntClass(ns + "Person");
		
		ExtendedIterator<Individual> it =  model.getOntClass(ns + "Person").listInstances();
				
		while (it.hasNext()) {
			Individual ind = it.next();
			System.out.println(ind.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> it2 = class.listSubClasses();
				
		while (it2.hasNext()) {
			OntClass ont = it2.next();
			System.out.println(ont.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass class2 = model2.getOntClass(ns + "Person");
				
		ExtendedIterator<Individual> it3 = (ExtendedIterator<Individual>) class2.listInstances();
	}
}
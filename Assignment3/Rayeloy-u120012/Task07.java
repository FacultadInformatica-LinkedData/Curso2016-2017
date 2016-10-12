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
		OntClass person=model.getOntClass(ns+"Person");
		ExtendedIterator <Individual> iter1 = model.listIndividuals(person);
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("The subclasses of Person are:");
		ExtendedIterator<OntClass> iter=model.getOntClass(ns+"Person").listSubClasses();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelInf=ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		InputStream input = FileManager.get().open(filename);
		if(input == null)
			throw new IllegalArgumentException("The file " + filename + " was not found.");
		modelInf.read(input, null);
		System.out.println("Inference:");
		ExtendedIterator<Individual> iter2=modelInf.listIndividuals(modelInf.getOntClass(ns+"Person"));
		while(iter2.hasNext()){
			System.out.println(iter2.next());
		}
		ExtendedIterator<OntClass> iter3=modelInf.getOntClass(ns+"Person").listSubClasses();
		while(iter3.hasNext()){
			System.out.println(iter3.next());
		}
	}
}

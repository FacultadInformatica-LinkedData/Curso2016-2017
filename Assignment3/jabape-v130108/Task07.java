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
		ExtendedIterator <Individual> iterador = model.listIndividuals(model.getOntClass(ns+"Person"));
		while(iterador.hasNext()) System.out.println(iterador.next().getURI());
		System.out.println("-------------------------------------------");
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator <OntClass> iterador2 = model.getOntClass(ns+"Person").listSubClasses();
		while(iterador2.hasNext()) System.out.println(iterador2.next().getURI());
		System.out.println("-------------------------------------------");
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		ExtendedIterator <Individual> iterador3 = (ExtendedIterator<Individual>) model2.getOntClass(ns+"Person").listInstances();
		while(iterador3.hasNext()) System.out.println(iterador3.next().getURI());
		ExtendedIterator <OntClass> iterador4 = model2.getOntClass(ns+"Person").listSubClasses();
		while(iterador4.hasNext()) System.out.println(iterador4.next().getURI());
		
	}
}

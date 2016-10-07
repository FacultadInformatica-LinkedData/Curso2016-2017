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


		Individual i;
		ExtendedIterator extendedIterator = model.getOntClass(ns+"Person").listInstances();
		while(extendedIterator.hasNext())
		{
			i = (Individual) extendedIterator.next();
			System.out.println( i.getURI() );
		}


		OntClass sc;
		extendedIterator = model.getOntClass(ns+"Person").listSubClasses();
		while(extendedIterator.hasNext())
		{
			sc = (OntClass) extendedIterator.hasNext();
			System.out.println("Person: " + sc.getURI());
		}

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **


	}
}

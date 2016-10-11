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
 *@author Sergio Palomino 
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
		ExtendedIterator<Individual> iter1 = model.listIndividuals(person);

		System.out.println("All individuals of Person:");
		while (iter1.hasNext())
		{
			Individual i = iter1.next();
			System.out.println(i.getURI());
		}

		// ** TASK 7.2: List all subclasses of "Person" **

		System.out.println("subclasses:");
		ExtendedIterator<OntClass> subIter = model.getOntClass(ns + "Person").listSubClasses();
		while (subIter.hasNext()) {
			System.out.println(subIter.next());
		}
		System.out.println("");

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelInference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		InputStream in2 = FileManager.get().open(filename);
		if (in2 == null)
			throw new IllegalArgumentException("File: " + filename + " not found");
		modelInference.read(in2, null);
		System.out.println("Individuals with inference:");

		ExtendedIterator<Individual> indIterIn = modelInference
				.listIndividuals(modelInference.getOntClass(ns + "Person"));
		while (indIterIn.hasNext()) {
			System.out.println(indIterIn.next());
		}
		System.out.println("");
		System.out.println("subclasses with inference:");

		ExtendedIterator<OntClass> subIterIn = modelInference.getOntClass(ns + "Person").listSubClasses();
		while (subIterIn.hasNext()) {
			System.out.println(subIterIn.next());
		}

	}
}

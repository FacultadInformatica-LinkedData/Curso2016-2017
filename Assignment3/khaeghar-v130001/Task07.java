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

		System.out.println("Individuals: ");

		while (model.listIndividuals(model.getOntClass(ns + "Person")).hasNext()) {

			System.out.println(model.listIndividuals(model.getOntClass(ns + "Person")).next()+" ");
		}


		// ** TASK 7.2: List all subclasses of "Person" **

		System.out.println("Person subclasses:");

		while (model.getOntClass(ns + "Person").listSubClasses().hasNext()) {
			System.out.println(model.getOntClass(ns + "Person").listSubClasses().next()+" ");
		}


		// ** TASK 7.3: Make the necessary changes to get as well indirect
		// instances and subclasses. TIP: you need some inference... **

		OntModel inferencedModel = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		in = FileManager.get().open(filename);

		if (in == null)
			throw new IllegalArgumentException("File: " + filename + " not found");

		inferencedModel.read(in, null);

		System.out.println("Inferenced individuals:");

		ExtendedIterator<Individual> it = inferencedModel.listIndividuals(inferencedModel.getOntClass(ns + "Person"));
		while (it.hasNext()) { System.out.println(it.next()); }

		System.out.println("Inferenced subclasses: ");

		ExtendedIterator<OntClass> it2 = inferencedModel.getOntClass(ns + "Person").listSubClasses();
		while (it2.hasNext()) { System.out.println(it2.next()); }

	}


}


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
 * @author Valeria Perfecto
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

		System.out.println("List all individuals of Person:");
		while (iter1.hasNext())
		{
			System.out.println(iter1.next().getURI());
		}

		// ** TASK 7.2: List all subclasses of "Person" **

		System.out.println("List all subclasses of Person:");
		ExtendedIterator<OntClass> subIter =person.listInstances(); 
		subIter=getOntClass(ns + "Person").listSubClasses();
		while (subIter.hasNext()) {
			OntClass subclass= (OntClass)subIter.next();
			System.out.println(subclass);
		}
		System.out.println("");

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelInference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		InputStream inf2 = FileManager.get().open(filename);
		
		if (inf2 == null)
			throw new IllegalArgumentException("File: " + filename + " not found");
		
		modelInference.read(inf2, null);

		System.out.println("Indirect Instances:");
		ExtendedIterator<Individual> iIterIn = modelInference.getOntClass(ns + "Person").listIndividuals;
		while (iIterIn.hasNext()) {
			System.out.println(iIterIn.next());
		}
		
		System.out.println("Subclasses:");
		ExtendedIterator<OntClass> sIterIn = modelInference.getOntClass(ns + "Person").listSubClasses();
		while (sIterIn.hasNext()) {
			System.out.println(sIterIn.next());
		}
	}
}
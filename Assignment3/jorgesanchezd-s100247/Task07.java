package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author jsanchezd
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
		ExtendedIterator<Individual> iter = model.listIndividuals(model.getResource(ns+"Person"));
		System.out.println("Individuals of Person: ");
		while(iter.hasNext()){
			System.out.println(iter.next().getURI());
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<? extends OntResource> iter2 = model.getOntClass(ns+"Person").listInstances();
		System.out.println("List of all subclasses of Person: ");
		iter2 = model.getOntClass(ns+"Person").listSubClasses();
		while(iter2.hasNext()){
			System.out.println(iter2.next());
		}

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		ExtendedIterator<OntClass> iter3 = model.getOntClass(ns+"Person").listSubClasses();
		while (iter3.hasNext()){
			OntClass subClass = iter3.next();
			ExtendedIterator<Individual> iter4 = (ExtendedIterator<Individual>) subClass.listInstances();
			System.out.println("The subclass: "+subClass.getURI()+" have the instances: ");
			while(iter4.hasNext()){
				System.out.println(iter4.next().getURI());
			}

		}
	}
}

package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
 * @author Jorge Amoros
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
		Resource person = model.getResource(ns + "Person");
		ExtendedIterator<Individual> individuals = model.listIndividuals(person);
		int size = 0;
		while (individuals.hasNext()) {
			  size++;
			  System.out.println(individuals.next().getURI());
			}
		System.out.println("------------> " + size);
		
		// ** TASK 7.2: List all subclasses of "Person" **
		
		OntClass myPersonClass = model.getOntClass(ns + "Person");
		ExtendedIterator<OntClass> subclasses = myPersonClass.listSubClasses();
		int size1 = 0;
		while (subclasses.hasNext()) {
			  size1++;
			  System.out.println(subclasses.next().getURI());
			}
		System.out.println("------------> " + size1);
		
		/*UNA SOLO System.out.println(myPersonClass.getSubClass().getURI());s*/
		
	
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		
		/* A specification for OWL DL models that are stored in memory and use the RDFS inferencer for additional entailments  */
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, model);
		
		OntClass PersonInference = model.getOntClass(ns+"Person");
		
		
		@SuppressWarnings("unchecked")
		ExtendedIterator<OntResource> instancias = (ExtendedIterator<OntResource>) PersonInference.listInstances();
		ExtendedIterator<OntClass> subclasses1 = PersonInference.listSubClasses();
		
		while(instancias.hasNext()){
			System.out.println(instancias.next().getURI());
		}
		while(subclasses1.hasNext()){
			System.out.println(subclasses1.next().getURI());
		}
		
		
	
	}
}

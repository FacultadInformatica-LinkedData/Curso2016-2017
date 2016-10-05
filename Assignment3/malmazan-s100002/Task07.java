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
		OntClass person = model.getOntClass(ns+"Person"); /*Class named Person*/
		ExtendedIterator<Individual> listPersons = model.listIndividuals();
		
		/*Loop to list all individual persons*/
		while (listPersons.hasNext()){
			Individual individualP = listPersons.next();
			//if (individualP.hasOntClass(person)){
				System.out.println("Individuals: "+ individualP); /*Print Uri of indviduals persons*/
			//}
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> listsubClass = person.listSubClasses();
		
		while(listsubClass.hasNext()){
			OntClass individualP2 =listsubClass.next();
			System.out.println("SubClase: "+ individualP2); /*Print Uri of SubClasses of persons*/
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		// Create an empty model
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model); /*New model with inference*/
		// Use the FileManager to find the input file
		InputStream in2 = FileManager.get().open(filename);
		// Read the RDF/XML file
		model.read(in2, null);
		
		OntClass personInf = inference.getOntClass(ns+"Person");
		ExtendedIterator<Individual> listPersonsInf = model.listIndividuals();
		
		while(listPersonsInf.hasNext()){
			Individual ind = listPersonsInf.next();
			System.out.println("Individuals Persons with inference: "+ ind);
		}
		
		ExtendedIterator<OntClass> listsubClassInf = personInf.listSubClasses();
		
		while(listsubClassInf.hasNext()){
			OntClass indS = listsubClassInf.next();
			System.out.println("SubClase with inference: "+indS);
		}
	}
}

package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.VCARD;

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
		//ExtendedIterator<Individual> it = model.listIndividuals(model.getOntClass(ns+"Person"));
		System.out.println("\n //////////////////// TASK 7.1 /////////////////// \n");

		for(ExtendedIterator<Individual> it = model.listIndividuals(model.getOntClass(ns+"Person"));it.hasNext();){
			Individual r = it.next();
			System.out.println("Individual: "+r);
		}
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n //////////////////// TASK 7.2 /////////////////// \n");

	OntClass mainclass = model.getOntClass(ns+"Person");
		for(ExtendedIterator<OntClass> it2 = mainclass.listSubClasses();it2.hasNext();){
			System.out.println("SubClase: "+it2.next());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		System.out.println("\n //////////////////// TASK 7.3 /////////////////// \n");
		
		OntClass clase = model.getOntClass(ns+"Person");
		System.out.println("Class: "+clase);
			
			ExtendedIterator<Individual> inference = model.listIndividuals(clase);
			while(inference.hasNext()){
				System.out.println("Individual: "+inference.next());
			}
			ExtendedIterator<OntClass> inference2 = clase.listSubClasses();
			while(inference2.hasNext()){
				OntClass aux = inference2.next();
				System.out.println("Subclass: "+aux);
				 ExtendedIterator<Individual> individualAux = model.listIndividuals(aux);
				 while(individualAux.hasNext())System.out.println("Individual: "+individualAux.next());
				ExtendedIterator<OntClass> inference3 = aux.listSubClasses();
				while(inference3.hasNext()){
					OntClass aux2 = inference3.next();
					System.out.println("Subclass of: "+aux+ ", "+aux2);
					
				}
				
			}
	
	}
}

package ontologyapi;

import java.io.InputStream;
import java.util.Iterator;

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
		System.out.println("TASK 7.1");
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator listInsP= person.listInstances();
		while(listInsP.hasNext()){
			Individual ind = (Individual) listInsP.next();			
			System.out.println("PERSON: "+ ind.getLocalName());
		}		
		System.out.println();
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("TASK 7.2");
		ExtendedIterator listSubC  = person.listSubClasses();	
		while(listSubC.hasNext()){
			OntClass subC = (OntClass) listSubC.next();			
			System.out.println("SUBCLASE: "+ subC.getLocalName());
		}
		System.out.println();
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("TASK 7.2");
		OntModel modelINF = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
		
		OntClass person_I = modelINF.getOntClass(ns+"Person");
		ExtendedIterator listInsP_I= person_I.listInstances();
		while(listInsP_I.hasNext()){
			Individual ind_I = (Individual) listInsP_I.next();			
			System.out.println("PERSON_I: "+ ind_I.getLocalName());
		}		
		System.out.println();
		
		ExtendedIterator listSubC_I  = person_I.listSubClasses();	
		while(listSubC_I.hasNext()){
			OntClass subC_I = (OntClass) listSubC_I.next();			
			System.out.println("SUBCLASE_I: "+ subC_I.getLocalName());
		}
		System.out.println();
		
	
	}
}

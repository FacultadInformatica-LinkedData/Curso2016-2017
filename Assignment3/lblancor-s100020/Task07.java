package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.rulesys.builtins.Print;
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
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator<Individual> iterInd = model.listIndividuals(person);
		int i = 1;
		while (iterInd.hasNext())
		{
			Individual individualPerson = iterInd.next();
			System.out.println("Individual "+i+" of Person: "+individualPerson.getURI());
			i++;
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> iterSubClass = person.listSubClasses();
		int j = 1;
		while (iterSubClass.hasNext())
		{
			OntClass subClassPerson = iterSubClass.next();
			System.out.println("Subclass "+j+" of Person: "+subClassPerson.getURI());
			j++;
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass person2 = model2.getOntClass(ns+"Person");
		
		System.out.println("Indirect instances of Person(individuals);");
		ExtendedIterator<Individual> iterInd2 = model2.listIndividuals(person2);
		while (iterInd2.hasNext()) 
		{
			Individual individual = iterInd2.next();
			System.out.println(individual.getURI());
		}
		System.out.println("Subclasses of Person:" );
		ExtendedIterator<OntClass> iterSubClass2 = person2.listSubClasses();
		while (iterSubClass2.hasNext()) 
		{
			OntClass subSubClass = iterSubClass2.next();
			System.out.println(subSubClass.getURI());
		}
	
	}
}

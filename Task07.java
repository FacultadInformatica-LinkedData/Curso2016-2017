package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.ComplementClass;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
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
		ExtendedIterator<Individual> iterador1 = model.listIndividuals(model.getResource(ns + "Person"));
		while(iterador1.hasNext()){
			System.out.println(iterador1.next().getURI());
			
		}
		
		
		// ** TASK 7.2: List all subclasses of "Person" **+
		OntClass person = model.getOntClass(ns + "Person");
		ExtendedIterator<? extends OntResource> iterador2 = person.listInstances();
		iterador2= person.listSubClasses();
		while(iterador2.hasNext()){
			
			System.out.println(iterador2.next());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		 Reasoner reason = ReasonerRegistry.getRDFSReasoner();
		    reason = reason.bindSchema(model);

		    OntModelSpec ontology = OntModelSpec.RDFS_MEM;
		    ontology.setReasoner(reason);

		    OntModel ontModel = ModelFactory.createOntologyModel(ontology, model);

		     person = ontModel.getOntClass(ns + "Person");


		    ExtendedIterator<? extends OntResource> iter = person.listInstances();

		    while (iter.hasNext()) {
		        System.out.println(iter.next());
		    }

		    ExtendedIterator<OntClass> iterPerson = person.listSubClasses();

		    while (iterPerson.hasNext()) {
		        System.out.println(iterPerson.next());
		    }
		
	
	}
}

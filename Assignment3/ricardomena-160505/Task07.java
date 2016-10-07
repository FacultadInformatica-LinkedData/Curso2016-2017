package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDFS;
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
		OntClass person = model.getOntClass(ns + "Person");
		ExtendedIterator<Individual> iter1 = model.listIndividuals(person);
		
		while(iter1.hasNext())
		{
				System.out.println("Individual: "+iter1.next().getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		
		String queryString =
			    "PREFIX rdfs: <" + RDFS.getURI() + ">"+
			    " SELECT ?subclass " +
			    " WHERE { ?subclass rdfs:subClassOf <http://somewhere#Person>.} " ;
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		ResultSet results = qexec.execSelect() ;
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("subclass");
		    System.out.println("Subclass of Person: "+subj.getURI());
		}

		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		
		Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
		InfModel inf = ModelFactory.createInfModel(reasoner, model);
		
		String queryString2 =
			    "PREFIX rdfs: <" + RDFS.getURI() + ">"+
			    " SELECT ?person " +
			    " WHERE { ?person a <http://somewhere#Person>.} " ;
		Query query2 = QueryFactory.create(queryString2);
		QueryExecution qexec2 = QueryExecutionFactory.create(query2, inf) ;
		ResultSet results2 = qexec2.execSelect() ;
		while (results2.hasNext())
		{
			QuerySolution binding2 = results2.nextSolution();
			Resource subj2 = (Resource) binding2.get("person");
		    System.out.println("Person: "+subj2.getURI());
		}
		
		
	}
}

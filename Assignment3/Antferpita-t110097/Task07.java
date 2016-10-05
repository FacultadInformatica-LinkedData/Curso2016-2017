package ontologyapi;



import java.io.InputStream;



import org.apache.jena.ontology.Individual;

import org.apache.jena.ontology.OntClass;

import org.apache.jena.ontology.OntModel;

import org.apache.jena.ontology.OntModelSpec;

import org.apache.jena.ontology.Ontology;

import org.apache.jena.rdf.model.ModelFactory;

import org.apache.jena.rdf.model.RDFNode;

import org.apache.jena.rdf.model.ResIterator;

import org.apache.jena.rdf.model.StmtIterator;

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



		System.out.println("TASK 7.1:");

		

		// ** TASK 7.1: List all individuals of "Person" **

		OntClass person=model.getOntClass(ns+"Person");



		ExtendedIterator<Individual> iter=model.listIndividuals(person);

		while(iter.hasNext()){

			Individual ind=iter.next();

			System.out.println(ind.toString());

		}



		System.out.println("TASK 7.2:");



		// ** TASK 7.2: List all subclasses of "Person" **



		ExtendedIterator<OntClass> iter2=person.listSubClasses();



		while(iter2.hasNext()){

			OntClass ont=iter2.next();

			System.out.println(ont.toString());

		}



		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **





		System.out.println("TASK 7.3:");

		

		OntModel modelInference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);



		InputStream in2 = FileManager.get().open(filename);



		if (in2 == null)

			throw new IllegalArgumentException("File: "+filename+" not found");



		modelInference.read(in2,null);



		OntClass person2=model.getOntClass(ns+"Person");



		ExtendedIterator<Individual> iter3=modelInference.listIndividuals(person2);

		

		while(iter3.hasNext()){

			Individual ind=iter3.next();

			System.out.println(ind.toString());

		}



		ExtendedIterator<OntClass> iter4=person2.listSubClasses();



		while(iter4.hasNext()){

			OntClass ont=iter4.next();

			System.out.println(ont.toString());

		}



	}

}


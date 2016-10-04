package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
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
	/**
	 * @author mariosaigon- JOSE MARIO LOPEZ LEIVA u120247
	 * @subject WEB SEMANTICA Y LINKED DATA ASSIGNMENT 3
	 * @param args
	 */
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
		OntClass persona=model.getOntClass(ns+"Person");
		printIndividuals(model,persona);	//metodo generico que imprime todos los individuos de una clase
		// ** TASK 7.2: List all subclasses of "Person" **
		printSubclasses(persona);		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		printIndirectInstances(persona,model);
	
	}
	//given a class, print its indirect instances (individuals) and subclasses
	private static void printIndirectInstances(OntClass clase, OntModel modelo) 
	{
		//pensar en arbol: cada nodo es una clase que puede tener instancias (hojas) o nodos (otras clases que serían subclases de la de arriba, etc)
		System.out.println("Analyzing class "+clase.getLocalName());
		//funcionamiento: para la clase que me dan, obtengo sus subclases, y de cada una de esas subclases obtengo los individuos, así estaría obteniendo las instancias indirectas de la clase pedida
		ExtendedIterator<OntClass> itclases=clase.listSubClasses();
		while(itclases.hasNext())
		{
			System.out.println("***********************************");
			OntClass sub=itclases.next();//obtengo la subclase
			System.out.println("Direct subclass: "+sub.getLocalName());
			ExtendedIterator<Individual> itindi=modelo.listIndividuals(sub);//para esa subclase obtengo todos los individuos
			ExtendedIterator<OntClass> itsubs=sub.listSubClasses();//las subclases de la subclase estudiada
			//now we navigate through that subclass and get the individuals 
			while(itindi.hasNext())
			{
				System.out.println("Indirect instance: "+itindi.next());
			}
			//printing the subclasses of a subclass
			while(itsubs.hasNext())
			{
				System.out.println("	Indirect Subclass: "+itsubs.next());
			}
			System.out.println("***********************************");
		}
		
	}
	//function aimed to print  all individuals belonging to a certain model and for a certain class within that model.
	private static void printIndividuals(OntModel modelo, OntClass clase)
	{
		ExtendedIterator<Individual> iterador=modelo.listIndividuals(clase);
		System.out.println("Individuals of class"+clase.getLocalName());
		while(iterador.hasNext())
		{
			System.out.println("Individual: "+iterador.next());
		}
	}
	////////////////////////////////function that prints all subclasses of the given class (ontholgy class)
	private static void printSubclasses(OntClass clase)
	{
		ExtendedIterator<OntClass> it=clase.listSubClasses();
		System.out.println("Subclasses of"+clase.getLocalName());
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
}

/* Pablo Aceituno Ferro, t110115
 * Ejercicio 7 Jena
 */

package ontologyapi;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
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
		ExtendedIterator<Individual> iter = model.listIndividuals(FOAF.Person);
		
		while (iter.hasNext()) {
		    Resource r = iter.next();
		    System.out.println("Individual: "+r.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator iter2 = model.listClasses();
		
		while (iter2.hasNext()){
			OntClass Class = (OntClass) iter2.next();          // clase ue vamos a ver si es la clase Person
			String vClass = Class.getLocalName().toString();
			if(Class.equals(FOAF.Person)){                     // si es la clase Person
				if(Class.hasSubClass()){                       // si tiene subclases la clase Person
					System.out.println("Class: " + vClass);
                    OntClass cla = model.getOntClass(ns + vClass);
                    for (Iterator i = cla.listSubClasses(); i.hasNext();) {
                        OntClass c = (OntClass) i.next();
                        System.out.print(" " + c.getLocalName() + " " + "\n");
                    }
				}
			}
        }
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		ExtendedIterator classes = model.listClasses();
        while (classes.hasNext()){
            OntClass Clase = (OntClass) classes.next();
            String vClasse = Clase.getLocalName().toString();
            if (Clase.hasSubClass()) {
                System.out.println("Classe: " + vClasse);
                OntClass cla = model.getOntClass(ns + vClasse);
                for (Iterator i2 = cla.listSubClasses(); i2.hasNext();) {
                    OntClass c = (OntClass) i2.next();
                    System.out.print(" " + c.getLocalName() + " " + "\n");
                }
            }
        }
	
	}
}

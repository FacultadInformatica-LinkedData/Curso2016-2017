package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

/**
 * Task 06: Modifying ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task06
{
	public static String ns = "http://somewhere#";
	public static String foafNS = "http://xmlns.com/foaf/0.1/";
	public static String foafEmailURI = foafNS+"email";
	public static String foafKnowsURI = foafNS+"knows";
	public static String stringTypeURI = "http://www.w3.org/2001/XMLSchema#string";
	/**
	 * @author mariosaigon- JOSE MARIO LOPEZ LEIVA u120247
	 * @subject WEB SEMANTICA Y LINKED DATA ASSIGNMENT 3
	 * @param args
	 */
	public static void main(String args[])
	{
		String filename = "example5.rdf";
		OntClass persona=null;
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		//
		//clase persona
		persona=model.createClass(ns+"Person");//creo la clase Person
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		// Create a new class named "Researcher"
		OntClass researcher = model.createClass(ns+"Researcher");
		
		// ** TASK 6.1: Create a new class named "University" **
		OntClass uni= model.createClass(ns+"University");
		
		// ** TASK 6.2: Add "Researcher" as a subclass of "Person" **	
		persona.addSubClass(researcher);
		
		// ** TASK 6.3: Create a new property named "worksIn" **
		Property propiedad=model.createProperty(ns+"worksIn");
		
		// ** TASK 6.4: Create a new individual of Researcher named "Jane Smith" **
		Individual janesmith=researcher.createIndividual(ns+"Jane Smith");
		
		// ** TASK 6.5: Add to the individual JaneSmith the fullName, given and family names **
		janesmith.addProperty(VCARD.FN, "Jane Smith");
		janesmith.addProperty(VCARD.Given, "Jane");
		janesmith.addProperty(VCARD.Family, "Smith");
		// ** TASK 6.6: Add UPM as the university where John Smith works **
		//creo la UPM que es clase hija de Universidad
		Individual upm=uni.createIndividual(ns+"UPM");
		//creo a John Smith que es investigador
		Individual johnsmith=researcher.createIndividual(ns+"John Smith");
		johnsmith.setPropertyValue(propiedad, upm);//digo: john smith worksIn --propiedad-- UPM
		
		model.write(System.out, "RDF/XML-ABBREV");
	}
}

package ontologyapi;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.reasoner.rulesys.RDFSRuleReasonerFactory;
import org.apache.jena.util.FileManager;

import static org.apache.jena.ontology.OntModelSpec.RDFS_MEM_RDFS_INF;

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

        System.out.println("Individuals of Person:");
        for(Iterator indivIter = model.listIndividuals(person); indivIter.hasNext();) {
            System.out.println(indivIter.next().toString());
        }

        // ** TASK 7.2: List all subclasses of "Person" **
        System.out.println("Subclasses of Person:");
        for(Iterator subclassIter = person.listSubClasses(); subclassIter.hasNext();) {
            System.out.println(model.getOntClass(subclassIter.next().toString()));
        }


        // ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
        OntModel inferModel = ModelFactory.createOntologyModel(RDFS_MEM_RDFS_INF, model);

        System.out.println("Direct and Indirect individuals of Person:");
        for(Iterator indivIter = inferModel.listIndividuals(model.getOntClass(ns + "Person")); indivIter.hasNext();) {
            System.out.println(indivIter.next().toString());
        }

        System.out.println("Direct and Indirect subclasses of Person:");
        for(Iterator subclassIter = inferModel.getOntClass(ns + "Person").listSubClasses(); subclassIter.hasNext();) {
            System.out.println(model.getOntClass(subclassIter.next().toString()));
        }

    }
}
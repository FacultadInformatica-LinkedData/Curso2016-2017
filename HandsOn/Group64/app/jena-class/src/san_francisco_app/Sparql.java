package san_francisco_app;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Sparql {

    public static String foafNS = "http://xmlns.com/foaf/0.1/";
    public static List<Film> resultFilm;

    public static String file_arts = "arts.ttl";
    public static String file_films = "films.ttl";
    public static String file_parks = "parks.ttl";

    public static Model model = null;
    public static InputStream in_arts = null;
    public static InputStream in_films = null;
    public static InputStream in_parks = null;

    static {

        // Create an empty model
        model = ModelFactory.createDefaultModel();

        // Use the FileManager to find the input file
        in_arts = FileManager.get().open(file_arts);
        in_films = FileManager.get().open(file_films);
        in_parks = FileManager.get().open(file_parks);

        if (in_arts == null)
            throw new IllegalArgumentException("File: " + file_arts + " not found");

        if (in_films == null)
            throw new IllegalArgumentException("File: " + file_films + " not found");

        if (in_parks == null)
            throw new IllegalArgumentException("File: " + file_parks + " not found");

        // Read the RDF/XML file
        model.read(file_arts, "TURTLE");
        model.read(file_films, "TURTLE");
        model.read(file_parks, "TURTLE");
    }

    public static List<Film> allFilm() {
        List<Film> result = new ArrayList<Film>();
        String queryString = "PREFIX owl:<http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX foaf:<http://xmlns.com/foaf/spec>" +
                "PREFIX mydbp:<http://grupo64.es/>" +
                "SELECT DISTINCT *\n" +
                "where {\n" +
                "       ?x a <http://mappings.dbpedia.org/server/ontology/classes/Film> . \n" +
                "       ?x mydbp:hasOriginalTitle ?a . \n" +
                "       ?x mydbp:hasFilmingLocation ?b . \n" +
                "       ?x mydbp:hasDirector ?c . \n" +
                "       ?x mydbp:hasWriter ?d . " +
                "       ?sub " +
                "       SERVICE <http://es.dbpedia.org/sparql> {" +
                "       }}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();
        System.out.println("HOLITA " + results.getRowNumber());
        while (results.hasNext()) {
            Film newFilm = new Film();
            QuerySolution res = results.nextSolution();
            RDFNode a = res.getLiteral("a");
            RDFNode b = res.getLiteral("b");
            RDFNode c = res.getLiteral("c");
            RDFNode d = res.getLiteral("d");
            RDFNode e = res.getResource("e");

            newFilm.setTitle(a.asLiteral().getString());
            newFilm.setLocation(b.asLiteral().getString());
            newFilm.setDirector(c.asLiteral().getString());
            newFilm.setWriter(d.asLiteral().getString());
            newFilm.setDirectorURI(e.asResource().getURI());
            System.out.println("[NAME]: " + newFilm.getDirector());
            System.out.println("[DIRECTOR]: " + newFilm.getDirectorURI());
            result.add(newFilm);
        }
        return result;
    }

    public static List<String> allPark() {
        List<String> result = new ArrayList<String>();
        String queryString = "SELECT DISTINCT *" +
                "where {?x a <http://mappings.dbpedia.org/server/ontology/classes/Artwork>}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();
        while (results.hasNext()) {
            String park = results.next().toString();
            park = park.substring(7, park.length() - 2);
            //System.out.println("Park: " + park);
            result.add(park);
        }
        return result;
    }

    public static List<String> getLocations(String filmName) {
        filmName = "\"" + filmName + "\"";
        List<String> result = new ArrayList<String>();
        String queryString = "SELECT DISTINCT *" +
                "where { ?x <http://grupo64.es/hasOriginalTitle> " + filmName + " . \n" +
                "        ?x <http://grupo64.es/hasFilmingLocation> ?z}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();
        while (results.hasNext()) {
            QuerySolution res = results.nextSolution();
            RDFNode z = res.getLiteral("z");
            System.out.println("[LOCATION] " + z.asLiteral().getString());
            result.add(z.asLiteral().getString());
        }
        return result;
    }

    public static ArrayList<String> getAllTitle() {
        ArrayList<String> result = new ArrayList<String>();
        String queryString = "SELECT DISTINCT *" +
                "where {    ?x a <http://mappings.dbpedia.org/server/ontology/classes/Film> . " +
                "           ?x <http://grupo64.es/hasOriginalTitle> ?z}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();
        while (results.hasNext()) {
            QuerySolution res = results.nextSolution();
            RDFNode z = res.getLiteral("z");
            System.out.println("[TITLE]: " + z.asLiteral().getString());
            result.add(z.asLiteral().getString());
        }
        if (result == null) {
            System.out.println("WRONG!!!!!!!");
        }
        return result;
    }

    public static List<String> getDirector(String filmName) {
        List<String> result = new ArrayList<String>();
        String queryString = "SELECT DISTINCT *" +
                "where {    ?x  <http://grupo64.es/hasOriginalTitle> " + filmName + " . " +
                "           ?x <http://grupo64.es/hasDirector> ?z}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();
        while (results.hasNext()) {
            QuerySolution res = results.nextSolution();
            RDFNode z = res.getLiteral("z");
            System.out.println("[DIRECTOR]: " + z.asLiteral().getString());
            result.add(z.asLiteral().getString());
        }
        return result;
    }

    public static List<String> getBirthPlace(String directorUri) {
        List<String> result = new ArrayList<String>();
        String newQuery = "PREFIX dbpedia-owl:<http://dbpedia.org/ontology/>" +
                "           SELECT * " +
                "           WHERE {" +
                "                   <" + directorUri + "> dbpedia-owl:birthPlace ?z }";
        Query query = QueryFactory.create(newQuery);
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
        ResultSet resultset = qexec.execSelect();
        while (resultset.hasNext()) {
            QuerySolution res1 = resultset.nextSolution();
            String birthplace = res1.get("z").toString();
            System.out.println("[BIRTH PLACE]: " + birthplace);
        }
        return result;
    }

    public static List<String> getURI() {
        List<String> result = new ArrayList<String>();
        String queryString = "PREFIX g64:<http://grupo64.es/film_locations/>" +
                "               PREFIX owl:<http://www.w3.org/2002/07/owl#>" +
                "               SELECT * " +
                "               WHERE {" +
                "                       ?x a <http://mappings.dbpedia.org/server/ontology/classes/Film> ." +
                "                       ?x owl:sameAs ?v }";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        while (results.hasNext()) {
            QuerySolution res = results.nextSolution();
            String x = res.get("v").toString();
            String v = res.get("x").toString();
            System.out.println("[X]: " + x);

            getBirthPlace(x);

            result.add(v);
        }
        return result;
    }



    public static void main(String[] args) {
        // List<Film> allFilms = allFilm(); // Cogemos todas las peliculas
        // List<String> allTitle = getTitle("\"Bicentennial Man\"");
        //List<String> allTitle = getAllTitle();
        // getLocations("The Lineup");
        //  getDirector("Star Trek IV: The Voyage Home");
        List<String> uris = getURI();
    }

}

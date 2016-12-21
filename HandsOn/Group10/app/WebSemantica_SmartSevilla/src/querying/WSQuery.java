package querying;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

public class WSQuery {


	/**
	 * Metodo getAllCategoryObjects
	 * Este metodo devuelve todos los recursos encontrados en nuestro RDF que,
	 * al enlazarse con DBpedia, resultan ser de un tipo concreto.
	 * 
	 * @param categoria - Categoria por la que se desea buscar.
	 * Se recomienda que no incluya todas las letras (p.ej en vez de barroco, poner barroc
	 * ya que al poner barroco se perderian resultados de "Arquitectura barroca")
	 * 
	 * @return Lista de InfoResult con la descripcion, nombre e imagen de dbpedia de cada recurso
	 */
	public List<InfoResult> getAllCategoryObjects (String categoria) {

		String filename = "";
		String category = "";
		if (categoria.equals("Arte Mudejar"))
		{
			filename = "WS_LugaresInteres_Updated-with-links.ttl";
			category = "Arte_mud";
		}
		else if (categoria.equals("Barroco"))
		{
			filename = "WS_LugaresInteres_Updated-with-links.ttl";
			category = "barroc";
		}
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");

		List<InfoResult> list = new LinkedList<InfoResult>();

		String query = "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
				"PREFIX dbp: <http://dbpedia.org/property/> "+
				"PREFIX dbo: <http://dbpedia.org/ontology/> "+
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> "+
				"SELECT * WHERE  { ?sub dbp:fullname ?obj . "+
				"?sub owl:sameAs ?DbpediaUri . "+
				"SERVICE <http://es.dbpedia.org/sparql> { "+
				"?DbpediaUri dbo:wikiPageWikiLink ?x . "+
				"?DbpediaUri foaf:depiction ?im . "+
				"?DbpediaUri dbo:abstract ?abstract . "+
				"FILTER regex(?x,\"" + category +"\",'i') . "+
				"} } ";

		Query queryE = QueryFactory.create(query);
		QueryExecution qexec = QueryExecutionFactory.create(queryE, model);
		ResultSet results = qexec.execSelect();

		while (results.hasNext()) 
		{
			InfoResult ir = new InfoResult();
			QuerySolution res = results.nextSolution();
			RDFNode abstraction = res.getLiteral("abstract");
			RDFNode name = res.getLiteral("obj");
			RDFNode im = res.getResource("im");
			ir.setAbstraction(abstraction.asLiteral().getString());
			ir.setEstilo(name.asLiteral().getString());
			ir.setImage(im.asResource().getURI());
			list.add(ir);
		}
		return list;
	}

	/**
	 * Metodo getAllCategoryObjectsWithRedirect
	 * Este metodo devuelve todos los recursos encontrados en nuestro RDF que,
	 * al enlazarse con DBpedia, resultan ser de un tipo concreto.
	 * Difiere de la version anterior en que, en este caso, se realiza una redireccion a
	 * otra pagina de dbpedia (se hace porque hay conflictos en las paginas que llevan acentos)
	 * 
	 * @param categoria - Categoria por la que se desea buscar.
	 * Se recomienda que no incluya todas las letras (p.ej en vez de barroco, poner barroc
	 * ya que al poner barroco se perderian resultados de "Arquitectura barroca")
	 * 
	 * @return Lista de InfoResult con la descripcion, nombre e imagen de dbpedia de cada recurso
	 */
	public List<InfoResult> getAllCategoryObjectsWithRedirect (String categoria) {

		String filename = "";
		String category = "";
		if (categoria.equals("XVII"))
		{
			filename = "WS_LugaresInteres_Updated-with-links.ttl";
			category = "XVII";
		}
		else if (categoria.equals("Otros bienes culturales"))
		{
			filename = "WS_LugaresInteres_Updated-with-links.ttl";
			category = "cultur";
		}
		else
		{
			filename = "WS_Museos_Updated-with-links.ttl";
			category = "militar";
		}
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");

		List<InfoResult> list = new LinkedList<InfoResult>();

		String query = "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
				"PREFIX dbp: <http://dbpedia.org/property/> "+
				"PREFIX dbo: <http://dbpedia.org/ontology/> "+
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> "+
				"SELECT ?obj ?im ?abstract WHERE  { ?sub dbp:fullname ?obj . "+
				"?sub owl:sameAs ?DbpediaUri . "+
				"SERVICE <http://es.dbpedia.org/sparql> { "+
				"?DbpediaUri dbo:wikiPageRedirects ?wikis . "+
				"?wikis dbo:wikiPageWikiLink ?x . "+
				"?wikis foaf:depiction ?im . "+
				"?wikis dbo:abstract ?abstract . "+
				"FILTER regex(?x,\"" + category +"\",'i') . "+
				"} } ";

		Query queryE = QueryFactory.create(query);
		QueryExecution qexec = QueryExecutionFactory.create(queryE, model);
		ResultSet results = qexec.execSelect();

		while (results.hasNext()) 
		{
			InfoResult ir = new InfoResult();
			QuerySolution res = results.nextSolution();
			RDFNode abstraction = res.getLiteral("abstract");
			RDFNode name = res.getLiteral("obj");
			RDFNode im = res.getResource("im");
			ir.setAbstraction(abstraction.asLiteral().getString());
			ir.setEstilo(name.asLiteral().getString());
			ir.setImage(im.asResource().getURI());
			list.add(ir);
		}
		return list;
	}

	/**
	 * 
	 * @param valorNuevo
	 * @return
	 */
	public InfoResult getDataOf(String valorNuevo) {

		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open("WS_LugaresInteres_Updated-with-links.ttl");
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");

		String query = "PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
				"PREFIX dbp: <http://dbpedia.org/property/> "+
				"PREFIX dbo: <http://dbpedia.org/ontology/> "+
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> "+
				"SELECT ?info ?im "+
				"WHERE { ?sub dbp:fullname \"" + valorNuevo + "\"^^<xsd:string> . "+
				"?sub owl:sameAs ?DbpediaUri . "+  
				"SERVICE <http://es.dbpedia.org/sparql> { "+
				"?DbpediaUri foaf:depiction ?im . "+
				"?DbpediaUri dbo:abstract ?info . } } "; 

		Query queryE = QueryFactory.create(query);
		QueryExecution qexec = QueryExecutionFactory.create(queryE, model);
		ResultSet results = qexec.execSelect();

		if (!results.hasNext())
		{
			query = " PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
					"PREFIX dbp: <http://dbpedia.org/property/> "+
					"PREFIX dbo: <http://dbpedia.org/ontology/> "+
					"PREFIX foaf: <http://xmlns.com/foaf/0.1/> "+
					"SELECT ?info ?im "+
					"WHERE { ?sub dbp:fullname \"" + valorNuevo + "\"^^<xsd:string> . "+
					"?sub owl:sameAs ?DbpediaUri . "+  
					"SERVICE <http://es.dbpedia.org/sparql> { "+
					"?DbpediaUri dbo:wikiPageRedirects ?wikis . "+
					"?wikis foaf:depiction ?im . "+
					"?wikis dbo:abstract ?info . } } "; 

			queryE = QueryFactory.create(query);
			qexec = QueryExecutionFactory.create(queryE, model);
			results = qexec.execSelect();
			if (!results.hasNext()) return null;
			QuerySolution binding = results.nextSolution();
			RDFNode x = binding.getLiteral("info");
			RDFNode x2 = binding.getResource("im");				
			InfoResult ir = new InfoResult();
			ir.setEstilo(valorNuevo);
			ir.setAbstraction(x.asLiteral().getString());
			ir.setImage(x2.asResource().getURI());
			return ir;
		}


		QuerySolution binding = results.nextSolution();
		RDFNode x = binding.getLiteral("info");
		RDFNode x2 = binding.getResource("im");				
		InfoResult ir = new InfoResult();
		ir.setEstilo(valorNuevo);
		ir.setAbstraction(x.asLiteral().getString());
		ir.setImage(x2.asResource().getURI());
		return ir;

	}

	public InfoResultMuseo getDataOf_Museo(String valorNuevo) {

		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open("WS_Museos_Updated-with-links.ttl");
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");

		String query = "PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
				"PREFIX dbp: <http://dbpedia.org/property/> "+
				"PREFIX dbo: <http://dbpedia.org/ontology/> "+
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> "+
				"SELECT ?info ?im ?url ?address ?nt "+
				"WHERE { ?sub dbp:fullname \"" + valorNuevo + "\"^^<xsd:string> . "+
				"?sub owl:sameAs ?DbpediaUri . "+
				"?sub dbp:url ?url . "+
				"?sub dbp:address ?address . "+
				"?sub dbp:phoneNumber ?nt . "+
				"SERVICE <http://es.dbpedia.org/sparql> { "+
				"?DbpediaUri foaf:depiction ?im . "+
				"?DbpediaUri dbo:abstract ?info . } } "; 

		Query queryE = QueryFactory.create(query);
		QueryExecution qexec = QueryExecutionFactory.create(queryE, model);
		ResultSet results = qexec.execSelect();

		if (!results.hasNext())
		{
			query = " PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
					"PREFIX dbp: <http://dbpedia.org/property/> "+
					"PREFIX dbo: <http://dbpedia.org/ontology/> "+
					"PREFIX foaf: <http://xmlns.com/foaf/0.1/> "+
					"SELECT ?info ?im ?url ?address ?nt "+
					"WHERE { ?sub dbp:fullname \"" + valorNuevo + "\"^^<xsd:string> . "+
					"?sub owl:sameAs ?DbpediaUri . "+
					"?sub dbp:url ?url . "+
					"?sub dbp:address ?address . "+
					"?sub dbp:phoneNumber ?nt . "+
					"SERVICE <http://es.dbpedia.org/sparql> { "+
					"?DbpediaUri dbo:wikiPageRedirects ?wikis . "+
					"?wikis foaf:depiction ?im . "+
					"?wikis dbo:abstract ?info . } } "; 

			queryE = QueryFactory.create(query);
			qexec = QueryExecutionFactory.create(queryE, model);
			results = qexec.execSelect();
			if (!results.hasNext()) return null;
			QuerySolution binding = results.nextSolution();
			RDFNode x = binding.getLiteral("info");
			RDFNode x2 = binding.getResource("im");	
			RDFNode x3 = binding.getResource("url");
			RDFNode x4 = binding.getLiteral("address");
			RDFNode x5 = binding.getLiteral("nt");
			InfoResultMuseo ir = new InfoResultMuseo();
			ir.setNombre(valorNuevo);
			ir.setAbstraction(x.asLiteral().getString());
			ir.setImage(x2.asResource().getURI());
			ir.setDireccion(x4.asLiteral().getString());
			ir.setNtelf(x5.asLiteral().getString());
			ir.setWeb(x3.asResource().getURI());
			return ir;
		}


		QuerySolution binding = results.nextSolution();
		RDFNode x = binding.getLiteral("info");
		RDFNode x2 = binding.getResource("im");
		RDFNode x3 = binding.getResource("url");
		RDFNode x4 = binding.getLiteral("address");
		RDFNode x5 = binding.getLiteral("nt");
		InfoResultMuseo ir = new InfoResultMuseo();
		ir.setNombre(valorNuevo);
		ir.setAbstraction(x.asLiteral().getString());
		ir.setImage(x2.asResource().getURI());
		ir.setDireccion(x4.asLiteral().getString());
		ir.setNtelf(x5.asLiteral().getString());
		ir.setWeb(x3.asResource().getURI());
		return ir;

	}
}

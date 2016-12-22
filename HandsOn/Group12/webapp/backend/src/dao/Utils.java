package dao;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.json.simple.JSONObject;

public class Utils {

	private ResultSet executeQuery (String queryString, Model model){
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		return qexec.execSelect();
	}

	private ResultSet executeQueryDBPediaES (String queryString) {
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://es.dbpedia.org/sparql", query);
		return qexec.execSelect();
	}

	private ResultSet executeQueryDBPedia (String queryString) {
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		return qexec.execSelect();
	}

	public ResultSet sparqlDB (String queryString) {
		ResultSet res = executeQueryDBPediaES (queryString);

		if(!res.hasNext()){ //es.dbpedia no devuelve nada
			res = executeQueryDBPedia(queryString);
			if(!res.hasNext()){//dbpedia no devuelve nada
				res = null;
			}
		}
		return res; 	
	}

	public String printJSON (JSONObject json) throws IOException {
		StringWriter out = new StringWriter();
		json.writeJSONString(out);
		return out.toString();
	}

	@SuppressWarnings("unchecked")
	public JSONObject makeJSON (Model model, Model model2, int idBarrio){

		// Nombre de barrio y el nombre del distrito
		JSONObject json = getBarrioInfo(model, idBarrio);

		// ------------- Avisos (Barrio, Distrito y Madrid)

		// Numero de avisos de un barrio
		int avisosBarrio = getAvisosBarrio(model, idBarrio);
		//		System.out.println("Numero de avisos del barrio " + idBarrio + " es: " + avisosBarrio);

		//Numero de avisos de un distrito
		int avisosDistrito = getAvisosDistrito(model, idBarrio);
		//		System.out.println("Numero de avisos del distrito del barrio " + idBarrio + " es: " + avisosDistrito);

		// Numero de avisos de Madrid (numero total de avisos)
		int avisosMadrid = getAvisosMadrid(model);
		//		System.out.println("Numero de avisos de Madrid es: " + avisosMadrid);

		JSONObject avisos = new JSONObject();
		avisos.put("barrio", avisosBarrio);
		avisos.put("distrito", avisosDistrito);
		avisos.put("madrid", avisosMadrid);

		json.put("avisos", avisos);

		// ------------- Puntuaciones (Barrio, Distrito y Madrid)
		// Puntuacion Barrio
		double puntBarrio = getValoracionBarrio(model2, idBarrio);

		// Puntuacion Distrito
		double puntDistrito = getValoracionDistrito(model2, idBarrio);

		// Puntuacion Madrid
		double puntMadrid = getValoracionMadrid(model2);

		JSONObject puntuaciones = new JSONObject();
		puntuaciones.put("barrio", puntBarrio);
		puntuaciones.put("distrito", puntDistrito);
		puntuaciones.put("madrid", puntMadrid);

		json.put("puntuaciones", puntuaciones);
		// ------------- Poblacion (Barrio, Distrito y Madrid)
		// Poblacion Madrid 
		int poblacionMadrid = getPoblacionMadrid();

		json.put("poblacion", poblacionMadrid);

		// ------------- Imagen (Barrio, Distrito y Madrid)
		// Imagen Barrio (si tiene)
		String thumbBarrio = getThumbnailBarrio(model, idBarrio);

		// Imagen Distrito (si tiene)
		String thumbDistrito = getThumbnailDistrito(model, idBarrio);

		// Imagen Madrid
		String thumbMadrid = getThumbnailMadrid();

		JSONObject imagenes = new JSONObject();
		imagenes.put("barrio", thumbBarrio);
		imagenes.put("distrito", thumbDistrito);
		imagenes.put("madrid", thumbMadrid);

		json.put("imagenes", imagenes);


		return json;
	}


	private List<Integer> getIdsBarrios (Model model){
		String queryBarrios = "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#>"
				+ "SELECT ?Barrio ?Nombre ?Distrito"
				+ "WHERE {?Barrio ontology:hasNombre ?Nombre."
				+ "?Barrio ontology:hasDistrito ?Distrito}";
		ResultSet results = executeQuery(queryBarrios, model);
		List<Integer> ids = new ArrayList<>();
//		int cont = 0;
		while(results.hasNext()){
			QuerySolution binding = results.next();
			Resource barrio = (Resource) binding.get("Barrio");
			int id = Integer.valueOf(barrio.getURI().substring(48));
			ids.add(id);
//			cont++;
		}
//		System.out.println("Numero de barrios: " + cont);
		return ids;
	}

	public List<JSONObject> getBarrios (Model model, Model model2){
		List<Integer> ids = getIdsBarrios(model);
		List<JSONObject> list = new ArrayList<>();
		for (Integer idBarrio : ids) {
			JSONObject json = makeJSON(model, model2, idBarrio);
			list.add(json);
//			System.out.println(json);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private JSONObject getBarrioInfo (Model model, int idBarrio) {
		JSONObject json = new JSONObject();
		String urlBarrio = "<http://semanticweb.org/grupo12/resources/Barrio/";

		String queryBarrio = "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?NombreBarrio ?NombreDistrito "
				+ "WHERE {" + urlBarrio + idBarrio + ">" + " ontology:hasNombre ?NombreBarrio."
				+ urlBarrio + idBarrio + ">" + " ontology:hasDistrito ?Distrito."
				+ "?Distrito ontology:hasNombre ?NombreDistrito}";
		ResultSet results = executeQuery(queryBarrio, model);

		while (results.hasNext()){
			QuerySolution binding = results.next();
			Literal distrito = binding.getLiteral("NombreDistrito");
			Literal nombre = binding.getLiteral("NombreBarrio");
			json.put("nombre", nombre.toString());
			json.put("distrito", distrito.toString());
		}

		return json;

	}

	private int getAvisosBarrio (Model model, int idBarrio) {
		//		JSONObject json = new JSONObject();
		String urlBarrio = "<http://semanticweb.org/grupo12/resources/Barrio/";
		String queryAvisos = "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?Aviso "
				+ "WHERE {?Aviso ontology:hasBarrio " + urlBarrio + idBarrio + ">}";
		ResultSet results = executeQuery(queryAvisos, model);

		int cont = 0;
		while (results.hasNext()){
			results.next();
			cont++;
		}
		return cont;	
	}

	private int getAvisosDistrito (Model model, int idBarrio) throws NoSuchElementException{
		String urlBarrio = "<http://semanticweb.org/grupo12/resources/Barrio/";

		String queryDistrito = "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?Distrito "
				+ "WHERE {" + urlBarrio + idBarrio + "> ontology:hasDistrito ?Distrito}";
		ResultSet results = executeQuery(queryDistrito, model);
		// Como solo hay una respuesta (un barrio tiene un distrito), se hace un solo next
		QuerySolution binding = results.next();
		Resource distrito = (Resource) binding.get("Distrito");

		String queryAvisos = "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?Aviso ?Barrio "
				+ "WHERE {?Aviso ontology:hasBarrio ?Barrio."
				+ "?Barrio ontology:hasDistrito " + "<" + distrito.getURI() + ">" + "}";

		results = executeQuery(queryAvisos, model);
		int cont = 0;
		while(results.hasNext()){
			results.next();
			cont++;
		}
		return cont;
	}

	private int getAvisosMadrid (Model model){
		String queryMadrid = "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?Aviso ?Barrio"
				+ "WHERE {?Aviso ontology:hasBarrio ?Barrio}";
		ResultSet results = executeQuery(queryMadrid, model);
		int cont = 0;
		while(results.hasNext()){
			results.next();
			cont++;
		}
		return cont;
	}

	private int getPoblacionMadrid() {
		String queryMadrid = "SELECT ?Poblacion "
				+ "WHERE {<http://dbpedia.org/resource/Madrid> <http://dbpedia.org/ontology/populationTotal> ?Poblacion}";

		ResultSet results = sparqlDB(queryMadrid);
		QuerySolution binding = results.next();
		Literal poblacion = binding.getLiteral("Poblacion");
		//		System.out.println("La poblacion de Madrid es: " + poblacion.getInt());

		return poblacion.getInt();
	}
	
	private double getValoracionBarrio(Model model, int idBarrio) {
		String queryValBarrio = "PREFIX barr: <http://semanticweb.org/grupo12/resources/Barrio/> "
				+ "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?Valoracion ?Valor "
				+ "WHERE {?Valoracion ontology:hasBarrio barr:" + idBarrio + "."
				+ "?Valoracion ontology:hasPuntuacion ?Valor}";
		ResultSet results = executeQuery(queryValBarrio, model);
		int nResults = 0;
		double acc = 0;
		double res;
		if(results.hasNext()){
			while(results.hasNext()){
				QuerySolution binding = results.next();
				Literal valor = binding.getLiteral("Valor");
				acc += valor.getDouble();
				nResults++;
			}
			res = Math.rint((acc/nResults)*10)/10;
			//		System.out.println("Res (Barrio) redondeado a 1 decimal: " + res);
		} else {
			res = 0;
		}
		return res;
	}

	private double getValoracionDistrito(Model model, int idBarrio) {
		String queryDistrito = "PREFIX barr: <http://semanticweb.org/grupo12/resources/Barrio/> "
				+ "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?Distrito "
				+ "WHERE {barr:" + idBarrio + " ontology:hasDistrito ?Distrito}";
		ResultSet results = executeQuery(queryDistrito, model);
		// Como solo hay una respuesta (un barrio tiene un distrito), se hace un solo next
		QuerySolution binding = results.next();
		Resource distrito = (Resource) binding.get("Distrito");


		String queryValDistrito = "PREFIX barr: <http://semanticweb.org/grupo12/resources/Barrio/> "
				+ "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?Valoracion ?Barrio ?Valor "
				+ "WHERE {?Valoracion ontology:hasBarrio ?Barrio."
				+ "?Barrio ontology:hasDistrito " + "<" + distrito.getURI() + ">."
				+ "?Valoracion ontology:hasPuntuacion ?Valor}";
		results = executeQuery(queryValDistrito, model);
		int nResults = 0;
		double acc = 0;
		while(results.hasNext()){
			QuerySolution binding2 = results.next();
			Literal valor = binding2.getLiteral("Valor");
			acc += valor.getDouble();
			nResults++;
		}
		double res = Math.rint((acc/nResults)*10)/10;
		//		System.out.println("Res (Distrito) redondeado a 1 decimal: " + res);

		return res;
	}

	private double getValoracionMadrid(Model model) {
		String queryValMadrid = "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "SELECT ?Valoracion ?Valor "
				+ "WHERE {?Valoracion ontology:hasPuntuacion ?Valor}";

		ResultSet results = executeQuery(queryValMadrid, model);
		int nResults = 0;
		double acc = 0;
		while(results.hasNext()){
			QuerySolution binding2 = results.next();
			Literal valor = binding2.getLiteral("Valor");
			acc += valor.getDouble();
			nResults++;
		}
		double res = acc/nResults;

		res = Math.rint(res*10)/10;
		//		System.out.println("Res (Madrid) redondeado a 1 decimal: " + res);

		return res;
	}

	private String getThumbnailBarrio (Model model, int idBarrio) {
		String queryBarrio = "PREFIX barr: <http://semanticweb.org/grupo12/resources/Barrio/> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
				+ "SELECT ?BarrioDB "
				+ "WHERE {barr:" + idBarrio + " owl:sameAs ?BarrioDB}";
		ResultSet results = executeQuery(queryBarrio, model);
		String res;
		if(results.hasNext()){ // Si el barrio tiene el link a DBPedia
			QuerySolution binding = results.next();
			String barrioDBPedia = binding.get("BarrioDB").toString();
			//			System.out.println("DBPEDIA es: " + barrioDBPedia);

			String queryBarrioDB = "PREFIX ont: <http://dbpedia.org/ontology/> "
					+ "SELECT ?Imagen "
					+ "WHERE {<" + barrioDBPedia + ">" + " ont:thumbnail ?Imagen}";
			results = sparqlDB(queryBarrioDB);
			if(results == null || !results.hasNext()){
				res = ""; // Si no se puede consultar en ningun lado o si no tiene un thumbnail
			} else {
				binding = results.next();
				res = binding.get("Imagen").toString();
			}

		} else{ 
			res = ""; // Si el barrio no tiene el link a DBPedia
		}
		return res;
	}

	private String getThumbnailDistrito (Model model, int idBarrio){
		String queryDistrito = "PREFIX barr: <http://semanticweb.org/grupo12/resources/Barrio/> "
				+ "PREFIX ontology: <http://semanticweb.org/grupo12/ontology#> "
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
				+ "SELECT ?Distrito ?DistritoDB "
				+ "WHERE {barr:" + idBarrio + " ontology:hasDistrito ?Distrito."
				+ "?Distrito owl:sameAs ?DistritoDB}";
		ResultSet results = executeQuery(queryDistrito, model);
		String res ;
		if(results.hasNext()){ // Si el distrito tiene el link a DBPedia
			QuerySolution binding = results.next();
			String distritoDBPedia = binding.get("DistritoDB").toString();
			//			System.out.println("DBPEDIA es: " + distritoDBPedia);

			String queryDistritoDB = "PREFIX ont: <http://dbpedia.org/ontology/> "
					+ "SELECT ?Imagen "
					+ "WHERE {<" + distritoDBPedia + ">" + " ont:thumbnail ?Imagen}";
			results = sparqlDB(queryDistritoDB);
			if(results == null || !results.hasNext()){
				res = "";
			} else {
				binding = results.next();
				res = binding.get("Imagen").toString();
			}
		} else{
			res = ""; // Si el distrito no tiene el link a DBPedia
		}
		return res;
	}


	private String getThumbnailMadrid() {
		String queryMadrid = "SELECT ?Imagen "
				+ "WHERE {<http://dbpedia.org/resource/Madrid> <http://dbpedia.org/ontology/thumbnail> ?Imagen}";

		ResultSet results = executeQueryDBPediaES(queryMadrid);
		QuerySolution binding = results.nextSolution();
		return binding.get("Imagen").toString();
		
	}
}

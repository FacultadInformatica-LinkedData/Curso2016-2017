package WebSem;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

/**
 *	@author Grupo 07
 */
public class datos
{
    public static String empleo_publico = "https://raw.githubusercontent.com/cecilia21/Curso2016-2017/master/HandsOn/Group07/rdf/Empleo-Publico-with-links.ttl";
    public static String ofertas_Empleo = "https://raw.githubusercontent.com/cecilia21/Curso2016-2017/master/HandsOn/Group07/rdf/Ofertas-de-Empleo-with-links.ttl";
        

    private static ResultSet hacerQuery(String rdf, String queryString){
	ResultSet results = null;
	try{
            URLConnection conn = new URL(rdf).openConnection();
            InputStream in = conn.getInputStream();
            Model model = ModelFactory.createDefaultModel();
            if (in == null)
		throw new IllegalArgumentException("File: "+" not found");
            model.read(in, null);
            Query query = QueryFactory.create(queryString);
            QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
            results = qexec.execSelect();
	}catch(Exception e){
	}
	return results;
    }

    public static ArrayList<String> mostrarNombres (boolean emp_publico, String provincia, String localidad, String org_Gestor ){
        ArrayList<String> resultado = new ArrayList<String>();
        try{
            String rdf;
            String res;
            if (emp_publico){
                rdf = empleo_publico;
                if (provincia != ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "SELECT ?Subject ?Given ?Org_Gestor "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given;"
                        + "?Subject JobSearch:Provincia "+ provincia +";"
                        + "?Subject JobSearch:OrganismoGestor ?Org_Gestor."
                        + "}";
                    ResultSet results = hacerQuery(rdf,queryString);
                    while (results.hasNext()){
                        QuerySolution binding = results.nextSolution();
                        Literal given = binding.getLiteral("Given");
                        Literal gestor = binding.getLiteral("Org_Gestor");
                        res = given+" "+" "+provincia+" "+gestor;
                        resultado.add(res);
                    }
                }
                if (org_Gestor != ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "SELECT ?Subject ?Given ?Provincia "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given;"
                        + "?Subject JobSearch:OrganismoGestor "+ org_Gestor +";"
                        + "?Subject JobSearch:Provincia ?Provincia."
                        + "}";
                    ResultSet results = hacerQuery(rdf,queryString);
                    while (results.hasNext()){
                        QuerySolution binding = results.nextSolution();
                        Literal given = binding.getLiteral("Given");
                        Literal prov = binding.getLiteral("Provincia");
                        res = given+" "+" "+prov+" "+org_Gestor;
                        if(!resultado.contains(res)){
                            resultado.add(res);
                        }
                    }
                }
                if(org_Gestor == "" && provincia == ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "SELECT ?Subject ?Given ?Provincia ?Org_Gestor "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given;"
                        + "?Subject JobSearch:OrganismoGestor ?Org_Gestor;"
                        + "?Subject JobSearch:Provincia ?Provincia."
                        + "}";
                    ResultSet results = hacerQuery(rdf,queryString);
                    while (results.hasNext()){
                        QuerySolution binding = results.nextSolution();
                        Literal given = binding.getLiteral("Given");
                        Literal prov = binding.getLiteral("Provincia");
                        Literal gestor = binding.getLiteral("Org_Gestor");
                        res = given+" "+" "+prov+" "+gestor;
                        if(!resultado.contains(res)){
                            resultado.add(res);
                        }
                    }
                }
            }
            else {
                rdf = ofertas_Empleo;
                if (provincia != ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "SELECT ?Subject ?Given ?Localidad "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given;"
                        + "?Subject JobSearch:Provincia "+ provincia +";"
                        + "?Subject JobSearch:Localidad ?Localidad."
                        + "}";
                    ResultSet results = hacerQuery(rdf,queryString);
                    while (results.hasNext()){
                        QuerySolution binding = results.nextSolution();
                        Literal given = binding.getLiteral("Given");
                        Literal local = binding.getLiteral("Localidad");
                        res = given+" "+" "+local+" "+provincia;
                        resultado.add(res);
                    }
                }
                if (localidad != ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "SELECT ?Subject ?Given ?Provincia "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given;"
                        + "?Subject JobSearch:Localidad "+ localidad +";"
                        + "?Subject JobSearch:Provincia ?Provincia."
                        + "}";
                    ResultSet results = hacerQuery(rdf,queryString);
                    while (results.hasNext()){
                        QuerySolution binding = results.nextSolution();
                        Literal given = binding.getLiteral("Given");
                        Literal prov = binding.getLiteral("Provincia");
                        res = given+" "+" "+localidad+" "+prov;
                        if(!resultado.contains(res)){
                            resultado.add(res);
                        }
                    }
                }
                if ( localidad == "" && provincia == ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "SELECT ?Subject ?Given ?Provincia ?Localidad "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given;"
                        + "?Subject JobSearch:Localidad ?Localidad;"
                        + "?Subject JobSearch:Provincia ?Provincia."
                        + "}";
                    ResultSet results = hacerQuery(rdf,queryString);
                    while (results.hasNext()){
                        QuerySolution binding = results.nextSolution();
                        Literal given = binding.getLiteral("Given");
                        Literal local = binding.getLiteral("Localidad");
                        Literal prov = binding.getLiteral("Provincia");
                        res = given+" "+" "+local+" "+prov;
                        if(!resultado.contains(res)){
                            resultado.add(res);
                        }
                    }
                }
            }
        }catch(Exception e){
        }
        return resultado;
    }
        
    public static String info (boolean emp_publico , String uri){
        String resultado = "";
        try{
            String rdf = "";
            if (emp_publico){
                rdf = empleo_publico;
                String queryString = 
                    "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                    "SELECT  ?Subject ?Org_Gest ?Num_Plazas ?Tasas ?Lugar_Pres ?Provincia" +
                            "?Plazo_Present ?Fecha_Ini ?Fecha_Fin ?Requisitos ?Procedimiento ?Ult_Act ?Enlace "+
                    "WHERE {" +
                    "?Subject  JobSearch:Titulo  '" +uri +"' ." +
                    "?Subject  JobSearch:OrganismoGestor  ?Org_Gest." +
                    "?Subject  JobSearch:NumeroPlazas  ?Num_Plazas." +
                    "?Subject  JobSearch:Tasas  ?Tasas." +
                    "?Subject  JobSearch:LugarPresentacion  ?Lugar_Pres." +
                    "?Subject  JobSearch:Provincia  ?Provincia." +
                    "?Subject  JobSearch:PlazoPresentacion  ?Plazo_Present." +
                    "?Subject  JobSearch:FechaInicio  ?Fecha_Ini." +
                    "?Subject  JobSearch:FechaFinalizacion  ?Fecha_Fin. " +
                    "?Subject  JobSearch:Requisitos  ?Requisitos. " +
                    "?Subject  JobSearch:Procedimiento  ?Procedimiento. " +
                    "?Subject  JobSearch:UltimaActualizacion  ?Ult_Act. " +
                    "?Subject  JobSearch:URL  ?Enlace } " ;
                ResultSet results = hacerQuery(rdf,queryString);
                while (results.hasNext())
                {
                    QuerySolution binding = results.nextSolution();
                    Literal Org_Gest = binding.getLiteral("Org_Gest");
                    Literal Num_Plazas = binding.getLiteral("Num_Plazas");
                    Literal Tasas = binding.getLiteral("Tasas");
                    Literal Lugar_Pres = binding.getLiteral("Lugar_Pres");
                    Literal Provincia = binding.getLiteral("Provincia");
                    Literal Plazo_Present = binding.getLiteral("Plazo_Present");
                    Literal Fecha_Ini = binding.getLiteral("Fecha_Ini");
                    Literal Fecha_Fin = binding.getLiteral("Fecha_Fin");
                    Literal Requisitos = binding.getLiteral("Requisitos");
                    Literal Procedimiento = binding.getLiteral("Procedimiento");
                    Literal Ult_Act = binding.getLiteral("Ult_Act");
                    Literal Enlace = binding.getLiteral("Enlace");
                    resultado = "Nombre:	   "+ uri 
                                +"\nOrg_Gest:	   "+ Org_Gest
                                +"\nNum_Plazas:	   "+ Num_Plazas
                                +"\nTasas:	   "+ Tasas
                                +"\nLugar_Pres:	   "+ Lugar_Pres 
                                +"\nProvincia:	   "+ Provincia 
                                +"\nPlazo_Present: "+ Plazo_Present 
                                +"\nFecha_Ini:	   "+ Fecha_Ini 
                                +"\nFecha_Fin:	   "+ Fecha_Fin 
                                +"\nRequisitos:	   "+ Requisitos 
                                +"\nProcedimiento: "+ Procedimiento 
                                +"\nUlt_Act:	   "+ Ult_Act 
                                +"\nEnlace:	   "+ Enlace;
                }
            }
            else {
                rdf = ofertas_Empleo;
                String queryString = 
                    "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                    "SELECT  ?Subject ?Provincia ?Fecha_Pub ?Descripcion ?Localidad ?Ult_Act ?Enlace "+
                    "WHERE {" +
                    "?Subject  JobSearch:Titulo  '" +uri +"' ." +
                    "?Subject  JobSearch:Provincia  ?Provincia." +
                    "?Subject  JobSearch:FechaDePublicacion  ?Fecha_Pub." +
                    "?Subject  JobSearch:Descripcion  ?Descripcion." +
                    "?Subject  JobSearch:Localidad  ?Localidad." +
                    "?Subject  JobSearch:UltimaActualizacion  ?Ult_Act." +
                    "?Subject  JobSearch:URL  ?Enlace.";
                ResultSet results = hacerQuery(rdf,queryString);
                while (results.hasNext())
                {
                    QuerySolution binding = results.nextSolution();
                    Literal Provincia = binding.getLiteral("Provincia");
                    Literal Fecha_Pub = binding.getLiteral("Fecha_Pub");
                    Literal Descripcion = binding.getLiteral("Descripcion");
                    Literal Localidad = binding.getLiteral("Localidad");
                    Literal Ult_Act = binding.getLiteral("Ult_Act");
                    Literal Enlace = binding.getLiteral("Enlace");
                    resultado = "Nombre:	 "+ uri 
                                +"\nProvincia:	 "+ Provincia
                                +"\nFecha_Pub:	 "+ Fecha_Pub
                                +"\nDescripcion: "+ Descripcion
                                +"\nLocalidad:	 "+ Localidad 
                                +"\nUlt_Act:	 "+ Ult_Act 
                                +"\nEnlace:	 "+ Enlace;
                }
            }
        }catch(Exception e){
        }
        return resultado;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
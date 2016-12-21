package WebSem;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.iri.impl.Main;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

/**
 *	@author Grupo 07
 */
public class datos
{
    public static String empleo_publico = "Empleo-Publico-with-links.rdf";
    public static String ofertas_Empleo = "Ofertas-de-Empleo-with-links.rdf";

    public static ArrayList<String> mostrarNombres (boolean emp_publico, String provincia, String localidad, String org_Gestor ){
        ArrayList<String> resultado = new ArrayList<String>();
        try{
            String rdf;
            String res;
            Query query;
            QueryExecution qexec;
            ResultSet results;
            if (emp_publico){
            	
            	FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        		Model model = FileManager.get().loadModel("Empleo-Publico-with-links.rdf");
        		
                if (provincia != ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                        "PREFIX resc: <http://www.semanticweb.org/Group07/resources/>"+
                        "SELECT ?Subject ?Given ?Org_Gestor "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given . "
                        + "?Subject JobSearch:Provincia resc:"+provincia+" . "
                        + "?Subject JobSearch:OrganismoGestor ?Org_Gestor . "
                        + "}";
                    query = QueryFactory.create(queryString);
            		qexec = QueryExecutionFactory.create(query, model) ;
            		results = qexec.execSelect() ;
            		while (results.hasNext())
            		{
                        QuerySolution binding = results.nextSolution();
                        
                        Literal given = binding.getLiteral("Given");
                        
                        Resource org_gestor = binding.getResource("Org_Gestor");
                        String org_gestorstr= org_gestor.toString();
                        org_gestorstr= org_gestorstr.substring(45);
                        org_gestorstr= org_gestorstr.replace('+', ' ');
                        
                        res = given+", "+" "+org_gestorstr+", "+provincia;
                        resultado.add(res);
                    }

                }
                if (org_Gestor != ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                        "PREFIX resc: <http://www.semanticweb.org/Group07/resources/>"+
                        "SELECT ?Subject ?Given ?Provincia "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given . "
                        + "?Subject JobSearch:Provincia ?Provincia . "
                        + "?Subject JobSearch:OrganismoGestor resc:"+org_Gestor+" . "
                        + "}";
                    query = QueryFactory.create(queryString);
            		qexec = QueryExecutionFactory.create(query, model) ;
            		results = qexec.execSelect() ;
            		while (results.hasNext())
            		{
                        QuerySolution binding = results.nextSolution();
                        
                        Literal given = binding.getLiteral("Given");
                        
                        Resource prov = binding.getResource("Provincia");
                        String provstr= prov.toString();
                        provstr= provstr.substring(45);
                        
                        res = given+", "+" "+org_Gestor+", "+provstr;
                        if(!resultado.contains(res)){
                            resultado.add(res);
                        }
                    }
                }
                if(org_Gestor == "" && provincia == ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                        "PREFIX resc: <http://www.semanticweb.org/Group07/resources/>"+
                        "SELECT ?Subject ?Given ?Provincia ?Org_Gestor "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given . "
                        + "?Subject JobSearch:OrganismoGestor ?Org_Gestor . "
                        + "?Subject JobSearch:Provincia ?Provincia . "
                        + "}";
                    //ResultSet results = hacerQuery(rdf,queryString);
                    query = QueryFactory.create(queryString);
            		qexec = QueryExecutionFactory.create(query, model) ;
            		results = qexec.execSelect() ;
            		while (results.hasNext())
            		{
                        QuerySolution binding = results.nextSolution();
                        
                        Literal given = binding.getLiteral("Given");
                        
                        Resource prov = binding.getResource("Provincia");
                        String provstr= prov.toString();
                        provstr= provstr.substring(45);
                        
                        Resource org_gestor = binding.getResource("Org_Gestor");
                        String org_gestorstr= org_gestor.toString();
                        org_gestorstr= org_gestorstr.substring(45);
                        org_gestorstr= org_gestorstr.replace('+', ' ');
                        
                        res = given+", "+" "+org_gestorstr+", "+provstr;
                        if(!resultado.contains(res)){
                            resultado.add(res);
                        }
                    }
                    
                }
            }
            else {
            	
            	FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        		Model model = FileManager.get().loadModel(ofertas_Empleo);
                if (provincia != ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                        "PREFIX resc: <http://www.semanticweb.org/Group07/resources/>"+
                        "SELECT ?Subject ?Given ?Localidad "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given . "
                        + "?Subject JobSearch:Provincia resc:"+provincia+" . "
                        + "?Subject JobSearch:Localidad ?Localidad . "
                        + "}";
                    query = QueryFactory.create(queryString);
            		qexec = QueryExecutionFactory.create(query, model) ;
            		results = qexec.execSelect() ;
            		while (results.hasNext())
            		{
                        QuerySolution binding = results.nextSolution();
                        
                        Literal given = binding.getLiteral("Given");
                        
                        Resource local = binding.getResource("Localidad");
                        String localstr= local.toString();
                        localstr= localstr.substring(45);
                        localstr= localstr.replace('+', ' ');
                        
                        res = given+", "+" "+localstr+", "+provincia;
                        resultado.add(res);
                    }
                }
                if (localidad != ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                        "PREFIX resc: <http://www.semanticweb.org/Group07/resources/>"+
                        "SELECT ?Subject ?Given ?Provincia "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given . "
                        + "?Subject JobSearch:Localidad resc:"+localidad+" . "
                        + "?Subject JobSearch:Provincia ?Provincia . "
                        + "}";
                    query = QueryFactory.create(queryString);
            		qexec = QueryExecutionFactory.create(query, model) ;
            		results = qexec.execSelect() ;
            		while (results.hasNext())
            		{
                        QuerySolution binding = results.nextSolution();
                        
                        Literal given = binding.getLiteral("Given");
                        
                        Resource prov = binding.getResource("Provincia");
                        String provstr= prov.toString();
                        provstr= provstr.substring(45);
                        
                        res = given+", "+" "+localidad+", "+provstr;
                        if(!resultado.contains(res)){
                            resultado.add(res);
                        }
                    }
                }
                if ( localidad == "" && provincia == ""){
                    String queryString = 
                        "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                        "PREFIX resc: <http://www.semanticweb.org/Group07/resources/>"+
                        "SELECT ?Subject ?Given ?Provincia ?Localidad "+
                        "WHERE { "
                        + "?Subject JobSearch:Titulo ?Given . "
                        + "?Subject JobSearch:localidad ?Localidad;"
                        + "?Subject JobSearch:provincia ?Provincia."
                        + "}";
                    query = QueryFactory.create(queryString);
            		qexec = QueryExecutionFactory.create(query, model) ;
            		results = qexec.execSelect() ;
            		while (results.hasNext())
            		{
                        QuerySolution binding = results.nextSolution();
                        
                        Literal given = binding.getLiteral("Given");
                        
                        Resource local = binding.getResource("Localidad");
                        String localstr= local.toString();
                        localstr= localstr.substring(45);
                        localstr= localstr.replace('+', ' ');
                        
                        Resource prov = binding.getResource("Provincia");
                        String provstr= prov.toString();
                        provstr= provstr.substring(45);
                        res = given+", "+" "+localstr+", "+provstr;
                        resultado.add(res);
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
            if (emp_publico){
            	FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        		Model model = FileManager.get().loadModel(empleo_publico);
        		
                String queryString = 
                    "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                    "PREFIX resc: <http://www.semanticweb.org/Group07/resources/>"+
                    "SELECT  ?Subject ?Org_Gest ?Num_Plazas ?Tasas ?Lugar_Pres ?Provincia" +
                            "?Plazo_Present ?Fecha_Ini ?Fecha_Fin ?Requisitos ?Procedimiento ?Ult_Act ?Enlace "+
                    "WHERE {" +
                    "?Subject  JobSearch:Titulo  '" +uri +"' . " +
                    "?Subject  JobSearch:OrganismoGestor  ?Org_Gest . " +
                    "?Subject  JobSearch:NumeroPlazas  ?Num_Plazas . " +
                    "?Subject  JobSearch:Tasas  ?Tasas . " +
                    "?Subject  JobSearch:Provincia  ?Provincia . " +
                    "?Subject  JobSearch:FechaInicio  ?Fecha_Ini . " +
                    "?Subject  JobSearch:FechaFinalizacion  ?Fecha_Fin . " +
                    "?Subject  JobSearch:Requisitos  ?Requisitos . " +
                    "?Subject  JobSearch:Procedimiento  ?Procedimiento . " +
                    "?Subject  JobSearch:UltimaActualizacion  ?Ult_Act . " +
                    "} " ;
                Query query= QueryFactory.create(queryString);
                QueryExecution qexec = QueryExecutionFactory.create(query, model);
                ResultSet results = qexec.execSelect();
                while (results.hasNext())
                {
                    QuerySolution binding = results.nextSolution();
                    
                    Resource Org_Gest = binding.getResource("Org_Gest");
                    String org_gestorstr= Org_Gest.toString();
                    org_gestorstr= org_gestorstr.substring(45);
                    org_gestorstr= org_gestorstr.replace('+', ' ');
                    
                    Literal Num_Plazas = binding.getLiteral("Num_Plazas");
                    String Num_Plazastr= Num_Plazas.toString();
                    Num_Plazastr= Num_Plazastr.substring(0, 2);
                    
                    Literal Tasas = binding.getLiteral("Tasas");
                    
                    Resource Provincia = binding.getResource("Provincia");
                    String provstr= Provincia.toString();
                    provstr= provstr.substring(45);
                         
                    Literal Fecha_Ini = binding.getLiteral("Fecha_Ini");
                    String Fecha_Inistr= Fecha_Ini.toString();
                    Fecha_Inistr= Fecha_Inistr.substring(0, 8);
                    Fecha_Inistr= Fecha_Inistr.substring(0, 4)+"/"+Fecha_Inistr.substring(4, 6)+"/"+Fecha_Inistr.substring(6, 8);
                    
                    Literal Fecha_Fin = binding.getLiteral("Fecha_Fin");
                    String Fecha_Finstr= Fecha_Fin.toString();
                    Fecha_Finstr= Fecha_Finstr.substring(0, 8);
                    Fecha_Finstr= Fecha_Finstr.substring(0, 4)+"/"+Fecha_Finstr.substring(4, 6)+"/"+Fecha_Finstr.substring(6, 8);
                    
                    Literal Requisitos = binding.getLiteral("Requisitos");
                    
                    Literal Procedimiento = binding.getLiteral("Procedimiento");
                    
                    Literal Ult_Act = binding.getLiteral("Ult_Act");
                    String Ult_Actstr= Ult_Act.toString();
                    Ult_Actstr= Ult_Actstr.substring(0, 8);
                    Ult_Actstr= Ult_Actstr.substring(0, 4)+"/"+Ult_Actstr.substring(4, 6)+"/"+Ult_Actstr.substring(6, 8);
                    
                    resultado = "Nombre: "+ uri 
                                +"\nOrg_Gest: "+ org_gestorstr
                                +"\nNum_Plazas: "+ Num_Plazastr
                                +"\nTasas: "+ Tasas
                                +"\nProvincia: "+ provstr 
                                +"\nFecha_Ini: "+ Fecha_Inistr 
                                +"\nFecha_Fin: "+ Fecha_Finstr 
                                +"\nRequisitos: "+ Requisitos 
                                +"\nProcedimiento: "+ Procedimiento 
                                +"\nUlt_Act: "+ Ult_Actstr;
                }
            }
            else {
            	FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        		Model model = FileManager.get().loadModel("empleo_publico");
                String queryString = 
                    "PREFIX JobSearch: <http://www.semanticweb.org/Group07/ontology/JobSearch#>"+
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                    "PREFIX resc: <http://www.semanticweb.org/Group07/resources/>"+
                    "SELECT  ?Subject ?Provincia ?Fecha_Pub ?Descripcion ?Localidad ?Ult_Act ?Enlace "+
                    "WHERE {" +
                    "?Subject  JobSearch:Titulo  '" +uri +"' ." +
                    "?Subject  JobSearch:Provincia  ?Provincia." +
                    "?Subject  JobSearch:FechaDePublicacion  ?Fecha_Pub." +
                    "?Subject  JobSearch:Descripcion  ?Descripcion." +
                    "?Subject  JobSearch:Localidad  ?Localidad." +
                    "?Subject  JobSearch:UltimaActualizacion  ?Ult_Act." +
                    "}";
                Query query= QueryFactory.create(queryString);
                QueryExecution qexec = QueryExecutionFactory.create(query, model);
                ResultSet results = qexec.execSelect();
                while (results.hasNext())
                {
                    QuerySolution binding = results.nextSolution();
                    Resource Provincia = binding.getResource("Provincia");
                    String provstr= Provincia.toString();
                    provstr= provstr.substring(45);
                    
                    Literal Fecha_Pub = binding.getLiteral("Fecha_Pub");
                    String Fecha_Pubstr= Fecha_Pub.toString();
                    Fecha_Pubstr= Fecha_Pubstr.substring(0, 8);
                    Fecha_Pubstr= Fecha_Pubstr.substring(0, 4)+"/"+Fecha_Pubstr.substring(4, 6)+"/"+Fecha_Pubstr.substring(6, 8);
                    
                    Literal Descripcion = binding.getLiteral("Descripcion");
                    Resource Localidad = binding.getResource("Localidad");
                    String Localidadstr= Localidad.toString();
                    Localidadstr= Localidadstr.substring(45);
                    
                    Literal Ult_Act = binding.getLiteral("Ult_Act");
                    String Ult_Actstr= Ult_Act.toString();
                    Ult_Actstr= Ult_Actstr.substring(0, 8);
                    Ult_Actstr= Ult_Actstr.substring(0, 4)+"/"+Ult_Actstr.substring(4, 6)+"/"+Ult_Actstr.substring(6, 8);
                    
                    
                    resultado = "Nombre:	 "+ uri 
                                +"\nProvincia:	 "+ provstr
                                +"\nFecha_Pub:	 "+ Fecha_Pubstr
                                +"\nDescripcion: "+ Descripcion
                                +"\nLocalidad:	 "+ Localidadstr 
                                +"\nUlt_Act:	 "+ Ult_Actstr;
                }
            }
        }catch(Exception e){
        }
        return resultado;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method st
		ArrayList<String> resp = mostrarNombres(true,"","","");
		for(String x:resp){
			System.out.println(x);
		}
		//System.out.println(info(true, "Enfermero"));
	}

}

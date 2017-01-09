package barrios;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.simple.JSONObject;

import dao.SparqlRDF;
import model.BarriosList;

@Path("barrio")
public class BarrioRecurso {

	@Context
	UriInfo uriInfo;
	
	@GET
	public Response getBarrios(){
		List<JSONObject> barriosJson = SparqlRDF.getBarrios();
		BarriosList barrios = new BarriosList(barriosJson);
		Response res = Response.ok(barrios).status(Response.Status.OK)
				.header("Location", uriInfo.getAbsolutePath().toString()).build();
		return res;
	}
	
	@GET
	@Path("{idBarrio}")
	public Response getBarrio(@PathParam("idBarrio") int idBarrio){
		JSONObject jsonBarrio = SparqlRDF.getBarrio(idBarrio);
		Response res = Response.ok(jsonBarrio).status(Response.Status.OK).build();
		return res;
	}
}

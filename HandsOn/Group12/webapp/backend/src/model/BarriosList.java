package model;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.simple.JSONObject;

import dao.SparqlRDF;

@XmlRootElement
public class BarriosList {

	private List<JSONObject> listBarrios;
	
	public BarriosList(){};
	
	public BarriosList(List<JSONObject> listBarrios){
		this.listBarrios = listBarrios;
	}

	public List<JSONObject> getListBarrios() {
		return listBarrios;
	}

	public void setListBarrios(List<JSONObject> listBarrios) {
		this.listBarrios = listBarrios;
	}
	
	public String toString(){
		String res = "";
		
		for(JSONObject json : listBarrios) {
			try {
				res += SparqlRDF.printJSON(json) + "\n";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}

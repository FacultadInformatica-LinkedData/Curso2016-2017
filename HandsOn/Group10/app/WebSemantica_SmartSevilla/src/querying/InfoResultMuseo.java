package querying;

public class InfoResultMuseo {

	private String abstraction;
	private String nombre;
	private String image;
	private String direccion;
	private String web;
	private String ntelf;
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getNtelf() {
		return ntelf;
	}

	public void setNtelf(String ntelf) {
		this.ntelf = ntelf;
	}

	InfoResultMuseo () {}

	public String getAbstraction() {
		return abstraction;
	}

	public void setAbstraction(String abstraction) {
		this.abstraction = abstraction;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String estilo) {
		this.nombre = estilo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}

package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import querying.InfoResult;
import querying.InfoResultMuseo;
import querying.WSQuery;

public class MainController implements Initializable {

	/**
	 * FXML Items
	 */
	@FXML private JFXDrawer drawer;
	@FXML private JFXHamburger hamburger;    
	@FXML private JFXButton botonMudejar;
	@FXML private AnchorPane root;
	@FXML private Pane paneMuseos;
	@FXML private Pane paneMonumentos;
	@FXML private Pane paneTransporte;
	@FXML private Pane paneConocenos;
	@FXML private Pane home;
	@FXML private JFXTextField buscaMuseos;
	@FXML private JFXTextField buscaMonumentos;
	@FXML private Text monumentoTitle;
	@FXML private Text txtLabel;
	@FXML private Text noImg;
	@FXML private Text museo_tlf;
	@FXML private Text museo_dir;
	@FXML private Text museo_web;
	@FXML private Text museo_tlf_i;
	@FXML private Text museo_dir_i;
	@FXML private Text museo_web_i;
	@FXML private Text mudejarInfo;
	@FXML private ImageView imgZone;
	@FXML private ImageView imgZoneMuseo;
	@FXML private Text noImgMuseo;
	@FXML private Text museoTitle;
	@FXML private Text txtLabelMuseo;
	@FXML private Text textteam;
	@FXML private ImageView imgteam;
	/**
	 * FXML ListView for filtered searchs
	 */
	@FXML private ListView<String> viewMuseos = new ListView<String>();
	ObservableList<String> listaMuseos = FXCollections.observableArrayList();
	FilteredList<String> listaMuseosFiltrada = new FilteredList<>(listaMuseos, s -> true);
	@FXML private ListView<String> viewMonumentos = new ListView<String>();
	ObservableList<String> listaMonumentos = FXCollections.observableArrayList();
	FilteredList<String> listaMonumentosFiltrada = new FilteredList<>(listaMonumentos, s -> true);
	public static AnchorPane rootP;
	public static Pane paneMuseosP;
	public static Pane paneMonumentosP;
	public static Pane paneTransporteP;
	public static Pane paneConocenosP;
	public static Pane homeP;


	@Override
	/**
	 * Initialize
	 */
	public void initialize(URL url, ResourceBundle rb) {
		rootP = root;
		paneMuseosP = paneMuseos;
		paneMonumentosP = paneMonumentos;
		paneTransporteP = paneTransporte;
		paneConocenosP = paneConocenos;
		homeP = home;
		rellenarPaneMuseos();
		rellenarPaneMonumentos();
		fix();
		try {
			VBox box = FXMLLoader.load(getClass().getResource("SidePanelFXML.fxml"));
			drawer.setSidePane(box);
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
		/*
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
			transition.setRate(transition.getRate()*-1);
			transition.play();

			if(drawer.isShown())
			{
				drawer.close();
			}else
				drawer.open();
		});
		 */
		drawer.open();

	}

	private void fix() {

		listaMuseos.remove("Museo de Baile Flamenco");
		listaMuseos.remove("Museo del castillo de San Jorge");
		listaMuseos.remove("Museo Taurino y Pza. Toros Real Maestranza");
		listaMuseos.remove("Museo de Carruajes");
		listaMuseosFiltrada = new FilteredList<String>(listaMuseos, s->true);
		viewMuseos.setItems(listaMuseosFiltrada);
		listaMonumentos.remove("Real alcazar");
		listaMonumentos.remove("Parque de Maria Luisa (Sevilla)");
		listaMonumentos.remove("Convento de las Minimas");
		listaMonumentosFiltrada = new FilteredList<String>(listaMonumentos, s->true);
		viewMonumentos.setItems(listaMonumentosFiltrada);
	}

	/**
	 * RellenarPaneMonumentos()
	 */
	private void rellenarPaneMonumentos() {

		String filename = "WS_LugaresInteres_Updated-with-links.ttl";
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");
		WSQuery queries = new WSQuery();
		String query = ("prefix dbp: <http://dbpedia.org/property/>" + "SELECT ?FullName WHERE { ?Subject dbp:fullname ?FullName.}");
		Query queryE = QueryFactory.create(query);
		QueryExecution qexec = QueryExecutionFactory.create(queryE, model);
		ResultSet results = qexec.execSelect();
		listaMonumentos = FXCollections.observableArrayList();
		monumentoTitle.setText("Lugares de Interes de Sevilla");
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Literal texto = binding.getLiteral("FullName");
			listaMonumentos.add(texto.getString());
			listaMonumentosFiltrada = new FilteredList<String>(listaMonumentos, s->true);
			viewMonumentos.setItems(listaMonumentosFiltrada);
			viewMonumentos.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					imgZone.setImage(null);
					noImg.setVisible(false);

					String valor = viewMonumentos.getSelectionModel().getSelectedItem();
					InfoResult info = queries.getDataOf(valor);
					monumentoTitle.setText(valor);
					if (info != null)
					{
						if (info.getImage() != null) {
							Image img = new Image(imageDispatcher(info.getImage()));
							imgZone.setImage(img);
							imgZone.setVisible(true);}
						txtLabel.setText(valor.equals("La Giralda")||valor.equals("Muelle de las Delicias") ? primeraFrase(info.getAbstraction()) : info.getAbstraction());
						System.out.println(info.getImage());
					}
					else 
					{
						txtLabel.setText("No hay informacion enlazada");
						noImg.setVisible(true);
					}
				}
			});
		}
	}


	/**
	 * RellenarPaneMuseos()
	 */
	private void rellenarPaneMuseos() {

		String filename = "WS_Museos_Updated-with-links.ttl";
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");
		WSQuery queries = new WSQuery();
		String query = ("PREFIX owl: <http://www.w3.org/2002/07/owl#> prefix dbp: <http://dbpedia.org/property/>" + "SELECT ?FullName WHERE { ?Subject dbp:fullname ?FullName. ?Subject owl:sameAs ?uri . }");
		Query queryE = QueryFactory.create(query);
		QueryExecution qexec = QueryExecutionFactory.create(queryE, model);
		ResultSet results = qexec.execSelect();
		listaMuseos = FXCollections.observableArrayList();
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Literal texto = binding.getLiteral("FullName");
			listaMuseos.add(texto.getString());
			museoTitle.setText("Museos de Sevilla");
			viewMuseos.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					imgZoneMuseo.setImage(null);
					noImgMuseo.setVisible(false);
					museo_web_i.setVisible(false);
					museo_dir_i.setVisible(false);
					museo_tlf_i.setVisible(false);
					txtLabelMuseo.setVisible(false);
					museo_web.setText("");
					museo_dir.setText("");
					museo_tlf.setText("");
					String valor = viewMuseos.getSelectionModel().getSelectedItem();
					InfoResultMuseo info = queries.getDataOf_Museo(valor);
					museoTitle.setText(valor);
					if (info != null)
					{
						if (info.getImage() != null) {
							Image img = new Image(imageDispatcher(info.getImage()));
							imgZoneMuseo.setImage(img);
							imgZoneMuseo.setVisible(true);}
						txtLabelMuseo.setVisible(true);
						txtLabelMuseo.setText(primeraFrase(info.getAbstraction()));
						museo_web_i.setVisible(true);
						museo_dir_i.setVisible(true);
						museo_tlf_i.setVisible(true);
						museo_web.setText(info.getWeb());
						museo_dir.setText(info.getDireccion());
						museo_tlf.setText(info.getNtelf());
						System.out.println(info.getImage());
					}
				}
			});
		}	
		listaMuseosFiltrada = new FilteredList<String>(listaMuseos, s->true);
		viewMuseos.setItems(listaMuseosFiltrada);

	}
	/**
	 * filtrar
	 */
	@FXML protected void filtrarMuseos () {

		String txtBuscado = buscaMuseos.getText();
		if(txtBuscado == null || txtBuscado.length() == 0) 
			listaMuseosFiltrada.setPredicate(c -> true);       
		else 
			listaMuseosFiltrada.setPredicate(c -> c.toLowerCase().indexOf(txtBuscado.toLowerCase()) == 0);
	}

	/**
	 * filtrar
	 */
	@FXML protected void filtrarMonumentos () {

		String txtBuscado = buscaMonumentos.getText();
		if(txtBuscado == null || txtBuscado.length() == 0) 
			listaMonumentosFiltrada.setPredicate(c -> true);       
		else 
			listaMonumentosFiltrada.setPredicate(c -> c.toLowerCase().indexOf(txtBuscado.toLowerCase()) == 0);
	}

	@FXML protected void rutaMudejar () throws MalformedURLException {
		txtLabel.setText("El arte mudéjar es un estilo artístico que se desarrolla en los reinos cristianos de la península ibérica, "
				+ "pero que incorpora influencias, elementos o materiales de estilo hispano-musulmán, es la consecuencia de las condiciones "
				+ "de convivencia existente de la España medieval y se trata de un fenómeno exclusivamente hispánico que tiene lugar entre los "
				+ "siglos XII y XVI, como mezcla de las corrientes artísticas cristianas (románicas, góticas y renacentistas) y musulmanas de la "
				+ "época y que sirve de eslabón entre las culturas cristianas y el islam.");
		noImg.setVisible(false);
		imgZone.setVisible(true);
		imgZone.setImage(new Image("/resources/Ruta_mudejar.png.png"));
		String filename = "WS_LugaresInteres_Updated-with-links.ttl";
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");
		WSQuery queries = new WSQuery();
		WSQuery query = new WSQuery();
		List<InfoResult> mudejares = query.getAllCategoryObjects("Arte Mudejar");
		monumentoTitle.setText("Ruta de Arte Mudejar");
		listaMonumentos = FXCollections.observableArrayList();
		for (int i = 0; i < mudejares.size(); i++)
		{
			if (!listaMonumentos.contains(mudejares.get(i).getEstilo()))
			{
				listaMonumentos.add(mudejares.get(i).getEstilo());
				listaMonumentosFiltrada = new FilteredList<String>(listaMonumentos, s->true);
				viewMonumentos.setItems(listaMonumentosFiltrada);
				viewMonumentos.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						imgZone.setImage(null);
						String valor = viewMonumentos.getSelectionModel().getSelectedItem();
						InfoResult info = queries.getDataOf(valor);
						if (info != null)
						{
							if (info.getImage() != null) {
								Image img = new Image(imageDispatcher(info.getImage()));
								imgZone.setImage(img);
								imgZone.setVisible(true);}
							monumentoTitle.setText(valor);
							txtLabel.setText(info == null ? "No hay informacion enlazada" : info.getAbstraction());
							System.out.println(info.getImage());
						}
					}
				});
			}
		}
	}

	@FXML protected void rutaBarroca () throws MalformedURLException {
		txtLabel.setText("El Barroco fue un período de la historia en la cultura occidental originado por una nueva forma de concebir el arte (el «estilo barroco») y que, "
				+ "partiendo desde diferentes contextos histórico-culturales, produjo obras en numerosos campos artísticos: literatura, arquitectura, escultura, pintura, música, "
				+ "ópera, danza, teatro, etc. ");
		imgZone.setImage(new Image("/resources/Ruta_barroco.png"));
		imgZone.setVisible(true);
		noImg.setVisible(false);
		String filename = "WS_LugaresInteres_Updated-with-links.ttl";
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");
		WSQuery queries = new WSQuery();
		WSQuery query = new WSQuery();
		List<InfoResult> mudejares = query.getAllCategoryObjects("Barroco");
		monumentoTitle.setText("Ruta de Arte Barroco");
		listaMonumentos = FXCollections.observableArrayList();
		for (int i = 0; i < mudejares.size(); i++)
		{
			if (!listaMonumentos.contains(mudejares.get(i).getEstilo()))
			{
				listaMonumentos.add(mudejares.get(i).getEstilo());
				listaMonumentosFiltrada = new FilteredList<String>(listaMonumentos, s->true);
				viewMonumentos.setItems(listaMonumentosFiltrada);
				viewMonumentos.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						imgZone.setImage(null);
						String valor = viewMonumentos.getSelectionModel().getSelectedItem();
						InfoResult info = queries.getDataOf(valor);
						//File f = new File ("src/resources/Iglesia de San Esteban (Sevilla).jpg");
						//Image img = new Image(f.toURI().toString());
						//imgZone.setImage(img);
						if (info != null)
						{
							if (info.getImage() != null) {
								Image img = new Image(imageDispatcher(info.getImage()));
								imgZone.setImage(img);
								imgZone.setVisible(true);}
							monumentoTitle.setText(valor);
							txtLabel.setText(info == null ? "No hay informacion enlazada" : info.getAbstraction());
							System.out.println(info.getImage());
						}
					}
				});
			}
		}
	}

	@FXML protected void ruta17 () throws MalformedURLException {
		txtLabel.setText("El siglo XVIII d. C. (siglo decimoctavo después de Cristo) o siglo XVIII EC (siglo decimoctavo de la era común) comenzó el 1 de enero del año "
				+ "1701 y terminó el 31 de diciembre de 1800. En la historia occidental, el siglo XVIII también es llamado Siglo de las Luces, debido al nacimiento "
				+ "del movimiento intelectual conocido como Ilustración.");
		imgZone.setImage(new Image("/resources/Ruta_17.png"));
		imgZone.setVisible(true);
		noImg.setVisible(false);
		String filename = "WS_LugaresInteres_Updated-with-links.ttl";
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");
		WSQuery queries = new WSQuery();
		WSQuery query = new WSQuery();
		List<InfoResult> mudejares = query.getAllCategoryObjectsWithRedirect("XVII");
		monumentoTitle.setText("Edificios del Siglo XVII");
		listaMonumentos = FXCollections.observableArrayList();
		for (int i = 0; i < mudejares.size(); i++)
		{
			if (!listaMonumentos.contains(mudejares.get(i).getEstilo()))
			{
				listaMonumentos.add(mudejares.get(i).getEstilo());
				listaMonumentosFiltrada = new FilteredList<String>(listaMonumentos, s->true);
				viewMonumentos.setItems(listaMonumentosFiltrada);
				viewMonumentos.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						imgZone.setImage(null);
						String valor = viewMonumentos.getSelectionModel().getSelectedItem();
						InfoResult info = queries.getDataOf(valor);
						if (info != null)
						{
							if (info.getImage() != null) {
								Image img = new Image(imageDispatcher(info.getImage()));
								imgZone.setImage(img);
								imgZone.setVisible(true);}
							monumentoTitle.setText(valor);
							txtLabel.setText(info == null ? "No hay informacion enlazada" : info.getAbstraction());
							System.out.println(info.getImage());
						}
					}
				});
			}
		}
	}

	@FXML protected void rutaOthers () throws MalformedURLException {

		txtLabel.setText("En Sevilla también podrás encontrar otros lugares de interés muy interesantes! Estos son algunos de ellos.");
		imgZone.setVisible(true);
		imgZone.setImage(new Image("/resources/Ruta_others.png"));
		String filename = "WS_LugaresInteres_Updated-with-links.ttl";
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");
		WSQuery queries = new WSQuery();
		WSQuery query = new WSQuery();
		List<InfoResult> mudejares = query.getAllCategoryObjectsWithRedirect("Otros bienes culturales");
		monumentoTitle.setText("Otros bienes culturales de Sevilla");
		listaMonumentos = FXCollections.observableArrayList();
		for (int i = 0; i < mudejares.size(); i++)
		{
			if (!listaMonumentos.contains(mudejares.get(i).getEstilo()))
			{
				listaMonumentos.add(mudejares.get(i).getEstilo());
				listaMonumentosFiltrada = new FilteredList<String>(listaMonumentos, s->true);
				viewMonumentos.setItems(listaMonumentosFiltrada);

				viewMonumentos.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						imgZone.setImage(null);
						String valor = viewMonumentos.getSelectionModel().getSelectedItem();
						InfoResult info = queries.getDataOf(valor);
						if (info != null)
						{
							if (info.getImage() != null) {
								Image img = new Image(imageDispatcher(info.getImage()));
								imgZone.setImage(img);
								imgZone.setVisible(true);}
							monumentoTitle.setText(valor);
							txtLabel.setText(info == null ? "No hay informacion enlazada" : info.getAbstraction());
							System.out.println(info.getImage());
						}
					}
				});
			}
		}
	}

	@FXML protected void rutaTodosMonumentos () {
		monumentoTitle.setText("Lugares de Interés de Sevilla");
		txtLabel.setText("");
		imgZone.setVisible(false);
		noImg.setVisible(false);
		rellenarPaneMonumentos();
		fix();

	}

	@FXML protected void rutaMilitares() {
		imgZoneMuseo.setVisible(true);
		imgZoneMuseo.setImage(new Image("/resources/Museos_militares.png"));
		noImgMuseo.setVisible(false);
		museo_web_i.setVisible(false);
		museo_dir_i.setVisible(false);
		museo_tlf_i.setVisible(false);
		txtLabelMuseo.setVisible(false);
		museo_web.setText("");
		museo_dir.setText("");
		museo_tlf.setText("");
		txtLabelMuseo.setText("");
		String filename = "WS_Museos_Updated-with-links.ttl";
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");
		WSQuery queries = new WSQuery();

		WSQuery query = new WSQuery();
		List<InfoResult> mudejares = query.getAllCategoryObjectsWithRedirect("Museos Militares de Sevilla");
		museoTitle.setText("Museos Militares de Sevilla");

		listaMuseos = FXCollections.observableArrayList();
		for (int i = 0; i < mudejares.size(); i++)
		{
			if (!listaMuseos.contains(mudejares.get(i).getEstilo()))
			{
				listaMuseos.add(mudejares.get(i).getEstilo());
				listaMuseosFiltrada = new FilteredList<String>(listaMuseos, s->true);
				viewMuseos.setItems(listaMuseosFiltrada);

				viewMuseos.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						imgZoneMuseo.setImage(null);
						String valor = viewMuseos.getSelectionModel().getSelectedItem();
						InfoResultMuseo info = queries.getDataOf_Museo(valor);
						imgZoneMuseo.setImage(null);
						noImgMuseo.setVisible(false);
						museo_web_i.setVisible(false);
						museo_dir_i.setVisible(false);
						museo_tlf_i.setVisible(false);
						txtLabelMuseo.setVisible(false);
						museo_web.setText("");
						museo_dir.setText("");
						museo_tlf.setText("");
						if (info != null)
						{
							if (info.getImage() != null) {
								Image img = new Image(imageDispatcher(info.getImage()));
								imgZoneMuseo.setImage(img);
								imgZoneMuseo.setVisible(true);}

							txtLabelMuseo.setVisible(true);
							txtLabelMuseo.setText(primeraFrase(info.getAbstraction()));
							museo_web_i.setVisible(true);
							museo_dir_i.setVisible(true);
							museo_tlf_i.setVisible(true);
							museoTitle.setText(info.getNombre());
							museo_web.setText(info.getWeb());
							museo_dir.setText(info.getDireccion());
							museo_tlf.setText(info.getNtelf());
							System.out.println(info.getImage());
						}
					}
				});
			}
		}
	}


	@FXML protected void rutaTodosMuseos () {
		museoTitle.setText("Museos de Sevilla");
		txtLabelMuseo.setText("");
		imgZoneMuseo.setVisible(false);
		noImgMuseo.setVisible(false);
		museo_web.setText("");
		museo_dir.setText("");
		museo_tlf.setText("");
		museo_web_i.setVisible(false);
		museo_dir_i.setVisible(false);
		museo_tlf_i.setVisible(false);
		rellenarPaneMuseos();
		fix();
	}

	private String imageDispatcher(String path) {
		switch (path)
		{
		case "http://commons.wikimedia.org/wiki/Special:FilePath/San_Esteban_001.jpg" : 							return "https://upload.wikimedia.org/wikipedia/commons/5/53/San_Esteban_001.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Museo_casa_de_la_ciencia-2.jpg" : 					return "https://upload.wikimedia.org/wikipedia/commons/2/2e/Museo_casa_de_la_ciencia-2.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Monumento_Sevilla.jpg" : 							return "https://upload.wikimedia.org/wikipedia/commons/0/02/Monumento_Sevilla.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Capilla_del_Carmen_003.jpg" : 						return "https://upload.wikimedia.org/wikipedia/commons/a/a2/Capilla_del_Carmen_003.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/SevillaLaCartujaChimeneas.jpg" : 					return "https://upload.wikimedia.org/wikipedia/commons/d/d9/SevillaLaCartujaChimeneas.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Pabellon_navegacion_e2012003.jpg" : 				return "https://upload.wikimedia.org/wikipedia/commons/d/dd/Pabellon_navegacion_e2012003.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Jardín_Americano_de_Sevilla.jpg" : 				return "https://upload.wikimedia.org/wikipedia/commons/8/80/Jard%C3%ADn_Americano_de_Sevilla.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Iglesia_Magdalena_Sevilla_006.jpg" : 				return "https://upload.wikimedia.org/wikipedia/commons/a/a5/Iglesia_Magdalena_Sevilla_006.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Mosaicodelmercadodetriana.JPG" : 					return "https://upload.wikimedia.org/wikipedia/commons/a/a7/Mosaicodelmercadodetriana.JPG";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Iglesia_de_San_Gil_(Sevilla).jpg" : 				return "https://upload.wikimedia.org/wikipedia/commons/d/dd/Iglesia_de_San_Gil_%28Sevilla%29.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/San_Telmo_001.jpg" : 								return "https://upload.wikimedia.org/wikipedia/commons/5/56/San_Telmo_001.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Parroquia_del_Divino_Salvador_Sevilla_2.jpg" : 	return "https://upload.wikimedia.org/wikipedia/commons/e/e5/Parroquia_del_Divino_Salvador_Sevilla_2.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/La_Giralda_August_2012_Seville_Spain.jpg" : 		return "https://upload.wikimedia.org/wikipedia/commons/9/93/La_Giralda_August_2012_Seville_Spain.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Logo_del_Ayuntamiento_de_Sevilla.jpg" : 			return "https://upload.wikimedia.org/wikipedia/commons/e/e5/Logo_del_Ayuntamiento_de_Sevilla.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Muelle_de_las_Delicias.JPG" : 						return "https://upload.wikimedia.org/wikipedia/commons/c/c3/Muelle_de_las_Delicias.JPG";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Iglesia_de_San_Andrés_en_Sevilla.jpg" : 			return "https://upload.wikimedia.org/wikipedia/commons/2/22/Iglesia_de_San_Andr%C3%A9s_en_Sevilla.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Plaza_nueva.jpg" : 								return "https://upload.wikimedia.org/wikipedia/commons/5/52/Plaza_nueva.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Archivo_de_Indias_001.jpg" : 						return "https://upload.wikimedia.org/wikipedia/commons/e/ed/Archivo_de_Indias_001.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Convento_de_Santa_Ana._Sevilla.jpg" : 				return "https://upload.wikimedia.org/wikipedia/commons/6/63/Convento_de_Santa_Ana._Sevilla.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Iglesia_San_Isidoro_001.jpg" : 					return "https://upload.wikimedia.org/wikipedia/commons/a/aa/Iglesia_San_Isidoro_001.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Portada_santa_paula.jpg" : 						return "https://upload.wikimedia.org/wikipedia/commons/d/dd/Portada_santa_paula.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Edificio_de_las_Antiguas_Atarazanas_Reales_hoy_Maestranza_de_Artillería.jpg" :return "https://upload.wikimedia.org/wikipedia/commons/0/03/Edificio_de_las_Antiguas_Atarazanas_Reales_hoy_Maestranza_de_Artiller%C3%ADa.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/SevillaPalacioDeLebrija01.JPG" : 					return "https://upload.wikimedia.org/wikipedia/commons/6/64/SevillaPalacioDeLebrija01.JPG";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Sevilla_Torre_del_oro.JPG" : 						return "https://upload.wikimedia.org/wikipedia/commons/9/95/Sevilla_Torre_del_oro.JPG";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Antiquarium_1.jpg" : 								return "https://upload.wikimedia.org/wikipedia/commons/4/42/Antiquarium_1.jpg";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/SEVILLA_PABELLON_MUDEJAR.JPG" : 					return "https://upload.wikimedia.org/wikipedia/commons/0/04/SEVILLA_PABELLON_MUDEJAR.JPG";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/SevillaCasaMuseoMurillo.JPG" : 					return "https://upload.wikimedia.org/wikipedia/commons/a/ad/SevillaCasaMuseoMurillo.JPG";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Banderines_de_España.JPG" : 						return "https://upload.wikimedia.org/wikipedia/commons/7/7f/Banderines_de_Espa%C3%B1a.JPG";
		case "http://commons.wikimedia.org/wiki/Special:FilePath/Museo_arqueologico_2009.jpg" : 					return "https://upload.wikimedia.org/wikipedia/commons/9/9b/Museo_arqueologico_2009.jpg";
		default : return null;
		}

	}

	private String primeraFrase (String texto) {		
		String res = "";
		for (int i = 0; i < texto.length() && !(texto.charAt(i) == '.'); i++) res+= texto.charAt(i);
		return res+".";
	}
	
	@FXML protected void showImageJ () {
		
		imgteam.setVisible(true);
		textteam.setText("Jesús Pérez Melero");
		imgteam.setImage(new Image("/resources/corbiround.png"));
	}
	
	@FXML protected void showImageS () {
		
		imgteam.setVisible(true);
		textteam.setText("Sergio Palomino Sánchez");
		imgteam.setImage(new Image("/resources/sergio2.png"));
	}
	
	@FXML protected void showImageCb () {
		
		imgteam.setVisible(false);
		textteam.setText("Carolina Bouyich España");
	}
	
	@FXML protected void showImageYc () {
		
		imgteam.setVisible(false);
		textteam.setText("Yanira Calero Pereira");
	}
	
	@FXML protected void showImageJc () {
		
		imgteam.setVisible(false);
		textteam.setText("Jaime Caraza Luis");
	}
	
	@FXML protected void quitarCosas () {
		
		textteam.setText("");
		imgteam.setVisible(false);
	}

}

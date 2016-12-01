package ontologia;

/**
 * @author Jorge Amorós
 */
	
import java.io.File;
import java.io.IOException;

import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;

public class OntologyCons {

	public static final IRI ontologyiri = IRI.create("http://www.semanticweb.org/webSemantica/gropo02");
	public static OWLDataFactory df = OWLManager.getOWLDataFactory();

	public static OWLOntologyManager create() {
		OWLOntologyManager m = OWLManager.createOWLOntologyManager();
		m.addIRIMapper(new AutoIRIMapper(new File("materializedOntologies"), true));
		return m;
	}

	public static OWLClass crearClase(String nombreClase) {
		return df.getOWLClass(IRI.create(ontologyiri + "#" + nombreClase));
	}

	public static OWLDeclarationAxiom declararClases(OWLClass clase) {
		return df.getOWLDeclarationAxiom(clase);
	}

	public static OWLAxiom declararSubClases(OWLClass claseHija, OWLClass clasePadre) {
		return df.getOWLSubClassOfAxiom(claseHija, clasePadre);
	}
	
	public static OWLObjectProperty createObjectProperty(String nombrepropiedad) {
		return df.getOWLObjectProperty(IRI.create(ontologyiri + "#" + nombrepropiedad));
	}
	
	public static OWLDataProperty createDatatypeProperty(String nombrepropiedad) {
		return df.getOWLDataProperty(IRI.create(ontologyiri + "#" + nombrepropiedad));
	}

	public static OWLDeclarationAxiom declareProperty(OWLDataProperty propiedad) {
		return df.getOWLDeclarationAxiom(propiedad);
	}
	public static OWLDeclarationAxiom declareProperty(OWLObjectProperty propiedad) {
		return df.getOWLDeclarationAxiom(propiedad);
	}
	
	public static OWLDataPropertyDomainAxiom declareDataDomain(OWLDataProperty property, OWLClass cls){
		return  df.getOWLDataPropertyDomainAxiom(property, cls);

	}
	public static OWLObjectPropertyDomainAxiom declareObjectDomain(OWLObjectProperty property, OWLClass cls){
		return  df.getOWLObjectPropertyDomainAxiom(property, cls);

	}
	
	public static OWLDataPropertyRangeAxiom declareDataRange(OWLDataProperty property,OWLDatatype type){
		return  df.getOWLDataPropertyRangeAxiom(property,  type);
	}
	public static OWLDataPropertyRangeAxiom declareRestrictionRange(OWLDataProperty property,OWLDatatypeRestriction res){
		return  df.getOWLDataPropertyRangeAxiom(property,  res);
		
	}

	public static void applyChanges(OWLOntologyManager m, OWLOntology o, OWLAxiom axion) {
		m.applyChange(new AddAxiom(o, axion));
	}

	public static void main(String[] args)
			throws OWLOntologyCreationException, IOException, OWLOntologyStorageException {

		OWLDatatype string_dt = df.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
		OWLDatatype integer_dt = df.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
		OWLDatatype double_dt = df.getOWLDatatype(OWL2Datatype.XSD_DOUBLE.getIRI());
		OWLDatatype name_dt = df.getOWLDatatype(OWL2Datatype.XSD_NAME.getIRI());

		OWLOntologyManager m = create();
		OWLOntology o = m.createOntology(ontologyiri);

		/* propiedades de la ontologia */

		OWLClass parada = crearClase("parada");
		OWLClass parada_bus = crearClase("parada_bus");
		OWLClass parada_metro = crearClase("parada_metro");

		/* Declaramos las clases */
		OWLDeclarationAxiom decParada = declararClases(parada);
		OWLDeclarationAxiom decParada_metro = declararClases(parada_metro);
		OWLDeclarationAxiom decParada_bus = declararClases(parada_bus);
		/* Declaramos las subClases */
		OWLAxiom subClase1 = declararSubClases(parada_bus, parada);
		OWLAxiom subClase2 = declararSubClases(parada_metro, parada);

		/* creamos las objectPropertys */
		OWLObjectProperty tiene = createObjectProperty("tiene");
		/*creamos las dataproperty*/
		OWLDataProperty tieneX = createDatatypeProperty("tieneX");
		OWLDataProperty tieneY = createDatatypeProperty("tieneY");
		OWLDataProperty tieneLatitud = createDatatypeProperty("tieneLatitud");
		OWLDataProperty tieneLongitud = createDatatypeProperty("tieneLongitud");
		OWLDataProperty tieneNombre = createDatatypeProperty("tieneNombre");
		OWLDataProperty tieneCodigoDeParada = createDatatypeProperty("tieneCodigoDeParada");
		/*heading*/
		OWLDataProperty tieneAreaParada = createDatatypeProperty("tieneAreaParada");
		OWLDataProperty tieneZona = createDatatypeProperty("tieneZona");
		OWLDataProperty tieneCodigoPostal = createDatatypeProperty("tieneCodigoPostal");
		OWLDataProperty tieneParadasAdayacentes = createDatatypeProperty("tieneParadasAdyacentes");
		OWLDataProperty tieneSentido = createDatatypeProperty("tieneSentido");
		
		/* Declaramos las propiedaes */
		OWLDeclarationAxiom tieneDec = declareProperty(tiene);
		
		OWLDeclarationAxiom tieneXDec = declareProperty(tieneX);
		OWLDeclarationAxiom tieneYDec = declareProperty(tieneY);
		OWLDeclarationAxiom tieneLatitudDec = declareProperty(tieneLatitud);
		OWLDeclarationAxiom tieneLongitudDec = declareProperty(tieneLongitud);
		OWLDeclarationAxiom tieneNombreDec = declareProperty(tieneNombre);
		OWLDeclarationAxiom tieneCodigoDeParadaDec = declareProperty(tieneCodigoDeParada);
		OWLDeclarationAxiom tieneAreaParadaDec = declareProperty(tieneAreaParada);
		OWLDeclarationAxiom tieneZonaDec = declareProperty(tieneZona);
		OWLDeclarationAxiom tieneCodigoPostalDec = declareProperty(tieneCodigoPostal);
		OWLDeclarationAxiom tieneParadasAdayacentesDec = declareProperty(tieneParadasAdayacentes);
		OWLDeclarationAxiom tieneSentidoDec = declareProperty(tieneSentido);
	

		/*Establecemos los dominios*/
		OWLObjectPropertyDomainAxiom tieneDomain = declareObjectDomain(tiene, parada);
		
		OWLDataPropertyDomainAxiom tieneXDomain = declareDataDomain(tieneX, parada);
		OWLDataPropertyDomainAxiom tieneYDomain = declareDataDomain(tieneY, parada);
		OWLDataPropertyDomainAxiom tieneNombreDomain = declareDataDomain(tieneNombre, parada);
		
		OWLDataPropertyDomainAxiom tieneLatitudDomain = declareDataDomain(tieneLatitud, parada_metro);
		OWLDataPropertyDomainAxiom tieneLongitudDomain = declareDataDomain(tieneLongitud, parada_metro);
		OWLDataPropertyDomainAxiom tieneZonaDomain = declareDataDomain(tieneZona, parada_metro);
		OWLDataPropertyDomainAxiom tieneCodigoPostaldomain = declareDataDomain(tieneCodigoPostal, parada_metro);
		OWLDataPropertyDomainAxiom tieneParadasAdayacentesDomain = declareDataDomain(tieneParadasAdayacentes, parada_metro);
		
		OWLDataPropertyDomainAxiom tieneCodigoDeParadaDomain = declareDataDomain(tieneCodigoDeParada, parada_bus);
		OWLDataPropertyDomainAxiom tieneAreaParadaDomain = declareDataDomain(tieneAreaParada, parada_bus);
		OWLDataPropertyDomainAxiom tieneSentidoDomain = declareDataDomain(tieneSentido, parada_bus);
		
		/*rango*/
		OWLLiteral minX = df.getOWLLiteral("462,000.000");
		OWLLiteral maxX = df.getOWLLiteral("562,000.000");
		OWLDatatypeRestriction xMaxRes = df.getOWLDatatypeRestriction(double_dt, OWLFacet.MAX_INCLUSIVE, maxX);
		OWLDatatypeRestriction xMinRes = df.getOWLDatatypeRestriction(double_dt, OWLFacet.MIN_INCLUSIVE, minX);
		
		OWLDatatypeRestriction yMaxRes = df.getOWLDatatypeRestriction(double_dt, OWLFacet.MAX_INCLUSIVE, df.getOWLLiteral("208,000,00"));
		OWLDatatypeRestriction yMinRes = df.getOWLDatatypeRestriction(double_dt, OWLFacet.MIN_INCLUSIVE, df.getOWLLiteral("155,000,00"));
		
		OWLDatatypeRestriction latMaxRes = df.getOWLDatatypeRestriction(double_dt, OWLFacet.MAX_INCLUSIVE, df.getOWLLiteral("51,75"));
		OWLDatatypeRestriction latMinRes = df.getOWLDatatypeRestriction(double_dt, OWLFacet.MIN_INCLUSIVE, df.getOWLLiteral("51,28"));
		
		OWLDatatypeRestriction longMaxRes = df.getOWLDatatypeRestriction(double_dt, OWLFacet.MAX_INCLUSIVE, df.getOWLLiteral("-0,33"));
		OWLDatatypeRestriction longMinRes = df.getOWLDatatypeRestriction(double_dt, OWLFacet.MIN_INCLUSIVE, df.getOWLLiteral("-0.61"));
		
		OWLDatatypeRestriction zonaMaxres = df.getOWLDatatypeRestriction(integer_dt, OWLFacet.MAX_INCLUSIVE, df.getOWLLiteral(9));
		OWLDatatypeRestriction zonaMinRes = df.getOWLDatatypeRestriction(integer_dt, OWLFacet.MIN_INCLUSIVE, df.getOWLLiteral(1));
		
		OWLDatatypeRestriction paradaMaxRes = df.getOWLDatatypeRestriction(integer_dt, OWLFacet.MAX_INCLUSIVE, df.getOWLLiteral(47000));
		OWLDatatypeRestriction paradaMinRes = df.getOWLDatatypeRestriction(integer_dt, OWLFacet.MIN_INCLUSIVE, df.getOWLLiteral(99000));
		
		OWLDatatypeRestriction sentidoMaxRes = df.getOWLDatatypeRestriction(integer_dt, OWLFacet.MAX_INCLUSIVE, df.getOWLLiteral(360));
		OWLDatatypeRestriction sentidoMinRes = df.getOWLDatatypeRestriction(integer_dt, OWLFacet.MIN_INCLUSIVE, df.getOWLLiteral(0));
		
		
	
		
		OWLDataPropertyRangeAxiom tieneXrange  = declareDataRange(tieneX, integer_dt); //496,000.000 - 562,000.000
		OWLDataPropertyRangeAxiom tieneYrange  = declareDataRange(tieneY, integer_dt); //155,000,00—208,000,00
		OWLDataPropertyRangeAxiom tieneLatitudrange  = declareDataRange(tieneLatitud, double_dt); //51,28—51,75
		OWLDataPropertyRangeAxiom tieneLongitudrange  = declareDataRange(tieneLongitud, double_dt); //-0,61—0,33
		OWLDataPropertyRangeAxiom tieneNombreRange  = declareDataRange(tieneNombre, integer_dt); //String not null
		OWLDataPropertyRangeAxiom tieneCodigoDeParadaRange  = declareDataRange(tieneCodigoDeParada, integer_dt); //47000-99000, puede ser null
		OWLDataPropertyRangeAxiom tieneAreaParadaRange  = declareDataRange(tieneAreaParada, name_dt); // not unique, not empty.Sin standard
		OWLDataPropertyRangeAxiom tieneZonaRange  = declareDataRange(tieneZona , integer_dt); // 1 -9 restriccion
		OWLDataPropertyRangeAxiom tieneCodigoPostalRange  = declareDataRange(tieneCodigoPostal, string_dt); //metro string
		OWLDataPropertyRangeAxiom tieneParadasAdayacentesRange  = declareDataRange(tieneParadasAdayacentes, string_dt); //string
		OWLDataPropertyRangeAxiom tieneSentidoRange  = declareDataRange(tieneSentido, integer_dt); //string
		
		/*Reestricciones */
		OWLDataPropertyRangeAxiom xMaxRestriction = declareRestrictionRange(tieneX, xMaxRes);
		OWLDataPropertyRangeAxiom xMinRestriction = declareRestrictionRange(tieneX, xMinRes);
		
		OWLDataPropertyRangeAxiom yMaxRestriction = declareRestrictionRange(tieneY, yMaxRes);
		OWLDataPropertyRangeAxiom yMinRestriction = declareRestrictionRange(tieneY, yMinRes);
		
		OWLDataPropertyRangeAxiom latMaxRestriction = declareRestrictionRange(tieneLatitud, latMaxRes);
		OWLDataPropertyRangeAxiom latMinRestriction = declareRestrictionRange(tieneLatitud, latMinRes);
		
		OWLDataPropertyRangeAxiom longMaxRestriction = declareRestrictionRange(tieneLongitud, longMaxRes);
		OWLDataPropertyRangeAxiom longMinRestriction = declareRestrictionRange(tieneLongitud, longMinRes);
		
		
		OWLDataPropertyRangeAxiom zonaMaxRestriction = declareRestrictionRange(tieneZona, zonaMaxres );
		OWLDataPropertyRangeAxiom zonaMinRestriction = declareRestrictionRange(tieneZona, zonaMinRes);
		
		OWLDataPropertyRangeAxiom paradaMaxRestriccion = declareRestrictionRange(tieneCodigoDeParada, paradaMaxRes );
		OWLDataPropertyRangeAxiom paradaMinRestriccion= declareRestrictionRange(tieneCodigoDeParada, paradaMinRes);
		
		OWLDataPropertyRangeAxiom sentidoMaxRestriccion = declareRestrictionRange(tieneSentido, sentidoMaxRes);
		OWLDataPropertyRangeAxiom sentidoMinRestriccion = declareRestrictionRange(tieneSentido, sentidoMinRes);
		
		
		applyChanges(m, o, decParada);
		applyChanges(m, o, decParada_metro);
		applyChanges(m, o, decParada_bus);
		applyChanges(m, o, subClase1);
		applyChanges(m, o, subClase2);
		/* propiedades */
		applyChanges(m, o, tieneDec);
		applyChanges(m, o, tieneXDec);
		applyChanges(m, o, tieneYDec);
		applyChanges(m, o, tieneLatitudDec);
		applyChanges(m, o, tieneLongitudDec);
		applyChanges(m, o, tieneNombreDec);
		applyChanges(m, o, tieneCodigoDeParadaDec);
		applyChanges(m, o, tieneAreaParadaDec);
		applyChanges(m, o, tieneZonaDec);
		applyChanges(m, o, tieneCodigoPostalDec);
		applyChanges(m, o, tieneParadasAdayacentesDec);
		applyChanges(m, o, tieneSentidoDec);

		
		/*dominios*/
		applyChanges(m, o, tieneDomain);
		applyChanges(m, o, tieneXDomain);
		applyChanges(m, o, tieneYDomain);
		applyChanges(m, o, tieneLatitudDomain);
		applyChanges(m, o, tieneLongitudDomain);
		applyChanges(m, o, tieneNombreDomain);
		applyChanges(m, o, tieneCodigoDeParadaDomain);
		applyChanges(m, o, tieneAreaParadaDomain);
		applyChanges(m, o, tieneZonaDomain);
		applyChanges(m, o, tieneCodigoPostaldomain);
		applyChanges(m, o, tieneParadasAdayacentesDomain);
		applyChanges(m, o, tieneSentidoDomain);

		/*rangos*/
		
		applyChanges(m, o, tieneXrange);
		applyChanges(m, o, tieneYrange);
		applyChanges(m, o, tieneLatitudrange);
		applyChanges(m, o, tieneLongitudrange);
		applyChanges(m, o, tieneNombreRange);
		applyChanges(m, o, tieneCodigoDeParadaRange);
		applyChanges(m, o, tieneAreaParadaRange);
		applyChanges(m, o, tieneZonaRange);
		applyChanges(m, o, tieneCodigoPostalRange);
		applyChanges(m, o, tieneParadasAdayacentesRange);
		applyChanges(m, o, tieneSentidoRange);

		/*Restricciones*/
		applyChanges(m, o, xMaxRestriction);
		applyChanges(m, o, xMinRestriction);
		
		applyChanges(m, o, yMaxRestriction);
		applyChanges(m, o, yMinRestriction);
		
		applyChanges(m, o, latMaxRestriction);
		applyChanges(m, o, latMinRestriction);
		
		applyChanges(m, o, longMaxRestriction);
		applyChanges(m, o, longMinRestriction);
		
		applyChanges(m, o, zonaMaxRestriction);
		applyChanges(m, o, zonaMinRestriction);
		
		applyChanges(m, o, paradaMaxRestriccion);
		applyChanges(m, o, paradaMinRestriccion);
		
		applyChanges(m, o, sentidoMaxRestriccion);
		applyChanges(m, o, sentidoMinRestriccion);


		
		
		
		//m.saveOntology(o, new OWLXMLOntologyFormat(), new SystemOutDocumentTarget());
		m.saveOntology(o, new TurtleOntologyFormat(), new SystemOutDocumentTarget());

	}

}

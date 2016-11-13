package ontologia;

/**
 * @author Jorge Amorós
 */
	
import java.io.File;
import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.AutoIRIMapper;

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

	public static OWLObjectProperty crearPropiedad(String nombrepropiedad) {
		return df.getOWLObjectProperty(IRI.create(ontologyiri + "#" + nombrepropiedad));
	}

	public static OWLDeclarationAxiom declararPropiedad(OWLObjectProperty propiedad) {
		return df.getOWLDeclarationAxiom(propiedad);
	}
	
	public static OWLObjectPropertyDomainAxiom declararDominio(OWLObjectProperty property, OWLClass cls){
		return  df.getOWLObjectPropertyDomainAxiom(property, cls);

	}
	
//	public static OWLDataPropertyAxiom declararDominio(OWLObjectProperty property){
//		
//		return  df.getOWLDataPropertyAxiom(property, inte);
//
//	}

	public static void applyChanges(OWLOntologyManager m, OWLOntology o, OWLAxiom axion) {
		m.applyChange(new AddAxiom(o, axion));
	}

	public static void main(String[] args)
			throws OWLOntologyCreationException, IOException, OWLOntologyStorageException {

		File output = File.createTempFile("prueba", "owl");
		IRI documentIRI2 = IRI.create(output);

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

		/* creamos las propiedades */
		OWLObjectProperty tieneX = crearPropiedad("tieneX");
		OWLObjectProperty tieneY = crearPropiedad("tieneY");
		OWLObjectProperty tieneNombre = crearPropiedad("tieneNombre");
		OWLObjectProperty tieneCodigoDeParada = crearPropiedad("tieneCodigoDeParada");
		/*heading*/
		OWLObjectProperty tieneNumeroDeParada = crearPropiedad("tieneNumeroDeParada");
		OWLObjectProperty tieneAreaDondePara = crearPropiedad("tieneAreaDondePara");
		OWLObjectProperty tieneZona = crearPropiedad("tieneZona");
		OWLObjectProperty tieneCodigoPostal = crearPropiedad("tieneCodigoPostal");
		OWLObjectProperty tieneParadaAnterior = crearPropiedad("tieneParadaAnterior");
		OWLObjectProperty tieneParadaSiguiente = crearPropiedad("tieneParadaSiguiente");
		
		/* Declaramos las propiedaes */
		
		OWLDeclarationAxiom tieneXDec = declararPropiedad(tieneX);
		OWLDeclarationAxiom tieneYDec = declararPropiedad(tieneY);
		OWLDeclarationAxiom tieneNombreDec = declararPropiedad(tieneNombre);
		OWLDeclarationAxiom tieneCodigoDeParadaDec = declararPropiedad(tieneCodigoDeParada);
		OWLDeclarationAxiom tieneNumeroDeParadaDec = declararPropiedad(tieneNumeroDeParada);
		OWLDeclarationAxiom tieneAreaDondeParaDec = declararPropiedad(tieneAreaDondePara);
		OWLDeclarationAxiom tieneZonaDec = declararPropiedad(tieneZona);
		OWLDeclarationAxiom tieneCodigoPostalDec = declararPropiedad(tieneCodigoPostal);
		OWLDeclarationAxiom tieneParadaAnteriorDec = declararPropiedad(tieneParadaAnterior);
		OWLDeclarationAxiom tieneParadaSiguienteDec = declararPropiedad(tieneParadaSiguiente);

		/*Establecemos los dominios*/
		OWLObjectPropertyDomainAxiom tieneXDomain = declararDominio(tieneX, parada);
		OWLObjectPropertyDomainAxiom tieneYDomain = declararDominio(tieneY, parada);
		OWLObjectPropertyDomainAxiom tieneNombreDomain = declararDominio(tieneNombre, parada);
		OWLObjectPropertyDomainAxiom tieneCodigoDeParadaDomain = declararDominio(tieneCodigoDeParada, parada_bus);
		OWLObjectPropertyDomainAxiom tieneNumeroDeParadaDomain = declararDominio(tieneNumeroDeParada, parada_bus);
		OWLObjectPropertyDomainAxiom tieneAreaDondeParaDomain = declararDominio(tieneAreaDondePara, parada_bus);
		OWLObjectPropertyDomainAxiom tieneZonaDomain = declararDominio(tieneZona, parada_metro);
		OWLObjectPropertyDomainAxiom tieneCodigoPostaldomain = declararDominio(tieneCodigoPostal, parada_metro);
		OWLObjectPropertyDomainAxiom tieneParadaAnteriorDomain = declararDominio(tieneParadaAnterior, parada_metro);
		OWLObjectPropertyDomainAxiom tieneParadaSiguienteDomain = declararDominio(tieneParadaSiguiente, parada_metro);
		
		applyChanges(m, o, decParada);
		applyChanges(m, o, decParada_metro);
		applyChanges(m, o, decParada_bus);
		applyChanges(m, o, subClase1);
		applyChanges(m, o, subClase2);
		/* propiedades */
		applyChanges(m, o, tieneXDec);
		applyChanges(m, o, tieneYDec);
		applyChanges(m, o, tieneNombreDec);
		applyChanges(m, o, tieneCodigoDeParadaDec);
		applyChanges(m, o, tieneNumeroDeParadaDec);
		applyChanges(m, o, tieneAreaDondeParaDec);
		applyChanges(m, o, tieneZonaDec);
		applyChanges(m, o, tieneCodigoPostalDec);
		applyChanges(m, o, tieneParadaAnteriorDec);
		applyChanges(m, o, tieneParadaSiguienteDec);
		/*dominios*/
		applyChanges(m, o, tieneXDomain);
		applyChanges(m, o, tieneYDomain);
		applyChanges(m, o, tieneNombreDomain);
		applyChanges(m, o, tieneCodigoDeParadaDomain);
		applyChanges(m, o, tieneNumeroDeParadaDomain);
		applyChanges(m, o, tieneAreaDondeParaDomain);
		applyChanges(m, o, tieneZonaDomain);
		applyChanges(m, o, tieneParadaAnteriorDomain);
		applyChanges(m, o, tieneParadaSiguienteDomain);
		


		

		m.saveOntology(o, new OWLXMLOntologyFormat(), new SystemOutDocumentTarget());

	}

}

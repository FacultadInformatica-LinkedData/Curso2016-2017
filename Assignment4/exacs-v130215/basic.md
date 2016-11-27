# For all:

```rq
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX exe2: <http://GP-onto.fi.upm.es/exercise2#>
```


# 1. Get all the classes

```rq
SELECT * WHERE {
  ?class a rdfs:Class
} LIMIT 100
```

## Result

```
class
http://www.w3.org/2002/07/owl#Class
http://www.w3.org/2002/07/owl#Ontology
http://www.w3.org/2002/07/owl#OntologyProperty
http://www.w3.org/2002/07/owl#AnnotationProperty
http://www.w3.org/2002/07/owl#AllDifferent
http://www.w3.org/2002/07/owl#Restriction
http://www.w3.org/2002/07/owl#ObjectProperty
http://www.w3.org/2002/07/owl#DatatypeProperty
http://www.w3.org/2002/07/owl#TransitiveProperty
http://www.w3.org/2002/07/owl#SymmetricProperty
http://www.w3.org/2002/07/owl#FunctionalProperty
http://www.w3.org/2002/07/owl#InverseFunctionalProperty
http://www.w3.org/2002/07/owl#DeprecatedClass
http://www.w3.org/2002/07/owl#DeprecatedProperty
http://www.w3.org/2002/07/owl#DataRange
http://www.w3.org/1999/02/22-rdf-syntax-ns#Property
http://www.w3.org/2002/07/owl#Class
http://www.w3.org/2000/01/rdf-schema#Class
http://www.w3.org/1999/02/22-rdf-syntax-ns#List
http://www.w3.org/2002/07/owl#ObjectProperty
http://www.w3.org/2002/07/owl#DatatypeProperty
http://energy.linkeddata.es/PQube.owl#PropertySummaryValue
http://energy.linkeddata.es/PQube.owl#ObservationValue
http://energy.linkeddata.es/PQube.owl#PQubePropertySummary
http://www.loa-cnr.it/ontologies/DUL.owl#TimeInterval
http://energy.linkeddata.es/PQube.owl#PQubeProperty
http://www.w3.org/2000/01/rdf-schema#Resource
http://www.w3.org/1999/02/22-rdf-syntax-ns#Statement
http://energy.linkeddata.es/PQube.owl#DailyObservationCollection
http://energy.linkeddata.es/PQube.owl#PQubeDevice
http://www.w3.org/2000/01/rdf-schema#Container
http://www.w3.org/1999/02/22-rdf-syntax-ns#Bag
http://www.w3.org/2000/01/rdf-schema#ContainerMembershipProperty
http://energy.linkeddata.es/PQube.owl#Building
http://www.w3.org/2000/01/rdf-schema#Datatype
http://www.w3.org/1999/02/22-rdf-syntax-ns#Alt
http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral
http://energy.linkeddata.es/PQube.owl#PQubeConfiguration
http://www.w3.org/2000/01/rdf-schema#Literal
http://www.w3.org/1999/02/22-rdf-syntax-ns#Seq
http://datos.localidata.com/def/CityStats/code#Nacionalidad
http://GP-onto.fi.upm.es/exercise2#OnFoot
http://GP-onto.fi.upm.es/exercise2#TransportMedium
http://GP-onto.fi.upm.es/exercise2#Hostel
http://GP-onto.fi.upm.es/exercise2#Establishment
http://GP-onto.fi.upm.es/exercise2#PhysicalPoint
http://GP-onto.fi.upm.es/exercise2#Stage
http://GP-onto.fi.upm.es/exercise2#TransportInfrastructure
http://GP-onto.fi.upm.es/exercise2#Bus
http://GP-onto.fi.upm.es/exercise2#Car
http://GP-onto.fi.upm.es/exercise2#BankService
http://GP-onto.fi.upm.es/exercise2#Service
http://GP-onto.fi.upm.es/exercise2#Bicycle
http://GP-onto.fi.upm.es/exercise2#PostalAddress
http://GP-onto.fi.upm.es/exercise2#Path
http://GP-onto.fi.upm.es/exercise2#Road
http://GP-onto.fi.upm.es/exercise2#Cathedral
http://GP-onto.fi.upm.es/exercise2#TouristicLocation
http://GP-onto.fi.upm.es/exercise2#City
http://GP-onto.fi.upm.es/exercise2#Locality
http://GP-onto.fi.upm.es/exercise2#Route
http://GP-onto.fi.upm.es/exercise2#SpacialThing
http://GP-onto.fi.upm.es/exercise2#LocationOfInterest
http://GP-onto.fi.upm.es/exercise2#Stretch
http://GP-onto.fi.upm.es/exercise2#Chapel
http://GP-onto.fi.upm.es/exercise2#GuestHouse
http://GP-onto.fi.upm.es/exercise2#Hotel
http://GP-onto.fi.upm.es/exercise2#Church
http://GP-onto.fi.upm.es/exercise2#Location
http://GP-onto.fi.upm.es/exercise2#Palace
http://GP-onto.fi.upm.es/exercise2#PostalService
http://GP-onto.fi.upm.es/exercise2#Town
http://GP-onto.fi.upm.es/exercise2#RestaurationService
http://GP-onto.fi.upm.es/exercise2#HealthService
http://GP-onto.fi.upm.es/exercise2#SecurityService
http://GP-onto.fi.upm.es/exercise2#Track
http://GP-onto.fi.upm.es/exercise2#Train
http://GP-onto.fi.upm.es/exercise2#Railway
http://GP-onto.fi.upm.es/exercise2#Village
http://datos.localidata.com/def/CityStats/code#Nacionalidad
http://www.w3.org/1999/02/22-rdf-syntax-ns#Property
http://www.w3.org/2000/01/rdf-schema#Class
http://www.w3.org/1999/02/22-rdf-syntax-ns#List
http://www.w3.org/2000/01/rdf-schema#Resource
http://www.w3.org/1999/02/22-rdf-syntax-ns#Statement
http://www.w3.org/2000/01/rdf-schema#Container
http://www.w3.org/1999/02/22-rdf-syntax-ns#Bag
http://www.w3.org/2000/01/rdf-schema#ContainerMembershipProperty
http://www.w3.org/2000/01/rdf-schema#Datatype
http://www.w3.org/1999/02/22-rdf-syntax-ns#Alt
http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral
http://www.w3.org/2000/01/rdf-schema#Literal
http://www.w3.org/1999/02/22-rdf-syntax-ns#Seq
http://www.opengis.net/ont/sf#MultiPolygon
http://vocab.linkeddata.es/datosabiertos/def/medio-ambiente/otalex#AmplitudTermicaAbsoluta
```

# 2. Get all the subclasses of the class Establishment

```rq
SELECT * WHERE {
  ?class rdfs:subClassOf exe2:Establishment.
} LIMIT 100
```

## Result

```
class
http://GP-onto.fi.upm.es/exercise2#Hostel
http://GP-onto.fi.upm.es/exercise2#GuestHouse
http://GP-onto.fi.upm.es/exercise2#Hotel
```

# 3. Get all instances of the class City

```rq
SELECT * WHERE {
  ?instance a exe2:City.
}
```

## Result

```
instance
http://GP-onto.fi.upm.es/exercise2#ACoruC1a
http://GP-onto.fi.upm.es/exercise2#Santiago_de_Compostela
http://GP-onto.fi.upm.es/exercise2#Madrid
```


# 4. Get the number of inhabitants of Santiago de Compostela

```rq
SELECT * WHERE {
  exe2:Santiago_de_Compostela exe2:hasInhabitantNumber ?population.
}
```

## Result

```
population
" 300000 "^^<http://www.w3.org/2001/XMLSchema#integer>
```

# 5. Get the number of inhabitants of Santiago de Compostela and Arzua

```rq
SELECT distinct ?populationSantiago ?populationArzua WHERE {
  exe2:Santiago_de_Compostela exe2:hasInhabitantNumber ?populationSantiago.
  exe2:Arzua exe2:hasInhabitantNumber ?populationArzua
}
```

## Result

```
populationSantiago	populationArzua
" 300000 "^^<http://www.w3.org/2001/XMLSchema#integer>	" 38900 "^^<http://www.w3.org/2001/XMLSchema#integer>
```

# 6. Get all places, together with the number of inhabitants, ordered by the place name (ascending)

```rq
SELECT ?place ?population WHERE {
  ?place exe2:hasInhabitantNumber ?population.
} ORDER BY ASC(?place)
```

## Result

```
place	population
http://GP-onto.fi.upm.es/exercise2#Arzua	" 38900 "^^<http://www.w3.org/2001/XMLSchema#integer>
http://GP-onto.fi.upm.es/exercise2#Santiago_de_Compostela	" 300000 "^^<http://www.w3.org/2001/XMLSchema#integer>
```


# 7. Get all instances of  Locality together with their number of inhabitants (if this information exists)

```rq
SELECT distinct ?locality ?population WHERE {
  ?locality a ?subclass.
  ?subclass rdfs:subClassOf exe2:Locality
  OPTIONAL {
    ?locality exe2:hasInhabitantNumber ?population
  }
}
```

## Result

```
locality	population
http://GP-onto.fi.upm.es/exercise2#ACoruC1a	
http://GP-onto.fi.upm.es/exercise2#Santiago_de_Compostela	" 300000 "^^<http://www.w3.org/2001/XMLSchema#integer>
http://GP-onto.fi.upm.es/exercise2#Madrid	
http://GP-onto.fi.upm.es/exercise2#Arzua	" 38900 "^^<http://www.w3.org/2001/XMLSchema#integer>
```

# 8. Get all places with more than 200.000 inhabitants

```rq
SELECT ?place ?population WHERE {
  ?place exe2:hasInhabitantNumber ?population.
  FILTER(xsd:integer(?population) > 200000)
}
```

## Result

```
place	population
http://GP-onto.fi.upm.es/exercise2#Santiago_de_Compostela	" 300000 "^^<http://www.w3.org/2001/XMLSchema#integer>
```

# 9. Get postal address data for Pazo_Breogan (street, number, locality, province)

```rq
SELECT ?street ?number ?locality ?province WHERE {
  exe2:Pazo_Breogan exe2:hasAddress ?address.
  exe2:Pazo_Breogan exe2:isPlacedIn ?place.
  ?place rdfs:label ?locality.
  ?place exe2:inProvince ?province.
  ?address exe2:hasStreet ?street.
  ?address exe2:hasNumber ?number.
}
```

## Result

```
street	number	locality	province
C/Mayor	5	Arzua	Pontevedra
```


# 10. Get all subclasses of class Location

```rq
SELECT DISTINCT ?subclass WHERE {
  ?subclass rdfs:subClassOf exe2:Location
}
```

## Result

```
subclass
http://GP-onto.fi.upm.es/exercise2#LocationOfInterest
```

# 11. Get all instances of class Locality

```rq
SELECT DISTINCT ?instance WHERE {
  ?subclass rdfs:subClassOf exe2:Locality.
  ?instance a ?subclass.
}
```

## Result

```
instance
http://GP-onto.fi.upm.es/exercise2#ACoruC1a
http://GP-onto.fi.upm.es/exercise2#Santiago_de_Compostela
http://GP-onto.fi.upm.es/exercise2#Madrid
http://GP-onto.fi.upm.es/exercise2#Arzua
```

# 12. Describe the resource with rdfs:label "Madrid”

```rq
DESCRIBE ?resource WHERE {
  ?resource rdfs:label "Madrid"
}
```

## Result

```ttl
@prefix ns0:	<http://GP-onto.fi.upm.es/exercise2#> .
ns0:GP_Santiago_Instance_72	ns0:hasEnd	ns0:Madrid .
@prefix rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
ns0:Madrid	rdf:type	ns0:City .
@prefix rdfs:	<http://www.w3.org/2000/01/rdf-schema#> .
ns0:Madrid	rdfs:label	"Madrid" ;
	ns0:isLocatedAtPoint	ns0:GP_Santiago_Instance_74 ;
	ns0:inProvince	"Madrid" .
```


# 13. Construct a graph that relates directly all touristic places with their provinces, using a new property called ”isIn”

```rq
CONSTRUCT {?touristicPlace exe2:isIn ?province} WHERE {
  ?touristicPlace exe2:isPlacedIn ?place.
  ?place exe2:inProvince ?province.
} LIMIT 100
```

## Result

```turtle
@prefix ns0:  <http://GP-onto.fi.upm.es/exercise2#> .
ns0:Fuente_Talaverana ns0:isIn  "Pontevedra" .
ns0:Monte_Do_Gozo ns0:isIn  "Pontevedra" .
ns0:Cathedral_Santiago_Compostela ns0:isIn  "Pontevedra" .
ns0:Pazo_Breogan  ns0:isIn  "Pontevedra" .
```

# 14. Check whether there is any instance of Town

```rq
ASK {
  ?town a exe2:Town
}
```

## Result

```
yes
```

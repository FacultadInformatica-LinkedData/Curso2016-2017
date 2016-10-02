
Assignment 4
============

**SPARQL Questionnaire (Basic)**
Provide the SPARQL query and the result for the following queries expressed in natural language.
* 1. Get all the classes
* 2. Get all the subclasses of the class Establishment
* 3. Get all instances of the class City
* 4. Get the number of inhabitants of Santiago de Compostela
* 5. Get the number of inhabitants of Santiago de Compostela and Arzua
* 6. Get all places, together with the number of inhabitants, ordered by the place name (ascending)
* 7. Get all instances of  Locality together with their number of inhabitants (if this information exists)
* 8. Get all places with more than 200.000 inhabitants
* 9. Get postal address data for Pazo_Breogan (street, number, locality, province)
* 10. Get all subclasses of class Location
* 11. Get all instances of class Locality
* 12. Describe the resource with rdfs:label "Madrid”
* 13. Construct a graph that relates directly all touristic places with their provinces, using a new property called ”isIn”
* 14. Check whether there is any instance of Town

The endpoint that you can use for this exercise is: http://sandbox.linkeddata.es/sparql and the graph is http://sandbox.linkeddata.es/Grado_20122013

**SPARQL Questionnaire (Advanced)**
 Provide the SPARQL query and the result for the following queries expressed in natural language. The endpoint that you can use for this exercise is: http://es.dbpedia.org/sparql
* A1. Dame todas las propiedades aplicables a las instancias de la clase Politician (<http://dbpedia.org/ontology/Politician>)
* A2. Dame todas las propiedades, exceptuando rdf:type, aplicables a las instancias de la clase Politician (<http://dbpedia.org/ontology/Politician>)
* A3. ¿Cuántos valores distintos se pueden encontrar para las propiedades, exceptuando rdf:type, de las instancias de la clase Politician (<http://dbpedia.org/ontology/Politician>)?
* A4. Para cada una de las propiedades, exceptuando rdf:type, aplicables a las instancias de la clase Politician (<http://dbpedia.org/ontology/Politician>), dime cuántos valores distintos toman en dichas instancias
* A5. Para cada una de las propiedades, exceptuando rdf:type, aplicables a las instancias de la clase Politician (<http://dbpedia.org/ontology/Politician>), dime la media de valores distintos que toman en dichas instancias.
* A6. Para cada una de las propiedades, exceptuando rdf:type, aplicables a las instancias de la clase Politician (<http://dbpedia.org/ontology/Politician>), dime el máximo número de valores distintos que toman entre todas sus instancias, ordenado de mayor a menor

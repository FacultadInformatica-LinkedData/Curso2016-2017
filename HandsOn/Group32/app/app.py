filename = "..rdf/data.rdf" 

import rdflib
import rdfextras
rdfextras.registerplugins() # so we can Graph.query()

g=rdflib.Graph()
g.parse(filename)
results = g.query("""
SELECT ?p ?o
WHERE {
<%s> ?p ?o.
}
""") 

tar_filename = "..rdf/data.rdf.tar.gz" 
filename = "..rdf/data.rdf" 

import rdflib
import rdfextras
import tarfile
import os

 # Unpack the tar.gz
tar = tarfile.open(tar_filename, "r:gz")
tar.extractall()
tar.close()

rdfextras.registerplugins() # so we can Graph.query()
 # library to load an RDF
g=rdflib.Graph()
 # Load the the local RDF
g.parse(filename)
 # Query
results = g.query("""
SELECT ?p ?o
WHERE {
<%s> ?p ?o.
}""") 

 # Remove the RDF
os.remove(filename)

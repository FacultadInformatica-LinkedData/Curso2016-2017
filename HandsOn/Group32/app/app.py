tar_filename = "..rdf/data.rdf.tar.gz" 
filename = "..rdf/data.rdf" 

import rdflib
import rdfextras
import tarfile
import os

 # Unpack the tar.gz
print("Unpacking RDF ...")
tar = tarfile.open(tar_filename, "r:gz")
tar.extractall()
tar.close()
print("Unpacked!")

rdfextras.registerplugins() # so we can Graph.query()
 # library to load an RDF
g=rdflib.Graph()
 # Load the the local RDF
print("Loading RDF ...")
g.parse(filename)
print("RDF has %s statements." % len(g))
 # Query
print("--- All ---")
results = g.query("""
SELECT ?p ?o
WHERE {
<%s> ?p ?o.
}""")

for row in results:
    print("%s" % row)
 
print("Finished")
 # Remove the RDF
os.remove(filename)

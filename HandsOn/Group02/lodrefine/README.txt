Como se puede observar solo hay dos archivos json pese a tener 3 csv. La razón de esto es que los datos que nos interesan del csv de
las líneas de metro has sido unidos al csv de la estaciones de metro. Esto se ha realizado con las siguientes funciones cross
---forEach(cell.cross("London TubeLines","From Station"),r,r.cells["Tube Line"].value).join("|")
  Con esta hemos añadido a cada fila de las estaciones su linea ( o lineas ) a la que pertenece.
 
 ---forEach(cell.cross("London TubeLines","From Station"),r,r.cells["To Station"].value).join(" | ") + " | " + 
    forEach(cell.cross("London TubeLines","To Station"),r,r.cells["From Station"].value).join(" | ")
    
   Con esta hemos añadido a cada fila de las estaciones su estaciones adyacentes. En el csv final hay estaciones adyacentes repetidas pues
   en este metro de londres hay estaciones pertenecientes a varias lineas.
Nuestro csv de estaciones posee a su vez muchas estaciones que pertenecen a un ámbito diferente del metro propiamente dicho y cuyo atributo
linea y estaciones adyacentes estaban incompletos. Han sido eliminados del csv mediante el uso de una faceta y la selección de las columnas oportunas aunque esto no queda reflejado en el json.

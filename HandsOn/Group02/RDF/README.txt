El RDF de los restaurantes esta en un zip debido a que ocupa 27 megas y el tope de git son 25. Dejo aqui una muestra de como queda por comodidad.



@prefix : <> .
@prefix ont: <http://www.semanticweb.org/webSemantica/grupo02/vocabulary#> .
@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix owl: <http://www.w3.org/2002/07/owl#> .
@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .


<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#The+New+Inn> a ont:Restaurante ;
	ont:tieneNombre "The New Inn" ;
	ont:tieneDireccion-Restaurante "London" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.582250010011"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.21198230407423"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220354> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220354> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Bread+And+Breakfast+Cafe> a ont:Restaurante ;
	ont:tieneNombre "Bread And Breakfast Cafe" ;
	ont:tieneDireccion-Restaurante "London" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.5999535524"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.221922332759"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220356> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220356> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#A127+McDonalds> a ont:Restaurante ;
	ont:tieneNombre "A127 McDonalds" ;
	ont:tieneDireccion-Restaurante "London" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.589293494"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.21993183437222"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220358> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220358> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Ojs> a ont:Restaurante ;
	ont:tieneNombre "Ojs" ;
	ont:tieneDireccion-Restaurante "London" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.562911820723"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.21662849534836"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220359> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220359> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#The+Fatling> a ont:Restaurante ;
	ont:tieneNombre "The Fatling" ;
	ont:tieneDireccion-Restaurante "109 High Street, Hornchurch, United Kingdom" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.562755"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.218595"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220360> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220360> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Alec%27s+Restaurant%2C+Navestock> a ont:Restaurante ;
	ont:tieneNombre "Alec's Restaurant, Navestock" ;
	ont:tieneDireccion-Restaurante "London" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.5824142492"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.228002514844"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220361> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220361> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Yates+Bar+Romford> a ont:Restaurante ;
	ont:tieneNombre "Yates Bar Romford" ;
	ont:tieneDireccion-Restaurante "London" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.5788802331"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.236869731896"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220362> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220362> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Sorrento+Ristorante+Italiano> a ont:Restaurante ;
	ont:tieneNombre "Sorrento Ristorante Italiano" ;
	ont:tieneDireccion-Restaurante "13 Station Road, Upminster, RM14 2SJ, United Kingdom" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.5568113034"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.249455140475"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220363> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220363> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Bar+3+Zero> a ont:Restaurante ;
	ont:tieneNombre "Bar 3 Zero" ;
	ont:tieneDireccion-Restaurante "30 Corbets Tey Road, Upminster, RM14 2AD, United Kingdom" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.553579813309"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.24769021623283"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220365> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220365> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Thames+Chase+Cafe> a ont:Restaurante ;
	ont:tieneNombre "Thames Chase Cafe" ;
	ont:tieneDireccion-Restaurante "Thames Chase, The Forest Centre , Upminster, RM14 3NS, United Kingdom" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.55"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "0.25"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=220366> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=220366> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Bayleaf%2C+Whetstone> a ont:Restaurante ;
	ont:tieneNombre "Bayleaf, Whetstone" ;
	ont:tieneDireccion-Restaurante "1342 High Road, London, N20 9HJ, United Kingdom" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.6305123571"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "-0.175011351496"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=219579> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=219579> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Zefi> a ont:Restaurante ;
	ont:tieneNombre "Zefi" ;
	ont:tieneDireccion-Restaurante "Walton Street London, , London, SW3 2HP, UK , United Kingdom" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.494065102303"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "-0.1677936581264"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=219587> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=219587> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Mantoos+Afghan+Restaurant> a ont:Restaurante ;
	ont:tieneNombre "Mantoos Afghan Restaurant" ;
	ont:tieneDireccion-Restaurante "59 Battersea Bridge Road, London, SW11 3AU, United Kingdom" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.4782376666"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "-0.16983282469"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=219589> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=219589> .

<http://www.semanticweb.org/webSemantica/grupo02/resource/Restaurante#Casuarina+Tree+Restaurant+%26+Bar> a ont:Restaurante ;
	ont:tieneNombre "Casuarina Tree Restaurant & Bar" ;
	ont:tieneDireccion-Restaurante "407, London Road, Mitcham, CR4 4BG, United Kingdom" ;
	ont:tieneCategoria "restaurant" ;
	ont:tieneLocalizacion "London" ;
	ont:tieneLatitud "51.398117606543"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneLongitud "-0.17241831595287"^^<http://www.w3.org/2001/XMLSchema#double> ;
	ont:tieneDetalles <http://tour-pedia.org/api/getPlaceDetails?id=219594> ;
	ont:tieneCritica <http://tour-pedia.org/api/getReviewsByPlaceId?placeId=219594> .

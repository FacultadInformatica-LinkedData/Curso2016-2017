//
var opts = {
  crossDomain: false
}

$.ajax('ejemplo.json', opts).done(function(data) {
  console.log(data)

  // Nombre de barrio y distrito
  $('.js-barrio-nombre').text(data.nombre)
  $('.js-distrito-nombre').text(data.distrito)

  // Scores
  $('.js-barrio-puntuacion').text(data.puntuaciones.barrio)
  $('.js-distrito-puntuacion').text(data.puntuaciones.distrito)
  $('.js-madrid-puntuacion').text(data.puntuaciones.madrid)

  $('.js-barrio-hab').text(data.poblacion.barrio.toLocaleString('es-ES') + ' habitantes')
  $('.js-distrito-hab').text(data.poblacion.distrito.toLocaleString('es-ES') + ' habitantes')
  $('.js-madrid-hab').text(data.poblacion.madrid.toLocaleString('es-ES') + ' habitantes')

  $('.js-barrio-avisos').text(data.avisos.barrio.toLocaleString('es-ES'))
  $('.js-distrito-avisos').text(data.avisos.distrito.toLocaleString('es-ES'))
  $('.js-madrid-avisos').text(data.avisos.madrid.toLocaleString('es-ES'))

  var densidad = {
    barrio: data.avisos.barrio / data.poblacion.barrio * 1000,
    distrito: data.avisos.distrito / data.poblacion.distrito * 1000,
    madrid: data.avisos.madrid / data.poblacion.madrid * 1000
  }

  var localeOpts = {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }

  $('.js-barrio-densidad').text(densidad.barrio.toLocaleString('es-ES', localeOpts))
  $('.js-distrito-densidad').text(densidad.distrito.toLocaleString('es-ES', localeOpts))
  $('.js-madrid-densidad').text(densidad.madrid.toLocaleString('es-ES', localeOpts))

  console.log(data.imagen.distrito)

  $('.js-imagen').css('background-image', 'url(' + data.imagen.distrito + ')')
})

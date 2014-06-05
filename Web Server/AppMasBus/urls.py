from django.conf.urls import patterns, url

from AppMasBus import views

urlpatterns = patterns('',

	
	url(r'^$', views.index, name='index'),
	url(r'^buscarViaje/$', views.buscarViaje, name='buscarViaje'),
	url(r'^resultadoBusquedaViaje/$',views.resultadoBusquedaViaje, name ='resultadoBusquedaViaje'),

	url(r'^buscaEmpresa/$',views.buscaEmpresa, name ='buscaEmpresa'),
	url(r'^resultadoBusquedaEmpresa/$',views.resultadoBusquedaEmpresa, name ='resultadoBusquedaEmpresa'),

	url(r'^calificarEmpresa/$',views.calificarEmpresa, name ='calificarEmpresa'),
	url(r'^resultadoCalificacionEmpresa/$',views.resultadoCalificacionEmpresa, name ='resultadoCalificacionEmpresa'),
	url(r'^finCalificacion/$',views.finCalificacion, name ='finCalificacion'),

	url(r'^JSON/empresas/$', views.JSON_empresas, name = 'JSON_empresas'),
	url(r'^JSON/ciudades/$', views.JSON_ciudades, name = 'JSON_ciudades'),
	url(r'^JSON/empresas/(?P<idEmpresa>\d+)/$', views.JSON_datosEmpresa, name = 'JSON_datosEmpresa'),
	url(r'^JSON/calificar-(?P<idEmpresa>\d+)-(?P<valor>\d+)/$', views.JSON_calificarEmpresa, name = 'JSON_calificarEmpresa'),
	url(r'^JSON/pasajes-(?P<idOrigen>\d+)-(?P<idDestino>\d+)/$', views.JSON_pasajes, name = 'JSON_pasajes'),

)

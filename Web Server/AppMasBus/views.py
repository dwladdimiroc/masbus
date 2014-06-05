# -*- coding: utf-8 -*-

# Create your views here.

from django.shortcuts import render
from django.http import HttpResponse
from decimal import *
from django.utils import timezone
from django.db import models
import datetime
from datetime import datetime, time, date, timedelta
from django.core import serializers

from AppMasBus.models import Viaje, Empresa, Ciudad, Convenio, Infraccion, Pasaje, EmpresaCompleta

def index(request):
	return render(request, 'AppMasBus/index.html')

def buscarViaje(request):
	ciudades_list = Ciudad.objects.all()
	context = {'ciudades_list': ciudades_list}
	return render(request, 'AppMasBus/buscarViaje.html', context)

def resultadoBusquedaViaje(request):
	origen = Ciudad.objects.get(id = request.POST['origen'])
	destino = Ciudad.objects.get(id = request.POST['destino'])
	fechaComienzo = datetime.today()
	fechaTermino = fechaComienzo + timedelta(days=7)
	viajes_list=Viaje.objects.filter(origen_id=origen.id,destino_id=destino.id,fechaSalida__range=[fechaComienzo,fechaTermino]).order_by('fechaSalida')
	contexto = {'viajes_list': viajes_list, 'origen': origen, 'destino': destino}
	return render(request, 'AppMasBus/resultadoBusquedaViaje.html', contexto)

def buscaEmpresa(request):
	lista_empresas = Empresa.objects.all()
	contexto = {'lista_empresas': lista_empresas}
	return render(request, 'AppMasBus/buscaEmpresa.html', contexto)

def resultadoBusquedaEmpresa(request):

	empresa = Empresa.objects.get(id = request.POST['empresa'])
	listaConvenios = Convenio.objects.filter(empresa_id = empresa.id)
	listaInfracciones = Infraccion.objects.filter(empresa_id = empresa.id)
	promedioMostrar = "{:.1f}".format(empresa.promedioCalificaciones)

	contexto = {'empresa': empresa,
				'promedioCalificacion':promedioMostrar,
				'listaConvenios':listaConvenios,
				'listaInfracciones':listaInfracciones,
				'cantidadVotos':empresa.cantidadCalificaciones}
	return render(request, 'AppMasBus/resultadoBusquedaEmpresa.html', contexto)


def calificarEmpresa(request):
	lista_empresas = Empresa.objects.all()
	contexto = {'lista_empresas': lista_empresas}
	return render(request, 'AppMasBus/calificarEmpresa.html', contexto)


def resultadoCalificacionEmpresa(request):
	empresa = Empresa.objects.get(id = request.POST['empresa'])

	promedioMostrar="{:.1f}".format(empresa.promedioCalificaciones)

	contexto = {'empresa': empresa,
				'promedioCalificacion':promedioMostrar,
				'cantidadVotos':empresa.cantidadCalificaciones}
	return render(request, 'AppMasBus/resultadoCalificacionEmpresa.html', contexto)

def finCalificacion(request):

	votacion = int(request.POST['opcion'])
	idEmpresa = request.POST['empresa']

	empresa = Empresa.objects.get(id = idEmpresa)

	promedio = empresa.promedioCalificaciones * empresa.cantidadCalificaciones
	promedio = promedio + votacion

	empresa.cantidadCalificaciones+=1
	promedio= promedio/empresa.cantidadCalificaciones

	empresa.promedioCalificaciones = promedio
	
	promedioMostrar="{:.1f}".format(empresa.promedioCalificaciones)
	empresa.save()
	
	listaConvenios = Convenio.objects.filter(empresa_id = empresa.id)
	listaInfracciones = Infraccion.objects.filter(empresa_id = empresa.id)

	contexto = {'empresa': empresa,
				'promedioCalificacion': promedioMostrar,
				'listaConvenios':listaConvenios,
				'listaInfracciones':listaInfracciones,
				'cantidadVotos':empresa.cantidadCalificaciones}
	return render(request, 'AppMasBus/resultadoBusquedaEmpresa.html', contexto)

def JSON_empresas(request):

	queryset = Empresa.objects.all()
	data = serializers.serialize('json',queryset, fields = {'nombreEmpresa'})
	return HttpResponse(data, mimetype='application/json')

def JSON_ciudades(request):

	queryset = Ciudad.objects.all()
	data = serializers.serialize('json',queryset)
	return HttpResponse(data, mimetype='application/json')



def JSON_calificarEmpresa(request, idEmpresa, valor):

	empresa = Empresa.objects.get(id = idEmpresa)

	mensaje = 'Antes: calificación: ' + str(empresa.promedioCalificaciones) + ' (de ' + str(empresa.cantidadCalificaciones) + ' votos)\n'

	promedio = empresa.promedioCalificaciones * empresa.cantidadCalificaciones
	promedio += int(valor)
	empresa.cantidadCalificaciones += 1
	promedio = promedio / empresa.cantidadCalificaciones
	empresa.promedioCalificaciones = promedio
	
	promedioMostrar = "{:.2f}".format(empresa.promedioCalificaciones)
	empresa.save()

	mensaje += 'Después: calificación: ' + str(empresa.promedioCalificaciones) + ' (de ' + str(empresa.cantidadCalificaciones) + ' votos)\n'

	return HttpResponse(mensaje)
	
def JSON_datosEmpresa(request, idEmpresa):

	empresa = Empresa.objects.get(id = idEmpresa)
	listaConvenios = Convenio.objects.filter(empresa_id = idEmpresa)
	listaInfracciones = Infraccion.objects.filter(empresa_id = idEmpresa)
	cantidadConvenios = listaConvenios.count()
	cantidadInfracciones = listaInfracciones.count()
	stringConvenios = ''
	stringInfracciones = ''

	if cantidadConvenios > 0:
		
		for i in range(0, cantidadConvenios):
			stringConvenios += listaConvenios[i].descripcion

			if i != cantidadConvenios - 1:
				stringConvenios += '&'
			else:
				stringConvenios += '#'

	else:
		stringConvenios = '#'

	if cantidadInfracciones > 0:

		for i in range(0, cantidadInfracciones):

			stringInfracciones += listaInfracciones[i].infraccion

			if i != cantidadInfracciones - 1:
				stringInfracciones += '&'
			else:
				stringInfracciones += '#'

	else:
		stringInfracciones = '#'

	empresaCompleta = EmpresaCompleta()
	empresaCompleta.nombreEmpresa = empresa.nombreEmpresa
	empresaCompleta.tasaAccidentabilidad = empresa.tasaAccidentabilidad
	empresaCompleta.promedioCalificaciones = empresa.promedioCalificaciones
	empresaCompleta.cantidadCalificaciones = empresa.cantidadCalificaciones
	empresaCompleta.cantidadControles = empresa.cantidadControles
	empresaCompleta.porcentajeFlota = empresa.porcentajeFlota
	empresaCompleta.convenios = stringConvenios
	empresaCompleta.infracciones = stringInfracciones

	listaFinal = []
	listaFinal.append(empresaCompleta)

	datos = serializers.serialize('json', listaFinal)
	return HttpResponse(datos, mimetype = 'application/json')

def JSON_pasajes(request, idOrigen, idDestino):

	ciudadOrigen = Ciudad.objects.get(id = idOrigen)
	ciudadDestino = Ciudad.objects.get(id = idDestino)

	fechaComienzo = datetime.today()
	fechaTermino = fechaComienzo + timedelta(days = 7)
	listaViajes = Viaje.objects.filter(origen_id = idOrigen, destino_id = idDestino, fechaSalida__range = [fechaComienzo,fechaTermino])
	cantidadViajes = listaViajes.count()
	listaFinal = []

	for i in range(0, cantidadViajes):

		pasaje = Pasaje()
		empresa = Empresa.objects.get(id = listaViajes[i].empresa.id)
		
		pasaje.empresaRuta = empresa.nombreEmpresa
		pasaje.tiempoViaje = listaViajes[i].tiempoEstimadoMinutos
		pasaje.calificacionEmpresa = empresa.promedioCalificaciones
		pasaje.precio = listaViajes[i].precio

		listaFinal.append(pasaje)

	datos = serializers.serialize('json', listaFinal)
	return HttpResponse(datos, mimetype = 'application/json')
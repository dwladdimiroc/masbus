from django.db import models
import datetime
from django.utils import timezone
from django.core.validators import MinValueValidator, MaxValueValidator

# Modelos:

class Empresa(models.Model):

	nombreEmpresa = models.CharField(max_length = 50, unique = True)
	tasaAccidentabilidad = models.IntegerField(default = 0)
	promedioCalificaciones = models.DecimalField(max_digits=5, decimal_places=3, default = 3, validators = [MinValueValidator(1), MaxValueValidator(5)])
	cantidadCalificaciones = models.IntegerField(default = 0)
	cantidadControles = models.IntegerField(default = 0)
	porcentajeFlota = models.IntegerField(default = 100)

	def __unicode__(self):
		return self.nombreEmpresa

class Ciudad(models.Model):

	nombreCiudad = models.CharField(max_length = 50, unique = True)

	def __unicode__(self):
		return self.nombreCiudad

class Convenio(models.Model):

	empresa = models.ForeignKey(Empresa)
	descripcion = models.CharField(max_length = 1000)

	def __unicode__(self):
		return '[ ID: ' + str(self.id) + ' - ' + self.empresa.nombreEmpresa + ']'

class Infraccion(models.Model):

	empresa = models.ForeignKey(Empresa)
	infraccion = models.CharField(max_length = 1000)

	def __unicode__(self):
		return '[ ID: ' + str(self.id) + ' - ' + self.empresa.nombreEmpresa + ']'

class Viaje(models.Model):

	empresa = models.ForeignKey(Empresa)
	origen = models.ForeignKey(Ciudad, related_name = 'Ciudad origen')
	destino = models.ForeignKey(Ciudad, related_name = 'Ciudad destino')

	fechaSalida = models.DateTimeField('Hora de salida')
	precio = models.IntegerField(default = 1000)
	tiempoEstimadoMinutos = models.IntegerField(default = 60)

	def __unicode__(self):
		return '[ ID: ' + str(self.id) + ' - ' + self.empresa.nombreEmpresa + ' - ' + self.origen.nombreCiudad + ' / ' + self.destino.nombreCiudad + ')'

# Modelos de estructuras:

class Pasaje(models.Model):

	empresaRuta = models.CharField(max_length = 50, unique = True)
	tiempoViaje = models.IntegerField(default = 60)
	calificacionEmpresa = models.DecimalField(max_digits=5, decimal_places=3, default = 3, validators = [MinValueValidator(1), MaxValueValidator(5)])
	precio = models.IntegerField(default = 1000)

	def __unicode__(self):
		return self.empresaRuta

class EmpresaCompleta(models.Model):

	nombreEmpresa = models.CharField(max_length = 50, unique = True)
	tasaAccidentabilidad = models.IntegerField(default = 0)
	promedioCalificaciones = models.DecimalField(max_digits=5, decimal_places=3, default = 3, validators = [MinValueValidator(1), MaxValueValidator(5)])
	cantidadCalificaciones = models.IntegerField(default = 0)
	cantidadControles = models.IntegerField(default = 0)
	porcentajeFlota = models.IntegerField(default = 100)
	convenios = models.CharField(max_length = 10000)
	infracciones = models.CharField(max_length = 10000)

	def __unicode__(self):
		return self.nombreEmpresa


from django.contrib import admin
from AppMasBus.models import Viaje, Empresa, Ciudad, Convenio, Infraccion
 
admin.site.register(Viaje)
admin.site.register(Ciudad)
admin.site.register(Empresa)
admin.site.register(Convenio)
admin.site.register(Infraccion)

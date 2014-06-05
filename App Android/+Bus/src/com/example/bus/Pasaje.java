package com.example.bus;

public class Pasaje {
	String origenCiudad;
	String destinoCiudad;
	String empresaRuta;
	String tiempoViaje;
	String calificacionEmpresa;
	String precio;

	public Pasaje() {
			this.origenCiudad = null;
			this.destinoCiudad = null;
			this.empresaRuta = null;
			this.tiempoViaje = null;
			this.calificacionEmpresa = null;
			this.precio = null;
	}
	
	public Pasaje(String origenCiudad, String destinoCiudad,
			String empresaRuta, String tiempoViaje, String calificacionEmpresa,
			String precio) {
			this.origenCiudad = "Ciudad origen: ".concat(origenCiudad);
			this.destinoCiudad = "Ciudad destino: ".concat(destinoCiudad);
			this.empresaRuta = "Empresa: ".concat(empresaRuta);
			this.tiempoViaje = "Tiempo viaje: ".concat(tiempoViaje);
			this.calificacionEmpresa = "Calificación: ".concat(calificacionEmpresa);
			this.precio = "Precio: ".concat(precio);
	}

	public String getOrigenCiudad() {
		return origenCiudad;
	}

	public void setOrigenCiudad(String origenCiudad) {
		this.origenCiudad = origenCiudad;
	}

	public String getDestinoCiudad() {
		return destinoCiudad;
	}

	public void setDestinoCiudad(String destinoCiudad) {
		this.destinoCiudad = destinoCiudad;
	}

	public String getEmpresaRuta() {
		return empresaRuta;
	}

	public void setEmpresaRuta(String empresaRuta) {
		this.empresaRuta = empresaRuta;
	}

	public String getTiempoViaje() {
		return tiempoViaje;
	}

	public void setTiempoViaje(String tiempoViaje) {
		this.tiempoViaje = tiempoViaje;
	}

	public String getCalificacionEmpresa() {
		return calificacionEmpresa;
	}

	public void setCalificacionEmpresa(String calificacionEmpresa) {
		this.calificacionEmpresa = calificacionEmpresa;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

}

package com.example.bus;

public class Empresa {
	String accidentabilidad;
	String infracciones;
	String cantidadControles;
	String porcentajeFlota;
	String cantidadCalificaciones;
	String calificacion;
	String nombreEmpresa;

	public Empresa() {
		this.accidentabilidad = null;
		this.infracciones = null;
		this.cantidadControles = null;
		this.porcentajeFlota = null;
		this.cantidadCalificaciones = null;
		this.calificacion = null;
		this.nombreEmpresa = null;
	}

	public Empresa(String accidentabilidad, String infracciones,
			String cantidadControles, String porcentajeFlota,
			String cantidadCalificaciones, String calificacion,
			String nombreEmpresa) {
		this.accidentabilidad = "Accidentabilidad: ".concat(accidentabilidad);
		this.infracciones = "Infracciones: ".concat(infracciones);
		this.cantidadControles = "Cantidad controles: "
				.concat(cantidadControles);
		this.porcentajeFlota = "Porcnetaje flota: ".concat(porcentajeFlota);
		this.cantidadCalificaciones = "Cantidad calificaciones: "
				.concat(cantidadCalificaciones);
		this.calificacion = "Calificacion".concat(calificacion);
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getAccidentabilidad() {
		return accidentabilidad;
	}

	public void setAccidentabilidad(String accidentabilidad) {
		this.accidentabilidad = accidentabilidad;
	}

	public String getInfracciones() {
		return infracciones;
	}

	public void setInfracciones(String infracciones) {
		this.infracciones = infracciones;
	}

	public String getCantidadControles() {
		return cantidadControles;
	}

	public void setCantidadControles(String cantidadControles) {
		this.cantidadControles = cantidadControles;
	}

	public String getPorcentajeFlota() {
		return porcentajeFlota;
	}

	public void setPorcentajeFlota(String porcentajeFlota) {
		this.porcentajeFlota = porcentajeFlota;
	}

	public String getCantidadCalificaciones() {
		return cantidadCalificaciones;
	}

	public void setCantidadCalificaciones(String cantidadCalificaciones) {
		this.cantidadCalificaciones = cantidadCalificaciones;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
}

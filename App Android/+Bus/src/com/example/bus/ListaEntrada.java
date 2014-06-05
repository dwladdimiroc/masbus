package com.example.bus;

public class ListaEntrada {
	private int logoEmpresa;
	private String nombreEmpresa; // titulo
	private String calificacion; // descripcion
	private String tiempoTrayectoria;
	private String precio;

	public ListaEntrada(int logoEmpresa, String nombreEmpresa,
			String calificacion, String tiempoTrayectoria, String precio) {
		this.logoEmpresa = logoEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.calificacion = calificacion;
		this.tiempoTrayectoria = tiempoTrayectoria;
		this.precio = precio;
	}

	public int getLogoEmpresa() {
		return logoEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public String getTiempoTrayectoria() {
		return tiempoTrayectoria;
	}

	public String getPrecio() {
		return precio;
	}

}

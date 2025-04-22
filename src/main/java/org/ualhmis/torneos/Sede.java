package org.ualhmis.torneos;

import java.util.List;
import java.util.ArrayList;

public class Sede {
	private List<Instalacion> instalaciones;
	private String nombreSede;
	
	public List<Instalacion> getInstalaciones() {
		return instalaciones;
	}

	public void setInstalaciones(List<Instalacion> instalaciones) {
		this.instalaciones = instalaciones;
	}

	public String getNombreSede() {
		return nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public Sede(String nombreSede) {
		this.nombreSede = nombreSede;
		this.instalaciones = new ArrayList<>();
	}
	
	public void agregarInstalacion(Instalacion i) {
		this.instalaciones.add(i);
	}
}

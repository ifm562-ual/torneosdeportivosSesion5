package org.ualhmis.torneos;

import java.util.ArrayList;
import java.util.List;

public class Club {
	private List<Equipo> equipos;
	private String nombreClub;
	
	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public String getNombreClub() {
		return nombreClub;
	}

	public void setNombreClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}

	public Club(String nombreClub) throws IllegalArgumentException {
		
		if(nombreClub == null || nombreClub.trim().toLowerCase().isEmpty())
		{
			throw new IllegalArgumentException("El nombre no puede estar vacío.");
		}
		
		this.equipos = new ArrayList<>();
		this.nombreClub = nombreClub;
	}
	
	public void agregarEquipo(Equipo eq) {
		this.equipos.add(eq);
	}
	
}

package org.ualhmis.torneos;

import java.util.ArrayList;
import java.util.List;

class GestorTorneos {
    private List<Torneo> torneos;
    private List<Partido> partidosGestionados;
    private List<Equipo> equiposGestionados;

    public GestorTorneos() {
        this.torneos = new ArrayList<>();
        this.partidosGestionados = new ArrayList<>();
        this.equiposGestionados = new ArrayList<>();
    }

    public List<Torneo> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<Torneo> torneos) {
		this.torneos = torneos;
	}

	public List<Partido> getPartidosGestionados() {
		return partidosGestionados;
	}

	public void setPartidosGestionados(List<Partido> partidosGestionados) {
		this.partidosGestionados = partidosGestionados;
	}

	public List<Equipo> getEquiposGestionados() {
		return equiposGestionados;
	}

	public void setEquiposGestionados(List<Equipo> equiposGestionados) {
		this.equiposGestionados = equiposGestionados;
	}

	public Torneo crearTorneo(String nombre, String deporte, String categoria, String modalidad, String tipo, String nombreSede) {
        Torneo t;
        Sede s = new Sede(nombreSede);
        s.agregarInstalacion(new Instalacion("Pabellon"));
        s.agregarInstalacion(new Instalacion("Pista"));
        s.agregarInstalacion(new Instalacion("Campo"));
    	this.torneos.add(t = new Torneo(nombre, deporte, categoria, modalidad, tipo, s));
        return t;
    }
    
    public void crearEquipo(String nombre, String categoria, String modalidad, Entrenador entrenador) {
    	this.equiposGestionados.add(new Equipo(nombre, categoria, modalidad, entrenador, new Club("megaClub")));
    }
    
    public void crearPartido(Equipo eq1, Equipo eq2, String nombreT, String deporteT, String categT, String modalidadT, String tipoT, String nombreSede) {
    	this.partidosGestionados.add(new Partido(eq1, eq2, crearTorneo(nombreT, deporteT, categT, modalidadT, tipoT, nombreSede)));
    	
    }
}
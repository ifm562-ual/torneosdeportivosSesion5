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

    public void crearTorneo(String nombre, String deporte, String categoria, String modalidad, String tipo) {
        this.torneos.add(new Torneo(nombre, deporte, categoria, modalidad, tipo));
    }
    
    public void crearEquipo(String nombre, String categoria, String modalidad, Entrenador entrenador) {
    	this.equiposGestionados.add(new Equipo(nombre, categoria, modalidad, entrenador));
    }
    
    public void crearPartido(Equipo eq1, Equipo eq2) {
    	this.partidosGestionados.add(new Partido(eq1, eq2));
    }
}
package org.ualhmis.torneos;

import java.util.ArrayList;
import java.util.List;

class Torneo {
    public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	private String nombre;
    private String deporte;
    private String categoria;
    private String modalidad;
    private List<Equipo> equipos;
    private Sede sede;
    
	private String tipo;

    public Torneo(String nombre, String deporte, String categoria, String modalidad, String tipo, Sede sede) {
        this.nombre = nombre;
        this.sede = sede;
        
        if(!tipo.trim().toLowerCase().equals("liga") && !tipo.trim().toLowerCase().equals("copa"))
        {
        	throw new IllegalArgumentException("El torneo tiene que ser liga o copa.");
        }
        
        boolean encontradaAdecuada = false;
        switch(deporte.trim().toLowerCase()) 
        {
        	case "baloncesto":
        		
        		for(Instalacion i : sede.getInstalaciones())
        		{
        			if(i.getTipo().toLowerCase().trim().equals("pista")) { encontradaAdecuada = true; }
        		}
        		
        		break;
        	case "voleibol":
        		for(Instalacion i : sede.getInstalaciones())
        		{
        			if(i.getTipo().toLowerCase().trim().equals("pista") || i.getTipo().toLowerCase().trim().equals("pabellon")) 
        			{ encontradaAdecuada = true; }
        		}
        		break;
        	case "futbol":
        		encontradaAdecuada = true;
        		break;
        	case "fútbol":
        		encontradaAdecuada = true;
        		break;
        	default:
        		break;
        }
        
        if(!encontradaAdecuada) { throw new IllegalArgumentException("La sede necesita una instalación adecuada para el deporte."); }
        
        this.deporte = deporte;
        this.categoria = categoria;
        this.modalidad = modalidad;
        this.tipo = tipo;
        this.equipos = new ArrayList<>();

    }

    public void registrarEquipo(Equipo equipo) {
    	
        if (!equipo.getCategoria().equals(this.categoria) || !equipo.getModalidad().equals(this.modalidad)) {
            throw new IllegalArgumentException("El equipo no cumple con la categoría y modalidad del torneo");
        }
        if (!equipos.contains(equipo)) {
            equipos.add(equipo);   
        }
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    
}

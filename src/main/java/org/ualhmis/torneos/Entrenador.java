package org.ualhmis.torneos;

import java.time.LocalDate;

class Entrenador extends Persona {
	private boolean esPrincipal;
	
    public void setEsPrincipal(boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}

	public boolean getEsPrincipal() {
		return esPrincipal;
	}

	public Entrenador(String nombre, String genero, LocalDate fechaNacimiento, boolean esPrincipal) {
        super(nombre, genero, fechaNacimiento);
        this.esPrincipal = esPrincipal;
    }
}
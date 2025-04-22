package org.ualhmis.torneos;

public class Instalacion {
	private String tipo;
	public boolean estaOcupadaInst() {
		return estaOcupada;
	}

	public void setEstaOcupada(boolean estaOcupada) {
		this.estaOcupada = estaOcupada;
	}

	private boolean estaOcupada = false;
	
	public Instalacion(String tipo) throws IllegalArgumentException {
		
		String tipoL = tipo.trim().toLowerCase();
		if(!tipoL.equals("campo") || !tipoL.equals("pista") || !tipoL.equals("pabellon"))
		{
			throw new IllegalArgumentException("Debe de ser una pista, pabellón o campo.");
		}
		
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

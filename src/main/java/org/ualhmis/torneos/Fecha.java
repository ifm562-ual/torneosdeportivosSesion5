package org.ualhmis.torneos;

public class Fecha {
	private int diaNac;
	public int getDiaNac() { return diaNac; }
	public void setDiaNac(int diaNac) { this.diaNac = diaNac; }
	
	private int mesNac;
	public int getMesNac() { return mesNac; }
	public void setMesNac(int mesNac) { this.mesNac = mesNac; }
	
	private int anioNac;
	public int getAnioNac() { return anioNac; }
	public void setAnioNac(int anioNac) { this.anioNac = anioNac; }
	
	public boolean esBisiesto(int anioNac) { return anioNac % 4 == 0; }
	
	public Fecha(int diaNac, int mesNac, int anioNac) {
		if(anioNac<1900 || anioNac>3000) { throw new IllegalArgumentException("La fecha tiene que estar entre 1901 y 2999, ambos inclusive"); }
		if(mesNac <= 0 || mesNac >= 12) { throw new IllegalArgumentException("Solo se permiten meses entre 1 y 12, ambos inclusive."); }
		else
		{
			if(!esBisiesto(anioNac) && mesNac == 2 && diaNac == 29) 
			{
				throw new IllegalArgumentException("El año no es bisiesto, por lo que no se pudo nacer el 29 de febrero.");
			}
		}
	}
	
	public String toString() { return diaNac + "/" + mesNac + "/" + anioNac; }
}

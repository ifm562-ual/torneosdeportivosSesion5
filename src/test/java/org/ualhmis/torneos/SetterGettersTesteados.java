package org.ualhmis.torneos;

import java.awt.List;
import java.lang.reflect.Method;

public class SetterGettersTesteados {

    void testSetters
    (
    		String nombreClase,
    		String nombreMetodo, String tipoAtributo, String valor, boolean esGenerico, String tipoGenerico
    ) 
    {
    	Class<?> claseStr =
    							switch(nombreClase)
    							{
    							case "Club" -> Club.class;
    							case "Entrenador" -> Entrenador.class;
    							case "Equipo" -> Equipo.class;
    							case "GestorTorneos" -> GestorTorneos.class;
    							case "Jugador" -> Jugador.class;
    							case "Partido" -> Partido.class;
    							case "Persona" -> Persona.class;
    							case "Torneo" -> Torneo.class;
    							default -> throw new IllegalArgumentException("Clase no encontrada.");
    							};
    	
		Class<?> tipo = 
						switch(tipoAtributo)
						{
							case "String" -> int.class;
							case "GestorTorneos" -> GestorTorneos.class;
							case "List" -> List.class;
							default -> throw new IllegalArgumentException("Tipo no soportado por Torneo: " + tipoAtributo);
						};
		try {
			try {
				Object instancia = claseStr.getDeclaredConstructor().newInstance();
			}catch(Exception e) { };
			Method setter = claseStr.getMethod(nombreMetodo, tipo);
		}catch(NoSuchMethodException | SecurityException e) { System.err.println(e); }
    }
}

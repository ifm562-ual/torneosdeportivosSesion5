package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

// Registro de equipos en torneos con validación de categoría y modalidad

class PartidoTest {

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz'"
			}
	)
    void testRegistrarResultado
    (
    	String nombreEquipo, String categoriaEquipo, String modalidadEquipo, 
    		String nombreEntrenador, String generoEntrenador, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreClub,
    		
    	String nombreEquipo2, String categoriaEquipo2, String modalidadEquipo2, 
    	    String nombreEntrenador2, String generoEntrenador2, LocalDate fechaNacEnt2, boolean esPrincipal2,
    	    String nombreClub2,   	
    		
    	String nombreT, String deporteT, String categoriaT, String modalidadT, String tipoT, 
    	String nombreSede
    ) 
	{
        Entrenador entrenador = new Entrenador(nombreEntrenador, generoEntrenador, fechaNacEnt, esPrincipal);
        Entrenador entrenador2 = new Entrenador(nombreEntrenador2, generoEntrenador2, fechaNacEnt2, esPrincipal2);

        Equipo equipo1 = new Equipo(nombreEquipo, categoriaEquipo, modalidadEquipo, entrenador, new Club(nombreClub));
        Equipo equipo2 = new Equipo(nombreEquipo2, categoriaEquipo2, modalidadEquipo2, entrenador2, new Club(nombreClub2));
        
        Sede s = new Sede(nombreSede);
        s.agregarInstalacion(new Instalacion("pabellon"));
        s.agregarInstalacion(new Instalacion("campo"));
        s.agregarInstalacion(new Instalacion("pista"));
        
        Partido partido = new Partido(equipo1, equipo2, new Torneo(nombreT, deporteT, categoriaT, modalidadT, tipoT, s));
        partido.registrarResultado(2, 1);

        assertEquals(2, partido.getGolesEquipo1());
        assertEquals(1, partido.getGolesEquipo2());
    }
}

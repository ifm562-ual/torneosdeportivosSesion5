package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Registro de equipos en torneos con validación de categoría y modalidad

class PartidoTest extends TesteoSettersGetters<Partido> {

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

        assertEquals(2, partido.getPuntuacionEquipo1());
        assertEquals(1, partido.getPuntuacionEquipo2());
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz'"
			}
	)
    void testAsignarInstalacionNoOcupada
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
        partido.asignarInstalacion("pabellon");
        assertTrue(partido.getInstalacionDondeSeJuega().getEstaOcupada() == true);
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'PapasFritas', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz'"
			}
	)
    void testAsignarInstalacioOcupada
    (
    	String nombreEquipo, String categoriaEquipo, String modalidadEquipo, 
    		String nombreEntrenador, String generoEntrenador, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreClub,
    		
    	String nombreEquipo2, String categoriaEquipo2, String modalidadEquipo2, 
    	    String nombreEntrenador2, String generoEntrenador2, LocalDate fechaNacEnt2, boolean esPrincipal2,
    	    String nombreClub2,
    	    
    	String nombreEquipo3, String categoriaEquipo3, String modalidadEquipo3, 
    	    String nombreEntrenador3, String generoEntrenador3, LocalDate fechaNacEnt3, boolean esPrincipal3,
    	    String nombreClub3,
    		
    	String nombreT, String deporteT, String categoriaT, String modalidadT, String tipoT, 
    	String nombreSede
    ) 
	{
        Entrenador entrenador = new Entrenador(nombreEntrenador, generoEntrenador, fechaNacEnt, esPrincipal);
        Entrenador entrenador2 = new Entrenador(nombreEntrenador2, generoEntrenador2, fechaNacEnt2, esPrincipal2);
        Entrenador entrenador3 = new Entrenador(nombreEntrenador3, generoEntrenador3, fechaNacEnt3, esPrincipal3);

        Equipo equipo1 = new Equipo(nombreEquipo, categoriaEquipo, modalidadEquipo, entrenador, new Club(nombreClub));
        Equipo equipo2 = new Equipo(nombreEquipo2, categoriaEquipo2, modalidadEquipo2, entrenador2, new Club(nombreClub2));
        Equipo equipo3 = new Equipo(nombreEquipo3, categoriaEquipo3, modalidadEquipo3, entrenador3, new Club(nombreClub3));
        
        Sede s = new Sede(nombreSede);
        s.agregarInstalacion(new Instalacion("pabellon"));
        s.agregarInstalacion(new Instalacion("campo"));
        s.agregarInstalacion(new Instalacion("pista"));
        Torneo t;
        Partido partido = new Partido(equipo1, equipo2, t = new Torneo(nombreT, deporteT, categoriaT, modalidadT, tipoT, s));
        partido.asignarInstalacion("pabellon");
        assertThrows(IllegalArgumentException.class, () -> new Partido(equipo1, equipo3, t).asignarInstalacion("pabellon"));
        assertTrue(partido.getInstalacionDondeSeJuega().getEstaOcupada() == true);
    }
	
	@ParameterizedTest
	@CsvSource
	(		
			nullValues = "'null'",
			value =
			{
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz', 'pabellon'",
				
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz', 'pabellon'"
			}
	)
    void testLiberarInstalacionOcupada
    (
    	String nombreEquipo, String categoriaEquipo, String modalidadEquipo, 
    		String nombreEntrenador, String generoEntrenador, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreClub,
    		
    	String nombreEquipo2, String categoriaEquipo2, String modalidadEquipo2, 
    	    String nombreEntrenador2, String generoEntrenador2, LocalDate fechaNacEnt2, boolean esPrincipal2,
    	    String nombreClub2,   	
    		
    	String nombreT, String deporteT, String categoriaT, String modalidadT, String tipoT, 
    	String nombreSede,
    	
    	String tipoInstalacion
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
        partido.asignarInstalacion(tipoInstalacion);
        partido.liberarInstalacion(tipoInstalacion);
        assertTrue(partido.getInstalacionDondeSeJuega().getEstaOcupada() == false);
    }
	
	@ParameterizedTest
	@CsvSource
	(		
			nullValues = "'null'",
			value =
			{
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz', 'pabellon'",
				
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz', 'pabellon'",
				
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz', 'pista supersonica'"
			}
	)
    void testLiberarInstalacionNoOcupada
    (
    	String nombreEquipo, String categoriaEquipo, String modalidadEquipo, 
    		String nombreEntrenador, String generoEntrenador, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreClub,
    		
    	String nombreEquipo2, String categoriaEquipo2, String modalidadEquipo2, 
    	    String nombreEntrenador2, String generoEntrenador2, LocalDate fechaNacEnt2, boolean esPrincipal2,
    	    String nombreClub2,   	
    		
    	String nombreT, String deporteT, String categoriaT, String modalidadT, String tipoT, 
    	String nombreSede,
    	
    	String tipoInstalacion
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
        partido.liberarInstalacion(tipoInstalacion);
        assertNull(partido.getInstalacionDondeSeJuega());
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Tigres', 'Juvenil', 'Masculino', 'Carlos', 'Masculino', '1980-03-10', 'true', 'theLions',"
				+ "'Leones', 'Juvenil', 'Masculino', 'Ana', 'Femenino', '1985-06-20', 'true', 'theTigers',"
				+ "'FelinosCompetition', 'Futbol', 'Juvenil', 'Masculino', 'Liga', 'RocksKidz'"
			}
	)
	void testSettersGetters
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
        Sede s = new Sede(nombreSede);
        
        Entrenador entrenador = new Entrenador(nombreEntrenador, generoEntrenador, fechaNacEnt, esPrincipal);
        Entrenador entrenador2 = new Entrenador(nombreEntrenador2, generoEntrenador2, fechaNacEnt2, esPrincipal2);

        Equipo equipo1 = new Equipo(nombreEquipo, categoriaEquipo, modalidadEquipo, entrenador, new Club(nombreClub));
        Equipo equipo2 = new Equipo(nombreEquipo2, categoriaEquipo2, modalidadEquipo2, entrenador2, new Club(nombreClub2));
        
        Torneo t = new Torneo(nombreT, deporteT, categoriaT, modalidadT, tipoT, s);
        Torneo tSet = new Torneo("superTorneo", deporteT, categoriaT, modalidadT, tipoT, s);
        Partido p = new Partido(equipo1, equipo2, t);
        
        Equipo equipoSet = new Equipo("FernandezGarcia", "Absoluta", "Masculino", entrenador, new Club("EstoySolito:("));
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        valoresTest.put(Equipo.class, equipoSet);
        valoresTest.put(int.class, 0);
        valoresTest.put(Torneo.class, tSet);
        
        this.parteComunMetodoTesteo(p, valoresTest);
		
	}
}

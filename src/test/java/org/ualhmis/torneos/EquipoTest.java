package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// Creación de jugadores y cálculo automático de categoría

class EquipoTest extends TesteoSettersGetters<Equipo> {

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos','Masculino','1980-03-10','true',"
				+ "'Tigres', 'Junior', 'Masculino'," 
				+ "'Luis','Masculino', '2006-07-15', 'megaClub'"
			}
	)
    void testAgregarJugadorCorrectamente
    (
    		String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreEq, String categEq, String generoEq,
    		String nombreJug, String generoJug, LocalDate fechaNacJug, String nombreClub
    ) 
	{
        Entrenador entrenador = new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        Equipo equipo = new Equipo(nombreEq, categEq, generoEq, entrenador, new Club(nombreClub));
        
        Jugador jugador = new Jugador(nombreJug, generoJug, fechaNacJug); // Juvenil
        equipo.agregarJugador(jugador);

        assertEquals(1, equipo.getJugadores().size());
    }

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos','Masculino','1980-03-10','true',"
				+ "'Tigres', 'Juvenil', 'Masculino', " 
				+ "'Luis','Masculino', '2015-05-10', 'megaClub'"
			}
	)
    void testNoAgregarJugadorDeDiferenteCategoria
    (
    		String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreEq, String categEq, String generoEq,
    		String nombreJug, String generoJug, LocalDate fechaNacJug, String nombreClub
    ) 
	{
        Entrenador entrenador = new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        Equipo equipo = new Equipo(nombreEq, categEq, generoEq, entrenador, new Club(nombreClub));

        Jugador jugador = new Jugador(nombreJug, generoJug, fechaNacJug); // Infantil

        equipo.agregarJugador(jugador);

        assertEquals(0, equipo.getJugadores().size()); // No debe agregarse
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos','Masculino','1980-03-10','true', 'megaClub',"
				+ "'Tigres', 'Infantil', 'Masculino', " 
				+ "'Luis','Masculino', '2015-05-10'"
			}
	)
    void testJugadorYaExiste
    (
    		String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal, String nombreClub,
    		String nombreEq, String categEq, String generoEq,
    		String nombreJug, String generoJug, LocalDate fechaNacJug
    ) 
	{
        Entrenador entrenador = new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        Equipo equipo = new Equipo(nombreEq, categEq, generoEq, entrenador, new Club(nombreClub));

        Jugador jugador = new Jugador(nombreJug, generoJug, fechaNacJug);

        equipo.agregarJugador(jugador);
        equipo.agregarJugador(jugador);

        assertEquals(1, equipo.getJugadores().size()); // No debe agregarse
    }

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos','Masculino','1980-03-10', "
				+ "'Ana', 'Femenino', '1985-06-20', "
				+ "'Tigres', 'Juvenil', 'Masculino', 'nombreClub'" 
			}
	)
    void testAsignarSegundoEntrenador
    (
    		String nombreEnt1, String generoEnt1, LocalDate fechaNacEnt1,
    		String nombreEnt2, String generoEnt2, LocalDate fechaNacEnt2,
    		String nombreEq, String categEq, String modalidadEq, String nombreClub
    ) 
	{
        Entrenador entrenador1 = new Entrenador(nombreEnt1, generoEnt1, fechaNacEnt1, true);
        Entrenador entrenador2 = new Entrenador(nombreEnt2, generoEnt2, fechaNacEnt2, false);

        Equipo equipo = new Equipo(nombreEq, categEq, modalidadEq, entrenador1, new Club(nombreClub));
        equipo.asignarSegundoEntrenador(entrenador2);

        assertNotNull(equipo.getSegundoEntrenador());
        assertEquals("Ana", equipo.getSegundoEntrenador().getNombre());
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'ManILoveFrogsHunters', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'megaClub'"			
			}
	)
	void testSettersGetters
	(
			String nombre, String categoria, String modalidad,
			String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal, String nombreClub
	) 
	{
        Entrenador ent = new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        Equipo eq = new Equipo(nombre, categoria, modalidad, ent, new Club(nombreClub));
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        
        Jugador j;
        
        valoresTest.put(String.class, "setString");
        valoresTest.put(Entrenador.class, ent);
        valoresTest.put(List.class, new ArrayList<Jugador>(Arrays.asList( j = new Jugador("a", "a", LocalDate.of(2004, 2, 28)), j, j )));
        
        this.parteComunMetodoTesteo(eq, valoresTest);
		
	}
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"' ', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'megaClub'",
				"'null', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'megaClub'",
				"'HUnters', 'null', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'megaClub'",
				"'HUnters', 'null', 'Masculino', 'null', 'Masculino', '2006-11-12', 'true', 'megaClub'",
				"'Hunters', 'JaviTeniaRazon', 'null', 'null', 'Masculino', '2006-11-12', 'true', 'megaClub'",
				"'HUnters', 'null', 'null', 'null', 'Masculino', '2006-11-12', 'true', 'megaClub'"
			}
	)
	void testEquipoIncorrecto
	(
			String nombre, String categoria, String modalidad,
			String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal, String nombreClub
	) 
	{
		String nombreEf = nombre.trim().toLowerCase().equals("null") ? null : nombre;
		String categoriaEf = categoria.trim().toLowerCase().equals("null") ? null : categoria;
		String modalidadEf = modalidad.trim().toLowerCase().equals("null") ? null : modalidad;
		Entrenador ent = nombreEnt.trim().toLowerCase().equals("null") ? null : new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        assertThrows(IllegalArgumentException.class, () -> new Equipo(nombreEf, categoriaEf, modalidadEf, ent, new Club(nombreClub)));
		
	}
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'megaClub'"
			}
	)
	void testToString
	(
			String nombre, String categoria, String modalidad,
			String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal, String nombreClub
	) 
	{
		Equipo eq = new Equipo(nombre, categoria, modalidad, new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal), new Club(nombreClub));
		assertEquals
		(
				eq.toString(),
				"Equipo [nombre=EquipoCinco, categoria=Junior, modalidad=Masculino, entrenador=Entrenador Pepe, segundoEntrenador=null, jugadores=[]]"
	    );
		
	}
	
	@ParameterizedTest
	@CsvSource
	(
			nullValues = "null",
			value =
			{
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'false', 'true', 'megaClub'",
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'true', 'false', 'megaClub'",
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'false', 'false', 'megaClub'",
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'EquipoCinco', 'Juvenil', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'false', 'false', 'megaClub'",
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'EquipoCinco', 'Junior', 'Femenino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'false', 'false', 'megaClub'",
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'EquipoSeis', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'false', 'false', 'megaClub'",
			}
	)
	void testEquals
	(
			String nombreEq1, String caEtegoriaEq1, String modalidadEq1,
			String nombreEntEq1, String generoEntEq1, LocalDate fechaNacEntEq1, boolean esPrincipalEq1,
			
			String nombreEq2, String caEtegoriaEq2, String modalidadEq2,
			String nombreEntEq2, String generoEntEq2, LocalDate fechaNacEntEq2, boolean esPrincipalEq2,
			
			boolean seQuiereCompararMismaRef, boolean seQuiereCompararDistObj, String nombreClub
	) 
	{
		
		Equipo eq1 = new Equipo(nombreEq1, caEtegoriaEq1, modalidadEq1, new Entrenador(nombreEntEq1, generoEntEq1, fechaNacEntEq1, esPrincipalEq1), new Club(nombreClub));
		Equipo eq2 = new Equipo(nombreEq2, caEtegoriaEq2, modalidadEq2, new Entrenador(nombreEntEq2, generoEntEq2, fechaNacEntEq2, esPrincipalEq2), new Club(nombreClub));
		
		if(seQuiereCompararDistObj) { assertFalse(eq1.equals(new ArrayList())); assertFalse(eq1.equals(null)); }
		else
		{
			if(seQuiereCompararMismaRef) { assertTrue(eq1.equals(eq1)); }
			else
			{
				assertDoesNotThrow( () -> eq1.equals(eq2) );
			}
		}
		
	}
	
}

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
				"'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres', 'Junior', 'Masculino', " 
				+ "'Luis','Masculino', '2006-07-15'"
			}
	)
    void testAgregarJugadorCorrectamente
    (
    		String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreEq, String categEq, String generoEq,
    		String nombreJug, String generoJug, LocalDate fechaNacJug
    ) 
	{
        Entrenador entrenador = new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        Equipo equipo = new Equipo(nombreEq, categEq, generoEq, entrenador);
        
        Jugador jugador = new Jugador(nombreJug, generoJug, fechaNacJug); // Juvenil
        equipo.agregarJugador(jugador);

        assertEquals(1, equipo.getJugadores().size());
    }

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres', 'Juvenil', 'Masculino', " 
				+ "'Luis','Masculino', '2015-05-10'"
			}
	)
    void testNoAgregarJugadorDeDiferenteCategoria
    (
    		String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreEq, String categEq, String generoEq,
    		String nombreJug, String generoJug, LocalDate fechaNacJug
    ) 
	{
        Entrenador entrenador = new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        Equipo equipo = new Equipo(nombreEq, categEq, generoEq, entrenador);

        Jugador jugador = new Jugador(nombreJug, generoJug, fechaNacJug); // Infantil

        equipo.agregarJugador(jugador);

        assertEquals(0, equipo.getJugadores().size()); // No debe agregarse
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres', 'Infantil', 'Masculino', " 
				+ "'Luis','Masculino', '2015-05-10'"
			}
	)
    void testJugadorYaExiste
    (
    		String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreEq, String categEq, String generoEq,
    		String nombreJug, String generoJug, LocalDate fechaNacJug
    ) 
	{
        Entrenador entrenador = new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        Equipo equipo = new Equipo(nombreEq, categEq, generoEq, entrenador);

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
				+ "'Tigres', 'Juvenil', 'Masculino'" 
			}
	)
    void testAsignarSegundoEntrenador
    (
    		String nombreEnt1, String generoEnt1, LocalDate fechaNacEnt1,
    		String nombreEnt2, String generoEnt2, LocalDate fechaNacEnt2,
    		String nombreEq, String categEq, String modalidadEq
    ) 
	{
        Entrenador entrenador1 = new Entrenador(nombreEnt1, generoEnt1, fechaNacEnt1, true);
        Entrenador entrenador2 = new Entrenador(nombreEnt2, generoEnt2, fechaNacEnt2, false);

        Equipo equipo = new Equipo(nombreEq, categEq, modalidadEq, entrenador1);
        equipo.asignarSegundoEntrenador(entrenador2);

        assertNotNull(equipo.getSegundoEntrenador());
        assertEquals("Ana", equipo.getSegundoEntrenador().getNombre());
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'ManILoveFrogsHunters', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true'"			}
	)
	void testSettersGetters
	(
			String nombre, String categoria, String modalidad,
			String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal
	) 
	{
        Entrenador ent = new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        Equipo eq = new Equipo(nombre, categoria, modalidad, ent);
        
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
				"' ', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true'",
				"'null', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true'",
				"'HUnters', 'null', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true'",
				"'HUnters', 'null', 'Masculino', 'null', 'Masculino', '2006-11-12', 'true'",
				"'Hunters', 'JaviTeniaRazon', 'null', 'null', 'Masculino', '2006-11-12', 'true'",
				"'HUnters', 'null', 'null', 'null', 'Masculino', '2006-11-12', 'true'"
			}
	)
	void testEquipoIncorrecto
	(
			String nombre, String categoria, String modalidad,
			String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal
	) 
	{
		String nombreEf = nombre.trim().toLowerCase().equals("null") ? null : nombre;
		String categoriaEf = categoria.trim().toLowerCase().equals("null") ? null : categoria;
		String modalidadEf = modalidad.trim().toLowerCase().equals("null") ? null : modalidad;
		Entrenador ent = nombreEnt.trim().toLowerCase().equals("null") ? null : new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal);
        assertThrows(IllegalArgumentException.class, () -> new Equipo(nombreEf, categoriaEf, modalidadEf, ent));
		
	}
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true'"
			}
	)
	void testToString
	(
			String nombre, String categoria, String modalidad,
			String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal
	) 
	{
		Equipo eq = new Equipo(nombre, categoria, modalidad, new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal));
		assertEquals
		(
				eq.toString(),
				"Equipo [nombre=EquipoCinco, categoria=Junior, modalidad=Masculino, entrenador=Entrenador Pepe, segundoEntrenador=null, jugadores=[]]"
	    );
		
	}
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'EquipoCinco', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true'"
			}
	)
	void testEquals
	(
			String nombre, String categoria, String modalidad,
			String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal
	) 
	{
		Equipo eq = new Equipo(nombre, categoria, modalidad, new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal));
		Equipo eq2 = eq;
		Equipo eq3 = null;
		assertEquals
		(
				eq.toString(),
				"Equipo [nombre=EquipoCinco, categoria=Junior, modalidad=Masculino, entrenador=Entrenador Pepe, segundoEntrenador=null, jugadores=[]]"
	    );
		
	}
	
}

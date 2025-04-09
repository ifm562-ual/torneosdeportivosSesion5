package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

// Creación de jugadores y cálculo automático de categoría

class EquipoTest {

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres', 'Juvenil', 'Masculino', " 
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
}

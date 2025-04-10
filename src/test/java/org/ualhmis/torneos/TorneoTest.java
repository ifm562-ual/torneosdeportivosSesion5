package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.List;
import java.lang.reflect.Method;
import java.time.LocalDate;

// Registro de partidos y validación de resultados


class TorneoTest {

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Fútbol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres','Masculino'"
			}
	)
    void testRegistrarEquipoCorrectamente 
    (
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo, 
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String generoEquipo
    ) 
	{
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo);
        Entrenador entrenador = new Entrenador(nombreEntrenador, sexoEntrenador, fechaNacimientoEnt, entrenadorPrincipal);
        Equipo equipo = new Equipo(nombreEquipo, categoriaTorneo, modalidadTorneo, entrenador);
        
        torneo.registrarEquipo(equipo);
        torneo.registrarEquipo(equipo);

        assertEquals(1, torneo.getEquipos().size());
    }

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Fútbol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres', 'Cadete', 'Masculino'"
			}
	)
    void testNoRegistrarEquipoDeDiferenteCategoria
    (
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo,
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String categoriaEquipo, String generoEquipo
    ) 
	{
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo);
        
        Entrenador entrenador = new Entrenador(nombreEntrenador, sexoEntrenador, fechaNacimientoEnt, entrenadorPrincipal);
        
        Equipo equipo = new Equipo(nombreEquipo, categoriaEquipo, modalidadTorneo, entrenador);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Fútbol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Leonas', 'Juvenil', 'Femenino'"
			}
	)
    void testNoRegistrarEquipoDeDiferenteModalidad
    (
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo,
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String categoriaEquipo, String modalidadEquipo
    ) 
    {
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo);
        Entrenador entrenador = new Entrenador(nombreEntrenador, sexoEntrenador, fechaNacimientoEnt, entrenadorPrincipal);
        
        Equipo equipo = new Equipo(nombreEquipo, categoriaEquipo, modalidadEquipo, entrenador);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }
	
	
}

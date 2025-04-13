package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.List;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

// Registro de partidos y validación de resultados


class TorneoTest extends TesteoSettersGetters {

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
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Fútbol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Leonas', 'Juvenil', 'Femenino'"
				+ "'Liga Adulta', 'Culturismo'"
			}
	)
	void testSettersGetters(String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo) {
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo);
        
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        
        Equipo eq = new Equipo("a", "a", "a", new Entrenador("a", "a", LocalDate.of(1980, 12, 10), false));
        valoresTest.put(List.class, new ArrayList<>( Arrays.asList(eq, eq, eq) ));
        valoresTest.put(GestorTorneos.class, new GestorTorneos());
        valoresTest.put(String.class, "stringSet");
        
        this.parteComunMetodoTesteo(torneo, valoresTest);
		
	}
	
	
}

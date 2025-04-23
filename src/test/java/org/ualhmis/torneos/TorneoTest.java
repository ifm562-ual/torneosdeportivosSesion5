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
				+ "'Tigres','Masculino', 'nombreSede'"
			}
	)
    void testRegistrarEquipoCorrectamente 
    (
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo, 
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String generoEquipo, String nombreSede
    ) 
	{
		Sede s = new Sede(nombreSede);
		s.agregarInstalacion(new Instalacion("pabellon"));
		s.agregarInstalacion(new Instalacion("pista"));
		s.agregarInstalacion(new Instalacion("campo"));
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo, s);
        Entrenador entrenador = new Entrenador(nombreEntrenador, sexoEntrenador, fechaNacimientoEnt, entrenadorPrincipal);
        Equipo equipo = new Equipo(nombreEquipo, categoriaTorneo, modalidadTorneo, entrenador, new Club("megaClub"));
        
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
				+ "'Tigres', 'Cadete', 'Masculino', 'nombreSede'"
			}
	)
    void testNoRegistrarEquipoDeDiferenteCategoria
    (
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo,
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String categoriaEquipo, String generoEquipo, String nombreSede
    ) 
	{
		Sede s = new Sede(nombreSede);
		s.agregarInstalacion(new Instalacion("pabellon"));
		s.agregarInstalacion(new Instalacion("pista"));
		s.agregarInstalacion(new Instalacion("campo"));
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo, s);
        
        Entrenador entrenador = new Entrenador(nombreEntrenador, sexoEntrenador, fechaNacimientoEnt, entrenadorPrincipal);
        
        Equipo equipo = new Equipo(nombreEquipo, categoriaEquipo, modalidadTorneo, entrenador, new Club("megaClub"));

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Fútbol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Leonas', 'Juvenil', 'Femenino', 'leonasSede'"
			}
	)
    void testNoRegistrarEquipoDeDiferenteModalidad
    (
    		
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo,
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String categoriaEquipo, String modalidadEquipo, String nombreSede
    ) 
    {
		Sede s = new Sede(nombreSede);
		s.agregarInstalacion(new Instalacion("pabellon"));
		s.agregarInstalacion(new Instalacion("pista"));
		s.agregarInstalacion(new Instalacion("campo"));
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo, s);
        Entrenador entrenador = new Entrenador(nombreEntrenador, sexoEntrenador, fechaNacimientoEnt, entrenadorPrincipal);
        
        Equipo equipo = new Equipo(nombreEquipo, categoriaEquipo, modalidadEquipo, entrenador, new Club("megaClub"));

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Fútbol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Leonas', 'Juvenil', 'Femenino'"
				+ "'Liga', 'Culturismo', 'futbolPro'"
			}
	)
	void testSettersGetters(String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo, String nombreSede) {
		Sede s = new Sede(nombreSede);
		s.agregarInstalacion(new Instalacion("pabellon"));
		s.agregarInstalacion(new Instalacion("pista"));
		s.agregarInstalacion(new Instalacion("campo"));
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo, s);
        
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        
        Equipo eq = new Equipo("a", "a", "a", new Entrenador("a", "a", LocalDate.of(1980, 12, 10), false), new Club("megaClub"));
        valoresTest.put(List.class, new ArrayList<>( Arrays.asList(eq, eq, eq) ));
        valoresTest.put(String.class, "stringSet");
        
        this.parteComunMetodoTesteo(torneo, valoresTest);
		
	}
	
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Voleibol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres','Masculino', 'nombreSede'",
				
				"'Liga Juvenil','Baloncesto','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres','Masculino', 'nombreSede'"
			}
	)
    void testSedeNoSoportaInstalacion
    (
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo, 
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String generoEquipo, String nombreSede
    ) 
	{
		Sede s = new Sede(nombreSede);
		s.agregarInstalacion(new Instalacion("campo"));
        assertThrows(IllegalArgumentException.class ,() -> new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo, s));
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Voleibol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres','Masculino', 'nombreSede'",
				
				"'Liga Juvenil','Baloncesto','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres','Masculino', 'nombreSede'",
				
				"'Liga Juvenil','Baloncesto','Juvenil','Masculino','Copa', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres','Masculino', 'nombreSede'"
			}
	)
    void testSedeSoportaInstalacion
    (
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo, 
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String generoEquipo, String nombreSede
    ) 
	{
		Sede s = new Sede(nombreSede);
		s.agregarInstalacion(new Instalacion("campo"));
		s.agregarInstalacion(new Instalacion("pista"));
		s.agregarInstalacion(new Instalacion("pabellon"));
        assertDoesNotThrow(() -> new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo, s));
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Voleibol','Juvenil','Masculino','Ligaza', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres','Masculino', 'nombreSede'",
				
				"'Liga Juvenil','Baloncesto','Juvenil','Masculino','Liga super ligosa', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'Tigres','Masculino', 'nombreSede'"
			}
	)
    void testTipoTorneoNoSoportado
    (
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo, 
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal,
    		String nombreEquipo, String generoEquipo, String nombreSede
    ) 
	{
		Sede s = new Sede(nombreSede);
		s.agregarInstalacion(new Instalacion("campo"));
		s.agregarInstalacion(new Instalacion("pista"));
		s.agregarInstalacion(new Instalacion("pabellon"));
        assertThrows(IllegalArgumentException.class, () -> new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo, s));
    }
	
	
}

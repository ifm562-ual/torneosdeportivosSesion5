package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
//        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");
        Torneo torneo = new Torneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo);
        
        Entrenador entrenador = new Entrenador(nombreEntrenador, sexoEntrenador, fechaNacimientoEnt, entrenadorPrincipal);
        
//        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10), true);
//        Equipo equipo = new Equipo("Tigres", "Juvenil", "Masculino", entrenador);
        Equipo equipo = new Equipo(nombreEquipo, categoriaTorneo, modalidadTorneo, entrenador);

        torneo.registrarEquipo(equipo);

        assertEquals(1, torneo.getEquipos().size());
    }

    @Test
    void testNoRegistrarEquipoDeDiferenteCategoria() {
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10), true);
        Equipo equipo = new Equipo("Tigres", "Cadete", "Masculino", entrenador);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

    @Test
    void testNoRegistrarEquipoDeDiferenteModalidad() {
        Torneo torneo = new Torneo("Liga Juvenil", "Fútbol", "Juvenil", "Masculino", "Liga");

        Entrenador entrenador = new Entrenador("Carlos", "Masculino", LocalDate.of(1980, 3, 10), true);
        Equipo equipo = new Equipo("Leonas", "Juvenil", "Femenino", entrenador);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }
}

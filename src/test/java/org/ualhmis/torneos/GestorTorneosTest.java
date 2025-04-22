package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GestorTorneosTest extends TesteoSettersGetters<GestorTorneos> {

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos', 'Masculino', '1980-03-03', 'true', 'EquipoCinco', 'Juvenil', 'Masculino'"
			}
	)
	void crearEquipoTest
	(
    		String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal,
    		String nombreEq, String categEq, String generoEq
	) 
	{
		GestorTorneos gt = new GestorTorneos();
		gt.crearEquipo(nombreEq, categEq, generoEq, new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal));
		assertEquals(gt.getEquiposGestionados().size(), 1);
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
	void crearPartidoTest
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
		GestorTorneos gt = new GestorTorneos();
		Equipo eq1 = new Equipo(nombreEquipo, categoriaEquipo, modalidadEquipo, new Entrenador(nombreEntrenador, generoEntrenador, fechaNacEnt, esPrincipal), new Club(nombreClub));
		Equipo eq2 = new Equipo(nombreEquipo2, categoriaEquipo2, modalidadEquipo2, new Entrenador(nombreEntrenador2, generoEntrenador2, fechaNacEnt2, esPrincipal2), new Club(nombreClub));
		gt.crearPartido(eq1, eq2, nombreT, deporteT, categoriaT, modalidadT, tipoT, nombreSede);
		assertEquals(gt.getPartidosGestionados().size(), 1);
	}
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Liga Juvenil','Fútbol','Juvenil','Masculino','Liga', "
				+ "'Carlos','Masculino','1980-03-10','true', "
				+ "'nombreSede'"
			}
	)
	void crearTorneo
	(
    		String nombreTorneo, String deporteTorneo, String categoriaTorneo, String modalidadTorneo, String tipoTorneo, 
    		String nombreEntrenador, String sexoEntrenador, LocalDate fechaNacimientoEnt, boolean entrenadorPrincipal, String nombreSede
	) 
	{
		GestorTorneos gt = new GestorTorneos();
		gt.crearTorneo(nombreTorneo, deporteTorneo, categoriaTorneo, modalidadTorneo, tipoTorneo, nombreSede);
		assertEquals(1, gt.getTorneos().size());
	}
	
	@Test
	void testSettersGetters
	(
	) 
	{
        GestorTorneos gt = new GestorTorneos();
        List<Equipo> le = new ArrayList<Equipo>();
        List<Partido> lp = new ArrayList<Partido>();
        List<Torneo> lt = new ArrayList<Torneo>();
        
        HashMap< Map.Entry<Class<?>, Class<?>>, Object> valoresTest = new HashMap<>();
        valoresTest.put( new AbstractMap.SimpleEntry(List.class, Equipo.class), le);
        valoresTest.put( new AbstractMap.SimpleEntry(List.class, Partido.class), le);
        valoresTest.put( new AbstractMap.SimpleEntry(List.class, Torneo.class), le);
        
        this.parteComunMetodoTesteoGT(gt, valoresTest);
		
	}
	
}

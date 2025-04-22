package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestClub extends TesteoSettersGetters<Club> {
	
	@ParameterizedTest
	@CsvSource
	(
			{
			   "'megaClub'"
			}
	)
	void testSettersGetters(String nombreClub) {
        Club c = new Club(nombreClub);
        List<Equipo> l = new ArrayList<>();
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        
        valoresTest.put(Equipo.class, c);
        valoresTest.put(String.class, "stringSet");
        valoresTest.put(List.class, l);
        
        this.parteComunMetodoTesteo(c, valoresTest);
		
	}
	
	@ParameterizedTest
	@CsvSource
	(		
			nullValues = "null",
			value=
			{
				"'null'",
				"''"
			}
	)
	public void testCrearClubIncorrecto(String nombreClub) {
		assertThrows( IllegalArgumentException.class, () -> new Club(nombreClub));
	}	
	
	@ParameterizedTest
	@CsvSource
	(		
			{
				"'ManILoveFrogsHunters', 'Junior', 'Masculino', 'Pepe', 'Masculino', '2006-11-12', 'true', 'megaClub'"			
			}
	)
	public void agregarEquipo(String nombreEquipo, String categoria, String modalidad, String nombreEnt, String generoEnt, LocalDate fechaNacEnt, boolean esPrincipal) {
		assertDoesNotThrow(() -> new Club("megaClub").agregarEquipo(new Equipo(nombreEquipo, categoria, modalidad, new Entrenador(nombreEnt, generoEnt, fechaNacEnt, esPrincipal), new Club(nombreEquipo))));
	}
}

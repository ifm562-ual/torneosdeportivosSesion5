package org.ualhmis.torneos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SedeTest extends TesteoSettersGetters {
	@ParameterizedTest
	@CsvSource
	(
			{
				"'nombreSede'"
			}
	)
	void testSettersGetters(String nombreSede) {
        Sede s = new Sede(nombreSede);
        List l = new ArrayList<Instalacion>();
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        valoresTest.put(List.class, l);
        
        this.parteComunMetodoTesteo(s, valoresTest);
		
	}
}

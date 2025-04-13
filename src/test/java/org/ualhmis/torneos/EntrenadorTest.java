package org.ualhmis.torneos;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EntrenadorTest extends TesteoSettersGetters<Entrenador> {
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Ismael', 'Masculino', 2003-06-02, 'false'",
				"'Jesús', 'Masculino', 2002-03-03, 'true'"
			}
	)
	void testSettersGetters(String nombre, String genero, LocalDate fechaNacimiento, boolean esPrincipal) {
        Entrenador ent = new Entrenador(nombre, genero, fechaNacimiento, esPrincipal);        
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        valoresTest.put(boolean.class, false);
        
        this.parteComunMetodoTesteo(ent, valoresTest);
		
	}
}

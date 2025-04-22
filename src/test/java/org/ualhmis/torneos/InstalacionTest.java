package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InstalacionTest extends TesteoSettersGetters<Instalacion> {
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'pabellon'"
			}
	)
	void testSettersGetters(String tipoInstalacion) {
		Instalacion i = new Instalacion(tipoInstalacion);
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        valoresTest.put(boolean.class, false);
        valoresTest.put(String.class, "pista");
        
        this.parteComunMetodoTesteo(i, valoresTest);
	}
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'sala fitness'"
			}
	)
	void tipoInstalacionIncorrecta(String tipoInstalacion) {
		assertThrows(IllegalArgumentException.class, () -> new Instalacion(tipoInstalacion));
	}
	
}

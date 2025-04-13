package org.ualhmis.torneos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PersonaTest extends TesteoSettersGetters<Persona> {

	@ParameterizedTest
	@CsvSource
	(
			{
				"'','Masculino','1980-03-10'",
				"'null', 'Femenino', '1964-03-23'",
				"'      ', 'Masculino', '2006-03-03'",
				"'Ismael', 'null', '2003-06-02'",
				"'Ismael', 'Masculino', 'null'",
				"'Ismael', '   ', '2003-06-02'"
			}
	)
    void testAgregarPersonaIncorrecta(String nombre, String genero, String fechaNacimiento) 
	{
		LocalDate ld = fechaNacimiento.trim().toLowerCase().equals("null") ? null : LocalDate.parse(fechaNacimiento);
		String nombreEf = nombre.trim().toLowerCase().equals("null") ? null : nombre;
		String generoEf = genero.trim().toLowerCase().equals("null") ? null : genero;
		assertThrows(IllegalArgumentException.class, () -> new Persona(nombreEf, generoEf, ld));   
    }
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Ismael', 'Masculino', 2003-06-02",
				"'Jesús', 'Masculino', 2002-03-03"
			}
	)
	void testSettersGetters(String nombre, String genero, LocalDate fechaNacimiento) {
        Persona p = new Persona(nombre, genero, fechaNacimiento);
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        
        LocalDate ld = LocalDate.of(2004, 2, 3);
        valoresTest.put(LocalDate.class, ld);
        valoresTest.put(String.class, "stringSet");
        
        this.parteComunMetodoTesteo(p, valoresTest);
		
	}
	
	@ParameterizedTest
	@CsvSource
	(
			{
				"'Ismael', 'Masculino', 2003-06-02, '22'",
				"'Ismayoel', 'Masculino', 2003-12-12, '22'",
				"Isabel, 'Femenino', 2003-01-01, '22'"
			}
	)
	void testCalcularEdad(String nombre, String genero, LocalDate fechaNacimiento, int edadEsperada) {
        Persona p = new Persona(nombre, genero, fechaNacimiento);
        assertEquals(edadEsperada, p.calcularEdad());
	}
	
}

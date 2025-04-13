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
import java.util.HashMap;

// Restricciones en los equipos (jugadores de la misma categoría y modalidad)

class JugadorTest extends TesteoSettersGetters<Jugador> {

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos', 'Masculino', '2015-05-10', 'Infantil'",
				"'Luis', 'Masculino', '2010-03-15', 'Juvenil'",
				"'Ana', 'Femenino', '2005-08-22', 'Junior'",
				"'Pedro', 'Masculino', '2011-01-30', 'Cadete'",
				"'Marta', 'Femenino', '1998-06-05', 'Absoluta'"
			}
	)
    void testCategoriaPorEdad(String nombre, String genero, LocalDate fechaNacimiento, String categoriaEsperada) {
        Jugador jugador = new Jugador(nombre, genero, fechaNacimiento);
        assertEquals(categoriaEsperada, jugador.getCategoria());

    }

	@ParameterizedTest
	@CsvSource
	(
			{
				"'', 'Masculino', 2010-01-01",
				"'Juan', '', 2010-01-01",
				"'Juan', 'Masculino', ''"
			}
	)
    void testCreacionJugadorInvalido(String nombre, String genero, String fechaNacimiento) {
		LocalDate fechaNac = fechaNacimiento.equals("") ? null : LocalDate.parse(fechaNacimiento);
		assertThrows(IllegalArgumentException.class, () -> new Jugador(nombre, genero, fechaNac));
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
        Jugador jug = new Jugador(nombre, genero, fechaNacimiento);        
        
        HashMap<Class<?>, Object> valoresTest = new HashMap<>();
        
        Equipo eq = new Equipo("a", "a", "a", new Entrenador("a", "a", LocalDate.of(1980, 12, 10), false));
        valoresTest.put(Equipo.class, eq);
        valoresTest.put(String.class, "stringSet");
        
        this.parteComunMetodoTesteo(jug, valoresTest);
		
	}
}
